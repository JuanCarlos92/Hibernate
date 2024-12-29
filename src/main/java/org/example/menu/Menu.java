package org.example.menu;

import org.example.services.DepartamentoService;
import org.example.services.EmpleadoService;
import org.example.services.EmpresaService;

import java.util.Scanner;

public class Menu {

    //Inicia la ejecución del menú principal
    public void iniciar() {
        Scanner sc = new Scanner(System.in);

        EmpresaService empresaService = new EmpresaService(sc);
        DepartamentoService departamentoService = new DepartamentoService(sc);
        EmpleadoService empleadoService = new EmpleadoService(sc);

        boolean salir = false;

        while (!salir) {
            EnumMenuPrincipal.mostrarOpcionesMenu();
            EnumMenuPrincipal opcionPrincipal = validarOpcionPrincipal(sc);

            if (opcionPrincipal == null) {
                System.out.println("Opción no válida.");
                continue;
            }

            switch (opcionPrincipal) {
                case GESTIONAR_EMPRESAS:
                    gestionarEmpresas(sc, empresaService);
                    break;
                case GESTIONAR_DEPARTAMENTOS:
                    gestionarDepartamentos(sc, departamentoService, empresaService);
                    break;
                case GESTIONAR_EMPLEADOS:
                    gestionarEmpleados(sc, empleadoService, departamentoService);
                    break;
                case SALIR:
                    salir = true;
                    break;
            }
        }
        // Cerrar repositorios o recursos al final
        empresaService.close();
        departamentoService.close();
        empleadoService.close();
        sc.close();
    }

    //Solicita al usuario que seleccione una opción del menu principal
    private EnumMenuPrincipal validarOpcionPrincipal(Scanner sc) {
        System.out.print("Selecciona una opción: ");
        int codigo = leerEntero(sc);
        return EnumMenuPrincipal.obtenerPorCodigo(codigo);
    }

    //Administra la lógica del submenú de empresas.
    private void gestionarEmpresas(Scanner sc, EmpresaService empresaService) {
        boolean volver = false;
        while (!volver) {
            EnumMenuEmpresas.mostrarOpcionesMenu();
            EnumMenuEmpresas opcion = validarOpcionEmpresas(sc);

            if (opcion == null) {
                System.out.println("Opción no válida.");
                continue;
            }

            switch (opcion) {
                case CREAR_EMPRESA:
                    empresaService.createEmpresa();
                    break;
                case CONSULTAR_ID_EMPRESA:
                    empresaService.readEmpresaById();
                    break;
                case CONSULTAR_EMPRESAS:
                    empresaService.readAllEmpresas();
                    break;
                case ACTUALIZAR_EMPRESA:
                    empresaService.readAllEmpresas();
                    empresaService.updateEmpresa();
                    break;
                case ELIMINAR_EMPRESA:
                    empresaService.readAllEmpresas();
                    empresaService.deleteEmpresa();
                    break;
                case MOSTRAR_DEPARTAMENTOS_DE_EMPRESA_Y_EMPLEADOS:
                    empresaService.readAllEmpresas();
                    empresaService.mostrarDepartamentosYEmpleados();
                    break;
                case VOLVER:
                    volver = true;
                    break;
            }
        }
    }

    // Solicita al usuario que seleccione una opción del submenú de empresas
    private EnumMenuEmpresas validarOpcionEmpresas(Scanner sc) {
        System.out.print("Selecciona una opción: ");
        int codigo = leerEntero(sc);
        sc.nextLine(); //Limpiar Buffer
        return EnumMenuEmpresas.obtenerPorCodigo(codigo);
    }

