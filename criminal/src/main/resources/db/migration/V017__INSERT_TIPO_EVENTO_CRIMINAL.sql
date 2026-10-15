DO $$
	BEGIN
		
	IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'AAU')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'AAU', 'Atribuição de Autoria dos Fatos', false);
    END IF;
    	
    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'SEN')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'SEN', 'Sentença', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'IND')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(true, 'IND', 'Indiciamento', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'OFD')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(true, 'OFD', 'Oferecimento da Denúncia', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'ADD')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(true, 'ADD', 'Aditamento da Denúncia', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'ADQ')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'ADQ', 'Aditamento da Queixa', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'RCD')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(true, 'RCD', 'Recebimento da Denúncia', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'PRI')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(true, 'PRI', 'Prisão', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'FUG')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(true, 'FUG', 'Fuga', false);
    END IF;  

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'SOL')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(true, 'SOL', 'Soltura', false);
    END IF;
    
    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'TRR')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'TRR', 'Transferência do Réu', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'SAP')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(true, 'SAP', 'Sentença Absolutória', false);
    END IF;    
    
    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'CQA')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'CQA', 'Cadastro de Queixa', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'NRQ')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'NRQ', 'Não Recebimento da Queixa', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'SAI')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'SAI', 'Sentença Absolutória Imprópria', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'SAS')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'SAS', 'Sentença Absolvição Sumária', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'SPR')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'SPR', 'Sentença de Pronúncia', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'SEI')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'SEI', 'Sentença de Impronúncia', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'SEP')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'SEP', 'Sentença de Extinção da Punibilidade', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'DIS')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(true, 'DIS', 'Decisão em Instância Superior', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'CRQ')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'CRQ', 'Cadastrar Recebimento de Queixa', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'TRP')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'TRP', 'Transação Penal', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'ETP')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'ETP', 'Encerrar Transação Penal', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'STP')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'STP', 'Suspender Transação Penal', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'ESP')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'ESP', 'Encerrar Suspensão do Processo', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'SUS')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(true, 'SUS', 'Suspensao do Processo', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'SCO')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(true, 'SCO', 'Sentença Condenatória', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'SSP')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'SSP', 'Suspender Suspensao do Processo', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'RSP')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'RSP', 'Retomar Suspensão do Processo', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'DES')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'DES', 'Desclassificação do Processo', false);
    END IF;

    IF NOT EXISTS  (SELECT 1 
            FROM core.tb_tipo_evento_criminal
                WHERE cd_tipo_evento_criminal  = 'NRD')
    THEN
        INSERT INTO core.tb_tipo_evento_criminal (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) 
        VALUES(false, 'NRD', 'Não Recebimento da Denuncia', false);
    END IF;        

END $$;
