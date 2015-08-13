package jota.server.service;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.IngredienteDeUsuarioDto;
import jota.server.exceptions.ServiceException;

public interface IngredienteService {
	public IngredienteDeUsuarioDto getById( Long id ) throws ServiceException;
	public Long insertar( IngredienteDeUsuarioDto ingredienteDeUsuarioDto, Long idUnidadDeMedida, Long idGrupoAlimento ) throws ServiceException;
	public void modificar( Long idIngrediente, IngredienteDeUsuarioDto ingredienteDeUsuarioDto, Long idUnidadDeMedida, Long idGrupoAlimento ) throws ServiceException;
	public void borrarById( Long id ) throws ServiceException;
	public List<IngredienteDeUsuarioDto> findByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException;
}