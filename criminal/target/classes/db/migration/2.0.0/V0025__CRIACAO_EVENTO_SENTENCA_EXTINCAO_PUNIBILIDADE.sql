DO $$ 
    BEGIN
	    
	IF NOT EXISTS
 		(SELECT 1 FROM core.tb_tipo_evento_criminal WHERE cd_tipo_evento_criminal = 'SEP') 
        THEN
		
        	insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) values (true, 'SEP', 'Sentença de Extinção de Punibilidade', false);    
	
	END IF; 
	    
END $$;