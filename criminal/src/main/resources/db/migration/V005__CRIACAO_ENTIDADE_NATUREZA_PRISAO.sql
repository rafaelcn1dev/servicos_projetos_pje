DO $$ 
    BEGIN
        
		IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_natureza_prisao')
		THEN

			CREATE SEQUENCE core.sq_tb_natureza_prisao
			INCREMENT 1
			MINVALUE 1
			MAXVALUE 9223372036854775807
			START 1
			CACHE 1;

            CREATE TABLE core.tb_natureza_prisao (
	            id_natureza_prisao integer NOT NULL DEFAULT nextval('core.sq_tb_natureza_prisao'::regclass),
	            ds_natureza_prisao varchar(255) NOT NULL,
	            in_ativo bool NOT NULL DEFAULT true,
	            CONSTRAINT tb_natureza_prisao_pkey PRIMARY KEY (id_natureza_prisao)
            )
            WITH (
	            OIDS=FALSE
            ) ;

		END IF;
END $$;
