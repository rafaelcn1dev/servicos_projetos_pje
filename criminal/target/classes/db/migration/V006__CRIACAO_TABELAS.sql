DO $$ 
    BEGIN
        
	IF NOT EXISTS
		(SELECT 1 FROM information_schema.tables
		WHERE table_schema  = 'core'
	            AND table_name  = 'tb_tipo_prisao')
		THEN
			CREATE SEQUENCE core.sq_tb_tipo_prisao
			  INCREMENT 1
			  MINVALUE 1
			  MAXVALUE 9223372036854775807
			  START 1
			  CACHE 1;

			CREATE TABLE core.tb_tipo_prisao
			(
			  id_tipo_prisao integer NOT NULL DEFAULT nextval('core.sq_tb_tipo_prisao'::regclass),
			  ds_tipo_prisao character varying(255) NOT NULL,
			  in_ativo boolean NOT NULL DEFAULT true,
			  CONSTRAINT tb_tipo_prisao_pkey PRIMARY KEY (id_tipo_prisao)
			)
			WITH (
			  OIDS=FALSE
			);

			INSERT INTO core.tb_tipo_prisao (ds_tipo_prisao) VALUES('Preventiva'); 
			INSERT INTO core.tb_tipo_prisao (ds_tipo_prisao) VALUES('Preventiva determinada ou mantida em decisão condenatória recorrível'); 
			INSERT INTO core.tb_tipo_prisao (ds_tipo_prisao) VALUES('Temporária'); 
			INSERT INTO core.tb_tipo_prisao (ds_tipo_prisao) VALUES('Flagrante'); 
			INSERT INTO core.tb_tipo_prisao (ds_tipo_prisao) VALUES('Provisória'); 
			INSERT INTO core.tb_tipo_prisao (ds_tipo_prisao) VALUES('Para fins de deportação'); 
			INSERT INTO core.tb_tipo_prisao (ds_tipo_prisao) VALUES('Para fins de expulsão'); 
			INSERT INTO core.tb_tipo_prisao (ds_tipo_prisao) VALUES('Para fins de extradição'); 
			INSERT INTO core.tb_tipo_prisao (ds_tipo_prisao) VALUES('Definitiva');
	END IF;
		
	IF NOT EXISTS
		(SELECT 1 FROM information_schema.tables
		WHERE table_schema  = 'core'
	            AND table_name  = 'tb_motivo_prisao')
		THEN		

			CREATE SEQUENCE core.sq_tb_motivo_prisao
			  INCREMENT 1
			  MINVALUE 1
			  MAXVALUE 9223372036854775807
			  START 1
			  CACHE 1;

			CREATE TABLE core.tb_motivo_prisao
			(
			  id_motivo_prisao integer NOT NULL DEFAULT nextval('core.sq_tb_motivo_prisao'::regclass),
			  ds_motivo_prisao character varying(255) NOT NULL,
			  in_ativo boolean NOT NULL DEFAULT true,
			  CONSTRAINT tb_motivo_prisao_pkey PRIMARY KEY (id_motivo_prisao)
			)
			WITH (
			  OIDS=FALSE
			);

			INSERT INTO core.tb_motivo_prisao (ds_motivo_prisao) VALUES('Prisão em flagrante');
			INSERT INTO core.tb_motivo_prisao (ds_motivo_prisao) VALUES('Recomendação de prisão');
			INSERT INTO core.tb_motivo_prisao (ds_motivo_prisao) VALUES('Manutenção de prisão');
	END IF;
		
	IF NOT EXISTS
		(SELECT 1 FROM information_schema.tables
		WHERE table_schema  = 'core'
	            AND table_name  = 'tb_tipo_soltura')
		THEN	
			CREATE SEQUENCE core.sq_tb_tipo_soltura
			  INCREMENT 1
			  MINVALUE 1
			  MAXVALUE 9223372036854775807
			  START 1
			  CACHE 1;

			CREATE TABLE core.tb_tipo_soltura
			(
			  id_tipo_soltura integer NOT NULL DEFAULT nextval('core.sq_tb_tipo_soltura'::regclass),
			  ds_tipo_soltura character varying(255) NOT NULL,
			  in_ativo boolean NOT NULL DEFAULT true,
			  CONSTRAINT tb_tipo_soltura_pkey PRIMARY KEY (id_tipo_soltura)
			)
			WITH (
			  OIDS=FALSE
			);

			INSERT INTO core.tb_tipo_soltura (ds_tipo_soltura) VALUES('Relaxamento'); 
			INSERT INTO core.tb_tipo_soltura (ds_tipo_soltura) VALUES('Revogação da Prisão'); 
			INSERT INTO core.tb_tipo_soltura (ds_tipo_soltura) VALUES('Final do Prazo da Temporária'); 
			INSERT INTO core.tb_tipo_soltura (ds_tipo_soltura) VALUES('Liberdade Provisória'); 
			INSERT INTO core.tb_tipo_soltura (ds_tipo_soltura) VALUES('Fiança');
	END IF;

	IF NOT EXISTS
		(SELECT 1 FROM information_schema.tables
		WHERE table_schema  = 'core'
	            AND table_name  = 'tb_motivo_soltura')
		THEN	
			CREATE SEQUENCE core.sq_tb_motivo_soltura
			  INCREMENT 1
			  MINVALUE 1
			  MAXVALUE 9223372036854775807
			  START 1
			  CACHE 1;

			CREATE TABLE core.tb_motivo_soltura
			(
			  id_motivo_soltura integer NOT NULL DEFAULT nextval('core.sq_tb_motivo_soltura'::regclass),
			  ds_motivo_soltura character varying(255) NOT NULL,
			  in_ativo boolean NOT NULL DEFAULT true,
			  CONSTRAINT tb_motivo_soltura_pkey PRIMARY KEY (id_motivo_soltura)
			)
			WITH (
			  OIDS=FALSE
			);

			INSERT INTO core.tb_motivo_soltura (ds_motivo_soltura) VALUES ('Fiança');
			INSERT INTO core.tb_motivo_soltura (ds_motivo_soltura) VALUES ('Relaxamento de prisão');
			INSERT INTO core.tb_motivo_soltura (ds_motivo_soltura) VALUES ('Liberdade provisória com fiança');
			INSERT INTO core.tb_motivo_soltura (ds_motivo_soltura) VALUES ('Liberdade provisória sem fiança');
			INSERT INTO core.tb_motivo_soltura (ds_motivo_soltura) VALUES ('Revogação da prisão');
			INSERT INTO core.tb_motivo_soltura (ds_motivo_soltura) VALUES ('Habeas corpus');
	END IF;
END $$;