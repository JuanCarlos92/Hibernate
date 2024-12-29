package org.example.repository;

import org.example.models.Departamento;
import org.example.models.Empleado;
import org.example.models.Empresa;
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
            session.beginTransaction();
            session.saveOrUpdate(empresa);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al guardar/actualizar la empresa: " + e.getMessage());
        }
    }

    //Obtener una empresa por ID
    public Empresa readEmpresaPorId(int id) {
        Empresa empresa = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            empresa = session.get(Empresa.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al obtener la empresa: " + e.getMessage());
        }
        return empresa;
    }

    //Obtener todas las empresas
    public List<Empresa> readTodasLasEmpresas() {
        List<Empresa> empresas = null;
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            empresas = session.createQuery("FROM Empresa", Empresa.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al obtener las empresas: " + e.getMessage());
        }
        return empresas;
    }

    //Actualizar empresa por ID
    public void updateEmpresaPorId(int id, Empresa empresa) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            empresa.setId(id);
            session.update(empresa);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al actualizar la empresa: " + e.getMessage());
        }
    }

    //Eliminar empresa por ID
    public void deleteEmpresaPorId(int id) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            Empresa empresa = session.get(Empresa.class, id);
            if (empresa != null) {
                session.delete(empresa);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al eliminar la empresa: " + e.getMessage());
        }
    }

    //Muestra los departamentos de la empresa y sus empleados.
    public void mostrarDepartamentosYEmpleados(int empresaId) {
        try (Session session = FACTORY.getCurrentSession()) {
            session.beginTransaction();
            Empresa empresa = session.get(Empresa.class, empresaId);

            //Si empresa es null...
            if (empresa == null) {
                System.out.println("No se encontró la empresa con ID: " + empresaId);
                return;
            }
            // Obtiene la lista de departamentos
            List<Departamento> departamentos = empresa.getDepartamentos();

            // Verifica si tiene departamentos
            if (departamentos == null || departamentos.isEmpty()) {
                System.out.println("La empresa '" + empresa.getNombre() + "' no tiene departamentos registrados.");
            } else {
                System.out.println("Departamentos de la empresa: " + empresa.getNombre());

                // Muestra cada departamento
                for (Departamento depto : departamentos) {
                    System.out.println("* Departamento: " + depto.getNombre());

                    // Lista de empleados en el departamento
                    List<Empleado> empleados = depto.getEmpleados();

                    // Verifica si el departamento no tiene empleados
                    if (empleados == null || empleados.isEmpty()) {
                        System.out.println("    Sin empleados registrados en este departamento.");
                    } else {
                        System.out.println(" -> Empleados en el departamento:");

                        // Muestra los datos de cada empleado
                        for (Empleado emp : empleados) {
                            System.out.println("    " + emp.getNombre() + " " + emp.getApellido() +
                                    " [Puesto: " + emp.getPuesto() + "]");
                        }
                    }
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al mostrar departamentos y empleados: " + e.getMessage());
        }
    }

    public void cerrar() {
        FACTORY.close();
    }
}
