package jota.server.dao.impl;

import jota.server.dao.IngredienteDao;
import jota.server.entity.Ingrediente;

import org.springframework.stereotype.Repository;

@Repository
public class IngredienteDaoImpl extends BaseDaoImpl<Ingrediente> implements IngredienteDao {
	protected IngredienteDaoImpl() {
		super( Ingrediente.class );
	}
}