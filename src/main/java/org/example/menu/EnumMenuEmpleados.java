package org.example.menu;

public enum EnumMenuEmpleados {
    CREAR_EMPLEADO(1, "Crear Empleado"),
    CONSULTAR_ID_EMPLEADO(2, "Consultar Empleado (por ID)"),
    CONSULTAR_EMPLEADOS(2, "Consultar todos los Empleados"),
    ACTUALIZAR_EMPLEADO(3, "Actualizar Empleado (por ID)"),
    ELIMINAR_EMPLEADO(4, "Eliminar Empleado (por ID)"),
    VOLVER(5, "Volver al Menú Principal");

    private final int codigo;
    private final String descripcion;

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

    public static EnumMenuEmpleados obtenerPorCodigo(int codigo) {
        for (EnumMenuEmpleados opcion : values()) {
            if (opcion.getCodigo() == codigo) {
                return opcion;
            }
        }
        return null;
    }

    public static void mostrarOpcionesMenu() {
        System.out.println("--- MENÚ DE EMPLEADOS ---");
        for (EnumMenuEmpleados opcion : EnumMenuEmpleados.values()) {
            System.out.println(opcion.getCodigo() + ". " + opcion.getDescripcion());
        }
    }
}

