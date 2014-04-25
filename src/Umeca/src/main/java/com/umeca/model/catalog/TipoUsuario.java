package com.umeca.model.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.umeca.infrastructure.Convert;


@Entity
@Table(name="CAT_TIPO_USUARIOS")
public class TipoUsuario {

	@Id
	@Column(name="ID_TIPO_USUARIO")
	private Long id;	

	@Column(name="TIPO_USUARIO", length=255, nullable=false)
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
