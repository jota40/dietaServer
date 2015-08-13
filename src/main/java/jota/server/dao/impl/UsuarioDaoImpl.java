package jota.server.dao.impl;

import java.util.List;

import jota.server.dao.UsuarioDao;
import jota.server.entity.Usuario;
import jota.server.exceptions.DaoException;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDaoImpl extends BaseDaoImpl<Usuario> implements UsuarioDao {

    protected UsuarioDaoImpl() {
        super( Usuario.class );
    }

	@Override
	public List<Usuario> findByLogin( String login ) throws DaoException {
		List<Usuario> dev = null;
		try {
			String query = "FROM " + Usuario.class.getName() +
					" WHERE login = :login";
			Query result = getCurrentSession().createQuery( query );
			result.setParameter( "login", login );
			dev = result.list();
		} catch (Exception e) {
			throw new DaoException( e );
		}
		return dev;
	}
}