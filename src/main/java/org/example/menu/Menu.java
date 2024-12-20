package org.example.menu;

import java.util.Scanner;

public class Menu {
    public void iniciar() {
        Scanner sc = new Scanner(System.in);
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
                    gestionarEmpresas(sc);
                    break;
                case GESTIONAR_DEPARTAMENTOS:
                    gestionarDepartamentos(sc);
                    break;
                case GESTIONAR_EMPLEADOS:
                    gestionarEmpleados(sc);
                    break;
                case SALIR:
                    salir = true;
                    break;
            }
        }

        sc.close();
    }

    private EnumMenuPrincipal validarOpcionPrincipal(Scanner sc) {
        System.out.print("Selecciona una opción: ");
        int codigo = leerEntero(sc);
        return EnumMenuPrincipal.obtenerPorCodigo(codigo);
    }

    private void gestionarEmpresas(Scanner sc) {
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
                    // Lógica crear empresa
                    break;
                case CONSULTAR_ID_EMPRESA:
                    // Lógica leer empresa por id
                    break;
                case CONSULTAR_EMPRESAS:
                    // Lógica leer empresas
                    break;
                case ACTUALIZAR_EMPRESA:
                    // Lógica actualizar empresa por id
                    break;
                case ELIMINAR_EMPRESA:
                    // Lógica eliminar empresa por id
                    break;
                case MOSTRAR_DEPARTAMENTOS_Y_EMPLEADOS:
                    // Lógica mostrar departamentos y empleados de la empresa
                    break;
                case VOLVER:
                    volver = true;
                    break;
            }
        }
    }

    private EnumMenuEmpresas validarOpcionEmpresas(Scanner sc) {
        System.out.print("Selecciona una opción: ");
        int codigo = leerEntero(sc);
        return EnumMenuEmpresas.obtenerPorCodigo(codigo);
    }

    private void gestionarDepartamentos(Scanner sc) {
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
                    // Lógica crear departamento
                    break;
                case CONSULTAR_ID_DEPARTAMENTO:
                    // Lógica leer departamento por id
                    break;
                case CONSULTAR_DEPARTAMENTOS:
                    // Lógica leer departamentos
                    break;
                case ACTUALIZAR_DEPARTAMENTO:
                    // Lógica actualizar departamento por id
                    break;
                case ELIMINAR_DEPARTAMENTO:
                    // Lógica eliminar departamento por id
                    break;
                case MOSTRAR_EMPLEADOS_DEPARTAMENTO:
                    // Lógica mostrar empleados del departamento en una empresa
                    break;
                case VOLVER:
                    volver = true;
                    break;
            }
        }
    }

    private EnumMenuDepartamentos validarOpcionDepartamentos(Scanner sc) {
        System.out.print("Selecciona una opción: ");
        int codigo = leerEntero(sc);
        return EnumMenuDepartamentos.obtenerPorCodigo(codigo);
    }

    private void gestionarEmpleados(Scanner sc) {
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
                    // Lógica crear empleado
                    break;
                case CONSULTAR_ID_EMPLEADO:
                    // Lógica leer empleado por id
                    break;
                case CONSULTAR_EMPLEADOS:
                    // Lógica leer empleados
                    break;
                case ACTUALIZAR_EMPLEADO:
                    // Lógica actualizar empleado por id
                    break;
                case ELIMINAR_EMPLEADO:
                    // Lógica eliminar empleado por id
                    break;
                case VOLVER:
                    volver = true;
                    break;
            }
        }
    }

    private EnumMenuEmpleados validarOpcionEmpleados(Scanner sc) {
        System.out.print("Selecciona una opción: ");
        int codigo = leerEntero(sc);
        return EnumMenuEmpleados.obtenerPorCodigo(codigo);
    }

    private int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Ingrese un número válido:");
            sc.next();
        }
        return sc.nextInt();
    }
}
