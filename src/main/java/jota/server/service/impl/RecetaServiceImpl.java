package jota.server.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jota.server.dao.IngredienteDeUsuarioDao;
import jota.server.dao.RecetaDao;
import jota.server.dto.FiltroServer;
import jota.server.dto.IngredienteDeRecetaDto;
import jota.server.dto.RecetaDto;
import jota.server.entity.IngredienteDeReceta;
import jota.server.entity.IngredienteDeUsuario;
import jota.server.entity.Receta;
import jota.server.exceptions.DaoException;
import jota.server.exceptions.ServiceException;
import jota.server.service.RecetaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service("recetaService")
@Transactional(readOnly = true)
public class RecetaServiceImpl implements RecetaService {

	@Autowired
	RecetaDao recetaDao;

	@Autowired
	IngredienteDeUsuarioDao ingredienteDeUsuarioDao;

	@Override
	public RecetaDto getById( Long id ) throws ServiceException {
		Assert.notNull( id, "El id no puede ser null" );
		
		RecetaDto dev = null;
		try {
			dev = recetaDao.getById( id ).instanceDto();
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
		return dev;
	}

	@Transactional( rollbackFor = ServiceException.class )
	@Override
	public Long insertar( RecetaDto recetaDto, List<IngredienteDeRecetaDto> ingredientesDeRecetaDto ) throws ServiceException {
		Assert.notNull( recetaDto, "El receta no puede ser null" );
		Long dev = null;
		try {
			Receta receta = new Receta( recetaDto );
			receta.setIngredientesDeReceta( instanceFrom( ingredientesDeRecetaDto, receta ) );
			recetaDao.save( receta );
			dev = receta.getId();
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
		return dev;
	}

	@Transactional( rollbackFor = ServiceException.class )
	@Override
	public void modificar( Long idReceta, RecetaDto recetaDto, List<IngredienteDeRecetaDto> ingredientesDeRecetaDto ) throws ServiceException {
		Assert.notNull( idReceta, "El idReceta no puede ser null" );
		Assert.notNull( recetaDto, "El recetaDto no puede ser null" );
		try {
			Receta receta = recetaDao.getById( idReceta );
			receta.copyFrom( recetaDto );
			receta.getIngredientesDeReceta().clear();
			receta.getIngredientesDeReceta().addAll( instanceFrom( ingredientesDeRecetaDto, receta ) );
			recetaDao.merge( receta );
    	} catch ( Exception ex ) {
			throw new ServiceException( ex );
		}
	}

	private Set<IngredienteDeReceta> instanceFrom( List<IngredienteDeRecetaDto> dto, Receta receta ) throws DaoException {
		Set<IngredienteDeReceta> dev = new HashSet<IngredienteDeReceta>();
		if ( dto != null ) {
			for ( IngredienteDeRecetaDto ingredienteDeRecetaDto : dto ) {
				IngredienteDeUsuario ingredienteDeUsuario = ingredienteDeUsuarioDao.getByPk( ingredienteDeRecetaDto.getId(), ingredienteDeRecetaDto.getIdUsuario() );
				dev.add( new IngredienteDeReceta( ingredienteDeUsuario, receta, ingredienteDeRecetaDto ) );
			}
		}
		return dev; 
	}

	@Transactional( rollbackFor = ServiceException.class  )
	@Override
	public void borrarById( Long id ) throws ServiceException {
		Assert.notNull( id, "El id no puede ser null" );
		try {
			Receta receta = recetaDao.getById( id );
			recetaDao.delete( receta  );
    	} catch ( DaoException daoException ) {
			throw daoException;
    	} catch ( Exception exception ) {
			throw new ServiceException( exception );
		}
	}

	@Override
	public List<RecetaDto> findByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException {
		Assert.notNull( filtro, "El filtro no puede ser null" );
		List<RecetaDto> dev = new ArrayList<RecetaDto>();
		try {
			List<Receta> aux = recetaDao.findByFiltro( start, size, filtro );
			if ( aux != null ) {
				for ( Receta receta : aux ) {
					dev.add( receta.instanceDto() );
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