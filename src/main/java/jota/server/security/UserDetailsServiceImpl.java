package jota.server.security;

import java.util.HashSet;
import java.util.Set;

import jota.server.entity.Usuario;
import jota.server.enumerations.RolEnum;
import jota.server.exceptions.ServiceException;
import jota.server.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("myUserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add( new GrantedAuthorityImpl( RolEnum.ADMINISTRADOR ) );
		
		Usuario usuario = null ;
		try {
			usuario = usuarioService.getByLogin( username );
		} catch (ServiceException e) {
			throw new UsernameNotFoundException("Error en el servicio getByLogin( " + username + " )", e);
		}
		if ( usuario == null ) {
			throw new UsernameNotFoundException("Wrong username or password");
		}
		return new UserDetailsImpl( usuario, authorities );
	}
}