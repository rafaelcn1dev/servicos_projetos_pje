DO $$ 
    BEGIN
        
		IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_unidade_prisional')
		THEN

			CREATE SEQUENCE core.sq_tb_unidade_prisional
			INCREMENT 1
			MINVALUE 1
			MAXVALUE 9223372036854775807
			START 1
			CACHE 1;

            CREATE TABLE core.tb_unidade_prisional (
	            id_unidade_prisional integer NOT NULL DEFAULT nextval('core.sq_tb_unidade_prisional'::regclass),
	            ds_unidade_prisional varchar(255) NOT NULL,
	            ds_apelido_unidade varchar(100) NULL,
	            ds_jurisdicao varchar(100) NOT NULL,
	            ds_email varchar(255) NULL,
	            nr_fax varchar(13) NULL,
	            nr_telefone varchar(13) NULL,
	            nr_cep varchar(9) NOT NULL,
	            id_municipio int4 NULL,
	            nm_bairro varchar(100) NOT NULL,
	            nm_logradouro varchar(200) NOT NULL,
	            in_ativo boolean NOT NULL,
	            CONSTRAINT tb_unidade_prisional_pkey PRIMARY KEY (id_unidade_prisional),
	            CONSTRAINT fk_tb_unidade_prisional_id_municipio FOREIGN KEY (id_municipio) REFERENCES core.tb_municipio(id_municipio)
            )			
            WITH (
	            OIDS=FALSE
	            );


		END IF;

END $$;
