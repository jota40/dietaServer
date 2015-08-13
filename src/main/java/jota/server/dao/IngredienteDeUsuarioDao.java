package jota.server.dao;

import jota.server.entity.IngredienteDeUsuario;
import jota.server.exceptions.DaoException;

public interface IngredienteDeUsuarioDao extends BaseDao<IngredienteDeUsuario> {
	IngredienteDeUsuario getByPk( Long idIngrediente, Long idUsuario ) throws DaoException;
}
