DO $$ 
    BEGIN
        
		IF NOT EXISTS
 		(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_processo' AND column_name = 'cd_tipo_processo') 
        THEN

	        alter table core.tb_processo add column cd_tipo_processo varchar(3) default 'CRI';
	        alter table audit.tb_processo_aud add column cd_tipo_processo varchar(3) default 'CRI';

		END IF;
        
		IF NOT EXISTS
 		(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_tipo_evento_criminal' AND column_name = 'cd_tipo_processo') 
        THEN

	        alter table core.tb_tipo_evento_criminal add column cd_tipo_processo varchar(3) default 'CRI';
	        alter table audit.tb_tipo_evento_criminal_aud add column cd_tipo_processo varchar(3) default 'CRI';
		    insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito, cd_tipo_processo)
		    	values (true, 'OFR', 'Oferecimento da Representação', true, 'INF');
		    insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito, cd_tipo_processo)
		    	values (true, 'RCR', 'Recebimento da Representação', true, 'INF');
		    insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito, cd_tipo_processo)
		    	values (true, 'INT', 'Internação', true, 'INF');
		    insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito, cd_tipo_processo)
		    	values (true, 'SML', 'Semiliberdade', true, 'INF');
		    insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito, cd_tipo_processo)
		    	values (true, 'NRR', 'Não Recebimento da Representação', true, 'INF');
		    insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito, cd_tipo_processo)
		    	values (true, 'ADR', 'Aditamento da Representação', true, 'INF');
		    insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito, cd_tipo_processo)
		    	values (true, 'RAR', 'Recebimento do Aditamento da Representação', true, 'INF');
		    insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito, cd_tipo_processo)
		    	values (true, 'SIN', 'Sentença Infracional', true, 'INF');
		    insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito, cd_tipo_processo)
		    	values (true, 'SEM', 'Sentença de Extinção da Medida Socioeducativa', true, 'INF');
		    insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito, cd_tipo_processo)
		    	values (true, 'EVA', 'Evasão', true, 'INF');
		    insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito, cd_tipo_processo)
		    	values (true, 'SMS', 'Suspensão da Medida Socioeducativa', true, 'INF');	    
			insert into core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito, cd_tipo_processo)
		    	values (true, 'DEC', 'Decisão', true, 'T');
	    
		END IF;
	
		IF NOT EXISTS
 		(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_tipo_internacao') 
        then
        
	        CREATE SEQUENCE core.sq_tb_tipo_internacao
				  INCREMENT 1
				  MINVALUE 1
				  MAXVALUE 9223372036854775807
				  START 1
				  CACHE 1;
	        
	        CREATE TABLE core.tb_tipo_internacao (
				id_tipo_internacao integer NOT NULL DEFAULT nextval('core.sq_tb_tipo_internacao'::regclass),
				cd_tipo_internacao varchar(3) NOT NULL,
				ds_tipo_internacao varchar(255) NOT NULL,
				in_ativo bool NOT NULL DEFAULT true,
				CONSTRAINT tb_tipo_internacao_pkey PRIMARY KEY (id_tipo_internacao)
			);
		
			insert into core.tb_tipo_internacao (cd_tipo_internacao, ds_tipo_internacao) values ('PRO', 'Provisória');
			insert into core.tb_tipo_internacao (cd_tipo_internacao, ds_tipo_internacao) values ('EST', 'Estrita');
			insert into core.tb_tipo_internacao (cd_tipo_internacao, ds_tipo_internacao) values ('SAN', 'Sanção');
        
        END IF;
       
		IF NOT EXISTS
 		(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_medida_socioeducativa') 
        THEN

	        CREATE SEQUENCE core.sq_tb_medida_socioeducativa
				  INCREMENT 1
				  MINVALUE 1
				  MAXVALUE 9223372036854775807
				  START 1
				  CACHE 1;
	        
		   	CREATE TABLE core.tb_medida_socioeducativa (
				id_medida_socioeducativa integer NOT NULL DEFAULT nextval('core.sq_tb_medida_socioeducativa'::regclass),
				cd_medida_socioeducativa varchar(3) NOT NULL,
				ds_medida_socioeducativa varchar(255) NOT NULL,
				in_ativo bool NOT NULL DEFAULT true,
				CONSTRAINT tb_medida_socioeducativa_pkey PRIMARY KEY (id_medida_socioeducativa)
			);
		
			insert into core.tb_medida_socioeducativa (cd_medida_socioeducativa, ds_medida_socioeducativa) values ('ADV', 'Advertência');
			insert into core.tb_medida_socioeducativa (cd_medida_socioeducativa, ds_medida_socioeducativa) values ('ORD', 'Obrigação de reparar o dano');
			insert into core.tb_medida_socioeducativa (cd_medida_socioeducativa, ds_medida_socioeducativa) values ('PSC', 'Prestação de serviços à comunidade');
			insert into core.tb_medida_socioeducativa (cd_medida_socioeducativa, ds_medida_socioeducativa) values ('LAS', 'Liberdade assistida');
			insert into core.tb_medida_socioeducativa (cd_medida_socioeducativa, ds_medida_socioeducativa) values ('SEM', 'Inserção em regime de semiliberdade');
			insert into core.tb_medida_socioeducativa (cd_medida_socioeducativa, ds_medida_socioeducativa) values ('INT', 'Internação em estabelecimento educacional');

		END IF;
	
		IF NOT EXISTS
 		(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_motivo_retorno_evasao') 
        THEN

	        CREATE SEQUENCE core.sq_tb_motivo_retorno_evasao
				  INCREMENT 1
				  MINVALUE 1
				  MAXVALUE 9223372036854775807
				  START 1
				  CACHE 1;
	        
		   	CREATE TABLE core.tb_motivo_retorno_evasao (
				id_motivo_retorno_evasao integer NOT NULL DEFAULT nextval('core.sq_tb_motivo_retorno_evasao'::regclass),
				ds_motivo_retorno_evasao varchar(255) NOT NULL,
				in_ativo bool NOT NULL DEFAULT true,
				CONSTRAINT tb_motivo_retorno_evasao_pkey PRIMARY KEY (id_motivo_retorno_evasao)
			);
		
			insert into core.tb_motivo_retorno_evasao (ds_motivo_retorno_evasao) values ('Mandado de Busca e Apreensão');

		END IF;
	
		IF NOT EXISTS
 		(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_motivo_extincao_med_socioeducativa') 
        THEN

	        CREATE SEQUENCE core.sq_tb_motivo_extincao_med_socioeducativa
				  INCREMENT 1
				  MINVALUE 1
				  MAXVALUE 9223372036854775807
				  START 1
				  CACHE 1;
	        
		   	CREATE TABLE core.tb_motivo_extincao_med_socioeducativa (
				id_motivo_extincao_med_socioeducativa integer NOT NULL DEFAULT nextval('core.sq_tb_motivo_extincao_med_socioeducativa'::regclass),
				ds_motivo_extincao_med_socioeducativa varchar(255) NOT NULL,
				in_ativo bool NOT NULL DEFAULT true,
				CONSTRAINT tb_motivo_extincao_med_socioeducativa_pkey PRIMARY KEY (id_motivo_extincao_med_socioeducativa)
			);
		
			insert into core.tb_motivo_extincao_med_socioeducativa (ds_motivo_extincao_med_socioeducativa) values ('Cumprimento Satisfatório');
			insert into core.tb_motivo_extincao_med_socioeducativa (ds_motivo_extincao_med_socioeducativa) values ('Decurso de Tempo');
			insert into core.tb_motivo_extincao_med_socioeducativa (ds_motivo_extincao_med_socioeducativa) values ('Envolvimento com a Justiça Criminal');
			insert into core.tb_motivo_extincao_med_socioeducativa (ds_motivo_extincao_med_socioeducativa) values ('21 anos');
			insert into core.tb_motivo_extincao_med_socioeducativa (ds_motivo_extincao_med_socioeducativa) values ('Prazo Limite');
			insert into core.tb_motivo_extincao_med_socioeducativa (ds_motivo_extincao_med_socioeducativa) values ('Reforma da Sentença');
			insert into core.tb_motivo_extincao_med_socioeducativa (ds_motivo_extincao_med_socioeducativa) values ('Medida idêntica');
			insert into core.tb_motivo_extincao_med_socioeducativa (ds_motivo_extincao_med_socioeducativa) values ('Medida mais gravosa');
			insert into core.tb_motivo_extincao_med_socioeducativa (ds_motivo_extincao_med_socioeducativa) values ('Morte');
			insert into core.tb_motivo_extincao_med_socioeducativa (ds_motivo_extincao_med_socioeducativa) values ('Prescrição');

		END IF;
		
		IF NOT EXISTS
 		(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_motivo_internacao_provisoria') 
        THEN

	        CREATE SEQUENCE core.sq_tb_motivo_internacao_provisoria
				  INCREMENT 1
				  MINVALUE 1
				  MAXVALUE 9223372036854775807
				  START 1
				  CACHE 1;
	        
		   	CREATE TABLE core.tb_motivo_internacao_provisoria (
				id_motivo_internacao_provisoria integer NOT NULL DEFAULT nextval('core.sq_tb_motivo_internacao_provisoria'::regclass),
				ds_motivo_internacao_provisoria varchar(255) NOT NULL,
				in_ativo bool NOT NULL DEFAULT true,
				CONSTRAINT tb_motivo_internacao_provisoria_pkey PRIMARY KEY (id_motivo_internacao_provisoria)
			);
		
			insert into core.tb_motivo_internacao_provisoria (ds_motivo_internacao_provisoria) values ('Flagrante Infracional');
			insert into core.tb_motivo_internacao_provisoria (ds_motivo_internacao_provisoria) values ('Mandado de Busca e Apreensão');

		END IF;
	
END $$;