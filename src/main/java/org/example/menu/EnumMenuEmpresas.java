package org.example.menu;

public enum EnumMenuEmpresas {
    CREAR_EMPRESA(1, "Crear Empresa"),
    CONSULTAR_ID_EMPRESA(2, "Consultar Empresa (por ID)"),
    CONSULTAR_EMPRESAS(2, "Consultar todas las Empresas"),
    ACTUALIZAR_EMPRESA(3, "Actualizar empresa (por ID)"),
    ELIMINAR_EMPRESA(4, "Eliminar empresa (por ID)"),
    MOSTRAR_DEPARTAMENTOS_Y_EMPLEADOS(5, "Mostrar todos los Departamentos de una Empresa y sus Empleados"),
    VOLVER(6, "Volver al Menú Principal");

    private final int codigo;
    private final String descripcion;

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

    public static EnumMenuEmpresas obtenerPorCodigo(int codigo) {
        for (EnumMenuEmpresas opcion : values()) {
            if (opcion.getCodigo() == codigo) {
                return opcion;
            }
        }
        return null;
    }

    public static void mostrarOpcionesMenu() {
        System.out.println("--- MENÚ DE EMPRESAS ---");
        for (EnumMenuEmpresas opcion : EnumMenuEmpresas.values()) {
            System.out.println(opcion.getCodigo() + ". " + opcion.getDescripcion());
        }
    }
}
