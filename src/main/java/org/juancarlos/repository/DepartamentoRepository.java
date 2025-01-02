package org.juancarlos.repository;

import org.juancarlos.models.Departamento;
import org.juancarlos.models.Empleado;
import org.juancarlos.models.Empresa;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

//Repositorio que gestiona operaciones CRUD y consultas para la entidad {@link Departamento}.
public class DepartamentoRepository {
    private final SessionFactory FACTORY;

    //Constructor que inicializa la la configuración de Hibernate.
    public DepartamentoRepository() {
        FACTORY = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Empresa.class)
                .addAnnotatedClass(Departamento.class)
                .addAnnotatedClass(Empleado.class)
                .buildSessionFactory();
    }

    //Crea un Departamento
    public void createDepartamento(Departamento departamento) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            session.saveOrUpdate(departamento); // Usa saveOrUpdate para insertar o actualizar.
            session.getTransaction().commit(); // Confirma los cambios en la BD.
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Obtiene un departamento por ID
    public Departamento readDepartamentoPorId(int id) {
        Departamento departamento = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            departamento = session.get(Departamento.class, id); // Obtiene el depart mediante el ID
            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return departamento;
    }

    //Obtiene todos los departamentos
    public List<Departamento> readTodosLosDepartamentos() {
        List<Departamento> departamentos = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            departamentos = session.createQuery("FROM Departamento", Departamento.class).getResultList(); // Consulta obtener todos los depart.
            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return departamentos;
    }

    //Obtiene departamentos de una empresa.
    public List<Departamento> readDepartamentosPorEmpresa(int empresaId) {
        List<Departamento> departamentos = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.

            // Consulta obtener todos los depart de una empresa
            departamentos = session.createQuery(
                            "FROM Departamento d WHERE d.empresa.id = :empId", Departamento.class)
                    .setParameter("empId", empresaId)
                    .getResultList();

            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return departamentos;
    }

    //Actualiza un departamento por ID
    public void updateDepartamentoPorId(int id, Departamento departamento) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            departamento.setId(id); // Indicamos el id del depart
            session.update(departamento); // Actualiza el depart con ese id
            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Elimina un departamento por ID
    public void deleteDepartamentoPorId(int id) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            Departamento departamento = session.get(Departamento.class, id);
            if (departamento != null) {
                session.delete(departamento);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Muestra todos los empleados asignados a un departamento.
    public Departamento obtenerDepartamentoConEmpleados(int departamentoId) {
        Departamento departamento = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction(); // Inicia la transacción.
            departamento = session.get(Departamento.class, departamentoId); // Obtiene el depart mediante el ID.

            // Si no es null, forzamos la inicialización.
            if (departamento != null) {
                Hibernate.initialize(departamento.getEmpleados());
            }

            session.getTransaction().commit(); // Corfirma los cambios en la BD
        } catch (Exception e) {
            System.out.println("Error al obtener el departamento con empleados: " + e.getMessage());
        }
        return departamento;
    }

    public void cerrar() {
        FACTORY.close();
    }
}
