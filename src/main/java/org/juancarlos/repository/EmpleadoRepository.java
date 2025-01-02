package org.juancarlos.repository;

import org.juancarlos.models.Departamento;
import org.juancarlos.models.Empleado;
import org.juancarlos.models.Empresa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

//Repositorio que gestiona operaciones CRUD y consultas para la entidad {@link Empleado}.
public class EmpleadoRepository {
    private final SessionFactory FACTORY;

    //Constructor que inicializa la la configuración de Hibernate.
    public EmpleadoRepository() {
        FACTORY = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Empresa.class)
                .addAnnotatedClass(Departamento.class)
                .addAnnotatedClass(Empleado.class)
                .buildSessionFactory();
    }

    //Crea un empleado
    public void createEmpleado(Empleado empleado) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            session.saveOrUpdate(empleado); // Usa saveOrUpdate para insertar o actualizar.
            session.getTransaction().commit(); // Confirma los cambios en la BD.
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Obtiene un empleado por ID
    public Empleado readEmpleadoPorId(int id) {
        Empleado empleado = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            empleado = session.get(Empleado.class, id); // Obtiene el empleado mediante el ID
            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return empleado;
    }

    //Obtiene todos los empleados
    public List<Empleado> readTodosLosEmpleados() {
        List<Empleado> empleados = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            empleados = session.createQuery("FROM Empleado", Empleado.class).getResultList(); // Consulta obtener todos los empleados.
            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return empleados;
    }

    //Obtiene empleados de un departamento.
    public List<Empleado> readEmpleadosPorDepartamento(int departamentoId) {
        List<Empleado> empleados = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.

            // Consulta obtener todos los empleados de un depart
            empleados = session.createQuery(
                            "FROM Empleado e WHERE e.departamento.id = :depId", Empleado.class)
                    .setParameter("depId", departamentoId)
                    .getResultList();

            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return empleados;
    }

    //Actualiza un empleado por ID
    public void updateEmpleadoPorId(int id, Empleado empleado) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            empleado.setId(id); // Indicamos el id del empleado
            session.update(empleado); // Actualiza el empleado con ese id
            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Elimina un empleado por ID
    public void deleteEmpleadoPorId(int id) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            Empleado empleado = session.get(Empleado.class, id); // Obtiene el empleado mediante el ID
            if (empleado != null) { // Si no es  null > Elimina
                session.delete(empleado);
            }
            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cerrar() {
        FACTORY.close();
    }
}
