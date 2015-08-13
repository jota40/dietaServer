package jota.server.enumerations;
public enum EstadoEnum {
    LIBRE( 1, "Libre" ),
    PENDIENTE( 2, "Pendiente" ),
    OCUPADO( 3, "Ocupado" );

    private Integer id;
    private String descripcion;

    private EstadoEnum() {
        this.id = null;
        this.descripcion = null;
    }

    private EstadoEnum( Integer id, String descripcion ) {
        this.id = id;
        this.descripcion = descripcion;
    }
 
    public Integer getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static EstadoEnum getEnum( Object id ) {
    	try {
    		return getEnum( Integer.parseInt( id.toString() ) );
    	} catch (Exception e) {
			return null;
		}
    }
 
    private static EstadoEnum getEnum( int id ) {
    	switch ( id ) {
			case 1: return LIBRE;
			case 2: return PENDIENTE;
			case 3: return OCUPADO;
    	}
    	return null;
    }
}