/**
* Cria colunas para controle de multi tenancy das tabelas tb_processo, tb_unidade_prisional, tb_tp_procedimento_origem, tb_org_prcdmnto_originario, tb_processo_proc_origem e tb_tipo_origem
**/
DO $$ 
	BEGIN
		-- tb_processo
		IF NOT EXISTS
	 		(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_processo' AND column_name = 'cd_tribunal') 
		THEN
			ALTER TABLE core.tb_processo ADD COLUMN cd_tribunal varchar(3) null;
			ALTER TABLE audit.tb_processo_aud ADD COLUMN cd_tribunal varchar(3) null;
		END IF;

		-- tb_unidade_prisional
		IF NOT EXISTS
		 	(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_unidade_prisional' AND column_name = 'cd_tribunal') 
		THEN
			ALTER TABLE core.tb_unidade_prisional ADD COLUMN cd_tribunal varchar(3) null;
		END IF;    

		-- tb_tp_procedimento_origem
		IF NOT EXISTS
		 	(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_tp_procedimento_origem' AND column_name = 'cd_tribunal') 
		THEN
			ALTER TABLE core.tb_tp_procedimento_origem ADD COLUMN cd_tribunal varchar(3) null;
			ALTER TABLE audit.tb_tp_procedimento_origem_aud ADD COLUMN cd_tribunal varchar(3) null;
		END IF;
			
		-- tb_org_prcdmnto_originario
		IF NOT EXISTS
		 	(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_org_prcdmnto_originario' AND column_name = 'cd_tribunal') 
		THEN
			ALTER TABLE core.tb_org_prcdmnto_originario ADD COLUMN cd_tribunal varchar(3) null;
			ALTER TABLE audit.tb_org_prcdmnto_originario_aud ADD COLUMN cd_tribunal varchar(3) null;
		END IF;

		-- tb_tipo_origem 	
		IF NOT EXISTS
		 	(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_tipo_origem' AND column_name = 'cd_tribunal') 
		THEN
			ALTER TABLE core.tb_tipo_origem ADD COLUMN cd_tribunal varchar(3) null;
			ALTER TABLE audit.tb_tipo_origem_aud ADD COLUMN cd_tribunal varchar(3) null;
		END IF;	

		-- tb_processo_proc_origem
		IF NOT EXISTS
		 	(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_processo_proc_origem' AND column_name = 'cd_tribunal') 
		THEN
			ALTER TABLE core.tb_processo_proc_origem ADD COLUMN cd_tribunal varchar(3) null;
			ALTER TABLE audit.tb_processo_proc_origem_aud ADD COLUMN cd_tribunal varchar(3) null;
		END IF;	
END $$;

