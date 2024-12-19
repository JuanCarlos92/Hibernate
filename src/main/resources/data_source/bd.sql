-- Tabla Empresa
CREATE TABLE Empresa
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    industria VARCHAR(100) NOT NULL
);

-- Tabla Departamento
CREATE TABLE Departamento
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(100) NOT NULL,
    empresa_id INT          NOT NULL,
    FOREIGN KEY (empresa_id) REFERENCES Empresa (id) ON DELETE CASCADE
);

-- Tabla Empleado
CREATE TABLE Empleado
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(100) NOT NULL,
    apellido        VARCHAR(100) NOT NULL,
    puesto          VARCHAR(100) NOT NULL,
    departamento_id INT          NOT NULL,
    FOREIGN KEY (departamento_id) REFERENCES Departamento (id) ON DELETE CASCADE
);

-- Insertar datos en Empresa
INSERT INTO Empresa (nombre, industria)
VALUES ('Tech Solutions', 'Tecnología'),
       ('Green Fields', 'Agricultura');

-- Insertar datos en Departamento
INSERT INTO Departamento (nombre, empresa_id)
VALUES ('Desarrollo', 1),
       ('Soporte Técnico', 1),
       ('Cultivo', 2);

-- Insertar datos en Empleado
INSERT INTO Empleado (nombre, apellido, puesto, departamento_id)
VALUES ('Juan', 'Pérez', 'Desarrollador', 1),
       ('Ana', 'López', 'Tester', 1),
       ('Carlos', 'Martínez', 'Soporte', 2),
       ('Laura', 'Gómez', 'Técnica', 2),
       ('Luis', 'Hernández', 'Agrónomo', 3);