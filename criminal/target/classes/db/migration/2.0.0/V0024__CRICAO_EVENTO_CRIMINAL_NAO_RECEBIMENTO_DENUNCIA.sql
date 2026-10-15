DO $$
	BEGIN
	
	IF NOT EXISTS  (SELECT 1 
			 FROM core.tb_tipo_evento_criminal
           		 WHERE cd_tipo_evento_criminal  = 'NRD')
        THEN
		INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) VALUES(true, 'NRD', 'Não Recebimento da Denúncia', false);
		INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) VALUES(true, 'CIT', 'Citação', false);
		INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) VALUES(true, 'RAD', 'Recebimento do Aditamento da Denúncia', false);
    	END IF;

END $$;
