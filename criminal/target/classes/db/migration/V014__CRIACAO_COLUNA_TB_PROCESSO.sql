DO $$ 
    BEGIN
		IF NOT EXISTS
	 		(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_processo' AND column_name = 'ds_pje_origem') 
        THEN
	        ALTER TABLE core.tb_processo ADD COLUMN ds_pje_origem varchar(50) null;
	        ALTER TABLE audit.tb_processo_aud ADD COLUMN ds_pje_origem varchar(50) null;
			ALTER TABLE core.tb_processo DROP CONSTRAINT IF EXISTS nr_processo_unico;
			ALTER TABLE core.tb_processo ADD CONSTRAINT nr_processo_unico UNIQUE (nr_processo, ds_pje_origem);
		END IF;
END $$;
