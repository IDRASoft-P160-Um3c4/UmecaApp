package com.umeca.model.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="BITACORA_MODIFICACIONES")
public class BitacoraModificar {

	@Id
	@GeneratedValue
	@Column(name="ID_BITACORA_MODIFICACION")
	private Long id;

    @Column(name="ENTIDAD", nullable = false)
    private String entidad;

    @Lob
    @Column(name="ANTES", nullable = false)
    private String antes;

    @Lob
    @Column(name="DESPUES", nullable = false)
    private String despues;

    @Column(name="FECHA", nullable=false)
    private Date fecha;

	public Long getId() {
		return id;
	}

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getAntes() {
        return antes;
    }

    public void setAntes(String antes) {
        this.antes = antes;
    }

    public String getDespues() {
        return despues;
    }

    public void setDespues(String despues) {
        this.despues = despues;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
