    create table grupo_alimento (
        id bigint not null auto_increment,
        nombre varchar(255) not null unique,
        color varchar(255),
        descripcion varchar(255),
        creado datetime not null,
        creado_por bigint not null,
        modificado datetime,
        modificado_por bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table ingrediente (
        id bigint not null auto_increment,
        nombre varchar(255) not null unique,
        descripcion varchar(255),
        peso_por_racion decimal(25,8),
        coste decimal(25,8),
        foto longblob,
        id_grupo_alimento_fk bigint not null,
        id_unidad_de_medida_fk bigint,
        creado datetime not null,
        creado_por bigint not null,
        modificado datetime,
        modificado_por bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table ingrediente_de_receta (
        cantidad_por_comensal decimal(25,8) not null,
        id_receta_fk bigint,
        id_ingrediente_fk bigint,
        primary key (id_ingrediente_fk, id_receta_fk)
    ) ENGINE=InnoDB;

    create table receta (
        id bigint not null auto_increment,
        descripcion varchar(255),
        nombre varchar(255) not null unique,
        pax integer,
        creado datetime not null,
        creado_por bigint not null,
        modificado datetime,
        modificado_por bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table unidad_de_medida (
        id bigint not null auto_increment,
        abreviacion varchar(255) not null unique,
        descripcion varchar(255),
        nombre varchar(255) not null unique,
        creado datetime not null,
        creado_por bigint not null,
        modificado datetime,
        modificado_por bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table usuario (
        id bigint not null auto_increment,
        nombre varchar(255) not null,
        apellido1 varchar(255) not null unique,
        apellido2 varchar(255),
        email varchar(255) not null unique,
        login varchar(255) not null unique,
        pass varchar(255) not null unique,
        primary key (id)
    ) ENGINE=InnoDB;

    alter table ingrediente 
        add index Ingrediente2UnidadDeMedida (id_unidad_de_medida_fk), 
        add constraint Ingrediente2UnidadDeMedida 
        foreign key (id_unidad_de_medida_fk) 
        references unidad_de_medida (id);

    alter table ingrediente 
        add index Ingrediente2GrupoAlimento (id_grupo_alimento_fk), 
        add constraint Ingrediente2GrupoAlimento 
        foreign key (id_grupo_alimento_fk) 
        references grupo_alimento (id);

    alter table ingrediente_de_receta 
        add index IngredienteDeReceta2Receta (id_receta_fk), 
        add constraint IngredienteDeReceta2Receta 
        foreign key (id_receta_fk) 
        references receta (id);

    alter table ingrediente_de_receta 
        add index IngredienteDeReceta2Ingrediente (id_ingrediente_fk), 
        add constraint IngredienteDeReceta2Ingrediente 
        foreign key (id_ingrediente_fk) 
        references ingrediente (id);
