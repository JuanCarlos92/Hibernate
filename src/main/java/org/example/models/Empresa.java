package org.example.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "industria", nullable = false)
    private String industria;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Departamento> departamentos;

    public Empresa() {
    }

    public Empresa(int id, String nombre, String industria) {
        this.id = id;
        this.nombre = nombre;
        this.industria = industria;
    }

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public void addEstudiante(Departamento departamento) {
        if (departamentos == null) {
            departamentos = new ArrayList<>();
        }
        departamentos.add(departamento);
        departamento.setEmpresa(this);  // Relación bidireccional
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
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

    public String getIndustria() {
        return industria;
    }

    public void setIndustria(String industria) {
        this.industria = industria;
    }


    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre='" + nombre + '\'' + ", industria='" + industria + '\'' + '}';
    }
}

