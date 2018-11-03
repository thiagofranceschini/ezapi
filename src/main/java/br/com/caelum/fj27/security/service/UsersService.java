package br.com.caelum.fj27.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.caelum.fj27.model.User;
import br.com.caelum.fj27.repositories.UserRepository;

@Service
public class UsersService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<User> possibleUser = userRepository.findByEmail(userName);

		return possibleUser.orElseThrow(
				() -> new UsernameNotFoundException("Não foi possível encontrar o usuário com email: " + userName));
	}
	
	public UserDetails loadUserById(Long userId){
		Optional<User> possibleUser = userRepository.findById(userId);
		return possibleUser.orElseThrow(
				()-> new UsernameNotFoundException("Não foi possível encontrar o usuário com o Id: " + userId));
	}

}
