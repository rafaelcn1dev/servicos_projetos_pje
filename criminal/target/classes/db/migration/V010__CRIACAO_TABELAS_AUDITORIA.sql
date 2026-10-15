-- Criação das tabelas tb_tipo_evento_criminal e tb_tipo_ec_movimento

CREATE SCHEMA IF NOT EXISTS audit;

CREATE TABLE IF NOT EXISTS audit.revinfo
(
  id integer NOT NULL,
  "timestamp" bigint NOT NULL,
  username character varying(255),
  CONSTRAINT revinfo_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE IF NOT EXISTS audit.tb_informacao_criminal_aud
(
  id_informacao_criminal bigint NOT NULL,
  rev integer NOT NULL,
  revtype smallint,
  ds_conteudo jsonb,
  id_parte bigint,
  id_processo bigint,
  CONSTRAINT tb_informacao_criminal_aud_pkey PRIMARY KEY (id_informacao_criminal, rev),
  CONSTRAINT fkpaxvvma8ff4nem0326wyd9xk1 FOREIGN KEY (rev)
      REFERENCES audit.revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE IF NOT EXISTS audit.tb_municipio_aud
(
  id_municipio bigint NOT NULL,
  rev integer NOT NULL,
  revtype smallint,
  cd_municipio_ibge character varying(10),
  ds_municipio character varying(50),
  cd_uf character varying(2),
  CONSTRAINT tb_municipio_aud_pkey PRIMARY KEY (id_municipio, rev),
  CONSTRAINT fkt2aex1ct8c2ttqrngrbelgr3g FOREIGN KEY (rev)
      REFERENCES audit.revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE IF NOT EXISTS audit.tb_org_prcdmnto_originario_aud
(
  id_org_procedimento_originario bigint NOT NULL,
  rev integer NOT NULL,
  revtype smallint,
  in_ativo boolean,
  nr_cep character varying(9),
  ds_cod_origem character varying(255),
  ds_ddd character varying(255),
  ds_nome_orgao character varying(255),
  ds_telefone character varying(255),
  nm_bairro character varying(255),
  nm_complemento character varying(255),
  nm_logradouro character varying(255),
  nm_numero character varying(255),
  id_municipio bigint,
  id_tipo_origem bigint,
  CONSTRAINT tb_org_prcdmnto_originario_aud_pkey PRIMARY KEY (id_org_procedimento_originario, rev),
  CONSTRAINT fkst5yq54hje8xgqwbvrup5ssx4 FOREIGN KEY (rev)
      REFERENCES audit.revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE IF NOT EXISTS audit.tb_parte_aud
(
  id_parte bigint NOT NULL,
  rev integer NOT NULL,
  revtype smallint,
  id_pessoa_legacy bigint,
  id_processo_parte_legacy bigint,
  ds_rji character varying(255),
  in_situacao_parte character varying(1),
  id_processo bigint,
  CONSTRAINT tb_parte_aud_pkey PRIMARY KEY (id_parte, rev),
  CONSTRAINT fkh0oni81bt4ae28cuf3b4nu78c FOREIGN KEY (rev)
      REFERENCES audit.revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE IF NOT EXISTS audit.tb_processo_aud
(
  id_processo bigint NOT NULL,
  rev integer NOT NULL,
  revtype smallint,
  nr_cep character varying(9),
  nm_complemento character varying(255),
  ds_latitude character varying(255),
  ds_local_fato character varying(255),
  ds_longitude character varying(255),
  dt_local_fato timestamp without time zone,
  nm_bairro character varying(255),
  nm_logradouro character varying(255),
  nm_numero character varying(255),
  nr_processo character varying(50),
  id_municipio bigint,
  CONSTRAINT tb_processo_aud_pkey PRIMARY KEY (id_processo, rev),
  CONSTRAINT fk3qqpsahjc40x7o4xnwyjqvkvm FOREIGN KEY (rev)
      REFERENCES audit.revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE IF NOT EXISTS audit.tb_processo_proc_origem_aud
(
  id_processo_proc_origem bigint NOT NULL,
  rev integer NOT NULL,
  revtype smallint,
  nr_ano integer,
  in_ativo boolean,
  dt_instauracao timestamp without time zone,
  dt_lavratura timestamp without time zone,
  nr_protocolo_policia character varying(255),
  nr_procedimento character varying(255),
  in_retombamento_redistribuicao boolean,
  uf character varying(255),
  id_org_procedimento_originario bigint,
  id_processo bigint,
  id_processo_proc_origem_retombado bigint,
  id_tipo_origem bigint,
  id_tipo_procedimento_origem bigint,
  CONSTRAINT tb_processo_proc_origem_aud_pkey PRIMARY KEY (id_processo_proc_origem, rev),
  CONSTRAINT fkmi3wgr10912a4y1fjylqjowew FOREIGN KEY (rev)
      REFERENCES audit.revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE IF NOT EXISTS audit.tb_tipo_evento_criminal_aud
(
  id_tipo_evento_criminal integer NOT NULL,
  rev integer NOT NULL,
  revtype smallint,
  in_ativo boolean,
  cd_tipo_evento_criminal character varying(255),
  ds_tipo_evento_criminal character varying(255),
  in_exige_tipificacao_delito boolean,
  CONSTRAINT tb_tipo_evento_criminal_aud_pkey PRIMARY KEY (id_tipo_evento_criminal, rev),
  CONSTRAINT fk92d0vxgyo6c39q8nywxodm7xg FOREIGN KEY (rev)
      REFERENCES audit.revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE IF NOT EXISTS audit.tb_tipo_origem_aud
(
  id_tipo_origem bigint NOT NULL,
  rev integer NOT NULL,
  revtype smallint,
  in_ativo boolean,
  ds_tipo_origem character varying(255),
  in_obrigatorio_numero_origem boolean,
  CONSTRAINT tb_tipo_origem_aud_pkey PRIMARY KEY (id_tipo_origem, rev),
  CONSTRAINT fk1ubuk9iko7nr4uycdo7fdewvq FOREIGN KEY (rev)
      REFERENCES audit.revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE IF NOT EXISTS audit.tb_tp_org_tp_proced_origem_aud
(
  rev integer NOT NULL,
  id_tipo_origem bigint NOT NULL,
  id_tipo_procedimento_origem bigint NOT NULL,
  revtype smallint,
  CONSTRAINT tb_tp_org_tp_proced_origem_aud_pkey PRIMARY KEY (rev, id_tipo_procedimento_origem, id_tipo_origem),
  CONSTRAINT fkha40hl5e8grd8i89kvtcj58s9 FOREIGN KEY (rev)
      REFERENCES audit.revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE IF NOT EXISTS audit.tb_tp_procedimento_origem_aud
(
  id_tipo_procedimento_origem bigint NOT NULL,
  rev integer NOT NULL,
  revtype smallint,
  in_ativo boolean,
  ds_tipo_procedimento character varying(30),
  CONSTRAINT tb_tp_procedimento_origem_aud_pkey PRIMARY KEY (id_tipo_procedimento_origem, rev),
  CONSTRAINT fkfvichta1ncvgilyw30kqq3rqa FOREIGN KEY (rev)
      REFERENCES audit.revinfo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);