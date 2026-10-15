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
	            AND table_name  = 'tb_municipio')
        THEN

            CREATE SEQUENCE core.sq_tb_municipio
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            START 1;

            CREATE TABLE core.tb_municipio (
	            id_municipio INTEGER NOT NULL DEFAULT nextval('core.sq_tb_municipio'::regclass),
	            ds_municipio varchar(50) NULL,
	            cd_municipio_ibge varchar(10) NULL,
	            cd_uf varchar(2) NULL,
	            CONSTRAINT tb_municio_pkey PRIMARY KEY (id_municipio)
            );
	    END IF;


        IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_tipo_origem')
        THEN

            CREATE SEQUENCE core.sq_tb_tipo_origem
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            START 1;

            CREATE TABLE core.tb_tipo_origem (
	            id_tipo_origem  INTEGER NOT NULL DEFAULT nextval('core.sq_tb_tipo_origem'::regclass),
	            ds_tipo_origem varchar(100) NOT NULL,
	            in_obrigatorio_numero_origem bool NOT NULL,
	            in_ativo bool NOT NULL,
	            CONSTRAINT tb_tipo_origem_pkey PRIMARY KEY (id_tipo_origem)
            );
	    END IF;


        IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_tp_procedimento_origem')
        THEN

            CREATE SEQUENCE core.sq_tb_tp_procedimento_origem
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            START 1;

            CREATE TABLE core.tb_tp_procedimento_origem (
	            id_tipo_procedimento_origem INTEGER NOT NULL DEFAULT nextval('core.sq_tb_tp_procedimento_origem'::regclass),
	            ds_tipo_procedimento varchar(100) NOT NULL,
	            in_ativo bool NOT NULL,
	            CONSTRAINT tb_tipo_procedimento_origem_pkey PRIMARY KEY (id_tipo_procedimento_origem)
            );

	    END IF;


        IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_tp_org_tp_proced_origem')
        THEN

                CREATE TABLE core.tb_tp_org_tp_proced_origem (
	                id_tipo_origem int4 NOT NULL,
	                id_tipo_procedimento_origem int4 NOT NULL,
	                CONSTRAINT tb_tipo_origem_tipo_procedimento_origem_pkey PRIMARY KEY (id_tipo_origem, id_tipo_procedimento_origem),
	                CONSTRAINT fk_tb_tipo_origem_tipo_procedimento_id_tipo_origem FOREIGN KEY (id_tipo_origem) REFERENCES core.tb_tipo_origem(id_tipo_origem),
	                CONSTRAINT fk_tb_tipo_origem_tipo_procedimento_id_tipo_procedimento_origem FOREIGN KEY (id_tipo_procedimento_origem) REFERENCES core.tb_tp_procedimento_origem  (id_tipo_procedimento_origem)
                );


	    END IF;

	    
        IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_org_prcdmnto_originario')
        THEN

            CREATE SEQUENCE core.sq_tb_org_prcdmnto_originario
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            START 1;


            CREATE TABLE core.tb_org_prcdmnto_originario (
	            id_org_procedimento_originario INTEGER NOT NULL DEFAULT nextval('core.sq_tb_org_prcdmnto_originario'::regclass),
	            id_tipo_origem int4 NOT NULL,
	            ds_cod_origem varchar(10) NULL,
	            ds_nome_orgao varchar(100) NOT NULL,
	            nm_logradouro varchar(200) NOT NULL,
	            nm_bairro varchar(100) NOT NULL,
	            ds_telefone varchar(10) NULL,
	            in_ativo bool NOT NULL,
	            ds_ddd varchar(2) NULL,
	            nm_numero varchar(10) NULL,
	            nm_complemento varchar(70) NULL,
	            id_municipio int4 NULL,
	            nr_cep varchar(9) NULL,
	            CONSTRAINT tb_orgao_procedimento_originario_pkey PRIMARY KEY (id_org_procedimento_originario),
	            CONSTRAINT tb_org_prcdmnto_originario_id_tipo_origem_fkey FOREIGN KEY (id_tipo_origem) REFERENCES core.tb_tipo_origem(id_tipo_origem),
	            CONSTRAINT tb_org_prcdmnto_originario_id_municipio_fkey FOREIGN KEY (id_municipio) REFERENCES core.tb_municipio(id_municipio)
            );

	    END IF;

		IF NOT EXISTS
		        (SELECT 1 FROM information_schema.tables
		            WHERE table_schema  = 'core'
			            AND table_name  = 'tb_processo')
		        THEN

            CREATE SEQUENCE core.sq_tb_processo
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            START 1;

            CREATE TABLE core.tb_processo (
	            id_processo INTEGER NOT NULL DEFAULT nextval('core.sq_tb_processo'::regclass),
	            nr_processo varchar NOT NULL,
	            dt_local_fato timestamp NULL,
	            ds_local_fato varchar(255) NOT NULL,
	            ds_longitude varchar(10) NULL,
	            ds_latitude varchar(10) NULL,
	            nr_protocolo_policia varchar(20) NULL,
	            id_municipio int4 NULL,
	            CONSTRAINT nr_processo_unico UNIQUE (nr_processo),
	            CONSTRAINT tb_processo_pkey PRIMARY KEY (id_processo),
	            CONSTRAINT tb_processo_id_municipio_fkey FOREIGN KEY (id_municipio) REFERENCES core.tb_municipio(id_municipio)
            );
	    END IF;


	    IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_processo_proc_origem')
        THEN


            CREATE SEQUENCE core.sq_processo_proc_origem
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            START 1;

            CREATE TABLE core.tb_processo_proc_origem (
	            id_processo_proc_origem INTEGER NOT NULL DEFAULT nextval('core.sq_processo_proc_origem'::regclass),
	            id_processo int4 NOT NULL,
	            id_org_procedimento_originario int4 NULL,
	            id_tipo_procedimento_origem int4 NULL,
	            dt_instauracao timestamp NULL,
	            nr_procedimento varchar(60) NULL,
	            nr_ano int4 NULL,
	            id_tipo_origem int4 NOT NULL,
	            in_ativo bool NOT NULL DEFAULT true,
	            dt_lavratura timestamp NULL,
	            CONSTRAINT pk_tb_processo_proc_origem PRIMARY KEY (id_processo_proc_origem),
	            CONSTRAINT fk_tb_processo_proc_origem_tb_org_proc_orig FOREIGN KEY (id_org_procedimento_originario) REFERENCES core.tb_org_prcdmnto_originario(id_org_procedimento_originario),
	            CONSTRAINT fk_tb_processo_proc_origem_tb_processo FOREIGN KEY (id_processo) REFERENCES core.tb_processo(id_processo),
	            CONSTRAINT fk_tb_processo_proc_origem_tb_tipo_proc_orig FOREIGN KEY (id_tipo_procedimento_origem) REFERENCES core.tb_tp_procedimento_origem(id_tipo_procedimento_origem),
	            CONSTRAINT tb_processo_proc_origem_fk FOREIGN KEY (id_tipo_origem) REFERENCES core.tb_tipo_origem(id_tipo_origem) ON UPDATE CASCADE ON DELETE RESTRICT
            );

	    END IF;
END $$;



