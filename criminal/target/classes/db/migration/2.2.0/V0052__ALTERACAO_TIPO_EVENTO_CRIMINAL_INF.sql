DO $$
	BEGIN	
	
	UPDATE core.tb_tipo_evento_criminal SET cd_tipo_processo = 'INF' WHERE cd_tipo_evento_criminal = 'REM';
	
END $$;