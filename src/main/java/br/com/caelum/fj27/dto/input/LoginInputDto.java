package br.com.caelum.fj27.dto.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginInputDto {

	private String email;
	private String password;
	
	public UsernamePasswordAuthenticationToken build(){
		return new UsernamePasswordAuthenticationToken(this.email, this.password);
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
