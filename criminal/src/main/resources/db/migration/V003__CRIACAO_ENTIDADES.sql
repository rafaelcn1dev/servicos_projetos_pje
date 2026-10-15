DO $$ 
    BEGIN
        
		IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_informacao_criminal')
		THEN

			CREATE SEQUENCE core.sq_tb_informacao_criminal
			INCREMENT 1
			MINVALUE 1
			MAXVALUE 9223372036854775807
			START 1
			CACHE 1;

			CREATE TABLE core.tb_informacao_criminal
			(
			id_informacao_criminal integer NOT NULL DEFAULT nextval('core.sq_tb_informacao_criminal'::regclass),
			id_processo integer NOT NULL,
			ds_conteudo jsonb NOT NULL,
			CONSTRAINT tb_informacao_criminal_pkey PRIMARY KEY (id_informacao_criminal),
			CONSTRAINT fk_tb_informacao_criminal_tb_processo FOREIGN KEY (id_processo)
				REFERENCES core.tb_processo (id_processo) MATCH SIMPLE
				ON UPDATE NO ACTION ON DELETE NO ACTION
			)
			WITH (
			OIDS=FALSE
			);

		END IF;

		IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_tipo_norma')
		THEN

			CREATE SEQUENCE core.sq_tb_tipo_norma
			INCREMENT 1
			MINVALUE 1
			MAXVALUE 9223372036854775807
			START 1
			CACHE 1;


			CREATE TABLE core.tb_tipo_norma
			(
			id_tipo_norma integer NOT NULL DEFAULT nextval('core.sq_tb_tipo_norma'::regclass),
			ds_tipo_norma character varying(100),
			in_ativo boolean,
			CONSTRAINT tb_tipo_norma_pkey PRIMARY KEY (id_tipo_norma)
			)
			WITH (
			OIDS=FALSE
			);

		END IF;

		IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_norma')
		THEN

			CREATE SEQUENCE core.sq_tb_norma
			INCREMENT 1
			MINVALUE 1
			MAXVALUE 9223372036854775807
			START 1
			CACHE 1;

			CREATE TABLE core.tb_norma
			(
			id_norma integer NOT NULL DEFAULT nextval('core.sq_tb_norma'::regclass),
			nr_norma integer NOT NULL,
			nm_norma character varying(100) NOT NULL,
			ds_sigla character varying(10),
			dt_inicio_vigencia date NOT NULL,
			dt_fim_vigencia date,
			id_tipo_norma integer NOT NULL,
			in_ativo boolean NOT NULL DEFAULT true,
			CONSTRAINT tb_norma_pkey PRIMARY KEY (id_norma),
			CONSTRAINT fk_tb_norma_to_tb_tipo_norma FOREIGN KEY (id_tipo_norma)
				REFERENCES core.tb_tipo_norma (id_tipo_norma) MATCH SIMPLE
				ON UPDATE NO ACTION ON DELETE NO ACTION
			)
			WITH (
			OIDS=FALSE
			);    

		END IF;

		IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_tipo_dispositivo')
		THEN
		
			CREATE SEQUENCE core.sq_tb_tipo_dispositivo
			INCREMENT 1
			MINVALUE 1
			MAXVALUE 9223372036854775807
			START 1
			CACHE 1;

			CREATE TABLE core.tb_tipo_dispositivo
			(
			id_tipo_dispositivo integer NOT NULL DEFAULT nextval('core.sq_tb_tipo_dispositivo'::regclass),
			ds_tipo_dispositivo character varying(100) NOT NULL,
			in_ativo boolean NOT NULL,
			CONSTRAINT tb_tipo_dispositivo_pkey PRIMARY KEY (id_tipo_dispositivo)
			)
			WITH (
			OIDS=FALSE
			);

		END IF;

		IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_dispositivo')
		THEN

			CREATE SEQUENCE core.sq_tb_dispositivo
			INCREMENT 1
			MINVALUE 1
			MAXVALUE 9223372036854775807
			START 1
			CACHE 1;

			CREATE TABLE core.tb_dispositivo
			(
			id_dispositivo integer NOT NULL DEFAULT nextval('core.sq_tb_dispositivo'::regclass),
			id_tipo_dispositivo integer NOT NULL,
			id_norma integer NOT NULL,
			id_dispositivo_pai integer,
			ds_simbolo character varying(20),
			ds_identificador character varying(100) NOT NULL,
			ds_texto text NOT NULL,
			dt_inicio_vigencia date NOT NULL,
			dt_fim_vigencia date,
			in_ativo boolean NOT NULL DEFAULT true,
			CONSTRAINT tb_dispositivo_pkey PRIMARY KEY (id_dispositivo),
			CONSTRAINT fk_tb_dispositivo_to_tb_norma FOREIGN KEY (id_norma)
				REFERENCES core.tb_norma (id_norma) MATCH SIMPLE
				ON UPDATE NO ACTION ON DELETE NO ACTION,
			CONSTRAINT fk_tb_dispositivo_to_tb_tipo_dispositivo FOREIGN KEY (id_tipo_dispositivo)
				REFERENCES core.tb_tipo_dispositivo (id_tipo_dispositivo) MATCH SIMPLE
				ON UPDATE NO ACTION ON DELETE NO ACTION,
			CONSTRAINT fk_tb_dispositivo_to_tb_tipo_dispositivo_pai FOREIGN KEY (id_dispositivo_pai)
				REFERENCES core.tb_dispositivo (id_dispositivo) MATCH SIMPLE
				ON UPDATE NO ACTION ON DELETE NO ACTION
			)
			WITH (
			OIDS=FALSE
			);

		END IF;

END $$;