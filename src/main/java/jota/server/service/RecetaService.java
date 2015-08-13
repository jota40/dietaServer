package jota.server.service;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.IngredienteDeRecetaDto;
import jota.server.dto.RecetaDto;
import jota.server.exceptions.ServiceException;

public interface RecetaService {
	public RecetaDto getById( Long id ) throws ServiceException;
	public Long insertar( RecetaDto recetaDto, List<IngredienteDeRecetaDto> ingredientesDeRecetaDto ) throws ServiceException;
	public void modificar( Long id, RecetaDto recetaDto, List<IngredienteDeRecetaDto> ingredientesDeRecetaDto ) throws ServiceException;
	public void borrarById( Long id ) throws ServiceException;
	public List<RecetaDto> findByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException;
}