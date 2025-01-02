package org.juancarlos.services;

import org.juancarlos.models.Departamento;
import org.juancarlos.models.Empleado;
import org.juancarlos.repository.DepartamentoRepository;
import org.juancarlos.repository.EmpleadoRepository;

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
        String nombreEmp;
        String apellidoEmp;
        String puestoEmp;

        do {
            System.out.println("Ingrese nombre del empleado: ");
            nombreEmp = sc.nextLine().trim();
            if (nombreEmp.isEmpty()) {
                System.out.println("El nombre no puede estar vacío");
            }
        } while (nombreEmp.isEmpty());
        do {
            System.out.println("Ingrese apellido del empleado: ");
            apellidoEmp = sc.nextLine().trim();
            if (apellidoEmp.isEmpty()) {
                System.out.println("El apellido no puede estar vacío");
            }
        } while (apellidoEmp.isEmpty());
        do {
            System.out.println("Ingrese puesto del empleado: ");
            puestoEmp = sc.nextLine().trim();
            if (puestoEmp.isEmpty()) {
                System.out.println("El puesto no puede estar vacío");
            }
        } while (puestoEmp.isEmpty());

        System.out.println("Ingrese el ID del departamento al que pertenece: ");
        int idDepto = leerEntero();
        Departamento departamento = deptoRepo.readDepartamentoPorId(idDepto);
        if (departamento == null) {
            System.out.println("No existe un departamento con ese ID.");
            return;
        }

        Empleado emp = new Empleado();
        emp.setNombre(nombreEmp);
        emp.setApellido(apellidoEmp);
        emp.setPuesto(puestoEmp);
        emp.setDepartamento(departamento);

        empleadoRepo.createEmpleado(emp);
        System.out.println("Empleado creado!");
    }

    public void readEmpleadoById() {
        System.out.println("Ingrese el ID del empleado: ");
        int id = leerEntero();
        Empleado emp = empleadoRepo.readEmpleadoPorId(id);
        if (emp == null) {
            System.out.println("No existe un empleado con ese ID.");
        } else {
            System.out.println("Empleado: " + emp);
        }
    }

    public void readAllEmpleados() {
        List<Empleado> empleados = empleadoRepo.readTodosLosEmpleados();
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
        List<Empleado> empleados = empleadoRepo.readEmpleadosPorDepartamento(idDep);
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
        Empleado emp = empleadoRepo.readEmpleadoPorId(id);
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

        if (!nuevoNombre.isEmpty()) {
            emp.setNombre(nuevoNombre);
        }
        if (!nuevoApellido.isEmpty()) {
            emp.setApellido(nuevoApellido);
        }
        if (!nuevoPuesto.isEmpty()) {
            emp.setPuesto(nuevoPuesto);
        }
        empleadoRepo.updateEmpleadoPorId(id, emp);
        System.out.println("Empleado actualizado!");
    }

    public void deleteEmpleado() {
        System.out.println("Ingrese el ID del empleado a eliminar: ");
        int id = leerEntero();
        Empleado emp = empleadoRepo.readEmpleadoPorId(id);
        if (emp == null) {
            System.out.println("No existe un empleado con ese ID.");
            return;
        }
        empleadoRepo.deleteEmpleadoPorId(id);
        System.out.println("Empleado eliminado!");
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

