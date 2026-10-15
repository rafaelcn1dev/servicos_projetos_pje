do $$
begin
	IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_tipo_dispositivo' AND column_name = 'cd_tipo_dispositivo')
	THEN
		ALTER TABLE core.tb_tipo_dispositivo ADD COLUMN cd_tipo_dispositivo varchar(20);	
		update core.tb_tipo_dispositivo t set cd_tipo_dispositivo = 'ARTIGO'  where UPPER(t.ds_tipo_dispositivo) = UPPER('Artigo');
		update core.tb_tipo_dispositivo t set cd_tipo_dispositivo = 'INCISO'  where UPPER(t.ds_tipo_dispositivo) = UPPER('inciso');
		update core.tb_tipo_dispositivo t set cd_tipo_dispositivo = 'PARAGRAFO'  where UPPER(t.ds_tipo_dispositivo) = UPPER('Parágrafo');
	update core.tb_tipo_dispositivo t set cd_tipo_dispositivo = 'ALINEA'  where UPPER(t.ds_tipo_dispositivo) = UPPER('Alínea');
	end if;
end
$$;
