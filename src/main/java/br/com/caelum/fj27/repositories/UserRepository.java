package br.com.caelum.fj27.repositories;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.caelum.fj27.model.User;

public interface UserRepository extends Repository<User, Long> {

	Optional<User>findByEmail(String email);
	
	Optional<User>findById(Long id);
}
