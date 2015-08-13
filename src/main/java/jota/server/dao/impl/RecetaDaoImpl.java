package jota.server.dao.impl;

import jota.server.dao.RecetaDao;
import jota.server.entity.Receta;

import org.springframework.stereotype.Repository;

@Repository
public class RecetaDaoImpl extends BaseDaoImpl<Receta> implements RecetaDao {
	protected RecetaDaoImpl() {
		super( Receta.class );
	}
}