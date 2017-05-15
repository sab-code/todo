package org.softwarily.todo;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.softwarily.todo.dao.TodoRepository;
import org.softwarily.todo.domain.Todo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("applicationContext.xml")
public class TodoApplication {
	public static final Logger log = LoggerFactory.getLogger(TodoApplication.class);
	

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(final TodoRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Todo("Test One", "Test Todo One", new Date(2017, Calendar.JULY, 3, 8, 45)));
			repository.save(new Todo("Test Two", "Test Todo Two", new Date(2017, Calendar.AUGUST, 4, 22, 15)));
			repository.save(new Todo("Test Three", "Test Todo Three", new Date(2017, Calendar.SEPTEMBER, 5, 13, 30)));

			// fetch all customers
			log.info("Todos found with findAll():");
			log.info("-------------------------------");
			for (Todo todo : repository.findAll()) {
				log.info(todo.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Todo oneTodo = repository.findOne(1L);
			log.info("Todo found with findOne(1L):");
			log.info("--------------------------------");
			log.info(oneTodo.toString());
			log.info("");

			// fetch customers by last name
			log.info("Todo found with findByDescription('Test Two'):");
			log.info("--------------------------------------------");
			for (Todo todo : repository.findByTitle("Test Two")) {
				log.info(todo.toString());
			}
			log.info("");
		};
	}
}
