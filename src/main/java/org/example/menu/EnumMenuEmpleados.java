package org.example.menu;

/**
 * Esta enumeración representa las diferentes opciones de un menú principal.
 * Cada opción se identifica por un código (entero) y una descripción (String).
 */
public enum EnumMenuEmpleados {
    CREAR_EMPLEADO(1, "Crear Empleado"),
    CONSULTAR_ID_EMPLEADO(2, "Consultar Empleado (por ID)"),
    CONSULTAR_EMPLEADOS(3, "Consultar todos los Empleados"),
    CONSULTAR_EMPLEADO_DE_DEPARTAMENTO(4, "Consultar Empleados de Departamento"),
    ACTUALIZAR_EMPLEADO(5, "Actualizar Empleado (por ID)"),
    ELIMINAR_EMPLEADO(6, "Eliminar Empleado (por ID)"),
    VOLVER(7, "Volver al Menú Principal");

    private final int codigo;
    private final String descripcion;

    //Constructor de la enumeración. Asigna el código y descripción a la opción.
    EnumMenuEmpleados(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Retorna la opción del menú empleados correspondiente al código proporcionado.
     * Si no existe ninguna coincidencia, retorna null.
     */
    public static EnumMenuEmpleados obtenerPorCodigo(int codigo) {
        for (EnumMenuEmpleados opcion : values()) {
            if (opcion.getCodigo() == codigo) {
                return opcion;
            }
        }
        return null;
    }

    /**
     * Muestra por consola todas las opciones del menú empleados con su código y descripción.
     */
    public static void mostrarOpcionesMenu() {
        System.out.println("--- MENÚ DE EMPLEADOS ---");
        for (EnumMenuEmpleados opcion : EnumMenuEmpleados.values()) {
            System.out.println(opcion.getCodigo() + ". " + opcion.getDescripcion());
        }
    }
}

