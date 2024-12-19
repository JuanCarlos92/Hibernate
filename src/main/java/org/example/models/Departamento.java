package org.example.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToMany(mappedBy = "Empleado", cascade = CascadeType.ALL)
    private List<Empleado> empleados;

    public Departamento() {
    }

    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void addEmpleado(Empleado empleado) {
        if (empleados == null) {
            empleados = new ArrayList<>();
        }
        empleados.add(empleado);
        empleado.setDepartamento(this);  // Relación bidireccional
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Departamento{" + "id=" + id + ", nombre='" + nombre + '}';
    }
}
