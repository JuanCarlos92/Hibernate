# Gestión de Empresas, Departamentos y Empleados

## Objetivo
Desarrollar una aplicación Java que gestione empresas, sus departamentos y los empleados asignados a estos, utilizando relaciones one-to-many y many-to-one.

## Tecnologías Utilizadas
- Java
- Hibernate
- MySQL
- Maven

## Requisitos del Ejercicio

### Definición de Entidades
- **Empresa**: id, nombre, industria.
- **Departamento**: id, nombre, empresa.
- **Empleado**: id, nombre, apellido, puesto, departamento.

### Relaciones
- **One-to-Many**: Empresa → Departamento (una empresa puede tener varios departamentos).
- **Many-to-One**: Empleado → Departamento (un empleado pertenece a un único departamento).

### CRUD para cada entidad:
- Crear un nuevo registro.
- Leer un registro por su identificador.
- Actualizar un registro existente.
- Eliminar un registro existente (asegurando la integridad referencial).

### Datos Iniciales Requeridos
- Al menos **dos empresas**.
- Al menos **tres departamentos**.
- Al menos **cinco empleados**.

### Funcionalidades Adicionales
- Consultar todos los empleados de un departamento.
- Mostrar los departamentos de una empresa y el número de empleados en cada uno.
- Manejo de excepciones y consistencia de datos.

## Instalación y Configuración

1. **Clonar el repositorio**
   ```sh
   git clone https://github.com/JuanCarlos92/Hibernate.git
   ```

2. **Configurar la base de datos** (MySQL o H2)
   - Para MySQL, crea una base de datos y actualiza el archivo `hibernate.cfg.xml` con las credenciales adecuadas.
   
