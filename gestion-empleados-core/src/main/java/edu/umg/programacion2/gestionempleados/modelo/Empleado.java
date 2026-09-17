package edu.umg.programacion2.gestionempleados.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Empleado {

    private int id;
    private String nombreCompleto;
    private String departamento;
    private BigDecimal salario;
    private LocalDate fechaContratacion;
    private boolean activo;

    //Constructor para empleado nuevo
public Empleado(String nombreCompleto, String departamento, BigDecimal salario, LocalDate fechaContratacion, boolean activo) {
	this.nombreCompleto = nombreCompleto;
	this.departamento = departamento;
	this.salario = salario;
	this.fechaContratacion = fechaContratacion;
	this.activo = activo;
}

// Sobrecarga = varios constructores con el mismo nombre pero diferentes parametros
//Para representar un empleado que ya tiene ID
public Empleado(int id, String nombreCompleto, String departamento, BigDecimal salario, LocalDate fechaContratacion,boolean activo) {
	this.id = id;
	this.nombreCompleto = nombreCompleto;
	this.departamento = departamento;
	this.salario = salario;
	this.fechaContratacion = fechaContratacion;
	this.activo = activo;
}


public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getNombreCompleto() {
    return nombreCompleto;
}

public void setNombreCompleto(String nombreCompleto) {
    this.nombreCompleto = nombreCompleto;
}

public String getDepartamento() {
    return departamento;
}

public void setDepartamento(String departamento) {
    this.departamento = departamento;
}

public BigDecimal getSalario() {
    return salario;
}

public void setSalario(BigDecimal salario) {
    this.salario = salario;
}

public LocalDate getFechaContratacion() {
    return fechaContratacion;
}

public void setFechaContratacion(LocalDate fechaContratacion) {
    this.fechaContratacion = fechaContratacion;
}

public boolean isActivo() {
    return activo;
}

public void setActivo(boolean activo) {
    this.activo = activo;
}

@Override
public String toString() {
    return "Empleado{" +
            "id=" + id +
            ", nombreCompleto='" + nombreCompleto + '\'' +
            ", departamento='" + departamento + '\'' +
            ", salario=" + salario +
            ", fechaContratacion=" + fechaContratacion +
            ", activo=" + activo +
            '}';
}
}