package org.juancarlos.repository;

import org.juancarlos.models.Departamento;
import org.juancarlos.models.Empleado;
import org.juancarlos.models.Empresa;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmpresaRepository {
    private final SessionFactory FACTORY;

    public EmpresaRepository() {
        FACTORY = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Empresa.class)
                .addAnnotatedClass(Departamento.class)
                .addAnnotatedClass(Empleado.class)
                .buildSessionFactory();
    }

    //Crear una empresa
    public void createEmpresa(Empresa empresa) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();  // Inicia la transacción.
            session.saveOrUpdate(empresa);  // Usa saveOrUpdate para insertar o actualizar.
            session.getTransaction().commit(); // Confirma los cambios en la BD.
        } catch (Exception e) {
            System.out.println("Error al guardar/actualizar la empresa: " + e.getMessage());
        }
    }

    //Obtener una empresa por ID
    public Empresa readEmpresaPorId(int id) {
        Empresa empresa = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            empresa = session.get(Empresa.class, id); // Obtiene la empresa mediante el ID
            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error al obtener la empresa: " + e.getMessage());
        }
        return empresa;
    }

    //Obtener todas las empresas
    public List<Empresa> readTodasLasEmpresas() {
        List<Empresa> empresas = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            empresas = session.createQuery("FROM Empresa", Empresa.class).getResultList(); // Consulta obtener todas las empresas
            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error al obtener las empresas: " + e.getMessage());
        }
        return empresas;
    }

    //Actualizar empresa por ID
    public void updateEmpresaPorId(int id, Empresa empresa) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            empresa.setId(id); // Indicamos el id de la empresa
            session.update(empresa); // Actualiza la empresa con ese id
            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error al actualizar la empresa: " + e.getMessage());
        }
    }

    //Eliminar empresa por ID
    public void deleteEmpresaPorId(int id) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            Empresa empresa = session.get(Empresa.class, id); // Obtiene la empresa mediante el ID
            if (empresa != null) { // Si no es  null > Elimina
                session.delete(empresa);
            }
            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error al eliminar la empresa: " + e.getMessage());
        }
    }

    //Muestra los departamentos de la empresa y sus empleados.
    public Empresa obtenerEmpresaConDepartamentosYEmpleados(int empresaId) {
        Empresa empresa = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            empresa = session.get(Empresa.class, empresaId); // Obtiene la empresa mediante el ID.

            // Si no es null, forzamos la inicialización de la colección 'departamentos'.
            if (empresa != null && empresa.getDepartamentos() != null) {
                Hibernate.initialize(empresa.getDepartamentos());

                // Y para cada departamento, si también necesitas empleados:
                for (Departamento depto : empresa.getDepartamentos()) {
                    if (depto.getEmpleados() != null) {
                        Hibernate.initialize(depto.getEmpleados());
                    }
                }

            }
            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error al obtener la empresa con sus departamentos y empleados: " + e.getMessage());
        }
        return empresa;
    }

    public void cerrar() {
        FACTORY.close();
    }
}
