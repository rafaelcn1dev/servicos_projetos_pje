DO $$ 
    BEGIN
		IF NOT EXISTS
	 		(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_tipo_recurso' AND column_name = 'in_admissibilidade_recurso') 
        THEN
	        ALTER TABLE core.tb_tipo_recurso ADD COLUMN in_admissibilidade_recurso bool default false;
		END IF;
END $$;