package jota.server.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Base {

	protected Long id;

	@Id
	@GeneratedValue
	@Column(name="id")
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
        	 !( obj instanceof Base ) )
        {
        	return false;
        }
        
        Base objBase = (Base) obj;
        if ( id != null && objBase.id != null && id.equals( objBase.id ) ) {
        	return true;
        }
        return false;
	}
}