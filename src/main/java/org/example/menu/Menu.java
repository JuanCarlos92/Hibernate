package org.example.menu;

import java.util.Scanner;

public class Menu {

    /**
     * Inicia la ejecución del menú principal. Muestra las opciones disponibles
     * y delega el control a submenús según la selección del usuario.
     **/
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

    /**
     * Solicita al usuario que seleccione una opción del menú principal y valida
     * que sea un valor numérico asociado a la enumeración {@link EnumMenuPrincipal}.
     */
    private EnumMenuPrincipal validarOpcionPrincipal(Scanner sc) {
        System.out.print("Selecciona una opción: ");
        int codigo = leerEntero(sc);
        return EnumMenuPrincipal.obtenerPorCodigo(codigo);
    }

    /**
     * Administra la lógica del submenú de empresas. Muestra las opciones asociadas
     * a la enumeración {@link EnumMenuEmpresas} y procesa la selección del usuario.
     */
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
                case MOSTRAR_DEPARTAMENTOS_DE_EMPRESA_Y_EMPLEADOS:
                    // Lógica mostrar departamentos de una empresa y sus empleados
                    break;
                case VOLVER:
                    volver = true;
                    break;
            }
        }
    }

    /**
     * Solicita al usuario que seleccione una opción del submenú de empresas y valida
     * que sea un valor numérico asociado a la enumeración {@link EnumMenuEmpresas}.
     */
    private EnumMenuEmpresas validarOpcionEmpresas(Scanner sc) {
        System.out.print("Selecciona una opción: ");
        int codigo = leerEntero(sc);
        return EnumMenuEmpresas.obtenerPorCodigo(codigo);
    }

    /**
     * Administra la lógica del submenú de departamentos. Muestra las opciones asociadas
     * a la enumeración {@link EnumMenuDepartamentos} y procesa la selección del usuario.
     */
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
                case CONSULTAR_DEPARTAMENTO_DE_EMPRESA:
                    // Lógica leer departamentos
                    break;
                case ACTUALIZAR_DEPARTAMENTO:
                    // Lógica actualizar departamento por id
                    break;
                case ELIMINAR_DEPARTAMENTO:
                    // Lógica eliminar departamento por id
                    break;
                case MOSTRAR_EMPLEADOS_ASIG_DEPARTAMENTO_DE_EMPRESA:
                    // Lógica mostrar empleados asignado a un departamento de una empresa
                    break;
                case VOLVER:
                    volver = true;
                    break;
            }
        }
    }

    /**
     * Solicita al usuario que seleccione una opción del submenú de departamentos y valida
     * que sea un valor numérico asociado a la enumeración {@link EnumMenuDepartamentos}.
     */
    private EnumMenuDepartamentos validarOpcionDepartamentos(Scanner sc) {
        System.out.print("Selecciona una opción: ");
        int codigo = leerEntero(sc);
        return EnumMenuDepartamentos.obtenerPorCodigo(codigo);
    }

    /**
     * Administra la lógica del submenú de empleados. Muestra las opciones asociadas
     * a la enumeración {@link EnumMenuEmpleados} y procesa la selección del usuario.
     */
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
                case CONSULTAR_EMPLEADO_DE_DEPARTAMENTO:
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

    /**
     * Solicita al usuario que seleccione una opción del submenú de empleados y valida
     * que sea un valor numérico asociado a la enumeración {@link EnumMenuEmpleados}.
     */
    private EnumMenuEmpleados validarOpcionEmpleados(Scanner sc) {
        System.out.print("Selecciona una opción: ");
        int codigo = leerEntero(sc);
        return EnumMenuEmpleados.obtenerPorCodigo(codigo);
    }

    /**
     * Lee un valor entero introducido por el usuario mediante el objeto {@code Scanner}.
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
