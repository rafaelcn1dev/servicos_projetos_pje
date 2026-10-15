DO $$
   BEGIN
        INSERT INTO core.tb_medida_socioeducativa(cd_medida_socioeducativa, ds_medida_socioeducativa, in_ativo)
		VALUES 
	   ('NA', 'Não se aplica', true),
       ('RMS', 'Remissão Simples', true),
       ('LPS', 'Liberdade assistida e prestação de serviços à comunidade', true);
   END
$$;