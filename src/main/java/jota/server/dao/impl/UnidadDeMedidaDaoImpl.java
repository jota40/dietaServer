package jota.server.dao.impl;

import jota.server.dao.UnidadDeMedidaDao;
import jota.server.entity.UnidadDeMedida;

import org.springframework.stereotype.Repository;

@Repository
public class UnidadDeMedidaDaoImpl extends BaseDaoImpl<UnidadDeMedida> implements UnidadDeMedidaDao {
	protected UnidadDeMedidaDaoImpl() {
		super( UnidadDeMedida.class );
	}
}