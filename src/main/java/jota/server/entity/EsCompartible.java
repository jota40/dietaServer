package jota.server.entity;


public interface EsCompartible extends EsAuditable {
	Compartible getCompartible();
	void setCompartible( Compartible compartible );
}