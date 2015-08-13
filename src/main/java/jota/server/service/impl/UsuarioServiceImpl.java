package jota.server.service.impl;

import java.util.List;

import jota.server.dao.UsuarioDao;
import jota.server.entity.Usuario;
import jota.server.exceptions.ServiceException;
import jota.server.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("usuarioService")
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public Usuario getByLogin( String login ) throws ServiceException {
		Usuario dev = null;
		List<Usuario> coleccion = usuarioDao.findByLogin( login );
		if ( coleccion != null ) {
			switch ( coleccion.size() ) {
				case 1: dev = coleccion.iterator().next();
				break;
				case 0: throw new ServiceException("No existe ningún usuario con login = " + login );
				default: throw new ServiceException("Se ha encontrado más de 1 usuari con login = " + login );
			}
		}
		return dev;
	}
}