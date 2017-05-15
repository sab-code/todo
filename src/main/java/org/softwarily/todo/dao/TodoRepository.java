/**
 * 
 */
package org.softwarily.todo.dao;

import java.util.Date;
import java.util.List;

import org.softwarily.todo.domain.Todo;
import org.springframework.data.repository.CrudRepository;

/**
 * Data access object for the Todo items.
 * 
 * @author Steve Buck (steve@softwarily.org)
 */
public interface TodoRepository extends CrudRepository<Todo, Long> {
	List<Todo> findAll();
	List<Todo> findByTitle(String title);
	List<Todo> findByDue(Date due);
}