    // Administra la lógica del submenú de departamentos.
    private void gestionarDepartamentos(Scanner sc, DepartamentoService departamentoService, EmpresaService empresaService) {
        boolean volver = false;
        while (!volver) {
            EnumMenuDepartamentos.mostrarOpcionesMenu();
            EnumMenuDepartamentos opcion = validarOpcionDepartamentos(sc);

            if (opcion == null) {
                System.out.println("Opción no válida.");
                continue;
            }

            switch (opcion) {
                case CREAR_DEPARTAMENTO:
                    empresaService.readAllEmpresas();
                    departamentoService.createDepartamento();
                    break;
                case CONSULTAR_ID_DEPARTAMENTO:
                    departamentoService.readDepartamentoById();
                    break;
                case CONSULTAR_DEPARTAMENTOS:
                    departamentoService.readAllDepartamentos();
                    break;
                case CONSULTAR_DEPARTAMENTO_DE_EMPRESA:
                    empresaService.readAllEmpresas();
                    departamentoService.readDepartamentosDeEmpresa();
                    break;
                case ACTUALIZAR_DEPARTAMENTO:
                    departamentoService.readAllDepartamentos();
                    departamentoService.updateDepartamento();
                    break;
                case ELIMINAR_DEPARTAMENTO:
                    departamentoService.readAllDepartamentos();
                    departamentoService.deleteDepartamento();
                    break;
                case MOSTRAR_EMPLEADOS_ASIG_DEPARTAMENTO_DE_EMPRESA:
                    departamentoService.readAllDepartamentos();
                    departamentoService.mostrarEmpleadosDeDepartamento();
                    break;
                case VOLVER:
                    volver = true;
                    break;
            }
        }
    }

    // Solicita al usuario que seleccione una opción del submenú de departamentos
    private EnumMenuDepartamentos validarOpcionDepartamentos(Scanner sc) {
        System.out.print("Selecciona una opción: ");
        int codigo = leerEntero(sc);
        sc.nextLine(); //Limpiar Buffer
        return EnumMenuDepartamentos.obtenerPorCodigo(codigo);
    }

    // Administra la lógica del submenú de empleados
    private void gestionarEmpleados(Scanner sc, EmpleadoService empleadoService, DepartamentoService departamentoService) {
        boolean volver = false;
        while (!volver) {
            EnumMenuEmpleados.mostrarOpcionesMenu();
            EnumMenuEmpleados opcion = validarOpcionEmpleados(sc);

            if (opcion == null) {
                System.out.println("Opción no válida.");
                continue;
            }

            switch (opcion) {
                case CREAR_EMPLEADO:
                    departamentoService.readAllDepartamentos();
                    empleadoService.createEmpleado();
                    break;
                case CONSULTAR_ID_EMPLEADO:
                    empleadoService.readEmpleadoById();
                    break;
                case CONSULTAR_EMPLEADOS:
                    empleadoService.readAllEmpleados();
                    break;
                case CONSULTAR_EMPLEADO_DE_DEPARTAMENTO:
                    empleadoService.readEmpleadosPorDepartamento();
                    break;
                case ACTUALIZAR_EMPLEADO:
                    empleadoService.readAllEmpleados();
                    empleadoService.updateEmpleado();
                    break;
                case ELIMINAR_EMPLEADO:
                    empleadoService.readAllEmpleados();
                    empleadoService.deleteEmpleado();
                    break;
                case VOLVER:
                    volver = true;
                    break;
            }
        }
    }

    // Solicita al usuario que seleccione una opción del submenú de empleados
    private EnumMenuEmpleados validarOpcionEmpleados(Scanner sc) {
        System.out.print("Selecciona una opción: ");
        int codigo = leerEntero(sc);
        sc.nextLine(); //Limpiar Buffer
        return EnumMenuEmpleados.obtenerPorCodigo(codigo);
    }

    /**
     * Lee un valor entero introducido por el usuario mediante Scanner.
     * Si la entrada no es un número entero válido, solicita nuevamente al usuario que introduzca un valor correcto.
     */
    private int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Ingrese un número válido:");
            sc.next();
        }
        return sc.nextInt();
    }
}
