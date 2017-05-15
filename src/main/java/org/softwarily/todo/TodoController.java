/**
 * 
 */
package org.softwarily.todo;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.softwarily.todo.dao.TodoRepository;
import org.softwarily.todo.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public List<Todo> listTodos() {
		return repository.findAll();
	}
	
	@RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
	public Todo getOneTodo(@PathVariable("id") final Long id) {
		
		if (id == null) {
			throw new TodoNotFoundException();
		}
		
		final Todo sought = repository.findOne(id);
		
		if (sought == null) {
			throw new TodoNotFoundException();
		}
		
		return sought;
	}
	
	@RequestMapping(value = "/todo/{id}", method = RequestMethod.PATCH)
	public void updateOneTodo(@PathVariable("id") final long id, @RequestBody final Todo todo) {
		final Todo old = repository.findOne(id);
		
		// Object could potentially only have the deltas.
		if (old != null && todo != null) {
			final String title = todo.getTitle();
			if (title != null) {
				old.setTitle(title);
			}
			
			final String description = todo.getDescription();
			if (description != null) {
				old.setDescription(description);
			}
			
			final String due = todo.getDue();
			if (due != null) {
				todo.setDue(due);
			}
			
			repository.save(old);
			
			
		} else {
			throw new TodoNotFoundException();
		}
	}
	
	@RequestMapping(value = "/todo/new", method = RequestMethod.POST)
	public Todo newTodo(@RequestBody final Todo todo, final HttpServletResponse response) {
		if (todo != null) {
			try {
				final Todo newTodo = repository.save(todo);
				response.setStatus(HttpServletResponse.SC_CREATED);
				return newTodo;
			} catch (final Exception e) {
				throw new TodoGenericException();
			}
			
		} else {
			throw new TodoGenericException();
		}
	}
}
