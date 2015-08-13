package jota.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import jota.server.dao.GrupoAlimentoDao;
import jota.server.dto.FiltroServer;
import jota.server.dto.GrupoAlimentoDto;
import jota.server.entity.GrupoAlimento;
import jota.server.exceptions.DaoException;
import jota.server.exceptions.ServiceException;
import jota.server.service.GrupoAlimentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service("grupoAlimentoService")
@Transactional(readOnly = true)
public class GrupoAlimentoServiceImpl implements GrupoAlimentoService {

	@Autowired
	GrupoAlimentoDao grupoAlimentoDao;

	@Override
	public GrupoAlimentoDto getById( Long id ) throws ServiceException {
		Assert.notNull( id, "El id no puede ser null" );
		GrupoAlimentoDto dev = null;
		try {
			dev = grupoAlimentoDao.getById( id ).instanceDto();
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
		return dev;
	}

	@Transactional( rollbackFor = ServiceException.class )
	@Override
	public Long insertar( GrupoAlimentoDto grupoAlimentoDto ) throws ServiceException {
		Assert.notNull( grupoAlimentoDto, "La grupoAlimentoDto no puede ser null" );
		Long dev = null;
		try {
			GrupoAlimento grupoAlimento = new GrupoAlimento();
			grupoAlimento.copyFrom( grupoAlimentoDto );
			grupoAlimentoDao.save( grupoAlimento );
			dev= grupoAlimento.getId();
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
		return dev;
	}

	@Transactional( rollbackFor = ServiceException.class )
	@Override
	public void modificar( Long id, GrupoAlimentoDto grupoAlimentoDto ) throws ServiceException {
		Assert.notNull( id, "La id no puede ser null" );
		Assert.notNull( grupoAlimentoDto, "La grupoAlimentoDto no puede ser null" );
		try {
			GrupoAlimento grupoAlimento = grupoAlimentoDao.getById( id );
			grupoAlimento.copyFrom( grupoAlimentoDto );
			grupoAlimentoDao.merge( grupoAlimento );
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
			GrupoAlimento grupoAlimento = grupoAlimentoDao.getById( id );
			grupoAlimentoDao.delete( grupoAlimento  );
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
	}

	@Override
	public List<GrupoAlimentoDto> findByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException {
		Assert.notNull( filtro, "El filtro no puede ser null" );
		List<GrupoAlimentoDto> dev = new ArrayList<GrupoAlimentoDto>();
		try {
			List<GrupoAlimento> aux = grupoAlimentoDao.findByFiltro( start, size, filtro );
			if ( aux != null ) {
				for ( GrupoAlimento grupoAlimento : aux ) {
					dev.add( grupoAlimento.instanceDto() );
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