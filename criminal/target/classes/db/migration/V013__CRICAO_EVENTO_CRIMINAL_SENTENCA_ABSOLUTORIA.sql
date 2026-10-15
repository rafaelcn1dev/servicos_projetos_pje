DO $$
	BEGIN
	
	IF NOT EXISTS  (SELECT 1 
			 FROM core.tb_tipo_evento_criminal
           		 WHERE cd_tipo_evento_criminal  = 'SAP')
        THEN
		INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) VALUES(true, 'SAP', 'Sentença Absolutória', false);
    	END IF;

END $$;
