package jota.server.dto;

import java.io.Serializable;

public class BaseDto implements Serializable {

	private static final long serialVersionUID = 2833328423367951112L;

	protected Long id;

	public BaseDto() {}

	public BaseDto( Long id ) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals( final Object obj ) {
        if ( this == obj ) {
        	return true;
        }

        if ( obj == null || 
        	 !( obj instanceof BaseDto ) )
        {
        	return false;
        }
        
        BaseDto objBase = (BaseDto) obj;
        if ( id != null && objBase.id != null && id.equals( objBase.id ) ) {
        	return true;
        }
        return false;
	}
}