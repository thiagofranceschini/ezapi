package br.com.caelum.fj27.repositories;

import org.springframework.data.repository.Repository;

import br.com.caelum.fj27.model.Course;

public interface CourseRepository extends Repository<Course, Long>{

	Course findByName(String name);
}
