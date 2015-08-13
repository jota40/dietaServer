package jota.server.security;

import java.util.HashSet;
import java.util.Set;

import jota.server.entity.Usuario;
import jota.server.enumerations.RolEnum;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{
	private static final long serialVersionUID = -6509897037222767090L;
	
	private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	private Usuario usuario;
	
	public UserDetailsImpl( Usuario usuario, Set<GrantedAuthority> authorities){
		this.usuario = usuario;
		this.authorities = authorities;
	}

	public Set<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public String getPassword() {
		return usuario.getPass();
	}

	public String getUsername() {
		return usuario.getLogin();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public boolean hasRol(RolEnum rol) {
		return authorities.contains( rol.name() );
	}
}