DO $$
	BEGIN
	
    ALTER TABLE core.tb_unidade_prisional ALTER column nr_fax TYPE varchar(14);
    ALTER TABLE core.tb_unidade_prisional ALTER column nr_telefone TYPE varchar(14);

END $$;