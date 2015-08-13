package jota.server.dao.impl;

import jota.server.dao.IngredienteDeUsuarioDao;
import jota.server.entity.IngredienteDeUsuario;
import jota.server.exceptions.DaoException;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class IngredienteDeUsuarioDaoImpl extends BaseDaoImpl<IngredienteDeUsuario> implements IngredienteDeUsuarioDao {
	protected IngredienteDeUsuarioDaoImpl() {
		super( IngredienteDeUsuario.class );
	}

	@Override
	public IngredienteDeUsuario getByPk( Long idIngrediente, Long idUsuario ) throws DaoException {
		Assert.notNull( idIngrediente, "idIngrediente no puede ser null" );
		Assert.notNull( idUsuario, "idUsuario no puede ser null" );
		IngredienteDeUsuario dev = null;
		try {
			String sql = "FROM " + IngredienteDeUsuario.class.getName() +
					" WHERE pk.ingrediente.id = :idIngrediente and pk.usuario.id = :idUsuario";

			Query result = getCurrentSession().createQuery( sql );
			result.setLong( "idIngrediente", idIngrediente );
			result.setLong( "idUsuario", idUsuario );

			result.setFirstResult( 0 );
			dev = (IngredienteDeUsuario) result.uniqueResult();
		} catch (Exception e) {
			throw new DaoException( e );
		}
		return dev;
	}
}