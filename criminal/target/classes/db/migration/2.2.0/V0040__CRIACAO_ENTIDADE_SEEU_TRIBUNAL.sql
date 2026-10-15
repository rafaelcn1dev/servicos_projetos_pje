DO $$
BEGIN
	
	IF NOT EXISTS
	(SELECT 1 FROM information_schema.tables
		WHERE table_schema  = 'core'
			AND table_name  = 'tb_seeu_tribunal')
	THEN

		CREATE TABLE core.tb_seeu_tribunal (
			id_seeu_tribunal int4 NOT NULL,
			nr_cnpj varchar(14) NOT NULL,
			ds_tribunal varchar(255) NOT NULL,
			cd_seeu int4 NOT NULL,
			ds_identificador varchar(3) NOT NULL,
			cd_tribunal varchar(3) NOT NULL,
			ds_parte_autora_seeu varchar(100) NOT NULL,
			ds_municipio varchar(100) NOT NULL,
			ds_estado varchar(100) NOT NULL,
			ds_login_pje_mni varchar(30) NOT NULL,
			ds_senha_pje_mni varchar(25) NOT NULL,
			ds_url_pje_mni varchar(100) NOT NULL,
			ds_codigo_tipo_documento_pje varchar(20) NOT NULL,
			in_ativo bool NOT NULL DEFAULT TRUE,
			CONSTRAINT tb_seeu_tribunal_pk PRIMARY KEY (id_seeu_tribunal)
		);
		CREATE INDEX tb_seeu_tribunal_cd_seeu_idx ON core.tb_seeu_tribunal (cd_seeu);
		-- Column comments
		COMMENT ON COLUMN core.tb_seeu_tribunal.id_seeu_tribunal IS 'ID sequencial';
		COMMENT ON COLUMN core.tb_seeu_tribunal.cd_seeu IS 'Codigo do Tribunal no SEEU';
		COMMENT ON COLUMN core.tb_seeu_tribunal.ds_identificador IS 'Usuário associado a este Tribunal no SEEU para fins de entregas de manifestação processual via MNI';
		COMMENT ON COLUMN core.tb_seeu_tribunal.ds_municipio IS 'Campo obrigatóri para o SEEU';
		COMMENT ON COLUMN core.tb_seeu_tribunal.ds_estado IS 'Campo obrigatóri para o SEEU';
	END IF;

END $$;



