package jota.server.utils;

import jota.server.enumerations.RolEnum;
import jota.server.security.UserDetailsImpl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

	private static UserDetailsImpl getUseDetails() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context == null ? null : context.getAuthentication();
		Object principal = (auth == null ? null : auth.getPrincipal());
		if ( principal != null && principal instanceof UserDetailsImpl ) {
			return (UserDetailsImpl) principal;
		}
		return null;
	}

	public static Long getIdUsuario() {
		return getUseDetails() == null || getUseDetails().getUsuario() == null ? null : getUseDetails().getUsuario().getId();
	}

	public static String getNombreUsuario() {
		return getUseDetails() == null || getUseDetails().getUsuario() == null ? null : getUseDetails().getUsuario().getNombre();
	}

	public static boolean hasRol( RolEnum rol ) {
		return getUseDetails() == null || getUseDetails().getUsuario() == null ? null : getUseDetails().hasRol( rol );
	}
}