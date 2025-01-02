package org.juancarlos.services;

import org.juancarlos.models.Departamento;
import org.juancarlos.models.Empleado;
import org.juancarlos.models.Empresa;
import org.juancarlos.repository.DepartamentoRepository;
import org.juancarlos.repository.EmpresaRepository;

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

    // Crear departamento
    public void createDepartamento() {
        String nombreDepto;

        do {
            System.out.println("Ingrese el nombre del departamento: ");
            nombreDepto = sc.nextLine().trim();
            if (nombreDepto.isEmpty()) {
                System.out.println("El nombre no puede estar vacío");
            }
        } while (nombreDepto.isEmpty());

        System.out.println("Ingrese el ID de la empresa a la que pertenece: ");
        int idEmpresa = leerEntero();
        Empresa empresa = empresaRepo.readEmpresaPorId(idEmpresa);
        if (empresa == null) {
            System.out.println("No existe una empresa con ID: " + idEmpresa);
            return;
        }

        Departamento depto = new Departamento();
        depto.setNombre(nombreDepto);
        depto.setEmpresa(empresa);

        deptoRepo.createDepartamento(depto);
        System.out.println("Departamento creado!");
    }

    public void readDepartamentoById() {
        System.out.println("Ingrese el ID del departamento: ");
        int id = leerEntero();
        Departamento depto = deptoRepo.readDepartamentoPorId(id);
        if (depto == null) {
            System.out.println("No existe un departamento con ese ID.");
        } else {
            System.out.println("Departamento: " + depto);
        }
    }

    public void readAllDepartamentos() {
        List<Departamento> departamentos = deptoRepo.readTodosLosDepartamentos();
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
        List<Departamento> departamentos = deptoRepo.readDepartamentosPorEmpresa(idEmpresa);
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
        Departamento depto = deptoRepo.readDepartamentoPorId(id);
        if (depto == null) {
            System.out.println("No existe un departamento con ese ID.");
            return;
        }

        System.out.println("Ingrese el nuevo nombre del departamento: ");
        String nuevoNombre = sc.nextLine();

        if (!nuevoNombre.isEmpty()) {
            depto.setNombre(nuevoNombre);
        }

        deptoRepo.updateDepartamentoPorId(id, depto);
        System.out.println("Departamento actualizado!");
    }

    public void deleteDepartamento() {
        System.out.println("Ingrese el ID del departamento a eliminar: ");
        int id = leerEntero();
        Departamento depto = deptoRepo.readDepartamentoPorId(id);
        if (depto == null) {
            System.out.println("No existe un departamento con ese ID.");
            return;
        }
        deptoRepo.deleteDepartamentoPorId(id);
        System.out.println("Departamento eliminado!");
    }

    public void mostrarEmpleadosDeDepartamento() {
        System.out.println("Ingrese el ID del departamento: ");
        int id = leerEntero();

        // Obtenemos el departamento con sus empleados
        Departamento departamento = deptoRepo.obtenerDepartamentoConEmpleados(id);

        // Validación básica
        if (departamento == null) {
            System.out.println("No se encontró el departamento con ID: " + id);
            return;
        }

        // Obtenemos la lista de empleados
        List<Empleado> empleados = departamento.getEmpleados();

        // Verificamos si la lista está vacía o es null
        if (empleados == null || empleados.isEmpty()) {
            System.out.println("El departamento '" + departamento.getNombre()
                    + "' no tiene empleados registrados.");
        } else {
            System.out.println("Empleados en el departamento: " + departamento.getNombre());
            for (Empleado emp : empleados) {
                System.out.println("- " + emp.getNombre() + " " + emp.getApellido()
                        + " [Puesto: " + emp.getPuesto() + "]");
            }
        }
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

