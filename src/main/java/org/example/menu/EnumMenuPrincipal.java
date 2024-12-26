package org.example.menu;

/**
 * Esta enumeración representa las diferentes opciones de un menú principal.
 * Cada opción se identifica por un código (entero) y una descripción (String).
 */
public enum EnumMenuPrincipal {
    GESTIONAR_EMPRESAS(1, "Gestionar Empresas"),
    GESTIONAR_DEPARTAMENTOS(2, "Gestionar Departamentos"),
    GESTIONAR_EMPLEADOS(3, "Gestionar Empleados"),
    SALIR(4, "Salir");

    private final int codigo;
    private final String descripcion;

    //Constructor de la enumeración. Asigna el código y descripción a la opción.
    EnumMenuPrincipal(int codigo, String descripcion) {
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
     * Retorna la opción del menú principal correspondiente al código proporcionado.
     * Si no existe ninguna coincidencia, retorna null.
     */
    public static EnumMenuPrincipal obtenerPorCodigo(int codigo) {
        for (EnumMenuPrincipal opcion : values()) {
            if (opcion.getCodigo() == codigo) {
                return opcion;
            }
        }
        return null;
    }

    /**
     * Muestra por consola todas las opciones del menú principal con su código y descripción.
     */
    public static void mostrarOpcionesMenu() {
        System.out.println("--- MENÚ PRINCIPAL ---");
        for (EnumMenuPrincipal opcion : EnumMenuPrincipal.values()) {
            System.out.println(opcion.getCodigo() + ". " + opcion.getDescripcion());
        }
    }
}
