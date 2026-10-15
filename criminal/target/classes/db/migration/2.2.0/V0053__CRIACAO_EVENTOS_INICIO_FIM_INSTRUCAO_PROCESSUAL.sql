DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
          FROM core.tb_tipo_evento_criminal
         WHERE cd_tipo_evento_criminal = 'IIP'
    ) THEN
        INSERT INTO core.tb_tipo_evento_criminal
            (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito)
        VALUES
            (true, 'IIP', 'Início da Instrução Processual', false);
    ELSE
        UPDATE core.tb_tipo_evento_criminal
           SET in_ativo = true,
               ds_tipo_evento_criminal = 'Início da Instrução Processual',
               in_exige_tipificacao_delito = false
         WHERE cd_tipo_evento_criminal = 'IIP';
    END IF;

    IF NOT EXISTS (
        SELECT 1
          FROM core.tb_tipo_evento_criminal
         WHERE cd_tipo_evento_criminal = 'FIP'
    ) THEN
        INSERT INTO core.tb_tipo_evento_criminal
            (in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito)
        VALUES
            (true, 'FIP', 'Fim da Instrução Processual', false);
    ELSE
        UPDATE core.tb_tipo_evento_criminal
           SET in_ativo = true,
               ds_tipo_evento_criminal = 'Fim da Instrução Processual',
               in_exige_tipificacao_delito = false
         WHERE cd_tipo_evento_criminal = 'FIP';
    END IF;
END $$;
