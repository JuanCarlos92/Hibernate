package org.juancarlos.menu;

/**
 * Esta enumeración representa las diferentes opciones de un menú principal.
 * Cada opción se identifica por un código (entero) y una descripción (String).
 */
public enum EnumMenuDepartamentos {
    CREAR_DEPARTAMENTO(1, "Crear Departamento"),
    CONSULTAR_ID_DEPARTAMENTO(2, "Consultar Departamento (por ID)"),
    CONSULTAR_DEPARTAMENTOS(3, "Consultar todos los Departamentos"),
    CONSULTAR_DEPARTAMENTO_DE_EMPRESA(4,"Consultar Departamento de Empresas"),
    ACTUALIZAR_DEPARTAMENTO(5, "Actualizar Departamento (por ID)"),
    ELIMINAR_DEPARTAMENTO(6, "Eliminar Departamento (por ID)"),
    MOSTRAR_EMPLEADOS_ASIG_DEPARTAMENTO_DE_EMPRESA(7, "Mostrar todos los empleados asignadas a un Departamento en un Empresa"),
    VOLVER(8, "Volver al Menú Principal");

    private final int codigo;
    private final String descripcion;

    //Constructor de la enumeración. Asigna el código y descripción a la opción.
    EnumMenuDepartamentos(int codigo, String descripcion) {
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
     * Retorna la opción del menú departamento correspondiente al código proporcionado.
     * Si no existe ninguna coincidencia, retorna null.
     */
    public static EnumMenuDepartamentos obtenerPorCodigo(int codigo) {
        for (EnumMenuDepartamentos opcion : values()) {
            if (opcion.getCodigo() == codigo) {
                return opcion;
            }
        }
        return null;
    }

    /**
     * Muestra por consola todas las opciones del menú departamento con su código y descripción.
     */
    public static void mostrarOpcionesMenu() {
        System.out.println("--- MENÚ DE DEPARTAMENTOS ---");
        for (EnumMenuDepartamentos opcion : EnumMenuDepartamentos.values()) {
            System.out.println(opcion.getCodigo() + ". " + opcion.getDescripcion());
        }
    }
}

