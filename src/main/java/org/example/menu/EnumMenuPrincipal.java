package org.example.menu;

public enum EnumMenuPrincipal {
    GESTIONAR_EMPRESAS(1, "Gestionar Empresas"),
    GESTIONAR_DEPARTAMENTOS(2, "Gestionar Departamentos"),
    GESTIONAR_EMPLEADOS(3, "Gestionar Empleados"),
    SALIR(4, "Salir");

    private final int codigo;
    private final String descripcion;

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

    public static EnumMenuPrincipal obtenerPorCodigo(int codigo) {
        for (EnumMenuPrincipal opcion : values()) {
            if (opcion.getCodigo() == codigo) {
                return opcion;
            }
        }
        return null;
    }

    public static void mostrarOpcionesMenu() {
        System.out.println("--- MENÚ PRINCIPAL ---");
        for (EnumMenuPrincipal opcion : EnumMenuPrincipal.values()) {
            System.out.println(opcion.getCodigo() + ". " + opcion.getDescripcion());
        }
    }
}
