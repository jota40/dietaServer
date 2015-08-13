package jota.server.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FiltroServer implements Serializable {

	private static final long serialVersionUID = 434906658212536332L;

	private List<FiltroWhere> where;

	public FiltroServer() {
		this.where = new ArrayList<FiltroWhere>();
	}

	public FiltroServer(List<FiltroWhere> where) {
		this.where = where;
	}

	public List<FiltroWhere> getWhere() {
		return where;
	}

	public void setWhere(List<FiltroWhere> where) {
		this.where = where;
	}

	public void add(FiltroWhere filtroWhere) {
		where.add( filtroWhere );
	}

	public void addAll(List<FiltroWhere> where) {
		where.addAll( where );
	}
}