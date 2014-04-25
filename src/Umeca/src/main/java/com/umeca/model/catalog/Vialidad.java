package com.umeca.model.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.umeca.infrastructure.Convert;


@Entity
@Table(name="CAT_VIALIDADES")
public class Vialidad {

	@Id
	@Column(name="ID_VIALIDAD")
	private Long id;	

	@Column(name="VIALIDAD", length=255, nullable=false)
	private String nombre;
	

	@Transient
	private String value;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@JsonIgnore
	public String getValue() {
		
		if(id == null)
			return null;
		
		value = id.toString();
		
		return value;
	}


	public void setValue(String value) {
		
		id = Convert.ToLong(value);
		
		this.value = value;
	}
	
}
