package com.note.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Note")
@EntityListeners(AuditingEntityListener.class)
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Title is mandatory")
	private String name;

	private String crtBy;
	
	private LocalDateTime crtDate;

	private LocalDateTime mdyDate;

	@NotBlank(message = "Description is mandatory")
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	
		public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(LocalDateTime crtDate) {
		this.crtDate = crtDate;
	}
	public LocalDateTime getMdyDate() {
		return mdyDate;
	}
	public void setMdyDate(LocalDateTime mdyDate) {
		this.mdyDate = mdyDate;
	}
	public String getCrtBy() {
		return crtBy;
	}
	public void setCrtBy(String crtBy) {
		this.crtBy = crtBy;
	}


	
}
