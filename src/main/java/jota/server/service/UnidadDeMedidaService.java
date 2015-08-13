package jota.server.service;

import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.dto.UnidadDeMedidaDto;
import jota.server.exceptions.ServiceException;

public interface UnidadDeMedidaService {
	public UnidadDeMedidaDto getById( Long id ) throws ServiceException;
	public Long insertar( UnidadDeMedidaDto unidadDeMedidaDto ) throws ServiceException;
	public void modificar( Long id, UnidadDeMedidaDto unidadDeMedidaDto ) throws ServiceException;
	public void borrarById( Long id ) throws ServiceException;
	public List<UnidadDeMedidaDto> findByFiltro( int start, int size, FiltroServer filtro ) throws ServiceException;
}