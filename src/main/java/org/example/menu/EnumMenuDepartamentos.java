package org.example.menu;

public enum EnumMenuDepartamentos {
    CREAR_DEPARTAMENTO(1, "Crear Departamento"),
    CONSULTAR_ID_DEPARTAMENTO(2, "Consultar Departamento (por ID)"),
    CONSULTAR_DEPARTAMENTOS(2, "Consultar todos los Departamentos"),
    ACTUALIZAR_DEPARTAMENTO(3, "Actualizar Departamento (por ID)"),
    ELIMINAR_DEPARTAMENTO(4, "Eliminar Departamento (por ID)"),
    MOSTRAR_EMPLEADOS_DEPARTAMENTO(5, "Mostrar todos los empleados asignadas a un Departamento en un Empresa"),
    VOLVER(6, "Volver al Menú Principal");

    private final int codigo;
    private final String descripcion;

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

    public static EnumMenuDepartamentos obtenerPorCodigo(int codigo) {
        for (EnumMenuDepartamentos opcion : values()) {
            if (opcion.getCodigo() == codigo) {
                return opcion;
            }
        }
        return null;
    }

    public static void mostrarOpcionesMenu() {
        System.out.println("--- MENÚ DE DEPARTAMENTOS ---");
        for (EnumMenuDepartamentos opcion : EnumMenuDepartamentos.values()) {
            System.out.println(opcion.getCodigo() + ". " + opcion.getDescripcion());
        }
    }
}

