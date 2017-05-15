/**
 * 
 */
package org.softwarily.todo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softwarily.todo.dao.TodoRepository;
import org.softwarily.todo.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Steve Buck (steve@softwarily.org)
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TodoController.class })
@WebAppConfiguration
@DataJpaTest
public class TodoControllerTest {

	@Autowired
	private TodoRepository repository;

	@Autowired
	private TodoController controller;

	private List<Todo> todoList = new ArrayList<>();

	@Before
	public void setup() throws Exception {
		List<Todo> todos = new ArrayList<Todo>();
		todoList.add(repository.save(new Todo("Test One", "Test Todo One", new Date(2017, Calendar.JULY, 3, 8, 45))));
		todoList.add(
				repository.save(new Todo("Test Two", "Test Todo Two", new Date(2017, Calendar.AUGUST, 4, 22, 15))));
		todoList.add(repository
				.save(new Todo("Test Three", "Test Todo Three", new Date(2017, Calendar.SEPTEMBER, 5, 13, 30))));

	}

	@Test
	public void testFindAllTodos() throws Exception {
		List<Todo> output = controller.listTodos();
		assertThat(output).isEqualTo(todoList);
	}

	@Test
	public void testFindOneTodo() throws Exception {
		List<Todo> list = controller.listTodos();
		
		Todo output = controller.getOneTodo(list.get(0).getId());
		assertThat(output).isEqualTo(todoList.get(0));
	}
	
}
