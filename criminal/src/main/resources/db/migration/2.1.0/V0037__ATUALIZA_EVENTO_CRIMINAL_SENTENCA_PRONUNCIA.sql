DO $$
	BEGIN
	
	IF EXISTS  (SELECT 1 
			 FROM core.tb_tipo_evento_criminal
           		 WHERE cd_tipo_evento_criminal  = 'SPR')
        THEN
            UPDATE core.tb_tipo_evento_criminal SET in_ativo = true WHERE cd_tipo_evento_criminal = 'SPR';
		END IF;

END $$;
