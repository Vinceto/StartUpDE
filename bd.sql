CREATE TABLE usuarios (
      id SERIAL PRIMARY KEY,
      correo VARCHAR(255) NOT NULL,
      nick VARCHAR(50) NOT NULL,
      nombre VARCHAR(100) NOT NULL,
      password VARCHAR(255) NOT NULL,
      peso INT NOT NULL,
      created_at TIMESTAMP NOT NULL,
      updated_at TIMESTAMP NOT NULL
);

CREATE TABLE direcciones (
     id SERIAL PRIMARY KEY,
     nombre VARCHAR(255) NOT NULL,
     numeracion VARCHAR(50) NOT NULL,
     usuario_id INT NOT NULL,
     FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE roles (
   id SERIAL PRIMARY KEY,
   nombre VARCHAR(50) NOT NULL
);

CREATE TABLE roles_usuarios (
    usuario_id INT NOT NULL,
    rol_id INT NOT NULL,
    PRIMARY KEY (usuario_id, rol_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (rol_id) REFERENCES roles(id)
);

-- Insertar roles en la tabla roles
INSERT INTO roles (nombre) VALUES ('Admin'), ('User'), ('Manager'), ('Supervisor'), ('Developer'), ('Analyst');