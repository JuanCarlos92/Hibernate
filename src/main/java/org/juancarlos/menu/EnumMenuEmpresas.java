package org.juancarlos.menu;

/**
 * Esta enumeración representa las diferentes opciones de un menú principal.
 * Cada opción se identifica por un código (entero) y una descripción (String).
 */
public enum EnumMenuEmpresas {
    CREAR_EMPRESA(1, "Crear Empresa"),
    CONSULTAR_ID_EMPRESA(2, "Consultar Empresa (por ID)"),
    CONSULTAR_EMPRESAS(3, "Consultar todas las Empresas"),
    ACTUALIZAR_EMPRESA(4, "Actualizar empresa (por ID)"),
    ELIMINAR_EMPRESA(5, "Eliminar empresa (por ID)"),
    MOSTRAR_DEPARTAMENTOS_DE_EMPRESA_Y_EMPLEADOS(6, "Mostrar todos los Departamentos de una Empresa y sus Empleados"),
    VOLVER(7, "Volver al Menú Principal");

    private final int codigo;
    private final String descripcion;

    //Constructor de la enumeración. Asigna el código y descripción a la opción.
    EnumMenuEmpresas(int codigo, String descripcion) {
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
     * Retorna la opción del menú empresas correspondiente al código proporcionado.
     * Si no existe ninguna coincidencia, retorna null.
     */
    public static EnumMenuEmpresas obtenerPorCodigo(int codigo) {
        for (EnumMenuEmpresas opcion : values()) {
            if (opcion.getCodigo() == codigo) {
                return opcion;
            }
        }
        return null;
    }

    /**
     * Muestra por consola todas las opciones del menú empresas con su código y descripción.
     */
    public static void mostrarOpcionesMenu() {
        System.out.println("--- MENÚ DE EMPRESAS ---");
        for (EnumMenuEmpresas opcion : EnumMenuEmpresas.values()) {
            System.out.println(opcion.getCodigo() + ". " + opcion.getDescripcion());
        }
    }
}
