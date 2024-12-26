package org.example.services;

import org.example.models.Departamento;
import org.example.models.Empresa;
import org.example.repository.DepartamentoRepository;
import org.example.repository.EmpresaRepository;

import java.util.List;
import java.util.Scanner;

public class DepartamentoService {

    private final DepartamentoRepository deptoRepo;
    private final EmpresaRepository empresaRepo;
    private final Scanner sc;

    public DepartamentoService(Scanner sc) {
        this.sc = sc;
        this.deptoRepo = new DepartamentoRepository();
        this.empresaRepo = new EmpresaRepository();
    }

    public void createDepartamento() {
        System.out.println("Ingrese el nombre del departamento: ");
        String nombreDepto = sc.nextLine();

        System.out.println("Ingrese el ID de la empresa a la que pertenece: ");
        int idEmpresa = leerEntero();
        Empresa empresa = empresaRepo.obtenerEmpresaPorId(idEmpresa);
        if (empresa == null) {
            System.out.println("No existe una empresa con ID: " + idEmpresa);
            return;
        }

        Departamento depto = new Departamento();
        depto.setNombre(nombreDepto);
        depto.setEmpresa(empresa);

        deptoRepo.guardarDepartamento(depto);
        System.out.println("Departamento creado.");
    }

    public void readDepartamentoById() {
        System.out.println("Ingrese el ID del departamento: ");
        int id = leerEntero();
        Departamento depto = deptoRepo.obtenerDepartamentoPorId(id);
        if (depto == null) {
            System.out.println("No existe un departamento con ese ID.");
        } else {
            System.out.println("Departamento: " + depto);
        }
    }

    public void readAllDepartamentos() {
        List<Departamento> departamentos = deptoRepo.obtenerTodosLosDepartamentos();
        if (departamentos.isEmpty()) {
            System.out.println("No hay departamentos registrados.");
        } else {
            System.out.println("Departamentos registrados:");
            departamentos.forEach(System.out::println);
        }
    }

    public void readDepartamentosDeEmpresa() {
        System.out.println("Ingrese el ID de la empresa: ");
        int idEmpresa = leerEntero();
        List<Departamento> departamentos = deptoRepo.obtenerDepartamentosPorEmpresa(idEmpresa);
        if (departamentos.isEmpty()) {
            System.out.println("La empresa no existe o no tiene departamentos registrados.");
        } else {
            System.out.println("Departamentos de la empresa con ID " + idEmpresa + ":");
            departamentos.forEach(System.out::println);
        }
    }

    public void updateDepartamento() {
        System.out.println("Ingrese el ID del departamento a actualizar: ");
        int id = leerEntero();
        Departamento depto = deptoRepo.obtenerDepartamentoPorId(id);
        if (depto == null) {
            System.out.println("No existe un departamento con ese ID.");
            return;
        }
        System.out.println("Ingrese el nuevo nombre del departamento: ");
        String nuevoNombre = sc.nextLine();
        depto.setNombre(nuevoNombre);
        deptoRepo.actualizarDepartamentoPorId(id, depto);
        System.out.println("Departamento actualizado.");
    }

    public void deleteDepartamento() {
        System.out.println("Ingrese el ID del departamento a eliminar: ");
        int id = leerEntero();
        deptoRepo.eliminarDepartamentoPorId(id);
        System.out.println("Departamento eliminado (si existía).");
    }

    public void mostrarEmpleadosDeDepartamento() {
        System.out.println("Ingrese el ID del departamento: ");
        int id = leerEntero();
        deptoRepo.mostrarEmpleadosDeDepartamento(id);
    }

    public void close() {
        deptoRepo.cerrar();
        empresaRepo.cerrar();
    }

    private int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.println("Ingrese un número válido:");
            sc.next();
        }
        int numero = sc.nextInt();
        sc.nextLine();  // limpiar buffer
        return numero;
    }
}

