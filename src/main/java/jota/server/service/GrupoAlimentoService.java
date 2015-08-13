package jota.server.service;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.GrupoAlimentoDto;
import jota.server.exceptions.ServiceException;

public interface GrupoAlimentoService {
	public GrupoAlimentoDto getById( Long id ) throws ServiceException;
	public Long insertar( GrupoAlimentoDto grupoAlimentoDto ) throws ServiceException;
	public void modificar( Long id, GrupoAlimentoDto grupoAlimentoDto ) throws ServiceException;
	public void borrarById( Long id ) throws ServiceException;
	public List<GrupoAlimentoDto> findByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException;
}