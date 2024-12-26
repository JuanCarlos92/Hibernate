package org.example.services;

import org.example.models.Departamento;
import org.example.models.Empleado;
import org.example.repository.DepartamentoRepository;
import org.example.repository.EmpleadoRepository;

import java.util.List;
import java.util.Scanner;

public class EmpleadoService {

    private final EmpleadoRepository empleadoRepo;
    private final DepartamentoRepository deptoRepo;
    private final Scanner sc;

    public EmpleadoService(Scanner sc) {
        this.sc = sc;
        this.empleadoRepo = new EmpleadoRepository();
        this.deptoRepo = new DepartamentoRepository();
    }

    public void createEmpleado() {
        System.out.println("Ingrese nombre del empleado: ");
        String nombreEmp = sc.nextLine();
        System.out.println("Ingrese apellido del empleado: ");
        String apellidoEmp = sc.nextLine();
        System.out.println("Ingrese puesto del empleado: ");
        String puesto = sc.nextLine();

        System.out.println("Ingrese el ID del departamento al que pertenece: ");
        int idDepto = leerEntero();
        Departamento departamento = deptoRepo.obtenerDepartamentoPorId(idDepto);
        if (departamento == null) {
            System.out.println("No existe un departamento con ese ID.");
            return;
        }

        Empleado emp = new Empleado();
        emp.setNombre(nombreEmp);
        emp.setApellido(apellidoEmp);
        emp.setPuesto(puesto);
        emp.setDepartamento(departamento);

        empleadoRepo.guardarEmpleado(emp);
        System.out.println("Empleado creado.");
    }

    public void readEmpleadoById() {
        System.out.println("Ingrese el ID del empleado: ");
        int id = leerEntero();
        Empleado emp = empleadoRepo.obtenerEmpleadoPorId(id);
        if (emp == null) {
            System.out.println("No existe un empleado con ese ID.");
        } else {
            System.out.println("Empleado: " + emp);
        }
    }

    public void readAllEmpleados() {
        List<Empleado> empleados = empleadoRepo.obtenerTodosLosEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            System.out.println("Empleados registrados:");
            empleados.forEach(System.out::println);
        }
    }

    public void readEmpleadosPorDepartamento() {
        System.out.println("Ingrese el ID del departamento: ");
        int idDep = leerEntero();
        List<Empleado> empleados = empleadoRepo.obtenerEmpleadosPorDepartamento(idDep);
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados en este departamento, o no existe.");
        } else {
            System.out.println("Empleados del departamento con ID " + idDep + ":");
            empleados.forEach(System.out::println);
        }
    }

    public void updateEmpleado() {
        System.out.println("Ingrese el ID del empleado a actualizar: ");
        int id = leerEntero();
        Empleado emp = empleadoRepo.obtenerEmpleadoPorId(id);
        if (emp == null) {
            System.out.println("No existe un empleado con ese ID.");
            return;
        }
        System.out.println("Ingrese el nuevo nombre: ");
        String nuevoNombre = sc.nextLine().trim();
        System.out.println("Ingrese el nuevo apellido: ");
        String nuevoApellido = sc.nextLine().trim();
        System.out.println("Ingrese el nuevo puesto: ");
        String nuevoPuesto = sc.nextLine().trim();

        if(!nuevoNombre.isEmpty()){
            emp.setNombre(nuevoNombre);
        }
        if(!nuevoApellido.isEmpty()){
            emp.setApellido(nuevoApellido);
        }
        if(!nuevoPuesto.isEmpty()){
            emp.setPuesto(nuevoPuesto);
        }
        empleadoRepo.actualizarEmpleadoPorId(id, emp);
        System.out.println("Empleado actualizado.");
    }

    public void deleteEmpleado() {
        System.out.println("Ingrese el ID del empleado a eliminar: ");
        int id = leerEntero();
        empleadoRepo.eliminarEmpleadoPorId(id);
        System.out.println("Empleado eliminado (si existía).");
    }

    public void close() {
        empleadoRepo.cerrar();
        deptoRepo.cerrar();
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

