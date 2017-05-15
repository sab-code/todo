/**
 * 
 */
package org.softwarily.todo.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.softwarily.todo.TodoGenericException;

/**
 * Todo domain object.
 * 
 * @author Steve Buck (steve@softwarily.org)
 */
@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/* Title of the todo item. */
	private String title;
	/* Description of the todo item. */
	private String description;
	/* Date and time that the todo item is due. */
	private Date due;
	
	@Transient
	private SimpleDateFormat dueDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");


	/**
	 * Default constructor not allowing for empty values in item.
	 */
	protected Todo() {
	}

	/**
	 * Constructor enforcing values being entered.
	 * 
	 * @param title
	 *            the initial title of the todo item.
	 * @param description
	 *            the initial description of the todo item.
	 * @param due
	 *            the initial due date of the todo item.
	 */
	public Todo(final String title, final String description, final Date due) {
		this.title = title;
		this.description = description;
		this.due = due;
	}

	/**
	 * Accessor for the description of the todo item.
	 * 
	 * @return a string representation of the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Modifier of the description of the todo item.
	 *
	 * @param description
	 *            the new description.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Accessor of the title of the todo item.
	 * 
	 * @return the current title of the todo item.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * The modifier of the title of the todo item.
	 * 
	 * @param title
	 *            the new title of the todo item.
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Accessor of the due date of the todo item.
	 * 
	 * @return the current value of the due date of the todo item.
	 */
	public String getDue() {
		return dueDateFormat.format(due);
	}

	public Date getRawDueDate() {
		return due;
	}
	
	/**
	 * Modifier of the due date of the todo item.
	 * 
	 * @param due
	 *            the new value of the due date of the todo item.
	 */
	public void setDue(final String due) {
		if (due != null) {
			try {
				Date newDueDate = dueDateFormat.parse(due);
				this.due = newDueDate;
			} catch (final ParseException e) {
				throw new TodoGenericException("Could not parse date format.");
			}
		}
	}
	
	public void setRawDueDate(final Date due) {
		this.due = due;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
