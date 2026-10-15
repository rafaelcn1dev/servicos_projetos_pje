DO $$
	BEGIN
	
    IF NOT EXISTS  (SELECT 1 
			 FROM core.tb_tipo_evento_criminal
           		 WHERE cd_tipo_evento_criminal  = 'SEI')
        THEN
		INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) VALUES(true, 'SEI', 'Sentença de Impronúncia', false);
    	END IF;

    IF NOT EXISTS  (SELECT 1 
			 FROM core.tb_tipo_evento_criminal
           		 WHERE cd_tipo_evento_criminal  = 'SPR')
        THEN
		INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) VALUES(true, 'SPR', 'Sentença de Pronúncia', false);
    	END IF;

	IF NOT EXISTS  (SELECT 1 
			 FROM core.tb_tipo_evento_criminal
           		 WHERE cd_tipo_evento_criminal  = 'DES')
        THEN
		INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) VALUES(true, 'DES', 'Desclassificação do Processo', false);
    	END IF;

END $$;

