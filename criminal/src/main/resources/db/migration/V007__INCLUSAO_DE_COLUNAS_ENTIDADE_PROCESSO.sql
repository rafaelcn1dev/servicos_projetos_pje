DO $$ 
    BEGIN
        
		IF NOT EXISTS
 		(SELECT 1 FROM information_schema.columns WHERE table_schema = 'core' AND table_name = 'tb_processo_proc_origem' AND column_name = 'in_retombamento_redistribuicao') 
        THEN


        alter table core.tb_processo_proc_origem add column in_retombamento_redistribuicao bool NOT NULL DEFAULT false;
        alter table core.tb_processo_proc_origem add column id_processo_proc_origem_retombado int4 null;
        alter table core.tb_processo_proc_origem add column nr_protocolo_policia varchar(20) null;
        alter table core.tb_processo_proc_origem add constraint fk_tb_processo_proc_origem_tb_processo_proc_origem FOREIGN KEY (id_processo_proc_origem_retombado) REFERENCES core.tb_processo_proc_origem(id_processo_proc_origem);
        alter table core.tb_processo_proc_origem add column uf varchar(2) null;

        alter table core.tb_processo add column nm_logradouro varchar(200) null;
        alter table core.tb_processo add column nm_bairro varchar(100) null;
        alter table core.tb_processo add column nm_numero varchar(10) null;
        alter table core.tb_processo add column nm_complemento varchar(70) null;
        alter table core.tb_processo add column nr_cep varchar(9) null;

		END IF;
END $$;
