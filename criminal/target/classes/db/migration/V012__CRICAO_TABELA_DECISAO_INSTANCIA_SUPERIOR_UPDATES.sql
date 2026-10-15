DO $$
BEGIN
		IF NOT EXISTS
	          (SELECT 1 FROM information_schema.tables
	             WHERE table_schema  = 'core')
	           THEN
	        	create schema core;
	    	END IF;
		
	
	        IF NOT EXISTS
	        (SELECT 1 FROM information_schema.tables
	            WHERE table_schema  = 'core'
		            AND table_name  = 'tb_tipo_recurso')
	        THEN
	
	            CREATE SEQUENCE core.sq_tb_tipo_recurso
				  INCREMENT 1
				  MINVALUE 1
				  MAXVALUE 9223372036854775807
				  START 1
				  CACHE 1;
	
				CREATE TABLE core.tb_tipo_recurso
				(
				  id_tipo_recurso integer NOT NULL DEFAULT nextval('core.sq_tb_tipo_recurso'::regclass),
				  cod_tipo_recurso character varying(10) NOT NULL,
				  ds_tipo_recurso character varying(255) NOT NULL,
				  in_ativo boolean NOT NULL DEFAULT true,
				  CONSTRAINT tb_tipo_recurso_pkey PRIMARY KEY (id_tipo_recurso)
				)
				WITH (
				  OIDS=FALSE
				);
				
		IF NOT EXISTS
	          (SELECT 1 FROM core.tb_tipo_evento_criminal
	             WHERE cd_tipo_evento_criminal  = 'DIS')
	           THEN
			INSERT INTO core.tb_tipo_evento_criminal (id_tipo_evento_criminal, in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) VALUES(nextval('core.sq_tb_tipo_evento_criminal'), true, 'DIS', 'Decisão em Instância Superior', false);
	    	END IF;
	    	
	END IF;
	
		
	INSERT INTO core.tb_tipo_recurso (cod_tipo_recurso, ds_tipo_recurso, in_ativo) VALUES('DAS', 'Anulação de Sentença', true);
	INSERT INTO core.tb_tipo_recurso (cod_tipo_recurso, ds_tipo_recurso, in_ativo) VALUES('DAP', 'Absolvição', true);
	INSERT INTO core.tb_tipo_recurso (cod_tipo_recurso, ds_tipo_recurso, in_ativo) VALUES('DAI', 'Absolvição Imprópria', true);
	INSERT INTO core.tb_tipo_recurso (cod_tipo_recurso, ds_tipo_recurso, in_ativo) VALUES('DEP', 'Extinção da Punibilidade', true);
	INSERT INTO core.tb_tipo_recurso (cod_tipo_recurso, ds_tipo_recurso, in_ativo) VALUES('SCS', 'Sentença Condenatória', true);

END $$;

