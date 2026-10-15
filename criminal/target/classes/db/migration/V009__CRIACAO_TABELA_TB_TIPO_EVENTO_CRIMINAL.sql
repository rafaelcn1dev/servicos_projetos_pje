-- Criação das tabelas tb_tipo_evento_criminal e tb_tipo_ec_movimento

DO $$ 
    BEGIN

	IF NOT EXISTS
		(SELECT 1 FROM information_schema.tables
		WHERE table_schema  = 'core'
	            AND table_name  = 'tb_tipo_evento_criminal')
		THEN	
			CREATE SEQUENCE core.sq_tb_tipo_evento_criminal
				INCREMENT 1
				MINVALUE 1
				MAXVALUE 9223372036854775807
				START 1
				CACHE 1;

			CREATE TABLE core.tb_tipo_evento_criminal (
				id_tipo_evento_criminal integer NOT NULL DEFAULT nextval('core.sq_tb_tipo_evento_criminal'::regclass),
				in_ativo boolean NOT NULL DEFAULT true,
				cd_tipo_evento_criminal varchar(5) NOT NULL,
				ds_tipo_evento_criminal varchar(255) NOT NULL,
				in_exige_tipificacao_delito boolean NOT NULL DEFAULT false,
				PRIMARY KEY (id_tipo_evento_criminal),
				UNIQUE (cd_tipo_evento_criminal)
			) WITH (
			  	OIDS=FALSE
			);

	END IF;

	IF NOT EXISTS
		(SELECT 1 FROM information_schema.tables
		WHERE table_schema  = 'core'
	            AND table_name  = 'tb_tipo_ec_movimento')
		THEN	
			CREATE SEQUENCE core.sq_tb_tipo_ec_movimento
				INCREMENT 1
				MINVALUE 1
				MAXVALUE 9223372036854775807
				START 1
				CACHE 1;

		CREATE TABLE core.tb_tipo_ec_movimento (
			id_tipo_ec_movimento integer NOT NULL DEFAULT nextval('core.sq_tb_tipo_ec_movimento'::regclass),
			ds_cod_movimento varchar(5) NOT NULL,
			id_tipo_ec integer NULL,
			PRIMARY KEY (id_tipo_ec_movimento),
			UNIQUE (ds_cod_movimento),
			FOREIGN KEY (id_tipo_ec) REFERENCES core.tb_tipo_evento_criminal(id_tipo_evento_criminal)
		);
		
		alter table core.tb_tipo_ec_movimento 
		  add constraint uk_tb_tipo_ec_movimento unique (ds_cod_movimento);

	END IF;
END $$;