package jota.server.dao.impl;

import jota.server.dao.GrupoAlimentoDao;
import jota.server.entity.GrupoAlimento;

import org.springframework.stereotype.Repository;

@Repository
public class GrupoAlimentoDaoImpl extends BaseDaoImpl<GrupoAlimento> implements GrupoAlimentoDao {
	protected GrupoAlimentoDaoImpl() {
		super( GrupoAlimento.class );
	}
}