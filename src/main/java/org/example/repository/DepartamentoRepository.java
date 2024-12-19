package org.example.repository;

import org.example.models.Departamento;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DepartamentoRepository {
    private final SessionFactory FACTORY;

    public DepartamentoRepository() {
        FACTORY = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Departamento.class)
                .buildSessionFactory();
    }

    public void cerrar() {
        FACTORY.close();
    }
}
