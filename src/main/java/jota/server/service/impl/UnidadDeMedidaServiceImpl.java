package jota.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import jota.server.dao.UnidadDeMedidaDao;
import jota.server.dto.FiltroServer;
import jota.server.dto.UnidadDeMedidaDto;
import jota.server.entity.UnidadDeMedida;
import jota.server.exceptions.DaoException;
import jota.server.exceptions.ServiceException;
import jota.server.service.UnidadDeMedidaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service("unidadDeMedidaService")
@Transactional(readOnly = true)
public class UnidadDeMedidaServiceImpl implements UnidadDeMedidaService {

	@Autowired
	UnidadDeMedidaDao unidadDeMedidaDao;

	@Override
	public UnidadDeMedidaDto getById( Long id ) throws ServiceException {
		Assert.notNull( id, "El id no puede ser null" );
		UnidadDeMedidaDto dev = null;
		try {
			dev = unidadDeMedidaDao.getById( id ).instanceDto();
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
		return dev;
	}

	@Transactional( rollbackFor = ServiceException.class )
	@Override
	public Long insertar( UnidadDeMedidaDto unidadDeMedidaDto ) throws ServiceException {
		Assert.notNull( unidadDeMedidaDto, "La unidadDeMedidaDto no puede ser null" );
		Long dev = null;
		try {
			UnidadDeMedida unidadDeMedida = new UnidadDeMedida();
			unidadDeMedida.copyFrom( unidadDeMedidaDto );
			unidadDeMedidaDao.save( unidadDeMedida );
			dev= unidadDeMedida.getId();
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
		return dev;
	}

	@Transactional( rollbackFor = ServiceException.class )
	@Override
	public void modificar( Long id, UnidadDeMedidaDto unidadDeMedidaDto ) throws ServiceException {
		Assert.notNull( id, "La id no puede ser null" );
		Assert.notNull( unidadDeMedidaDto, "La unidadDeMedidaDto no puede ser null" );
		try {
			UnidadDeMedida unidadDeMedida = unidadDeMedidaDao.getById( id );
			unidadDeMedida.copyFrom( unidadDeMedidaDto );
			unidadDeMedidaDao.merge( unidadDeMedida );
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
	}

	@Transactional( rollbackFor = ServiceException.class )
	@Override
	public void borrarById( Long id ) throws ServiceException {
		Assert.notNull( id, "El id no puede ser null" );
		try {
			UnidadDeMedida unidadDeMedida = unidadDeMedidaDao.getById( id );
			unidadDeMedidaDao.delete( unidadDeMedida  );
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
	}

	@Override
	public List<UnidadDeMedidaDto> findByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException {
		Assert.notNull( filtro, "El filtro no puede ser null" );
		List<UnidadDeMedidaDto> dev = new ArrayList<UnidadDeMedidaDto>();
		try {
			List<UnidadDeMedida> aux = unidadDeMedidaDao.findByFiltro( start, size, filtro );
			if ( aux != null ) {
				for ( UnidadDeMedida unidadDeMedida : aux ) {
					dev.add( unidadDeMedida.instanceDto() );
				}
			}
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
		return dev;
	}
}