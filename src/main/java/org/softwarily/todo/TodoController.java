/**
 * 
 */
package org.softwarily.todo;

import java.util.List;

import org.softwarily.todo.dao.TodoRepository;
import org.softwarily.todo.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller to handle todo list management.
 * 
 * @author Steve Buck (steve@softwarily.org)
 */
@RestController
public class TodoController {
	@Autowired
	private TodoRepository repository;
	
	@RequestMapping("/todo")
	public List<Todo> listTodos() {
		return repository.findAll();
	}
	
	@RequestMapping("/todo/{id}")
	public Todo getOneTodo(@PathVariable("id") final long id) {
		return repository.findOne(id);
	}
}
