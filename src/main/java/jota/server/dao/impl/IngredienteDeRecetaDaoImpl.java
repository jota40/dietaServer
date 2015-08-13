package jota.server.dao.impl;

import jota.server.dao.IngredienteDeRecetaDao;
import jota.server.entity.IngredienteDeReceta;

import org.springframework.stereotype.Repository;

@Repository
public class IngredienteDeRecetaDaoImpl extends BaseDaoImpl<IngredienteDeReceta> implements IngredienteDeRecetaDao {
	protected IngredienteDeRecetaDaoImpl() {
		super( IngredienteDeReceta.class );
	}
}