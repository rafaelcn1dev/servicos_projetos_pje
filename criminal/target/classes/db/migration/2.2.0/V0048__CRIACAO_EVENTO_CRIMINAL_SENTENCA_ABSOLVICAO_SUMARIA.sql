DO $$
	BEGIN
	
	IF NOT EXISTS  (SELECT 1 
			 FROM core.tb_tipo_evento_criminal
           		 WHERE cd_tipo_evento_criminal  = 'SAS')
        THEN
			INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) VALUES(true, 'SAS', 'Sentença de absolvição sumária', false);
    	ELSE
    		UPDATE core.tb_tipo_evento_criminal SET in_ativo = true WHERE cd_tipo_evento_criminal = 'SAS';
		END IF;

END $$;
