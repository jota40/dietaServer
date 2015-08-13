package jota.server.dao;

import java.util.List;

import jota.server.entity.Usuario;
import jota.server.exceptions.DaoException;

public interface UsuarioDao extends BaseDao<Usuario> {
	List<Usuario> findByLogin(String login) throws DaoException;
}
