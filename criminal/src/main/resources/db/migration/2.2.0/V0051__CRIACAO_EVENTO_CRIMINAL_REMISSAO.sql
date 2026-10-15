DO $$
	BEGIN
	
		IF NOT EXISTS  (SELECT 1 
			 FROM core.tb_tipo_evento_criminal
           		 WHERE cd_tipo_evento_criminal  = 'REM')
        THEN
			INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) VALUES(true, 'REM', 'Remissão', false);
    	ELSE
            UPDATE core.tb_tipo_evento_criminal SET in_ativo = true WHERE cd_tipo_evento_criminal = 'REM';
        END IF;
        
        IF NOT EXISTS
 		(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_tipo_remissao') 
        then
        
	        CREATE SEQUENCE core.sq_tb_tipo_remissao
				  INCREMENT 1
				  MINVALUE 1
				  MAXVALUE 9223372036854775807
				  START 1
				  CACHE 1;
	        
	        CREATE TABLE core.tb_tipo_remissao (
				id_tipo_remissao integer NOT NULL DEFAULT nextval('core.sq_tb_tipo_remissao'::regclass),
				cd_tipo_remissao varchar(3) NOT NULL,
				ds_tipo_remissao varchar(255) NOT NULL,
				in_ativo bool NOT NULL DEFAULT true,
				CONSTRAINT tb_tipo_remissao_pkey PRIMARY KEY (id_tipo_remissao)
			);
		
			INSERT INTO core.tb_tipo_remissao (cd_tipo_remissao, ds_tipo_remissao) values ('EXC', 'Exclusão');
			INSERT INTO core.tb_tipo_remissao (cd_tipo_remissao, ds_tipo_remissao) values ('SUS', 'Suspensão');
			INSERT INTO core.tb_tipo_remissao (cd_tipo_remissao, ds_tipo_remissao) values ('EXP', 'Extinção do Processo');
        
        END IF;

END $$;
