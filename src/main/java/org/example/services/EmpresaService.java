package org.example.services;

import org.example.models.Empresa;
import org.example.repository.EmpresaRepository;

import java.util.List;
import java.util.Scanner;

//Clase que encapsula la lógica de negocio para la entidad {@link Empresa}.

public class EmpresaService {

    private final EmpresaRepository empresaRepo;
    private final Scanner sc;

    public EmpresaService(Scanner sc) {
        this.sc = sc;
        this.empresaRepo = new EmpresaRepository();
    }

    public void createEmpresa() {
        System.out.println("Ingrese nombre de la empresa: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese industria de la empresa: ");
        String industria = sc.nextLine();

        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setNombre(nombre);
        nuevaEmpresa.setIndustria(industria);

        empresaRepo.guardarEmpresa(nuevaEmpresa);
        System.out.println("Empresa creada.");
    }

    public void readEmpresaById() {
        System.out.println("Ingrese el ID de la empresa: ");
        int id = leerEntero();
        Empresa empresa = empresaRepo.obtenerEmpresaPorId(id);
        if (empresa != null) {
            System.out.println("Empresa: " + empresa);
        } else {
            System.out.println("No existe una empresa con ese ID.");
        }
    }

    public void readAllEmpresas() {
        List<Empresa> empresas = empresaRepo.obtenerTodasLasEmpresas();
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
        Empresa empresa = empresaRepo.obtenerEmpresaPorId(id);
        if (empresa == null) {
            System.out.println("No existe una empresa con ese ID.");
            return;
        }
        System.out.println("Ingrese el nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        System.out.println("Ingrese la nueva industria: ");
        String nuevaIndustria = sc.nextLine();

        empresa.setNombre(nuevoNombre);
        empresa.setIndustria(nuevaIndustria);
        empresaRepo.actualizarEmpresaPorId(id, empresa);
        System.out.println("Empresa actualizada!.");
    }

    public void deleteEmpresa() {
        System.out.println("Ingrese el ID de la empresa a eliminar: ");
        int id = leerEntero();
        empresaRepo.eliminarEmpresaPorId(id);
        System.out.println("Empresa eliminada!.");
    }

    public void mostrarDepartamentosYEmpleados() {
        System.out.println("Ingrese el ID de la empresa: ");
        int id = leerEntero();
        empresaRepo.mostrarDepartamentosYEmpleados(id);
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

