DO $$
	BEGIN
	

		
	IF NOT EXISTS  (SELECT 1 
			 FROM core.tb_tipo_evento_criminal
           		 WHERE cd_tipo_evento_criminal  = 'SUS')
        THEN
		INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) VALUES(true, 'SUS', 'Suspensão do Processo', false);
    	END IF;
    	
    	IF NOT EXISTS  (SELECT 1 
			 FROM core.tb_tipo_evento_criminal
           		 WHERE cd_tipo_evento_criminal  = 'SCO')
        THEN
		INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) VALUES(true, 'SCO', 'Sentença Condenatória', false);
    	END IF;


IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core')
        THEN
        	create schema core;
    	END IF;


        IF NOT EXISTS
        (SELECT 1 FROM information_schema.tables
            WHERE table_schema  = 'core'
	            AND table_name  = 'tb_motivo_suspensao_processo')
        THEN

            CREATE SEQUENCE core.sq_tb_motivo_suspensao_processo
			  INCREMENT 1
			  MINVALUE 1
			  MAXVALUE 9223372036854775807
			  START 1
			  CACHE 1;

			CREATE TABLE core.tb_motivo_suspensao_processo
			(
			  id_motivo_suspensao integer NOT NULL DEFAULT nextval('core.sq_tb_motivo_suspensao_processo'::regclass),
			  ds_motivo_suspensao character varying(255) NOT NULL,
			  in_ativo boolean NOT NULL DEFAULT true,
			  CONSTRAINT tb_motivo_suspensao_processo_pkey PRIMARY KEY (id_motivo_suspensao)
			)
			WITH (
			  OIDS=FALSE
			);
END IF;

INSERT INTO core.tb_motivo_suspensao_processo (ds_motivo_suspensao) VALUES('Lei n. 9099/95');
INSERT INTO core.tb_motivo_suspensao_processo (ds_motivo_suspensao) VALUES('art. 366, CPP');

END $$;
