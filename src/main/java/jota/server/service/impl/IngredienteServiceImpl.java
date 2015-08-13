package jota.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import jota.server.dao.GrupoAlimentoDao;
import jota.server.dao.IngredienteDao;
import jota.server.dao.IngredienteDeUsuarioDao;
import jota.server.dao.UnidadDeMedidaDao;
import jota.server.dto.FiltroServer;
import jota.server.dto.IngredienteDeRecetaDto;
import jota.server.dto.IngredienteDeUsuarioDto;
import jota.server.entity.Ingrediente;
import jota.server.entity.IngredienteDeUsuario;
import jota.server.entity.UnidadDeMedida;
import jota.server.exceptions.DaoException;
import jota.server.exceptions.ServiceException;
import jota.server.service.IngredienteService;
import jota.server.utils.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service("ingredienteService")
@Transactional(readOnly = true)
public class IngredienteServiceImpl implements IngredienteService {

	@Autowired
	IngredienteDao ingredienteDao;

	@Autowired
	IngredienteDeUsuarioDao ingredienteUsuarioDao;
	
	@Autowired
	UnidadDeMedidaDao unidadDeMedidaDao;
	
	@Autowired
	GrupoAlimentoDao grupoAlimentoDao;

	@Override
	public IngredienteDeRecetaDto getById( Long id ) throws ServiceException {
		Assert.notNull( id, "El id no puede ser null" );
		IngredienteDeRecetaDto dev = null;
		try {
			dev = ingredienteUsuarioDao.getById( id ).instanceDto();

    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
		return dev;
	}

	@Transactional( rollbackFor = ServiceException.class )
	@Override
	public Long insertar( IngredienteDeRecetaDto ingredienteDeRecetaDto, Long idUnidadDeMedida, Long idGrupoAlimento  ) throws ServiceException {
		Assert.notNull( ingredienteDeRecetaDto, "El ingredienteDto no puede ser null" );
		Assert.notNull( idGrupoAlimento, "El idGrupoAlimento no puede ser null" );
		Long dev = null;
		try {
			Ingrediente ingrediente = new Ingrediente();
			ingrediente.copyFrom( ingredienteDeRecetaDto );
			UnidadDeMedida unidadDeMedida = idUnidadDeMedida == null ? null : unidadDeMedidaDao.getById( idUnidadDeMedida );
			ingrediente.setUnidadDeMedida( unidadDeMedida );
			ingrediente.setGrupoAlimento( grupoAlimentoDao.getById( idGrupoAlimento ) );
			ingredienteUsuarioDao.save( ingrediente );
			dev= ingrediente.getId();
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
		return dev;
	}

	@Transactional( rollbackFor = ServiceException.class )
	@Override
	public void modificar( Long idIngrediente, IngredienteDeUsuarioDto ingredienteDeUsuarioDto, Long idUnidadDeMedida, Long idGrupoAlimento ) throws ServiceException {
		Assert.notNull( idIngrediente, "El idIngrediente no puede ser null" );
		Assert.notNull( ingredienteDeUsuarioDto, "El ingredienteDeUsuarioDto no puede ser null" );
		Assert.notNull( idGrupoAlimento, "El idGrupoAlimento no puede ser null" );
		Assert.notNull( idUnidadDeMedida, "El idUnidadDeMedida no puede ser null" );
		try {
			Ingrediente ingrediente = ingredienteDao.getById( idIngrediente );
			ingrediente.copyFrom( ingredienteDeUsuarioDto );
			ingrediente.setUnidadDeMedida( unidadDeMedidaDao.getById( idUnidadDeMedida ) );
			ingrediente.setGrupoAlimento( grupoAlimentoDao.getById( idGrupoAlimento ) );
			ingredienteDao.merge( ingrediente );

			IngredienteDeUsuario ingredienteDeUsuario = ingredienteUsuarioDao.getByPk( idIngrediente, SecurityUtils.getIdUsuario() );
			ingredienteDeUsuario.copyFrom( ingredienteDeUsuarioDto );
			ingredienteUsuarioDao.merge( ingredienteDeUsuario );
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
			IngredienteDeUsuario ingrediente = ingredienteUsuarioDao.getByPk( id );
			ingredienteUsuarioDao.delete( ingrediente  );
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
	}

	@Override
	public List<IngredienteDeUsuarioDto> findByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException {
		Assert.notNull( filtro, "El filtro no puede ser null" );
		List<IngredienteDeUsuarioDto> dev = new ArrayList<IngredienteDeUsuarioDto>();
		try {
			List<IngredienteDeUsuario> aux = ingredienteUsuarioDao.findByFiltro( start, size, filtro );
			if ( aux != null ) {
				for ( IngredienteDeUsuario ingredienteDeUsuario : aux ) {
					dev.add( ingredienteDeUsuario.instanceDto() );
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