package org.example.repository;

import org.example.models.Empleado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmpleadoRepository {
    private final SessionFactory FACTORY;

    public EmpleadoRepository() {
        FACTORY = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Empleado.class)
                .buildSessionFactory();
    }

    public void guardarEmpleado(Empleado empleado) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(empleado);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

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

    public void cerrar() {
        FACTORY.close();
    }
}
