package org.example.repository;

import org.example.models.Empleado;
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
                .addAnnotatedClass(Empleado.class)
                .buildSessionFactory();
    }

    //Crea un empleado
    public void guardarEmpleado(Empleado empleado) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(empleado);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Obtiene un empleado por ID
    public Empleado obtenerEmpleadoPorId(int id) {
        Empleado empleado = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            empleado = session.get(Empleado.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return empleado;
    }

    //Obtiene todos los empleados
    public List<Empleado> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            empleados = session.createQuery("FROM Empleado", Empleado.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return empleados;
    }

    //Obtiene empleados de un departamento.
    public List<Empleado> obtenerEmpleadosPorDepartamento(int departamentoId) {
        List<Empleado> empleados = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            empleados = session.createQuery(
                            "FROM Empleado e WHERE e.departamento.id = :depId", Empleado.class)
                    .setParameter("depId", departamentoId)
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return empleados;
    }

    //Actualiza un empleado por ID
    public void actualizarEmpleadoPorId(int id, Empleado empleado) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            empleado.setId(id);
            session.update(empleado);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Elimina un empleado por ID
    public void eliminarEmpleadoPorId(int id) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            Empleado empleado = session.get(Empleado.class, id);
            if (empleado != null) {
                session.delete(empleado);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cerrar() {
        FACTORY.close();
    }
}
