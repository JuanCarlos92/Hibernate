package org.juancarlos.services;

import org.juancarlos.models.Departamento;
import org.juancarlos.models.Empleado;
import org.juancarlos.models.Empresa;
import org.juancarlos.repository.EmpresaRepository;

import java.util.List;
import java.util.Scanner;

// Clase que encapsula la lógica de negocio para la entidad Empresa.
public class EmpresaService {

    private final EmpresaRepository empresaRepo;
    private final Scanner sc;

    public EmpresaService(Scanner sc) {
        this.sc = sc;
        this.empresaRepo = new EmpresaRepository();
    }

    public void createEmpresa() {
        String nombre;
        String industria;

        do {
            System.out.println("Ingrese nombre de la empresa: ");
            nombre = sc.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("No se puede dejar el nombre vacía.");
            }
        } while (nombre.isEmpty());
        do {
            System.out.println("Ingrese industria de la empresa: ");
            industria = sc.nextLine().trim();
            if (industria.isEmpty()) {
                System.out.println("No se puede dejar la industria vacía.");
            }
        } while (industria.isEmpty());

        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setNombre(nombre);
        nuevaEmpresa.setIndustria(industria);

        empresaRepo.createEmpresa(nuevaEmpresa);
        System.out.println("Empresa creada!");
    }

    public void readEmpresaById() {
        System.out.println("Ingrese el ID de la empresa: ");
        int id = leerEntero();
        Empresa empresa = empresaRepo.readEmpresaPorId(id);
        if (empresa != null) {
            System.out.println("Empresa: " + empresa);
        } else {
            System.out.println("No existe una empresa con ese ID.");
        }
    }

    public void readAllEmpresas() {
        List<Empresa> empresas = empresaRepo.readTodasLasEmpresas();
        if (empresas.isEmpty()) {
            System.out.println("No hay empresas registradas.");
        } else {
            System.out.println("Empresas registradas:");
            empresas.forEach(System.out::println);
        }
    }

    public void updateEmpresa() {
        System.out.println("Ingrese el ID de la empresa a actualizar: ");
        int id = leerEntero();
        Empresa empresa = empresaRepo.readEmpresaPorId(id);
        if (empresa == null) {
            System.out.println("No existe una empresa con ese ID.");
            return;
        }
        System.out.println("Ingrese el nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        System.out.println("Ingrese la nueva industria: ");
        String nuevaIndustria = sc.nextLine();

        if (!nuevoNombre.isEmpty()) {
            empresa.setNombre(nuevoNombre);
        }
        if (!nuevaIndustria.isEmpty()) {
            empresa.setIndustria(nuevaIndustria);
        }

        empresaRepo.updateEmpresaPorId(id, empresa);
        System.out.println("Empresa actualizada!.");
    }

    public void deleteEmpresa() {
        System.out.println("Ingrese el ID de la empresa a eliminar: ");
        int id = leerEntero();
        Empresa empresa = empresaRepo.readEmpresaPorId(id);
        if (empresa == null) {
            System.out.println("No existe una empresa con ese ID.");
            return;
        }
        empresaRepo.deleteEmpresaPorId(id);
        System.out.println("Empresa eliminada!");
    }

    public void mostrarDepartamentosYEmpleados() {
        System.out.println("Ingrese el ID de la empresa: ");
        int id = leerEntero();

        // Obtenemos la empresa con sus departamentos y empleados.
        Empresa empresa = empresaRepo.obtenerEmpresaConDepartamentosYEmpleados(id);

        if (empresa == null) {
            System.out.println("No se encontró la empresa con ID: " + id);
            return;
        }

        //Lista <Departamentos> que recoge todos los departamentos
        List<Departamento> departamentos = empresa.getDepartamentos();

        if (departamentos == null || departamentos.isEmpty()) {
            System.out.println("La empresa '" + empresa.getNombre() + "' no tiene departamentos registrados.");

        } else {
            //Muestra los departamentos de la empresa
            System.out.println("Departamentos de la empresa: " + empresa.getNombre());
            for (Departamento depto : departamentos) {
                System.out.println("* Departamento: " + depto.getNombre());

                //Lista <Empleado> que recoge todos los empleados del departamento
                List<Empleado> empleados = depto.getEmpleados();
                if (empleados == null || empleados.isEmpty()) {
                    System.out.println("    Sin empleados registrados en este departamento.");
                } else {
                    //Muestra los empleados del departamento
                    System.out.println(" -> Empleados en el departamento:");
                    for (Empleado emp : empleados) {
                        System.out.println("    " + emp.getNombre() + " " + emp.getApellido() +
                                " [Puesto: " + emp.getPuesto() + "]");
                    }
                }
            }
        }
    }

    public void close() {
        empresaRepo.cerrar();
    }

    private int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.println("Ingrese un número válido:");
            sc.next();
        }
        int numero = sc.nextInt();
        sc.nextLine(); // limpiar buffer
        return numero;
    }
}

