DO $$ 
    BEGIN
	    
	ALTER TABLE core.tb_tipo_ec_movimento ALTER COLUMN ds_cod_movimento TYPE varchar(30) USING ds_cod_movimento::varchar;	
	    
END $$;