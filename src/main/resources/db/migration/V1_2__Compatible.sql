    create table compartible (
        id bigint not null auto_increment,
        publico bit not null,
        primary key (id)
    ) ENGINE=InnoDB;

    ALTER TABLE `dieta`.`receta` ADD COLUMN `ids` VARCHAR(45) NULL  AFTER `modificado_por` ;

    alter table ingrediente 
    	add column id_compartible bigint null after id_unidad_de_medida_fk;

    alter table receta
        	add column id_compartible bigint null after pax;

    create table usuario_de_compartible (
        escritura bit not null,
        id_compartible_fk bigint,
        id_usuario_fk bigint,
        primary key (id_compartible_fk, id_usuario_fk)
    ) ENGINE=InnoDB;

    alter table ingrediente 
        add index Ingrediente2Compartible (id_compartible), 
        add constraint Ingrediente2Compartible 
        foreign key (id_compartible) 
        references compartible (id);

    alter table receta 
        add index Receta2Compartible (id_compartible), 
        add constraint Receta2Compartible 
        foreign key (id_compartible) 
        references compartible (id);

    alter table usuario_de_compartible 
        add index UsuarioDeCompartible2Compartible (id_compartible_fk), 
        add constraint UsuarioDeCompartible2Compartible 
        foreign key (id_compartible_fk) 
        references compartible (id);

    alter table usuario_de_compartible 
        add index UsuarioDeCompartible2Usuario (id_usuario_fk), 
        add constraint UsuarioDeCompartible2Usuario 
        foreign key (id_usuario_fk) 
        references usuario (id);
