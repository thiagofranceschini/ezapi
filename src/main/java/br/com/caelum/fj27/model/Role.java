package br.com.caelum.fj27.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
@Entity
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	@Id
	public String authority;

	@Override
	public String getAuthority() {

		return this.authority;
	}

}
