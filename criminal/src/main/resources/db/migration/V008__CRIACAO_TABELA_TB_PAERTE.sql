-- Criação da tabela tb_parte e inclusão de chave estrangeira em tb_informacao_criminal

DO $$ 
    BEGIN

	IF NOT EXISTS
		(SELECT 1 FROM information_schema.tables
		WHERE table_schema  = 'core'
	            AND table_name  = 'tb_parte')
		THEN	
			CREATE SEQUENCE core.sq_tb_parte
				INCREMENT 1
				MINVALUE 1
				MAXVALUE 9223372036854775807
				START 1
				CACHE 1;

			CREATE TABLE core.tb_parte
			(
				id_parte integer NOT NULL DEFAULT nextval('core.sq_tb_parte'::regclass),
				id_processo integer NOT NULL,
			  	in_situacao_parte character(1) NOT NULL DEFAULT 'A'::bpchar,
			  	id_processo_parte_legacy integer NOT NULL,
			  	id_pessoa_legacy integer NOT NULL,
			  	ds_rji character varying(100),
			  	CONSTRAINT tb_parte_pkey PRIMARY KEY (id_parte),
              	CONSTRAINT fk_tb_parte_tb_processo FOREIGN KEY (id_processo)
              		REFERENCES core.tb_processo (id_processo) MATCH SIMPLE
                	ON UPDATE NO ACTION ON DELETE NO ACTION
			)
			WITH (
			  	OIDS=FALSE
			);
	END IF;

	IF NOT EXISTS
		(SELECT 1 FROM information_schema.columns
		WHERE table_schema  = 'core'
	            AND table_name  = 'tb_informacao_criminal'
					AND column_name = 'id_parte')
	THEN
		ALTER TABLE core.tb_informacao_criminal ADD COLUMN id_parte integer;
		ALTER TABLE core.tb_informacao_criminal ADD 
			CONSTRAINT fk_tb_informacao_criminal_tb_parte 
			FOREIGN KEY (id_parte) REFERENCES core.tb_parte(id_parte);
	END IF;
END $$;
