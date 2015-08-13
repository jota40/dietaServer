package jota.server.service;

import jota.server.entity.Usuario;
import jota.server.exceptions.ServiceException;

public interface UsuarioService {
	Usuario getByLogin( String username ) throws ServiceException;
}

