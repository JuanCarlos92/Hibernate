package org.example.repository;

import org.example.models.Empleado;
import org.example.models.Empresa;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmpresaRepository {
    private final SessionFactory FACTORY;

    public EmpresaRepository() {
        FACTORY = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Empresa.class)
                .buildSessionFactory();
    }

    public void cerrar() {
        FACTORY.close();
    }
}
