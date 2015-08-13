package jota.server.dto;

import java.io.Serializable;

public class FiltroWhere implements Serializable {

	private static final long serialVersionUID = 1280582354408531564L;

	private String sql;
	private String parameter;
	private Serializable valor;

	public FiltroWhere() {}

	public FiltroWhere( String sql, String parameter, Serializable valor ) {
		this.sql = sql;
		this.parameter = parameter;
		this.valor = valor;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Serializable getValor() {
		return valor;
	}

	public void setValor(Serializable valor) {
		this.valor = valor;
	}
}