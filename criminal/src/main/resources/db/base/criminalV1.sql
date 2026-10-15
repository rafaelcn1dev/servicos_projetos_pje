--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.6
-- Dumped by pg_dump version 9.6.6

-- Started on 2019-02-10 00:05:07 -02

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 9 (class 2615 OID 1134351)
-- Name: audit; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA audit;


ALTER SCHEMA audit OWNER TO postgres;

--
-- TOC entry 10 (class 2615 OID 1133212)
-- Name: core; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA core;


ALTER SCHEMA core OWNER TO postgres;

--
-- TOC entry 1 (class 3079 OID 12655)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2 (class 3079 OID 1133213)
-- Name: dblink; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS dblink WITH SCHEMA public;


--
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION dblink; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION dblink IS 'connect to other PostgreSQL databases from within a database';


SET search_path = audit, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 242 (class 1259 OID 1134352)
-- Name: revinfo; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE revinfo (
    id integer NOT NULL,
    "timestamp" bigint NOT NULL,
    username character varying(255)
);


ALTER TABLE revinfo OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 1134492)
-- Name: tb_informacao_criminal_aud; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE tb_informacao_criminal_aud (
    id_informacao_criminal bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    ds_conteudo jsonb,
    id_parte bigint,
    id_processo bigint
);


ALTER TABLE tb_informacao_criminal_aud OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 1134367)
-- Name: tb_municipio_aud; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE tb_municipio_aud (
    id_municipio bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    cd_municipio_ibge character varying(10),
    ds_municipio character varying(50),
    cd_uf character varying(2)
);


ALTER TABLE tb_municipio_aud OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 1134377)
-- Name: tb_org_prcdmnto_originario_aud; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE tb_org_prcdmnto_originario_aud (
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
    id_tipo_origem bigint
);


ALTER TABLE tb_org_prcdmnto_originario_aud OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 1134390)
-- Name: tb_parte_aud; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE tb_parte_aud (
    id_parte bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    id_pessoa_legacy bigint,
    id_processo_parte_legacy bigint,
    ds_rji character varying(255),
    in_situacao_parte character varying(1),
    id_processo bigint
);


ALTER TABLE tb_parte_aud OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 1134400)
-- Name: tb_processo_aud; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE tb_processo_aud (
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
    ds_pje_origem character varying(50)
);


ALTER TABLE tb_processo_aud OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 1134413)
-- Name: tb_processo_proc_origem_aud; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE tb_processo_proc_origem_aud (
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
    id_tipo_procedimento_origem bigint
);


ALTER TABLE tb_processo_proc_origem_aud OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 1134426)
-- Name: tb_tipo_evento_criminal_aud; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE tb_tipo_evento_criminal_aud (
    id_tipo_evento_criminal integer NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    in_ativo boolean,
    cd_tipo_evento_criminal character varying(255),
    ds_tipo_evento_criminal character varying(255),
    in_exige_tipificacao_delito boolean
);


ALTER TABLE tb_tipo_evento_criminal_aud OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 1134439)
-- Name: tb_tipo_origem_aud; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE tb_tipo_origem_aud (
    id_tipo_origem bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    in_ativo boolean,
    ds_tipo_origem character varying(255),
    in_obrigatorio_numero_origem boolean
);


ALTER TABLE tb_tipo_origem_aud OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 1134449)
-- Name: tb_tp_org_tp_proced_origem_aud; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE tb_tp_org_tp_proced_origem_aud (
    rev integer NOT NULL,
    id_tipo_origem bigint NOT NULL,
    id_tipo_procedimento_origem bigint NOT NULL,
    revtype smallint
);


ALTER TABLE tb_tp_org_tp_proced_origem_aud OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 1134459)
-- Name: tb_tp_procedimento_origem_aud; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE tb_tp_procedimento_origem_aud (
    id_tipo_procedimento_origem bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    in_ativo boolean,
    ds_tipo_procedimento character varying(30)
);


ALTER TABLE tb_tp_procedimento_origem_aud OWNER TO postgres;

SET search_path = core, pg_catalog;

--
-- TOC entry 239 (class 1259 OID 1133628)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 1133259)
-- Name: sq_processo_proc_origem; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_processo_proc_origem
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_processo_proc_origem OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 1133630)
-- Name: sq_tb_classe_judicial; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_classe_judicial
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_classe_judicial OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 1133261)
-- Name: sq_tb_dispositivo; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_dispositivo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_dispositivo OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 1133263)
-- Name: sq_tb_informacao_criminal; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_informacao_criminal
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_informacao_criminal OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 1133265)
-- Name: sq_tb_motivo_prisao; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_motivo_prisao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_motivo_prisao OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 1133267)
-- Name: sq_tb_motivo_soltura; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_motivo_soltura
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_motivo_soltura OWNER TO postgres;

--
-- TOC entry 254 (class 1259 OID 1134620)
-- Name: sq_tb_motivo_suspensao_processo; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_motivo_suspensao_processo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_motivo_suspensao_processo OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 1133269)
-- Name: sq_tb_municipio; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_municipio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_municipio OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 1133271)
-- Name: sq_tb_natureza_prisao; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_natureza_prisao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_natureza_prisao OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 1133273)
-- Name: sq_tb_norma; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_norma
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_norma OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 1133275)
-- Name: sq_tb_org_prcdmnto_originario; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_org_prcdmnto_originario
    START WITH 2
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_org_prcdmnto_originario OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 1133277)
-- Name: sq_tb_parte; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_parte
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_parte OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 1133279)
-- Name: sq_tb_processo; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_processo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_processo OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 1133281)
-- Name: sq_tb_tipo_dispositivo; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_tipo_dispositivo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_tipo_dispositivo OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 1133632)
-- Name: sq_tb_tipo_ec_movimento; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_tipo_ec_movimento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_tipo_ec_movimento OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 1133595)
-- Name: sq_tb_tipo_evento_criminal; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_tipo_evento_criminal
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_tipo_evento_criminal OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 1133287)
-- Name: sq_tb_tipo_norma; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_tipo_norma
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_tipo_norma OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 1133289)
-- Name: sq_tb_tipo_origem; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_tipo_origem
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_tipo_origem OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 1133291)
-- Name: sq_tb_tipo_prisao; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_tipo_prisao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_tipo_prisao OWNER TO postgres;

--
-- TOC entry 256 (class 1259 OID 1134746)
-- Name: sq_tb_tipo_recurso; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_tipo_recurso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_tipo_recurso OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 1133293)
-- Name: sq_tb_tipo_soltura; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_tipo_soltura
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_tipo_soltura OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 1133295)
-- Name: sq_tb_tp_procedimento_origem; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_tp_procedimento_origem
    START WITH 5
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_tp_procedimento_origem OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 1133297)
-- Name: sq_tb_unidade_prisional; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE sq_tb_unidade_prisional
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_unidade_prisional OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 1133621)
-- Name: tb_classe_judicial; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_classe_judicial (
    id_classe_judicial integer NOT NULL,
    in_ativo boolean NOT NULL,
    classe_judicial character varying(255),
    cd_classe_judicial character varying(15) NOT NULL,
    id_tipo_ic integer
);


ALTER TABLE tb_classe_judicial OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 1133299)
-- Name: tb_dispositivo; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_dispositivo (
    id_dispositivo integer DEFAULT nextval('sq_tb_dispositivo'::regclass) NOT NULL,
    id_tipo_dispositivo integer NOT NULL,
    id_norma integer NOT NULL,
    id_dispositivo_pai integer,
    ds_simbolo character varying(20),
    ds_identificador character varying(100) NOT NULL,
    ds_texto text NOT NULL,
    dt_inicio_vigencia date NOT NULL,
    dt_fim_vigencia date,
    in_ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE tb_dispositivo OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 1133307)
-- Name: tb_informacao_criminal; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_informacao_criminal (
    id_informacao_criminal integer DEFAULT nextval('sq_tb_informacao_criminal'::regclass) NOT NULL,
    id_processo integer NOT NULL,
    ds_conteudo jsonb NOT NULL,
    id_parte integer
);


ALTER TABLE tb_informacao_criminal OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 1133314)
-- Name: tb_motivo_prisao; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_motivo_prisao (
    id_motivo_prisao integer DEFAULT nextval('sq_tb_motivo_prisao'::regclass) NOT NULL,
    ds_motivo_prisao character varying(255) NOT NULL,
    in_ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE tb_motivo_prisao OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 1133319)
-- Name: tb_motivo_soltura; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_motivo_soltura (
    id_motivo_soltura integer DEFAULT nextval('sq_tb_motivo_soltura'::regclass) NOT NULL,
    ds_motivo_soltura character varying(255) NOT NULL,
    in_ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE tb_motivo_soltura OWNER TO postgres;

--
-- TOC entry 255 (class 1259 OID 1134622)
-- Name: tb_motivo_suspensao_processo; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_motivo_suspensao_processo (
    id_motivo_suspensao integer DEFAULT nextval('sq_tb_motivo_suspensao_processo'::regclass) NOT NULL,
    ds_motivo_suspensao character varying(255) NOT NULL,
    in_ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE tb_motivo_suspensao_processo OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 1133324)
-- Name: tb_municipio; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_municipio (
    id_municipio integer NOT NULL,
    ds_municipio character varying(50),
    cd_municipio_ibge character varying(10),
    cd_uf character varying(2)
);


ALTER TABLE tb_municipio OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 1133327)
-- Name: tb_municipio_id_municipio_seq; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE tb_municipio_id_municipio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tb_municipio_id_municipio_seq OWNER TO postgres;

--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 212
-- Name: tb_municipio_id_municipio_seq; Type: SEQUENCE OWNED BY; Schema: core; Owner: postgres
--

ALTER SEQUENCE tb_municipio_id_municipio_seq OWNED BY tb_municipio.id_municipio;


--
-- TOC entry 213 (class 1259 OID 1133329)
-- Name: tb_natureza_prisao; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_natureza_prisao (
    id_natureza_prisao integer DEFAULT nextval('sq_tb_natureza_prisao'::regclass) NOT NULL,
    ds_natureza_prisao character varying(255) NOT NULL,
    in_ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE tb_natureza_prisao OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 1133334)
-- Name: tb_norma; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_norma (
    id_norma integer DEFAULT nextval('sq_tb_norma'::regclass) NOT NULL,
    nr_norma integer NOT NULL,
    nm_norma character varying(100) NOT NULL,
    ds_sigla character varying(10),
    dt_inicio_vigencia date NOT NULL,
    dt_fim_vigencia date,
    id_tipo_norma integer NOT NULL,
    in_ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE tb_norma OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 1133339)
-- Name: tb_org_prcdmnto_originario; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_org_prcdmnto_originario (
    id_org_procedimento_originario integer NOT NULL,
    id_tipo_origem integer NOT NULL,
    ds_cod_origem character varying(10),
    ds_nome_orgao character varying(100) NOT NULL,
    nm_logradouro character varying(200) NOT NULL,
    nm_bairro character varying(100) NOT NULL,
    ds_telefone character varying(10),
    in_ativo boolean NOT NULL,
    ds_ddd character varying(2),
    nm_numero character varying(10),
    nm_complemento character varying(70),
    id_municipio integer,
    nr_cep character varying(9)
);


ALTER TABLE tb_org_prcdmnto_originario OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 1133345)
-- Name: tb_org_prcdmnto_originario_id_org_procedimento_originario_seq; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE tb_org_prcdmnto_originario_id_org_procedimento_originario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tb_org_prcdmnto_originario_id_org_procedimento_originario_seq OWNER TO postgres;

--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 216
-- Name: tb_org_prcdmnto_originario_id_org_procedimento_originario_seq; Type: SEQUENCE OWNED BY; Schema: core; Owner: postgres
--

ALTER SEQUENCE tb_org_prcdmnto_originario_id_org_procedimento_originario_seq OWNED BY tb_org_prcdmnto_originario.id_org_procedimento_originario;


--
-- TOC entry 217 (class 1259 OID 1133347)
-- Name: tb_parte; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_parte (
    id_parte integer DEFAULT nextval('sq_tb_parte'::regclass) NOT NULL,
    id_processo integer NOT NULL,
    in_situacao_parte character(1) DEFAULT 'A'::bpchar NOT NULL,
    id_processo_parte_legacy integer NOT NULL,
    id_pessoa_legacy integer NOT NULL,
    ds_rji character varying(100)
);


ALTER TABLE tb_parte OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 1133352)
-- Name: tb_processo; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_processo (
    id_processo integer DEFAULT nextval('sq_tb_processo'::regclass) NOT NULL,
    nr_processo character varying NOT NULL,
    dt_local_fato timestamp without time zone,
    ds_local_fato character varying(255) NOT NULL,
    ds_longitude character varying(10),
    ds_latitude character varying(10),
    nr_protocolo_policia character varying(20),
    id_municipio integer,
    nm_logradouro character varying(200),
    nm_bairro character varying(100),
    nm_numero character varying(10),
    nm_complemento character varying(70),
    nr_cep character varying(9),
    ds_pje_origem character varying(50)
);


ALTER TABLE tb_processo OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 1133359)
-- Name: tb_processo_proc_origem; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_processo_proc_origem (
    id_processo_proc_origem integer NOT NULL,
    id_processo integer NOT NULL,
    id_org_procedimento_originario integer,
    id_tipo_procedimento_origem integer,
    dt_instauracao timestamp without time zone,
    nr_procedimento character varying(60),
    nr_ano integer,
    id_tipo_origem integer NOT NULL,
    in_ativo boolean DEFAULT true NOT NULL,
    dt_lavratura timestamp without time zone,
    in_retombamento_redistribuicao boolean DEFAULT false NOT NULL,
    id_processo_proc_origem_retombado integer,
    nr_protocolo_policia character varying(20),
    uf character varying(2)
);


ALTER TABLE tb_processo_proc_origem OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 1133364)
-- Name: tb_tipo_dispositivo; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_tipo_dispositivo (
    id_tipo_dispositivo integer DEFAULT nextval('sq_tb_tipo_dispositivo'::regclass) NOT NULL,
    ds_tipo_dispositivo character varying(100) NOT NULL,
    in_ativo boolean NOT NULL
);


ALTER TABLE tb_tipo_dispositivo OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 1133368)
-- Name: tb_tipo_ec_movimento; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_tipo_ec_movimento (
    id_tipo_ec_movimento integer NOT NULL,
    ds_cod_movimento character varying(5) NOT NULL,
    id_tipo_ec integer
);


ALTER TABLE tb_tipo_ec_movimento OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 1133597)
-- Name: tb_tipo_evento_criminal; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_tipo_evento_criminal (
    id_tipo_evento_criminal integer DEFAULT nextval('sq_tb_tipo_evento_criminal'::regclass) NOT NULL,
    in_ativo boolean DEFAULT true NOT NULL,
    cd_tipo_evento_criminal character varying(5) NOT NULL,
    ds_tipo_evento_criminal character varying(255) NOT NULL,
    in_exige_tipificacao_delito boolean DEFAULT false NOT NULL
);


ALTER TABLE tb_tipo_evento_criminal OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 1133377)
-- Name: tb_tipo_norma; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_tipo_norma (
    id_tipo_norma integer DEFAULT nextval('sq_tb_tipo_norma'::regclass) NOT NULL,
    ds_tipo_norma character varying(100),
    in_ativo boolean
);


ALTER TABLE tb_tipo_norma OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 1133381)
-- Name: tb_tipo_origem; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_tipo_origem (
    id_tipo_origem integer NOT NULL,
    ds_tipo_origem character varying(100) NOT NULL,
    in_obrigatorio_numero_origem boolean NOT NULL,
    in_ativo boolean NOT NULL
);


ALTER TABLE tb_tipo_origem OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 1133384)
-- Name: tb_tipo_origem_id_tipo_origem_seq; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE tb_tipo_origem_id_tipo_origem_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tb_tipo_origem_id_tipo_origem_seq OWNER TO postgres;

--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 224
-- Name: tb_tipo_origem_id_tipo_origem_seq; Type: SEQUENCE OWNED BY; Schema: core; Owner: postgres
--

ALTER SEQUENCE tb_tipo_origem_id_tipo_origem_seq OWNED BY tb_tipo_origem.id_tipo_origem;


--
-- TOC entry 225 (class 1259 OID 1133386)
-- Name: tb_tipo_prisao; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_tipo_prisao (
    id_tipo_prisao integer DEFAULT nextval('sq_tb_tipo_prisao'::regclass) NOT NULL,
    ds_tipo_prisao character varying(255) NOT NULL,
    in_ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE tb_tipo_prisao OWNER TO postgres;

--
-- TOC entry 257 (class 1259 OID 1134748)
-- Name: tb_tipo_recurso; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_tipo_recurso (
    id_tipo_recurso integer DEFAULT nextval('sq_tb_tipo_recurso'::regclass) NOT NULL,
    cod_tipo_recurso character varying(10) NOT NULL,
    ds_tipo_recurso character varying(255) NOT NULL,
    in_ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE tb_tipo_recurso OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 1133391)
-- Name: tb_tipo_soltura; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_tipo_soltura (
    id_tipo_soltura integer DEFAULT nextval('sq_tb_tipo_soltura'::regclass) NOT NULL,
    ds_tipo_soltura character varying(255) NOT NULL,
    in_ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE tb_tipo_soltura OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 1133396)
-- Name: tb_tp_org_tp_proced_origem; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_tp_org_tp_proced_origem (
    id_tipo_origem integer NOT NULL,
    id_tipo_procedimento_origem integer NOT NULL
);


ALTER TABLE tb_tp_org_tp_proced_origem OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 1133399)
-- Name: tb_tp_procedimento_origem; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_tp_procedimento_origem (
    id_tipo_procedimento_origem integer NOT NULL,
    ds_tipo_procedimento character varying(100) NOT NULL,
    in_ativo boolean NOT NULL
);


ALTER TABLE tb_tp_procedimento_origem OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 1133402)
-- Name: tb_tp_procedimento_origem_id_tipo_procedimento_origem_seq; Type: SEQUENCE; Schema: core; Owner: postgres
--

CREATE SEQUENCE tb_tp_procedimento_origem_id_tipo_procedimento_origem_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tb_tp_procedimento_origem_id_tipo_procedimento_origem_seq OWNER TO postgres;

--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 229
-- Name: tb_tp_procedimento_origem_id_tipo_procedimento_origem_seq; Type: SEQUENCE OWNED BY; Schema: core; Owner: postgres
--

ALTER SEQUENCE tb_tp_procedimento_origem_id_tipo_procedimento_origem_seq OWNED BY tb_tp_procedimento_origem.id_tipo_procedimento_origem;


--
-- TOC entry 230 (class 1259 OID 1133404)
-- Name: tb_unidade_prisional; Type: TABLE; Schema: core; Owner: postgres
--

CREATE TABLE tb_unidade_prisional (
    id_unidade_prisional integer DEFAULT nextval('sq_tb_unidade_prisional'::regclass) NOT NULL,
    ds_unidade_prisional character varying(255) NOT NULL,
    ds_apelido_unidade character varying(100),
    ds_jurisdicao character varying(100) NOT NULL,
    ds_email character varying(255),
    nr_fax character varying(13),
    nr_telefone character varying(13),
    nr_cep character varying(9) NOT NULL,
    id_municipio integer,
    nm_bairro character varying(100) NOT NULL,
    nm_logradouro character varying(200) NOT NULL,
    in_ativo boolean NOT NULL
);


ALTER TABLE tb_unidade_prisional OWNER TO postgres;

SET search_path = public, pg_catalog;

--
-- TOC entry 231 (class 1259 OID 1133411)
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE books (
    id integer,
    data json
);


ALTER TABLE books OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 1133417)
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE flyway_schema_history OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 1133424)
-- Name: schema_version; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE schema_version (
    installed_rank integer NOT NULL,
    version character varying(50) NOT NULL,
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE schema_version OWNER TO postgres;

--
-- TOC entry 253 (class 1259 OID 1134610)
-- Name: schema_version_2; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE schema_version_2 (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE schema_version_2 OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 1133431)
-- Name: sq_tb_tp_procedimento_origem; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sq_tb_tp_procedimento_origem
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sq_tb_tp_procedimento_origem OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 1133433)
-- Name: temp_blockgroups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE temp_blockgroups (
    id_tipo_norma_penal integer
);


ALTER TABLE temp_blockgroups OWNER TO postgres;

SET search_path = core, pg_catalog;

--
-- TOC entry 2549 (class 2604 OID 1133436)
-- Name: tb_municipio id_municipio; Type: DEFAULT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_municipio ALTER COLUMN id_municipio SET DEFAULT nextval('tb_municipio_id_municipio_seq'::regclass);


--
-- TOC entry 2554 (class 2604 OID 1133437)
-- Name: tb_org_prcdmnto_originario id_org_procedimento_originario; Type: DEFAULT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_org_prcdmnto_originario ALTER COLUMN id_org_procedimento_originario SET DEFAULT nextval('tb_org_prcdmnto_originario_id_org_procedimento_originario_seq'::regclass);


--
-- TOC entry 2562 (class 2604 OID 1133438)
-- Name: tb_tipo_origem id_tipo_origem; Type: DEFAULT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_origem ALTER COLUMN id_tipo_origem SET DEFAULT nextval('tb_tipo_origem_id_tipo_origem_seq'::regclass);


--
-- TOC entry 2567 (class 2604 OID 1133439)
-- Name: tb_tp_procedimento_origem id_tipo_procedimento_origem; Type: DEFAULT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tp_procedimento_origem ALTER COLUMN id_tipo_procedimento_origem SET DEFAULT nextval('tb_tp_procedimento_origem_id_tipo_procedimento_origem_seq'::regclass);


SET search_path = audit, pg_catalog;

--
-- TOC entry 2887 (class 0 OID 1134352)
-- Dependencies: 242
-- Data for Name: revinfo; Type: TABLE DATA; Schema: audit; Owner: postgres
--

COPY revinfo (id, "timestamp", username) FROM stdin;
13	1531934589459	02289676195
15	1531935199943	02289676195
16	1531935200163	02289676195
17	1535385186013	08214164800
18	1535385186227	08214164800
19	1535385807930	08214164800
20	1535385808092	08214164800
21	1535390601803	08214164800
22	1535390601933	08214164800
23	1535391027338	08214164800
24	1535391904122	08214164800
25	1535391921907	08214164800
26	1535391923735	08214164800
27	1535391963469	08214164800
28	1535391990163	08214164800
29	1535392011314	08214164800
30	1535392025574	08214164800
31	1540844879350	08214164800
32	1540844879880	08214164800
33	1540845682591	08214164800
34	1540846163923	08214164800
35	1540846550743	08214164800
36	1540917412756	08214164800
37	1540917420307	08214164800
38	1546975080474	08214164800
39	1546975080943	08214164800
40	1548708326951	\N
41	1548776224285	08214164800
42	1548776228189	08214164800
43	1548777354868	\N
44	1548777899449	08214164800
45	1548778317353	08214164800
46	1548778317502	08214164800
47	1548779812970	\N
48	1548780602576	\N
49	1548780670368	\N
50	1548781549676	\N
51	1548786071327	08214164800
52	1548786071635	08214164800
54	1548948325745	08214164800
55	1548949041319	08214164800
56	1548949041751	08214164800
\.


--
-- TOC entry 2897 (class 0 OID 1134492)
-- Dependencies: 252
-- Data for Name: tb_informacao_criminal_aud; Type: TABLE DATA; Schema: audit; Owner: postgres
--

COPY tb_informacao_criminal_aud (id_informacao_criminal, rev, revtype, ds_conteudo, id_parte, id_processo) FROM stdin;
13	16	0	{"id": "23bf07d6-fec2-48ad-8d22-c74e651c760a", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "03458991-accc-408c-85cc-b67bebe7731c", "data": 1531935200112, "ativo": null, "observacao": null, "textoFinal": "CP 2848, Art. 157, § 1", "tipificacoes": [{"id": "c76d4e2b-e5e7-427a-a8a9-793f182b1ce1", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	4	66
14	18	0	{"id": "745e8394-797a-4cc5-8346-8fe0173e2f4e", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "19a42f77-552f-4059-a7b1-28ead6856c92", "data": 1535385186162, "ativo": null, "observacao": null, "textoFinal": "CP 2848, Art. 157, § 1", "tipificacoes": [{"id": "5f9e54d5-14be-421a-a1df-8c7bf7da220a", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	5	67
15	20	0	{"id": "792cdf02-46a3-4466-be76-aec5133a020c", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "9e173b24-1c8a-4366-83c3-d60dae2fd168", "data": 1535385808042, "ativo": null, "observacao": null, "textoFinal": "CP 2848, Art. 157, § 1", "tipificacoes": [{"id": "f0c64a14-7b62-4b8b-a68c-6f2ac40ebe23", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	6	68
16	22	0	{"id": "d2878855-69f3-4a75-865c-20f65d5e0d8f", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "d7a893e9-5b7a-4766-a945-a19ab5dcf54e", "data": 1535390601908, "ativo": null, "observacao": null, "textoFinal": "", "tipificacoes": [], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	7	69
16	23	1	{"id": "d2878855-69f3-4a75-865c-20f65d5e0d8f", "fugas": null, "nomes": null, "prisoes": [{"id": "db461f70-f5eb-453b-8b3d-9123bafb7aed", "data": 1535338800000, "ativo": null, "dtPrisao": null, "prazoAnos": null, "prazoDias": 30, "observacao": null, "prazoMeses": null, "textoFinal": "27/08/2018 - Preventiva - Prisão em flagrante", "tipoPrisao": {"id": 1, "ativo": true, "tipoPrisao": "Preventiva"}, "motivoPrisao": {"id": 1, "ativo": true, "motivoPrisao": "Prisão em flagrante"}, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 1, "ativo": true, "codTipoIc": "PRI", "descricao": "Prisão", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "d7a893e9-5b7a-4766-a945-a19ab5dcf54e", "data": 1535390601908, "ativo": null, "observacao": null, "textoFinal": "", "tipificacoes": [], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	7	69
16	24	1	{"id": "d2878855-69f3-4a75-865c-20f65d5e0d8f", "fugas": null, "nomes": null, "prisoes": [{"id": "db461f70-f5eb-453b-8b3d-9123bafb7aed", "data": 1535338800000, "ativo": null, "dtPrisao": null, "prazoAnos": null, "prazoDias": 30, "observacao": null, "prazoMeses": null, "textoFinal": "27/08/2018 - Preventiva - Prisão em flagrante", "tipoPrisao": {"id": 1, "ativo": true, "tipoPrisao": "Preventiva"}, "motivoPrisao": {"id": 1, "ativo": true, "motivoPrisao": "Prisão em flagrante"}, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 1, "ativo": true, "codTipoIc": "PRI", "descricao": "Prisão", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "solturas": [{"id": "7e43dd54-579f-4cdb-939e-5d4ee01fe005", "data": 1535338800000, "ativo": null, "motivo": {"id": 6, "ativo": true, "motivoSoltura": "Habeas corpus"}, "dtSoltura": null, "observacao": null, "textoFinal": "27/08/2018 - Habeas corpus - PENITENCIÁRIA DO DISTRITO FEDERAL", "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 3, "ativo": true, "codTipoIc": "SOL", "descricao": "Soltura", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "filiacoes": null, "documentos": null, "indiciamento": [{"id": "d7a893e9-5b7a-4766-a945-a19ab5dcf54e", "data": 1535390601908, "ativo": null, "observacao": null, "textoFinal": "", "tipificacoes": [], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	7	69
16	25	1	{"id": "d2878855-69f3-4a75-865c-20f65d5e0d8f", "fugas": null, "nomes": null, "prisoes": [], "solturas": [{"id": "7e43dd54-579f-4cdb-939e-5d4ee01fe005", "data": 1535338800000, "ativo": null, "motivo": {"id": 6, "ativo": true, "motivoSoltura": "Habeas corpus"}, "dtSoltura": null, "observacao": null, "textoFinal": "27/08/2018 - Habeas corpus - PENITENCIÁRIA DO DISTRITO FEDERAL", "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 3, "ativo": true, "codTipoIc": "SOL", "descricao": "Soltura", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "filiacoes": null, "documentos": null, "indiciamento": [{"id": "d7a893e9-5b7a-4766-a945-a19ab5dcf54e", "data": 1535390601908, "ativo": null, "observacao": null, "textoFinal": "", "tipificacoes": [], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	7	69
16	26	1	{"id": "d2878855-69f3-4a75-865c-20f65d5e0d8f", "fugas": null, "nomes": null, "prisoes": [], "solturas": [], "filiacoes": null, "documentos": null, "indiciamento": [{"id": "d7a893e9-5b7a-4766-a945-a19ab5dcf54e", "data": 1535390601908, "ativo": null, "observacao": null, "textoFinal": "", "tipificacoes": [], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	7	69
16	27	1	{"id": "d2878855-69f3-4a75-865c-20f65d5e0d8f", "fugas": null, "nomes": null, "prisoes": [{"id": "258cf229-1c95-4343-a8f2-935591956f04", "data": 1533092400000, "ativo": null, "dtPrisao": null, "prazoAnos": null, "prazoDias": 30, "observacao": null, "prazoMeses": null, "textoFinal": "01/08/2018 - Flagrante - Prisão em flagrante", "tipoPrisao": {"id": 4, "ativo": true, "tipoPrisao": "Flagrante"}, "motivoPrisao": {"id": 1, "ativo": true, "motivoPrisao": "Prisão em flagrante"}, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 1, "ativo": true, "codTipoIc": "PRI", "descricao": "Prisão", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "solturas": [], "filiacoes": null, "documentos": null, "indiciamento": [{"id": "d7a893e9-5b7a-4766-a945-a19ab5dcf54e", "data": 1535390601908, "ativo": null, "observacao": null, "textoFinal": "", "tipificacoes": [], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	7	69
16	28	1	{"id": "d2878855-69f3-4a75-865c-20f65d5e0d8f", "fugas": [{"id": "7839cba7-3079-4c5d-9201-0fa186c169f7", "data": 1533178800000, "ativo": null, "dsFuga": "Fuga da unidade prisional", "dtFuga": null, "observacao": null, "textoFinal": "02/08/2018 - Fuga da unidade prisional - PENITENCIÁRIA DO DISTRITO FEDERAL", "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 4, "ativo": true, "codTipoIc": "FUG", "descricao": "Fuga", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "nomes": null, "prisoes": [{"id": "258cf229-1c95-4343-a8f2-935591956f04", "data": 1533092400000, "ativo": null, "dtPrisao": null, "prazoAnos": null, "prazoDias": 30, "observacao": null, "prazoMeses": null, "textoFinal": "01/08/2018 - Flagrante - Prisão em flagrante", "tipoPrisao": {"id": 4, "ativo": true, "tipoPrisao": "Flagrante"}, "motivoPrisao": {"id": 1, "ativo": true, "motivoPrisao": "Prisão em flagrante"}, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 1, "ativo": true, "codTipoIc": "PRI", "descricao": "Prisão", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "solturas": [], "filiacoes": null, "documentos": null, "indiciamento": [{"id": "d7a893e9-5b7a-4766-a945-a19ab5dcf54e", "data": 1535390601908, "ativo": null, "observacao": null, "textoFinal": "", "tipificacoes": [], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	7	69
16	29	1	{"id": "d2878855-69f3-4a75-865c-20f65d5e0d8f", "fugas": [{"id": "7839cba7-3079-4c5d-9201-0fa186c169f7", "data": 1533178800000, "ativo": null, "dsFuga": "Fuga da unidade prisional", "dtFuga": null, "observacao": null, "textoFinal": "02/08/2018 - Fuga da unidade prisional - PENITENCIÁRIA DO DISTRITO FEDERAL", "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 4, "ativo": true, "codTipoIc": "FUG", "descricao": "Fuga", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "nomes": null, "prisoes": [{"id": "258cf229-1c95-4343-a8f2-935591956f04", "data": 1533092400000, "ativo": null, "dtPrisao": null, "prazoAnos": null, "prazoDias": 30, "observacao": null, "prazoMeses": null, "textoFinal": "01/08/2018 - Flagrante - Prisão em flagrante", "tipoPrisao": {"id": 4, "ativo": true, "tipoPrisao": "Flagrante"}, "motivoPrisao": {"id": 1, "ativo": true, "motivoPrisao": "Prisão em flagrante"}, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 1, "ativo": true, "codTipoIc": "PRI", "descricao": "Prisão", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}, {"id": "231f439c-a657-45fe-a7cf-c0fb265abd3c", "data": 1533438000000, "ativo": null, "dtPrisao": null, "prazoAnos": null, "prazoDias": 30, "observacao": null, "prazoMeses": null, "textoFinal": "05/08/2018 - Flagrante - Prisão em flagrante", "tipoPrisao": {"id": 4, "ativo": true, "tipoPrisao": "Flagrante"}, "motivoPrisao": {"id": 1, "ativo": true, "motivoPrisao": "Prisão em flagrante"}, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 1, "ativo": true, "codTipoIc": "PRI", "descricao": "Prisão", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "solturas": [], "filiacoes": null, "documentos": null, "indiciamento": [{"id": "d7a893e9-5b7a-4766-a945-a19ab5dcf54e", "data": 1535390601908, "ativo": null, "observacao": null, "textoFinal": "", "tipificacoes": [], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	7	69
16	30	1	{"id": "d2878855-69f3-4a75-865c-20f65d5e0d8f", "fugas": [{"id": "7839cba7-3079-4c5d-9201-0fa186c169f7", "data": 1533178800000, "ativo": null, "dsFuga": "Fuga da unidade prisional", "dtFuga": null, "observacao": null, "textoFinal": "02/08/2018 - Fuga da unidade prisional - PENITENCIÁRIA DO DISTRITO FEDERAL", "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 4, "ativo": true, "codTipoIc": "FUG", "descricao": "Fuga", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "nomes": null, "prisoes": [{"id": "258cf229-1c95-4343-a8f2-935591956f04", "data": 1533092400000, "ativo": null, "dtPrisao": null, "prazoAnos": null, "prazoDias": 30, "observacao": null, "prazoMeses": null, "textoFinal": "01/08/2018 - Flagrante - Prisão em flagrante", "tipoPrisao": {"id": 4, "ativo": true, "tipoPrisao": "Flagrante"}, "motivoPrisao": {"id": 1, "ativo": true, "motivoPrisao": "Prisão em flagrante"}, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 1, "ativo": true, "codTipoIc": "PRI", "descricao": "Prisão", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}, {"id": "231f439c-a657-45fe-a7cf-c0fb265abd3c", "data": 1533438000000, "ativo": null, "dtPrisao": null, "prazoAnos": null, "prazoDias": 30, "observacao": null, "prazoMeses": null, "textoFinal": "05/08/2018 - Flagrante - Prisão em flagrante", "tipoPrisao": {"id": 4, "ativo": true, "tipoPrisao": "Flagrante"}, "motivoPrisao": {"id": 1, "ativo": true, "motivoPrisao": "Prisão em flagrante"}, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 1, "ativo": true, "codTipoIc": "PRI", "descricao": "Prisão", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "solturas": [{"id": "a2b211e2-e153-4fe8-94b1-b53a012b092a", "data": 1535338800000, "ativo": null, "motivo": {"id": 6, "ativo": true, "motivoSoltura": "Habeas corpus"}, "dtSoltura": null, "observacao": null, "textoFinal": "27/08/2018 - Habeas corpus - PENITENCIÁRIA DO DISTRITO FEDERAL", "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 3, "ativo": true, "codTipoIc": "SOL", "descricao": "Soltura", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "filiacoes": null, "documentos": null, "indiciamento": [{"id": "d7a893e9-5b7a-4766-a945-a19ab5dcf54e", "data": 1535390601908, "ativo": null, "observacao": null, "textoFinal": "", "tipificacoes": [], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	7	69
17	32	0	{"id": "e5a3e218-6399-4be9-b53a-4e6fcfb2b99a", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "a54d4d50-69c2-44fc-8ac2-c19e7b34d72c", "data": 1540844879770, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "39640f90-72eb-4443-b8fc-204bd030a8f3", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	8	70
18	32	0	{"id": "1cefc0e5-0766-4131-9b82-975baffa5596", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "9d68938d-cb8a-448b-9daa-0140908fa294", "data": 1540844879775, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "4d8444d4-b801-4b81-b67e-9576da8c289b", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	9	70
17	33	1	{"id": "e5a3e218-6399-4be9-b53a-4e6fcfb2b99a", "fugas": null, "nomes": null, "prisoes": [{"id": "dabd44e4-8516-4256-b831-21eda4ffaec1", "data": 1539658800000, "ativo": null, "dtPrisao": null, "prazoAnos": 1, "prazoDias": 4, "observacao": null, "prazoMeses": 1, "textoFinal": null, "tipoPrisao": {"id": 1, "ativo": true, "tipoPrisao": "Preventiva"}, "motivoPrisao": {"id": 1, "ativo": true, "motivoPrisao": "Prisão em flagrante"}, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 1, "ativo": true, "codTipoIc": "PRI", "descricao": "Prisão", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "a54d4d50-69c2-44fc-8ac2-c19e7b34d72c", "data": 1540844879770, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "39640f90-72eb-4443-b8fc-204bd030a8f3", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	8	70
17	34	1	{"id": "e5a3e218-6399-4be9-b53a-4e6fcfb2b99a", "fugas": null, "nomes": null, "prisoes": [], "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "a54d4d50-69c2-44fc-8ac2-c19e7b34d72c", "data": 1540844879770, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "39640f90-72eb-4443-b8fc-204bd030a8f3", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	8	70
17	35	1	{"id": "e5a3e218-6399-4be9-b53a-4e6fcfb2b99a", "fugas": null, "nomes": null, "prisoes": [{"id": "b6eb3f15-879d-4873-8728-c1db01916975", "data": 1538362800000, "ativo": null, "dtPrisao": null, "prazoAnos": null, "prazoDias": null, "observacao": null, "prazoMeses": null, "textoFinal": null, "tipoPrisao": {"id": 1, "ativo": true, "tipoPrisao": "Preventiva"}, "motivoPrisao": {"id": 1, "ativo": true, "motivoPrisao": "Prisão em flagrante"}, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 1, "ativo": true, "codTipoIc": "PRI", "descricao": "Prisão", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "a54d4d50-69c2-44fc-8ac2-c19e7b34d72c", "data": 1540844879770, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "39640f90-72eb-4443-b8fc-204bd030a8f3", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	8	70
17	36	1	{"id": "e5a3e218-6399-4be9-b53a-4e6fcfb2b99a", "fugas": null, "nomes": null, "prisoes": [{"id": "b6eb3f15-879d-4873-8728-c1db01916975", "data": 1538362800000, "ativo": null, "dtPrisao": null, "prazoAnos": null, "prazoDias": null, "observacao": null, "prazoMeses": null, "textoFinal": null, "tipoPrisao": {"id": 1, "ativo": true, "tipoPrisao": "Preventiva"}, "motivoPrisao": {"id": 1, "ativo": true, "motivoPrisao": "Prisão em flagrante"}, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 1, "ativo": true, "codTipoIc": "PRI", "descricao": "Prisão", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "solturas": [{"id": "2548f4e8-89b7-4b37-948a-09d0dcd418b3", "data": 1539658800000, "ativo": null, "motivo": {"id": 1, "ativo": true, "motivoSoltura": "Fiança"}, "dtSoltura": null, "observacao": null, "textoFinal": null, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 3, "ativo": true, "codTipoIc": "SOL", "descricao": "Soltura", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "filiacoes": null, "documentos": null, "indiciamento": [{"id": "a54d4d50-69c2-44fc-8ac2-c19e7b34d72c", "data": 1540844879770, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "39640f90-72eb-4443-b8fc-204bd030a8f3", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	8	70
17	37	1	{"id": "e5a3e218-6399-4be9-b53a-4e6fcfb2b99a", "fugas": null, "nomes": null, "prisoes": [{"id": "b6eb3f15-879d-4873-8728-c1db01916975", "data": 1538362800000, "ativo": null, "dtPrisao": null, "prazoAnos": null, "prazoDias": null, "observacao": null, "prazoMeses": null, "textoFinal": null, "tipoPrisao": {"id": 1, "ativo": true, "tipoPrisao": "Preventiva"}, "motivoPrisao": {"id": 1, "ativo": true, "motivoPrisao": "Prisão em flagrante"}, "tipificacoes": [], "unidadePrisional": {"id": 1, "ativo": true, "nrCep": "70610-200", "nrFax": "(61)6161-6161", "dsEmail": "pen@com.br", "nmBairro": "Setores Complementares", "municipio": {"uf": "DF", "municipio": "BRASíLIA", "codigoIbge": "5300108"}, "nrTelefone": "(61)6161-6161", "dsJurisdicao": "Brasilia", "nmLogradouro": "Setor SPO", "dsApelidoUnidade": "PENITENCIÁRIA DO DISTRITO FEDERAL", "dsUnidadePrisional": "PENITENCIÁRIA DO DISTRITO FEDERAL"}, "tipoInformacaoCriminal": {"id": 1, "ativo": true, "codTipoIc": "PRI", "descricao": "Prisão", "exigeTipificacaoDelito": false}, "processoMovimentoLegacy": null}], "solturas": [], "filiacoes": null, "documentos": null, "indiciamento": [{"id": "a54d4d50-69c2-44fc-8ac2-c19e7b34d72c", "data": 1540844879770, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "39640f90-72eb-4443-b8fc-204bd030a8f3", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	8	70
19	39	0	{"id": "197caea0-e785-4b67-a12e-d4a66a3e30ae", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "aa73c896-2d74-44bb-a977-412f22202ff7", "data": 1546975080797, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "1ef2c368-fa24-4224-8b36-e2ca102b6acb", "dtFato": null, "dispositivo": {"id": 187, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">A pena aumenta-se de um terço até metade:</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 2", "fimVigencia": null, "identificador": "2", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	10	71
20	39	0	{"id": "646787ce-8240-409a-8b8c-94d3450f87e1", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "5802bc53-42c6-45f7-a687-70f9fad713ea", "data": 1546975080844, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "5a1dea07-6570-446b-9df5-98c1def44634", "dtFato": null, "dispositivo": {"id": 234, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Se o criminoso é primário, e é de pequeno valor o prejuízo, o juiz pode aplicar a pena conforme o disposto no art. 155, § 2º.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 171, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	11	71
21	42	0	{"id": "e0b77d86-57f4-4af3-833b-1c9777523082", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "350c2e4d-136b-466c-b8e6-9102cdbba36c", "data": 1548776228103, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "1beff58d-0d0f-4964-94fe-4764c28010f0", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	125	72
22	46	0	{"id": "f5e0c819-c947-47e3-a1af-79396e0d28f6", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "31654aca-a7a4-49d1-9254-6a4688df7ffa", "data": 1548778317454, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "f4abc43e-f99f-48f8-9910-56a6b675039d", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null}	127	74
23	52	0	{"id": "af328c56-9dfc-43bb-a1f7-5adcd685a7c5", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "3c285651-b320-49a4-a284-17877e18eca0", "data": 1548786071533, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "8636ff50-94a9-46b3-977a-79ea98759c57", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null, "sentencasCondenatorias": null, "decisoesInstanciaSuperior": null}	131	75
24	52	0	{"id": "3f4e5f01-acd8-45fb-adc0-c698c8e7a14a", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "f4476625-a5d2-4ba6-aa46-dbbe8c7f807c", "data": 1548786071539, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "82c2606d-bca2-4f40-b2c9-8adb67e0a89d", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "caracteristicasFisicas": null, "sentencasCondenatorias": null, "decisoesInstanciaSuperior": null}	132	75
25	56	0	{"id": "e83f33f1-ef3e-4c10-a06a-1afbfbfbdb46", "fugas": null, "nomes": null, "prisoes": null, "solturas": null, "filiacoes": null, "documentos": null, "indiciamento": [{"id": "d519eb07-b0cb-4595-8add-e9626d440414", "data": 1548949041595, "ativo": null, "observacao": null, "textoFinal": null, "tipificacoes": [{"id": "821a7295-3510-4ad2-b4b3-d981606e047a", "dtFato": null, "dispositivo": {"id": 186, "ativo": true, "norma": {"id": 2, "ativo": true, "norma": "Código Penal", "sigla": "CP", "fimVigencia": null, "numeroNorma": "2848", "inicioVigencia": -917298000000}, "texto": "<p xmlns=\\\\\\"http://www.lexml.gov.br/1.0\\\\\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>", "simbolo": "§", "textoFinal": "CP 2848, Art. 157, § 1", "fimVigencia": null, "identificador": "1", "inicioVigencia": -917298000000}, "dtPrescricao": null}], "tipoInformacaoCriminal": null, "processoMovimentoLegacy": null}], "nomePrincipal": null, "aditamentoDenuncia": [], "suspensoesProcesso": null, "recebimentoDenuncia": [], "oferecimentoDenuncia": [], "sentencasAbsolutorias": null, "caracteristicasFisicas": null, "sentencasCondenatorias": null, "decisoesInstanciaSuperior": null}	133	78
\.


--
-- TOC entry 2888 (class 0 OID 1134367)
-- Dependencies: 243
-- Data for Name: tb_municipio_aud; Type: TABLE DATA; Schema: audit; Owner: postgres
--

COPY tb_municipio_aud (id_municipio, rev, revtype, cd_municipio_ibge, ds_municipio, cd_uf) FROM stdin;
3	31	0	2804201	MONTE ALEGRE DE SERGIPE	SE
\.


--
-- TOC entry 2889 (class 0 OID 1134377)
-- Dependencies: 244
-- Data for Name: tb_org_prcdmnto_originario_aud; Type: TABLE DATA; Schema: audit; Owner: postgres
--

COPY tb_org_prcdmnto_originario_aud (id_org_procedimento_originario, rev, revtype, in_ativo, nr_cep, ds_cod_origem, ds_ddd, ds_nome_orgao, ds_telefone, nm_bairro, nm_complemento, nm_logradouro, nm_numero, id_municipio, id_tipo_origem) FROM stdin;
\.


--
-- TOC entry 2890 (class 0 OID 1134390)
-- Dependencies: 245
-- Data for Name: tb_parte_aud; Type: TABLE DATA; Schema: audit; Owner: postgres
--

COPY tb_parte_aud (id_parte, rev, revtype, id_pessoa_legacy, id_processo_parte_legacy, ds_rji, in_situacao_parte, id_processo) FROM stdin;
4	16	0	89964	230883	\N	A	66
5	18	0	89964	261734	\N	A	67
6	20	0	89964	261736	\N	A	68
7	22	0	89965	262234	\N	A	69
8	32	0	89965	265233	\N	A	70
9	32	0	89964	265234	\N	A	70
10	39	0	89964	267234	\N	A	71
11	39	0	89965	267235	\N	A	71
124	40	0	90011	269236	\N	A	71
125	42	0	89964	269737	\N	A	72
126	43	0	89965	269738	\N	A	72
127	46	0	89965	269742	\N	A	74
128	47	0	89968	269743	\N	A	74
129	48	0	89969	269744	\N	A	74
130	49	0	90011	269745	\N	A	74
129	50	1	89969	269744	\N	S	74
131	52	0	89964	270234	\N	A	75
132	52	0	89965	270235	\N	A	75
133	56	0	89965	270734	\N	A	78
\.


--
-- TOC entry 2891 (class 0 OID 1134400)
-- Dependencies: 246
-- Data for Name: tb_processo_aud; Type: TABLE DATA; Schema: audit; Owner: postgres
--

COPY tb_processo_aud (id_processo, rev, revtype, nr_cep, nm_complemento, ds_latitude, ds_local_fato, ds_longitude, dt_local_fato, nm_bairro, nm_logradouro, nm_numero, nr_processo, id_municipio, ds_pje_origem) FROM stdin;
65	13	0	70675-730	\N	\N	DF - BRASíLIA, 70675-730, Quadra QRSW 7 Bloco B-10, Setor Sudoeste	\N	2018-07-18 00:00:00	Setor Sudoeste	Quadra QRSW 7 Bloco B-10	\N	5000031-80.2018.4.03.0000	2	\N
66	15	0	70675-730	\N	\N	DF - BRASíLIA, 70675-730, Quadra QRSW 7 Bloco B-10, Setor Sudoeste	\N	2018-07-18 00:00:00	Setor Sudoeste	Quadra QRSW 7 Bloco B-10	\N	5000032-65.2018.4.03.0000	2	\N
67	17	0	73401-098	\N	\N	DF - BRASíLIA, 73401-098, Módulo G, Estância Mestre D'Armas I (Planaltina)	\N	2018-08-27 00:00:00	Estância Mestre D'Armas I (Planaltina)	Módulo G	\N	5000651-92.2018.4.03.0000	2	\N
68	19	0	72430-310	\N	\N	DF - BRASíLIA, 72430-310, Setor de Chácaras Chácara 06, Setor Norte (Gama)	\N	2018-08-27 00:00:00	Setor Norte (Gama)	Setor de Chácaras Chácara 06	\N	5000652-77.2018.4.03.0000	2	\N
69	21	0	72720-242	\N	\N	DF - BRASíLIA, 72720-242, Quadra  Quadra 24 Chácara 1, Setor Tradicional (Brazlândia)	\N	2018-08-27 00:00:00	Setor Tradicional (Brazlândia)	Quadra Quadra 24 Chácara 1	\N	5000653-62.2018.4.03.0000	2	\N
70	31	0	49690-970	Praça Deputado Passos Porto 260	\N	SE - MONTE ALEGRE DE SERGIPE, 49690-970, AC Monte Alegre de Sergipe, Centro, Praça Deputado Passos Porto 260	\N	2018-10-29 01:00:00	Centro	AC Monte Alegre de Sergipe	\N	5000654-47.2018.4.03.0000	3	\N
71	38	0	70675-730	\N	\N	DF - BRASíLIA, 70675-730, Quadra QRSW 7 Bloco B-10, Setor Sudoeste	\N	2011-01-01 00:00:00	Setor Sudoeste	Quadra QRSW 7 Bloco B-10	\N	5000001-11.2019.4.03.0000	2	\N
72	41	0	70675-730	\N	\N	DF - BRASíLIA, 70675-730, Quadra QRSW 7 Bloco B-10, Setor Sudoeste	\N	2011-01-01 00:00:00	Setor Sudoeste	Quadra QRSW 7 Bloco B-10	\N	5000004-63.2019.4.03.0000	2	\N
73	44	0	70675-730	\N	\N	DF - BRASíLIA, 70675-730, Quadra QRSW 7 Bloco B-10, Setor Sudoeste	\N	2011-01-01 00:00:00	Setor Sudoeste	Quadra QRSW 7 Bloco B-10	\N	5000005-48.2019.4.03.0000	2	\N
74	45	0	70675-730	\N	\N	DF - BRASíLIA, 70675-730, Quadra QRSW 7 Bloco B-10, Setor Sudoeste	\N	2019-01-01 00:00:00	Setor Sudoeste	Quadra QRSW 7 Bloco B-10	\N	5000006-33.2019.4.03.0000	2	\N
75	51	0	70675-730	\N	\N	DF - BRASíLIA, 70675-730, Quadra QRSW 7 Bloco B-10, Setor Sudoeste	\N	2019-01-01 00:00:00	Setor Sudoeste	Quadra QRSW 7 Bloco B-10	\N	5000007-18.2019.4.03.0000	2	\N
77	54	0	70675-730	\N	\N	DF - BRASíLIA, 70675-730, Quadra QRSW 7 Bloco B-10, Setor Sudoeste	\N	2019-01-01 00:00:00	Setor Sudoeste	Quadra QRSW 7 Bloco B-10	\N	5000009-85.2019.4.03.0000	2	pje-legacy
78	55	0	70675-730	\N	\N	DF - BRASíLIA, 70675-730, Quadra QRSW 7 Bloco B-10, Setor Sudoeste	\N	2019-01-01 00:00:00	Setor Sudoeste	Quadra QRSW 7 Bloco B-10	\N	5000010-70.2019.4.03.0000	2	pje-legacy
\.


--
-- TOC entry 2892 (class 0 OID 1134413)
-- Dependencies: 247
-- Data for Name: tb_processo_proc_origem_aud; Type: TABLE DATA; Schema: audit; Owner: postgres
--

COPY tb_processo_proc_origem_aud (id_processo_proc_origem, rev, revtype, nr_ano, in_ativo, dt_instauracao, dt_lavratura, nr_protocolo_policia, nr_procedimento, in_retombamento_redistribuicao, uf, id_org_procedimento_originario, id_processo, id_processo_proc_origem_retombado, id_tipo_origem, id_tipo_procedimento_origem) FROM stdin;
55	13	0	2018	t	2018-07-18 00:00:00	2018-07-18 00:00:00	asdasd	1	f	DF	13	65	\N	7	1
56	15	0	2018	t	2018-07-18 00:00:00	2018-07-18 00:00:00	asdasd	1	f	DF	13	66	\N	7	1
57	17	0	2018	t	2018-08-27 00:00:00	2018-08-27 00:00:00	1795679	1	f	DF	13	67	\N	7	1
58	19	0	2018	t	2018-08-27 00:00:00	2018-08-27 00:00:00	13216a546	2	f	DF	13	68	\N	7	1
59	21	0	2018	t	2018-08-27 00:00:00	2018-08-27 00:00:00	a1s3d2a03s	1	f	DF	13	69	\N	7	1
60	31	0	2018	t	2018-10-01 00:00:00	2018-10-01 00:00:00	12313	1	f	DF	13	70	\N	7	1
61	41	0	2018	t	2019-01-29 00:00:00	2019-01-29 00:00:00	132132	1	f	DF	13	72	\N	7	1
62	44	0	2019	t	2019-01-01 00:00:00	2019-01-01 00:00:00	1232131	1	f	DF	13	73	\N	7	1
63	45	0	2019	t	2019-01-29 00:00:00	2019-01-29 00:00:00	23r	1	f	DF	13	74	\N	7	1
64	51	0	2018	t	2019-01-29 00:00:00	2019-01-29 00:00:00	asdasd	1	f	DF	13	75	\N	7	1
66	54	0	2018	t	2019-01-01 00:00:00	2019-01-01 00:00:00	\N	1	f	DF	13	77	\N	7	1
67	55	0	2018	t	2019-01-01 00:00:00	2019-01-01 00:00:00	\N	1	f	DF	13	78	\N	7	1
\.


--
-- TOC entry 2893 (class 0 OID 1134426)
-- Dependencies: 248
-- Data for Name: tb_tipo_evento_criminal_aud; Type: TABLE DATA; Schema: audit; Owner: postgres
--

COPY tb_tipo_evento_criminal_aud (id_tipo_evento_criminal, rev, revtype, in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) FROM stdin;
\.


--
-- TOC entry 2894 (class 0 OID 1134439)
-- Dependencies: 249
-- Data for Name: tb_tipo_origem_aud; Type: TABLE DATA; Schema: audit; Owner: postgres
--

COPY tb_tipo_origem_aud (id_tipo_origem, rev, revtype, in_ativo, ds_tipo_origem, in_obrigatorio_numero_origem) FROM stdin;
\.


--
-- TOC entry 2895 (class 0 OID 1134449)
-- Dependencies: 250
-- Data for Name: tb_tp_org_tp_proced_origem_aud; Type: TABLE DATA; Schema: audit; Owner: postgres
--

COPY tb_tp_org_tp_proced_origem_aud (rev, id_tipo_origem, id_tipo_procedimento_origem, revtype) FROM stdin;
\.


--
-- TOC entry 2896 (class 0 OID 1134459)
-- Dependencies: 251
-- Data for Name: tb_tp_procedimento_origem_aud; Type: TABLE DATA; Schema: audit; Owner: postgres
--

COPY tb_tp_procedimento_origem_aud (id_tipo_procedimento_origem, rev, revtype, in_ativo, ds_tipo_procedimento) FROM stdin;
\.


SET search_path = core, pg_catalog;

--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 239
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 56, true);


--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 189
-- Name: sq_processo_proc_origem; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_processo_proc_origem', 67, true);


--
-- TOC entry 2917 (class 0 OID 0)
-- Dependencies: 240
-- Name: sq_tb_classe_judicial; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_classe_judicial', 1, false);


--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 190
-- Name: sq_tb_dispositivo; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_dispositivo', 1, false);


--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 191
-- Name: sq_tb_informacao_criminal; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_informacao_criminal', 25, true);


--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 192
-- Name: sq_tb_motivo_prisao; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_motivo_prisao', 3, true);


--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 193
-- Name: sq_tb_motivo_soltura; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_motivo_soltura', 6, true);


--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 254
-- Name: sq_tb_motivo_suspensao_processo; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_motivo_suspensao_processo', 8, true);


--
-- TOC entry 2923 (class 0 OID 0)
-- Dependencies: 194
-- Name: sq_tb_municipio; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_municipio', 3, true);


--
-- TOC entry 2924 (class 0 OID 0)
-- Dependencies: 195
-- Name: sq_tb_natureza_prisao; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_natureza_prisao', 1, false);


--
-- TOC entry 2925 (class 0 OID 0)
-- Dependencies: 196
-- Name: sq_tb_norma; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_norma', 1, false);


--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 197
-- Name: sq_tb_org_prcdmnto_originario; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_org_prcdmnto_originario', 15, true);


--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 198
-- Name: sq_tb_parte; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_parte', 133, true);


--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 199
-- Name: sq_tb_processo; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_processo', 78, true);


--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 200
-- Name: sq_tb_tipo_dispositivo; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_tipo_dispositivo', 1, false);


--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 241
-- Name: sq_tb_tipo_ec_movimento; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_tipo_ec_movimento', 1, false);


--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 236
-- Name: sq_tb_tipo_evento_criminal; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_tipo_evento_criminal', 12, true);


--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 201
-- Name: sq_tb_tipo_norma; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_tipo_norma', 1, false);


--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 202
-- Name: sq_tb_tipo_origem; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_tipo_origem', 82, true);


--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 203
-- Name: sq_tb_tipo_prisao; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_tipo_prisao', 9, true);


--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 256
-- Name: sq_tb_tipo_recurso; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_tipo_recurso', 5, true);


--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 204
-- Name: sq_tb_tipo_soltura; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_tipo_soltura', 5, true);


--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 205
-- Name: sq_tb_tp_procedimento_origem; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_tp_procedimento_origem', 6, true);


--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 206
-- Name: sq_tb_unidade_prisional; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_unidade_prisional', 1, true);


--
-- TOC entry 2883 (class 0 OID 1133621)
-- Dependencies: 238
-- Data for Name: tb_classe_judicial; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_classe_judicial (id_classe_judicial, in_ativo, classe_judicial, cd_classe_judicial, id_tipo_ic) FROM stdin;
\.


--
-- TOC entry 2852 (class 0 OID 1133299)
-- Dependencies: 207
-- Data for Name: tb_dispositivo; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_dispositivo (id_dispositivo, id_tipo_dispositivo, id_norma, id_dispositivo_pai, ds_simbolo, ds_identificador, ds_texto, dt_inicio_vigencia, dt_fim_vigencia, in_ativo) FROM stdin;
153	3	2	152	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nas mesmas penas incorre quem:</p>	1940-12-07	\N	t
223	4	2	220	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em razão de ofício, emprego ou profissão.</p>	1940-12-07	\N	t
389	3	2	388	§	Parágrafo Único	Preencher	1940-12-07	\N	t
154	4	2	153	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">cerceia o uso de qualquer meio de transporte por parte do trabalhador, com o fim de retê-lo no local de trabalho;</p>	1940-12-07	\N	t
2	2	2	\N	Art.	13	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">O resultado, de que depende a existência do crime, somente é imputável a quem lhe deu causa. Considera-se causa a ação ou omissão sem a qual o resultado não teria ocorrido.</p>	1940-12-07	\N	t
3	3	2	2	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A superveniência de causa relativamente independente exclui a imputação quando, por si só, produziu o resultado; os fatos anteriores, entretanto, imputam-se a quem os praticou.</p>	1940-12-07	\N	t
4	3	2	2	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A omissão é penalmente relevante quando o omitente devia e podia agir para evitar o resultado. O dever de agir incumbe a quem:</p>	1940-12-07	\N	t
5	5	2	4	\N	a	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">tenha por lei obrigação de cuidado, proteção ou vigilância;</p>	1940-12-07	\N	t
6	5	2	4	\N	b	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">de outra forma, assumiu a responsabilidade de impedir o resultado;</p>	1940-12-07	\N	t
7	5	2	4	\N	c	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">com seu comportamento anterior, criou o risco da ocorrência do resultado. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
8	2	2	\N	Art.	14	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Diz-se o crime:</p>	1940-12-07	\N	t
9	3	2	8	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Salvo disposição em contrário, pune-se a tentativa com a pena correspondente ao crime consumado, diminuída de um a dois terços. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
10	4	2	8	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">tentado, quando, iniciada a execução, não se consuma por circunstâncias alheias à vontade do agente.</p>	1940-12-07	\N	t
11	2	2	\N	Art.	16	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nos crimes cometidos sem violência ou grave ameaça à pessoa, reparado o dano ou restituída a coisa, até o recebimento da denúncia ou da queixa, por ato voluntário do agente, a pena será reduzida de um a dois terços. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
12	2	2	\N	Art.	18	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Diz-se o crime:</p>	1940-12-07	\N	t
13	4	2	12	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">doloso, quando o agente quis o resultado ou assumiu o risco de produzi-lo;</p>	1940-12-07	\N	t
14	4	2	12	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">culposo, quando o agente deu causa ao resultado por imprudência, negligência ou imperícia.</p>	1940-12-07	\N	t
15	2	2	\N	Art.	19	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Pelo resultado que agrava especialmente a pena, só responde o agente que o houver causado ao menos culposamente. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
16	2	2	\N	Art.	20	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">O erro sobre elemento constitutivo do tipo legal de crime exclui o dolo, mas permite a punição por crime culposo, se previsto em lei.</p>	1940-12-07	\N	t
17	3	2	16	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Responde pelo crime o terceiro que determina o erro.</p>	1940-12-07	\N	t
18	2	2	\N	Art.	22	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o fato é cometido sob coação irresistível ou em estrita obediência a ordem, não manifestamente ilegal, de superior hierárquico, só é punível o autor da coação ou da ordem. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
19	2	2	\N	Art.	24	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Considera-se em estado de necessidade quem pratica o fato para salvar de perigo atual, que não provocou por sua vontade, nem podia de outro modo evitar, direito próprio ou alheio, cujo sacrifício, nas circunstâncias, não era razoável exigir-se.</p>	1940-12-07	\N	t
20	3	2	19	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Embora seja razoável exigir-se o sacrifício do direito ameaçado, a pena poderá ser reduzida de um a dois terços. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
21	4	2	20	\N	II	Preencher	1940-12-07	\N	t
22	2	2	\N	Art.	26	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">É isento de pena o agente que, por doença mental ou desenvolvimento mental incompleto ou retardado, era, ao tempo da ação ou da omissão, inteiramente incapaz de entender o caráter ilícito do fato ou de determinar-se de acordo com esse entendimento.</p>	1940-12-07	\N	t
23	3	2	22	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena pode ser reduzida de um a dois terços, se o agente, em virtude de perturbação de saúde mental ou por desenvolvimento mental incompleto ou retardado não era inteiramente capaz de entender o caráter ilícito do fato ou de determinar-se de acordo com esse entendimento. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
24	2	2	\N	Art.	28	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Não excluem a imputabilidade penal:</p>	1940-12-07	\N	t
25	3	2	24	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena pode ser reduzida de um a dois terços, se o agente, por embriaguez, proveniente de caso fortuito ou força maior, não possuía, ao tempo da ação ou da omissão, a plena capacidade de entender o caráter ilícito do fato ou de determinar-se de acordo com esse entendimento. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
26	2	2	\N	Art.	29	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Quem, de qualquer modo, concorre para o crime incide nas penas a este cominadas, na medida de sua culpabilidade.</p>	1940-12-07	\N	t
115	4	2	114	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se o abandono ocorre em lugar ermo;</p>	1940-12-07	\N	t
334	5	2	331	\N	c	Preencher	1940-12-07	\N	t
27	3	2	26	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se a participação for de menor importância, a pena pode ser diminuída de um sexto a um terço.</p>	1940-12-07	\N	t
28	3	2	26	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se algum dos concorrentes quis participar de crime menos grave, ser-lhe-á aplicada a pena deste; essa pena será aumentada até metade, na hipótese de ter sido previsível o resultado mais grave. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
29	2	2	\N	Art.	60	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na fixação da pena de multa o juiz deve atender, principalmente, à situação econômica do réu.</p>	1940-12-07	\N	t
30	3	2	29	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A multa pode ser aumentada até o triplo, se o juiz considerar que, em virtude da situação econômica do réu, é ineficaz, embora aplicada no máximo.</p>	1940-12-07	\N	t
31	3	2	29	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena privativa de liberdade aplicada, não superior a 6 (seis) meses, pode ser substituída pela de multa, observados os critérios dos incisos II e III do art. 44 deste Código. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
32	2	2	\N	Art.	61	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">São circunstâncias que sempre agravam a pena, quando não constituem ou qualificam o crime: (\\"Caput\\" do artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
33	4	2	32	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">a reincidência; (Inciso com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
34	4	2	32	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">ter o agente cometido o crime: (Inciso com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
35	5	2	34	\N	a	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">por motivo fútil ou torpe; (Alínea com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
36	5	2	34	\N	b	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">para facilitar ou assegurar a execução, a ocultação, a impunidade ou vantagem de outro crime; (Alínea com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
37	5	2	34	\N	c	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">à traição, de emboscada, ou mediante dissimulação, ou outro recurso que dificultou ou tornou impossível a defesa do ofendido; (Alínea com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
38	5	2	34	\N	d	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">com emprego de veneno, fogo, explosivo, tortura ou outro meio insidioso ou cruel, ou de que podia resultar perigo comum; (Alínea com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
39	5	2	34	\N	e	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">contra ascendente, descendente, irmão ou cônjuge; (Alínea com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
40	5	2	34	\N	f	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">com abuso de autoridade ou prevalecendo-se de relações domésticas, de coabitação ou de hospitalidade, ou com violência contra a mulher na forma da lei específica; (Alínea acrescida pela Lei nº 11.340, de 7/8/2006)</p>	1940-12-07	\N	t
41	5	2	34	\N	g	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">com abuso de poder ou violação de dever inerente a cargo, ofício, ministério ou profissão; (Alínea com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
42	5	2	34	\N	h	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">contra criança, maior de 60 (sessenta) anos, enfermo ou mulher grávida; (Alínea com redação dada pela Lei nº 10.741, de 1/10/2003)</p>	1940-12-07	\N	t
43	5	2	34	\N	i	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">quando o ofendido estava sob a imediata proteção da autoridade; (Alínea com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
44	5	2	34	\N	j	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em ocasião de incêndio, naufrágio, inundação ou qualquer calamidade pública, ou de desgraça particular do ofendido; (Alínea com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
45	5	2	34	\N	l	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em estado de embriaguez preordenada. (Alínea com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
46	2	2	\N	Art.	62	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena será ainda agravada em relação ao agente que:</p>	1940-12-07	\N	t
47	4	2	46	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">promove, ou organiza a cooperação no crime ou dirige a atividade dos demais agentes;</p>	1940-12-07	\N	t
48	4	2	46	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">coage ou induz outrem à execução material do crime;</p>	1940-12-07	\N	t
49	4	2	46	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">instiga ou determina a cometer o crime alguém sujeito à sua autoridade ou não-punível em virtude de condição ou qualidade pessoal;</p>	1940-12-07	\N	t
50	4	2	46	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">executa o crime, ou nele participa, mediante paga ou promessa de recompensa. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
51	2	2	\N	Art.	65	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">São circunstâncias que sempre atenuam a pena:</p>	1940-12-07	\N	t
52	4	2	51	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">ser o agente menor de 21 (vinte e um), na data do fato, ou maior de 70 (setenta) anos, na data da sentença;</p>	1940-12-07	\N	t
53	4	2	51	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">o desconhecimento da lei;</p>	1940-12-07	\N	t
54	4	2	51	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">ter o agente:</p>	1940-12-07	\N	t
55	5	2	54	\N	a	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">cometido o crime por motivo de relevante valor social ou moral;</p>	1940-12-07	\N	t
56	5	2	54	\N	b	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">procurado, por sua espontânea vontade e com eficiência, logo após o crime, evitar-lhe ou minorar-lhe as conseqüências, ou ter, antes do julgamento, reparado o dano;</p>	1940-12-07	\N	t
57	5	2	54	\N	c	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">cometido o crime sob coação a que podia resistir, ou em cumprimento de ordem de autoridade superior, ou sob a influência de violenta emoção, provocada por ato injusto da vítima;</p>	1940-12-07	\N	t
58	5	2	54	\N	d	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">confessado espontaneamente, perante a autoridade, a autoria do crime;</p>	1940-12-07	\N	t
59	5	2	54	\N	e	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">cometido o crime sob a influência de multidão em tumulto, se não o provocou. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
60	2	2	\N	Art.	66	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena poderá ser ainda atenuada em razão de circunstância relevante, anterior ou posterior ao crime, embora não prevista expressamente em lei. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
61	2	2	\N	Art.	69	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Quando o agente, mediante mais de uma ação ou omissão, pratica dois ou mais crimes, idênticos ou não, aplicam-se cumulativamente as penas privativas de liberdade em que haja incorrido. No caso de aplicação cumulativa de penas de reclusão e de detenção, executa-se primeiro aquela.</p>	1940-12-07	\N	t
62	2	2	\N	Art.	70	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Quando o agente, mediante uma só ação ou omissão, pratica dois ou mais crimes, idênticos ou não, aplica-se-lhe a mais grave das penas cabíveis ou, se iguais, somente uma delas, mas aumentada, em qualquer caso, de um sexto até metade. As penas aplicam-se, entretanto, cumulativamente, se a ação ou omissão é dolosa e os crimes concorrentes resultam de desígnios autônomos, consoante o disposto no artigo anterior.</p>	1940-12-07	\N	t
63	3	2	62	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Não poderá a pena exceder a que seria cabível pela regra do art. 69 deste Código. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
64	2	2	\N	Art.	71	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Quando o agente, mediante mais de uma ação ou omissão, pratica dois ou mais crimes da mesma espécie e, pelas condições de tempo, lugar, maneira de execução e outras semelhantes, devem os subseqüentes ser havidos como continuação do primeiro, aplica-se-lhe a pena de um só dos crimes, se idênticas, ou a mais grave, se diversas, aumentada, em qualquer caso, de um sexto a dois terços.</p>	1940-12-07	\N	t
65	3	2	64	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nos crimes dolosos, contra vítimas diferentes, cometidos com violência ou grave ameaça à pessoa, poderá o juiz, considerando a culpabilidade, os antecedentes, a conduta social e a personalidade do agente, bem como os motivos e as circunstâncias, aumentar a pena de um só dos crimes, se idênticas, ou a mais grave, se diversas, até o triplo, observadas as regras do parágrafo único do art. 70 e do art. 75 deste Código. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
66	2	2	\N	Art.	73	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Quando, por acidente ou erro no uso dos meios de execução, o agente, ao invés de atingir a pessoa que pretendia ofender, atinge pessoa diversa, responde como se tivesse praticado o crime contra aquela, atendendo-se ao disposto no § 3º do art. 20 deste Código. No caso de ser também atingida a pessoa que o agente pretendia ofender, aplica-se a regra do art. 70 deste Código. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
67	2	2	\N	Art.	74	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Fora dos casos do artigo anterior, quando, por acidente ou erro na execução do crime, sobrevém resultado diverso do pretendido, o agente responde por culpa, se o fato é previsto como crime culposo; se ocorre também o resultado pretendido, aplica-se a regra do art. 70 deste Código. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
68	2	2	\N	Art.	97	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o agente for inimputável, o juiz determinará sua internação (art. 26). Se, todavia, o fato previsto como crime for punível com detenção, poderá o juiz submetê-lo a tratamento ambulatorial.</p>	1940-12-07	\N	t
69	3	2	68	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A internação, ou tratamento ambulatorial, será por tempo indeterminado, perdurando enquanto não for averiguada, mediante perícia médica, a cessação de periculosidade. O prazo mínimo deverá ser de 1 (um) a 3 (três) anos.</p>	1940-12-07	\N	t
70	2	2	\N	Art.	98	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na hipótese do parágrafo único do art. 26 deste Código e necessitando o condenado de especial tratamento curativo, a pena privativa de liberdade pode ser substituída pela internação, ou tratamento ambulatorial, pelo prazo mínimo de 1 (um) a 3 (três) anos, nos termos do artigo anterior e respectivos §§ 1º a 4º. (Artigo com redação dada pela Lei nº 7.209, de 11/7/1984)</p>	1940-12-07	\N	t
71	2	2	\N	Art.	121	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Matar alguem (nao comparar este artigo)</p>	1940-12-07	\N	t
72	3	2	71	§	1	Preencher	1940-12-07	\N	t
73	3	2	71	§	2	Preencher	1940-12-07	\N	t
74	4	2	73	\N	I	Preencher	1940-12-07	\N	t
75	4	2	73	\N	II	Preencher	1940-12-07	\N	t
76	4	2	73	\N	III	Preencher	1940-12-07	\N	t
77	4	2	73	\N	IV	Preencher	1940-12-07	\N	t
78	4	2	73	\N	V	Preencher	1940-12-07	\N	t
79	3	2	71	§	3	Preencher	1940-12-07	\N	t
80	3	2	71	§	4	Preencher	1940-12-07	\N	t
81	2	2	\N	Art.	122	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Induzir ou instigar alguém a suicidar-se ou prestar-lhe auxílio para que o faça:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a seis anos, se o suicídio se consuma; ou reclusão, de um a três anos, se da tentativa de suicídio resulta lesão corporal de natureza grave.</p>	1940-12-07	\N	t
82	2	2	\N	Art.	123	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Matar, sob a influência do estado puerperal, o próprio filho, durante o parto ou logo após:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de dois a seis anos.</p>	1940-12-07	\N	t
177	3	2	174	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Equipara-se à coisa móvel a energia elétrica ou qualquer outra que tenha valor econômico.</p>	1940-12-07	\N	t
83	2	2	\N	Art.	124	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Provocar aborto em si mesma ou consentir que outrem lho provoque:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos.</p>	1940-12-07	\N	t
84	2	2	\N	Art.	125	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Provocar aborto, sem o consentimento da gestante:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de três a dez anos.</p>	1940-12-07	\N	t
85	2	2	\N	Art.	126	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Provocar aborto com o consentimento da gestante:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos.</p>	1940-12-07	\N	t
86	2	2	\N	Art.	127	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">As penas cominadas nos dois artigos anteriores são aumentadas de um terço, se, em conseqüência do aborto ou dos meios empregados para provocá-lo, a gestante sofre lesão corporal de natureza grave; e são duplicadas, se, por qualquer dessas causas, lhe sobrevem a morte.</p>	1940-12-07	\N	t
148	4	2	145	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se a privação da liberdade dura mais de quinze dias.</p>	1940-12-07	\N	t
87	2	2	\N	Art.	129	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Ofender a integridade corporal ou a saúde de outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano.</p>	1940-12-07	\N	t
88	3	2	87	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se resulta:</p>	1940-12-07	\N	t
89	4	2	88	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incapacidade para as ocupações habituais, por mais de trinta dias;</p>	1940-12-07	\N	t
90	4	2	88	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">perigo de vida;</p>	1940-12-07	\N	t
91	4	2	88	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">debilidade permanente de membro, sentido ou função;</p>	1940-12-07	\N	t
92	4	2	88	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">aceleração de parto:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a cinco anos.</p>	1940-12-07	\N	t
93	3	2	87	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se resulta:</p>	1940-12-07	\N	t
94	4	2	93	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incapacidade permanente para o trabalho;</p>	1940-12-07	\N	t
95	4	2	93	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">enfermidade incuravel;</p>	1940-12-07	\N	t
96	4	2	93	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">perda ou inutilização de membro, sentido ou função;</p>	1940-12-07	\N	t
97	4	2	93	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">deformidade permanente;</p>	1940-12-07	\N	t
98	4	2	93	\N	V	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">aborto:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a oito anos.</p>	1940-12-07	\N	t
99	3	2	87	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se resulta morte e as circunstâncias evidenciam que o agente não quís o resultado, nem assumiu o risco de produzí-lo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de quatro a doze anos.</p>	1940-12-07	\N	t
100	3	2	87	§	4	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o agente comete o crime impelido por motivo de relevante valor social ou moral ou sob o domínio de violenta emoção, logo em seguida a injusta provocação da vítima, o juiz pode reduzir a pena de um sexto a um terço.</p>	1940-12-07	\N	t
101	3	2	87	§	6	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se a lesão é culposa:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de dois meses a um ano.</p>	1940-12-07	\N	t
102	3	2	87	§	7	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Aumenta-se a pena de um terço, se ocorrer qualquer das hipóteses do art. 121, § 4º. (arágrafo com redação dada pela Lei nº 8.069, de 13/7/1990)</p>	1940-12-07	\N	t
103	3	2	87	§	9	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se a lesão for praticada contra ascendente, descendente, irmão, cônjuge ou companheiro, ou com quem conviva ou tenha convivido, ou, ainda, prevalecendo-se o agente das relações domésticas, de coabitação ou de hospitalidade:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 3 (três) meses a 3 (três) anos. (arágrafo acrescido pela Lei nº 10.886, de 17/7/2004 e com nova redação dada pela Lei nº 11.340, de 7/8/2006)</p>	1940-12-07	\N	t
104	3	2	87	§	10	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nos casos previstos nos §§ 1º a 3º deste artigo, se as circunstâncias são as indicadas no § 9º deste artigo, aumenta-se a pena em 1/3 (um terço). (arágrafo acrescido pela Lei nº 10.886, de 17/7/2004)</p>	1940-12-07	\N	t
105	3	2	87	§	11	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na hipótese do § 9º deste artigo, a pena será aumentada de um terço se o crime for cometido contra pessoa portadora de deficiência. (arágrafo acrescido pela Lei nº 11.340, de 7/8/2006)</p>	1940-12-07	\N	t
106	2	2	\N	Art.	130	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Expor alguém, por meio de relações sexuais ou qualquer ato libidinoso, a contágio de moléstia venérea, de que sabe ou deve saber que está contaminado:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, ou multa.</p>	1940-12-07	\N	t
107	3	2	106	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se é intenção do agente transmitir a moléstia:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa.</p>	1940-12-07	\N	t
131	2	2	\N	Art.	140	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Injuriar alguém, ofendendo-lhe a dignidade ou o decoro:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a seis meses, ou multa.</p>	1940-12-07	\N	t
108	2	2	\N	Art.	131	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Praticar, com o fim de transmitir a outrem moléstia grave de que está contaminado, ato capaz de produzir o contágio:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa.</p>	1940-12-07	\N	t
109	2	2	\N	Art.	132	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Expor a vida ou a saúde de outrem a perigo direto e iminente:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, se o fato não constitui crime mais grave.</p>	1940-12-07	\N	t
110	3	2	109	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada de um sexto a um terço se a exposição da vida ou da saúde de outrem a perigo decorre do transporte de pessoas para a prestação de serviços em estabelecimentos de qualquer natureza, em desacordo com as normas legais. (Parágrafo único acrescido pela Lei nº 9.777, de 29/12/1998)</p>	1940-12-07	\N	t
111	2	2	\N	Art.	133	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Abandonar pessoa que está sob seu cuidado, guarda, vigilância ou autoridade, e, por qualquer motivo, incapaz de defender-se dos riscos resultantes do abandono:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a três anos.</p>	1940-12-07	\N	t
112	3	2	111	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se do abandono resulta lesão corporal de natureza grave:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a cinco anos.</p>	1940-12-07	\N	t
113	3	2	111	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se resulta a morte:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de quatro a doze anos.</p>	1940-12-07	\N	t
329	2	2	\N	Art.	223	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">(Revogado pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
114	3	2	111	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">As penas cominadas neste artigo aumentam-se de um terço:</p>	1940-12-07	\N	t
116	4	2	114	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se o agente é ascendente ou descendente, cônjuge, irmão, tutor ou curador da vítima;</p>	1940-12-07	\N	t
117	4	2	114	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se a vítima é maior de 60 (sessenta) anos. (Inciso acrescido pela Lei nº 10.741, de 1/10/2003)</p>	1940-12-07	\N	t
180	4	2	178	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">com abuso de confiança, ou mediante fraude, escalada ou destreza;</p>	1940-12-07	\N	t
118	2	2	\N	Art.	134	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Expor ou abandonar recém-nascido, para ocultar desonra própria:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos.</p>	1940-12-07	\N	t
119	3	2	118	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se do fato resulta lesão corporal de natureza grave:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos.</p>	1940-12-07	\N	t
120	3	2	118	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se resulta a morte:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de dois a seis anos.</p>	1940-12-07	\N	t
121	2	2	\N	Art.	135	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Deixar de prestar assistência, quando possível fazê-lo sem risco pessoal, a criança abandonada ou extraviada, ou a pessoa inválida ou ferida, ao desamparo ou em grave e iminente perigo; ou não pedir, nesses casos, o socorro da autoridade pública:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de um a seis meses, ou multa.</p>	1940-12-07	\N	t
122	2	2	\N	Art.	136	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Expor a perigo a vida ou a saúde de pessoa sob sua autoridade, guarda ou vigilância, para fim de educação, ensino, tratamento ou custódia, quer privando-a de alimentação ou cuidados indispensáveis, quer sujeitando-a a trabalho excessivo ou inadequado, quer abusando de meios de correção ou disciplina:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de dois meses a um ano, ou multa.</p>	1940-12-07	\N	t
123	3	2	122	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se do fato resulta lesão corporal de natureza grave:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos.</p>	1940-12-07	\N	t
124	3	2	122	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se resulta a morte:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de quatro a doze anos.</p>	1940-12-07	\N	t
125	3	2	122	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Aumenta-se a pena de um terço, se o crime é praticado contra pessoa menor de catorze anos. (arágrafo acrescido pela Lei nº 8.069, de 13/7/1990)</p>	1940-12-07	\N	t
126	2	2	\N	Art.	137	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Participar de rixa, salvo para separar os contendores:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de quinze dias a dois meses, ou multa.</p>	1940-12-07	\N	t
127	3	2	126	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se ocorre morte ou lesão corporal de natureza grave, aplica-se, pelo fato da participação na rixa, a pena de detenção, de seis meses a dois anos.</p>	1940-12-07	\N	t
128	2	2	\N	Art.	138	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Caluniar alguém, imputando-lhe falsamente fato definido como crime:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, e multa.</p>	1940-12-07	\N	t
129	3	2	128	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na mesma pena incorre quem, sabendo falsa a imputação, a propala ou divulga.</p>	1940-12-07	\N	t
130	2	2	\N	Art.	139	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Difamar alguém, imputando-lhe fato ofensivo à sua reputação:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, e multa.</p>	1940-12-07	\N	t
132	3	2	131	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se a injúria consiste em violência ou vias de fato, que, por sua natureza ou pelo meio empregado, se considerem aviltantes:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, e multa, além da pena correspondente à violência.</p>	1940-12-07	\N	t
133	3	2	131	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se a injúria consiste na utilização de elementos referentes a raça, cor, etnia, religião, origem ou a condição de pessoa idosa ou portadora de deficiência:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão de um a três anos e multa. (arágrafo acrescido pela Lei nº 9.459, de 13/5/1997 e com nova redação dada pela Lei nº 10.741, de 1/10/2003)</p>	1940-12-07	\N	t
134	2	2	\N	Art.	141	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">As penas cominadas neste capítulo aumentam-se de um terço, se qualquer dos crimes é cometido:</p>	1940-12-07	\N	t
135	3	2	134	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido mediante paga ou promessa de recompensa, aplica-se a pena em dobro.</p>	1940-12-07	\N	t
136	4	2	134	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">contra o Presidente da República, ou contra chefe de governo estrangeiro;</p>	1940-12-07	\N	t
137	4	2	134	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">contra funcionário público, em razão de suas funções;</p>	1940-12-07	\N	t
138	4	2	134	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">na presença de várias pessoas, ou por meio que facilite a divulgação da calúnia, da difamação ou da injúria.</p>	1940-12-07	\N	t
139	4	2	134	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">contra pessoa maior de 60 (sessenta) anos ou portadora de deficiência, exceto no caso de injúria. (Inciso acrescido pela Lei nº 10.741, de 1/10/2003)</p>	1940-12-07	\N	t
140	2	2	\N	Art.	146	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Constranger alguém, mediante violência ou grave ameaça, ou depois de lhe haver reduzido, por qualquer outro meio, a capacidade de resistência, a não fazer o que a lei permite, ou a fazer o que ela não manda:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, ou multa.</p>	1940-12-07	\N	t
141	3	2	140	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">As penas aplicam-se cumulativamente e em dobro, quando, para a execução do crime, se reúnem mais de três pessoas, ou há emprego de armas.</p>	1940-12-07	\N	t
142	3	2	140	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Além das penas cominadas, aplicam-se as correspondentes à violência.</p>	1940-12-07	\N	t
330	3	2	329	§	Parágrafo Único	Preencher	1940-12-07	\N	t
143	2	2	\N	Art.	147	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Ameaçar alguém, por palavra, escrito ou gesto, ou qualquer outro meio simbólico, de causar-lhe mal injusto e grave:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a seis meses, ou multa.</p>	1940-12-07	\N	t
144	2	2	\N	Art.	148	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Privar alguém de sua liberdade, mediante seqüestro ou cárcere privado:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a três anos.</p>	1940-12-07	\N	t
145	3	2	144	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é de reclusão, de dois a cinco anos:</p>	1940-12-07	\N	t
146	4	2	145	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se a vítima é ascendente, descendente, cônjuge ou companheiro do agente ou maior de 60 (sessenta) anos; (Inciso com redação dada pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
147	4	2	145	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se o crime é praticado mediante internação da vítima em casa de saúde ou hospital;</p>	1940-12-07	\N	t
149	4	2	145	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se o crime é praticado contra menor de 18 (dezoito) anos; (Inciso acrescido pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
150	4	2	145	\N	V	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se o crime é praticado com fins libidinosos. (Inciso acrescido pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
151	3	2	144	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se resulta à vítima, em razão de maus tratos ou da natureza da detenção, grave sofrimento físico ou moral:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a oito anos.</p>	1940-12-07	\N	t
152	2	2	\N	Art.	149	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Reduzir alguém a condição análoga à de escravo, quer submetendo-o a trabalhos forçados ou a jornada exaustiva, quer sujeitando-o a condições degradantes de trabalho, quer restringindo, por qualquer meio, sua locomoção em razão de dívida contraída com o empregador ou preposto:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a oito anos, e multa, além da pena correspondente à violência.</p>	1940-12-07	\N	t
155	4	2	153	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">mantém vigilância ostensiva no local de trabalho ou se apodera de documentos ou objetos pessoais do trabalhador, com o fim de retê-lo no local de trabalho.</p>	1940-12-07	\N	t
156	3	2	152	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada de metade, se o crime é cometido:</p>	1940-12-07	\N	t
157	4	2	156	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">contra criança ou adolescente;</p>	1940-12-07	\N	t
158	4	2	156	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">por motivo de preconceito de raça, cor, etnia, religião ou origem. (Artigo com redação dada pela Lei nº 10.803, de 11/12/2003)</p>	1940-12-07	\N	t
159	2	2	\N	Art.	150	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Entrar ou permanecer, clandestina ou astuciosamente, ou contra a vontade expressa ou tácita de quem de direito, em casa alheia ou em suas dependências:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três meses, ou multa.</p>	1940-12-07	\N	t
160	3	2	159	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido durante a noite, ou em lugar ermo, ou com o emprego de violência ou de arma, ou por duas ou mais pessoas:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, além da pena correspondente à violência.</p>	1940-12-07	\N	t
161	3	2	159	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Aumenta-se a pena de um terço, se o fato é cometido por funcionário público, fora dos casos legais, ou com inobservância das formalidades estabelecidas em lei, ou com abuso do poder.</p>	1940-12-07	\N	t
162	2	2	\N	Art.	151	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Devassar indevidamente o conteúdo de correspondência fechada, dirigida a outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a seis meses, ou multa.</p>	1940-12-07	\N	t
163	3	2	162	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na mesma pena incorre:</p>	1940-12-07	\N	t
164	4	2	163	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">quem se apossa indevidamente de correspondência alheia, embora não fechada e, no todo ou em parte, a sonega ou destrói;</p>	1940-12-07	\N	t
165	4	2	163	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">quem indevidamente divulga, transmite a outrem ou utiliza abusivamente comunicação telegráfica ou radioelétrica dirigida a terceiro, ou conversação telefônica entre outras pessoas;</p>	1940-12-07	\N	t
166	4	2	163	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">quem impede a comunicação ou a conversação referidas no número anterior;</p>	1940-12-07	\N	t
167	4	2	163	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">quem instala ou utiliza estação ou aparelho radioelétrico, sem observância de disposição legal.</p>	1940-12-07	\N	t
168	3	2	162	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">As penas aumentam-se de metade, se há dano para outrem.</p>	1940-12-07	\N	t
169	3	2	162	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o agente comete o crime, com abuso de função em serviço postal, telegráfico, radioelétrico ou telefônico:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos.</p>	1940-12-07	\N	t
170	2	2	\N	Art.	152	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Abusar da condição de sócio ou empregado de estabelecimento comercial ou industrial para, no todo ou em parte, desviar, sonegar, subtrair ou suprimir correspondência, ou revelar a estranho o seu conteúdo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a dois anos.</p>	1940-12-07	\N	t
171	2	2	\N	Art.	153	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Divulgar alguém, sem justa causa, conteúdo de documento particular ou de correspondência confidencial, de que é destinatário ou detentor, e cuja divulgação possa produzir dano a outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de um a seis meses, ou multa.</p>	1940-12-07	\N	t
172	3	2	171	§	1A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Divulgar, sem justa causa, informações sigilosas ou reservadas, assim definidas em lei, contidas ou não nos sistemas de informações ou banco de dados da Administração Pública.</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 1 (um) a 4 (quatro) anos, e multa. (arágrafo acrescido pela Lei nº 9.983, de 14/7/2000)</p>	1940-12-07	\N	t
259	4	2	254	\N	V	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">o diretor ou o gerente que, como garantia de crédito social, aceita em penhor ou em caução ações da própria sociedade;</p>	1940-12-07	\N	t
173	2	2	\N	Art.	154	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Revelar alguém, sem justa causa, segredo, de que tem ciência em razão de função, ministério, ofício ou profissão, e cuja revelação possa produzir dano a outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, ou multa.</p>	1940-12-07	\N	t
174	2	2	\N	Art.	155	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Subtrair, para si ou para outrem, coisa alheia móvel:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa.</p>	1940-12-07	\N	t
175	3	2	174	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena aumenta-se de um terço, se o crime é praticado durante o repouso noturno.</p>	1940-12-07	\N	t
176	3	2	174	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o criminoso é primário, e é de pequeno valor a coisa furtada, o juiz pode substituir a pena de reclusão pela de detenção, diminuí-la de um a dois terços, ou aplicar somente a pena de multa.</p>	1940-12-07	\N	t
178	3	2	174	§	4	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é de reclusão de dois a oito anos, e multa, se o crime é cometido:</p>	1940-12-07	\N	t
179	4	2	178	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">com destruição ou rompimento de obstáculo à subtração da coisa;</p>	1940-12-07	\N	t
181	4	2	178	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">com emprego de chave falsa;</p>	1940-12-07	\N	t
182	4	2	178	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">mediante concurso de duas ou mais pessoas.</p>	1940-12-07	\N	t
183	3	2	174	§	5	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é de reclusão de três a oito anos, se a subtração for de veículo automotor que venha a ser transportado para outro Estado ou para o exterior. (arágrafo acrescido pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
184	2	2	\N	Art.	156	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Subtrair o condômino, co-herdeiro ou sócio, para si ou para outrem, a quem legitimamente a detém, a coisa comum:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, ou multa.</p>	1940-12-07	\N	t
185	2	2	\N	Art.	157	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Subtrair coisa móvel alheia, para si ou para outrem, mediante grave ameaça ou violência a pessoa, ou depois de havê-la, por qualquer meio, reduzido à impossibilidade de resistência:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de quatro a dez anos, e multa.</p>	1940-12-07	\N	t
186	3	2	185	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na mesma pena incorre quem, logo depois de subtraída a coisa, emprega violência contra pessoa ou grave ameaça, afim de assegurar a impunidade do crime ou a detenção da coisa para si ou para terceiro.</p>	1940-12-07	\N	t
187	3	2	185	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena aumenta-se de um terço até metade:</p>	1940-12-07	\N	t
188	4	2	187	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se a violência ou ameaça é exercida com emprego de arma;</p>	1940-12-07	\N	t
189	4	2	187	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se há o concurso de duas ou mais pessoas;</p>	1940-12-07	\N	t
190	4	2	187	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se a vítima está em serviço de transporte de valores e o agente conhece tal circunstância.</p>	1940-12-07	\N	t
191	4	2	187	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se a subtração for de veículo automotor que venha a ser transportado para outro Estado ou para o exterior; (Inciso acrescido pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
192	4	2	187	\N	V	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se o agente mantém a vítima em seu poder, restringindo sua liberdade. (Inciso acrescido pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
193	3	2	185	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se da violência resulta lesão corporal grave, a pena é de reclusão, de sete a quinze anos, além da multa; se resulta morte, a reclusão é de vinte a trinta anos, sem prejuízo da multa. (arágrafo com redação dada pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
194	2	2	\N	Art.	158	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Constranger alguém, mediante violência ou grave ameaça, e com o intuito de obter para si ou para outrem indevida vantagem econômica, a fazer, tolerar que se faça ou deixar de fazer alguma coisa:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de quatro a dez anos, e multa.</p>	1940-12-07	\N	t
195	3	2	194	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido por duas ou mais pessoas, ou com emprego de arma, aumenta-se a pena de um terço até metade.</p>	1940-12-07	\N	t
196	3	2	194	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Aplica-se à extorsão praticada mediante violência o disposto no § 3º do artigo anterior.</p>	1940-12-07	\N	t
197	3	2	194	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido mediante a restrição da liberdade da vítima, e essa condição é necessária para a obtenção da vantagem econômica, a pena é de reclusão, de 6 (seis) a 12 (doze) anos, além da multa; se resulta lesão corporal grave ou morte, aplicam-se as penas previstas no art. 159, §§ 2º e 3º, respectivamente. (arágrafo acrescido pela Lei nº 11.923, de 17/4/2009)</p>	1940-12-07	\N	t
198	2	2	\N	Art.	159	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Seqüestrar pessoa com o fim de obter, para si ou para outrem, qualquer vantagem, como condição ou preço do resgate:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de oito a quinze anos. (\\"Caput\\" do artigo com redação dada pela Lei nº 8.072, de 25/7/1990)</p>	1940-12-07	\N	t
201	3	2	198	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se resulta a morte:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de vinte e quatro a trinta anos. (arágrafo com redação dada pela Lei nº 8.072, de 25/7/1990)</p>	1940-12-07	\N	t
202	3	2	198	§	4	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido em concurso, o concorrente que o denunciar à autoridade, facilitando a libertação do seqüestrado, terá sua pena reduzida de um a dois terços. (arágrafo acrescido pela Lei nº 8.072, de 25/7/1990 e com nova redação dada pela Lei nº 9.269, de 2/4/1996)</p>	1940-12-07	\N	t
234	3	2	233	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o criminoso é primário, e é de pequeno valor o prejuízo, o juiz pode aplicar a pena conforme o disposto no art. 155, § 2º.</p>	1940-12-07	\N	t
331	2	2	\N	Art.	224	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">(Revogado pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
332	5	2	331	\N	a	Preencher	1940-12-07	\N	t
203	2	2	\N	Art.	160	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Exigir ou receber, como garantia de dívida, abusando da situação de alguém, documento que pode dar causa a procedimento criminal contra a vítima ou contra terceiro:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a três anos, e multa.</p>	1940-12-07	\N	t
204	2	2	\N	Art.	161	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Suprimir ou deslocar tapume, marco, ou qualquer outro sinal indicativo de linha divisória, para apropriar-se, no todo ou em parte, de coisa imóvel alheia:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a seis meses, e multa.</p>	1940-12-07	\N	t
205	3	2	204	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na mesma pena incorre quem:</p>	1940-12-07	\N	t
306	2	2	\N	Art.	214	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">(Revogado pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
206	4	2	205	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">desvia ou represa, em proveito próprio ou de outrem, águas alheias;</p>	1940-12-07	\N	t
207	4	2	205	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">invade, com violência a pessoa ou grave ameaça, ou mediante concurso de mais de duas pessoas, terreno ou edifício alheio, para o fim de esbulho possessório.</p>	1940-12-07	\N	t
208	3	2	204	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o agente usa de violência, incorre também na pena a esta cominada.</p>	1940-12-07	\N	t
209	2	2	\N	Art.	162	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Suprimir ou alterar, indevidamente, em gado ou rebanho alheio, marca ou sinal indicativo de propriedade:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a três anos, e multa.</p>	1940-12-07	\N	t
210	2	2	\N	Art.	163	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Destruir, inutilizar ou deteriorar coisa alheia:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a seis meses, ou multa.</p>	1940-12-07	\N	t
211	3	2	210	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido:</p>	1940-12-07	\N	t
212	4	2	211	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">com violência a pessoa ou grave ameaça;</p>	1940-12-07	\N	t
213	4	2	211	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">com emprego de substância inflamável ou explosiva, se o fato não constitui crime mais grave;</p>	1940-12-07	\N	t
214	4	2	211	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">contra o patrimônio da União, Estado, Município, empresa concessionária de serviços públicos ou sociedade de economia mista; (Inciso com redação dada pela Lei nº 5.346, de 3/11/1967)</p>	1940-12-07	\N	t
215	4	2	211	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">por motivo egoístico ou com prejuízo considerável para a vítima;</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a três anos, e multa, além da pena correspondente à violência.</p>	1940-12-07	\N	t
216	2	2	\N	Art.	164	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Introduzir ou deixar animais em propriedade alheia, sem consentimento de quem de direito, desde que do fato resulte prejuízo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de quinze dias a seis meses, ou multa.</p>	1940-12-07	\N	t
217	2	2	\N	Art.	165	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Destruir, inutilizar ou deteriorar coisa tombada pela autoridade competente em virtude de valor artístico, arqueológico ou histórico:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, e multa.</p>	1940-12-07	\N	t
218	2	2	\N	Art.	166	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Alterar, sem licença da autoridade competente, o aspecto de local especialmente protegido por lei:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, ou multa.</p>	1940-12-07	\N	t
219	2	2	\N	Art.	168	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Apropriar-se de coisa alheia móvel, de que tem a posse ou a detenção:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa.</p>	1940-12-07	\N	t
220	3	2	219	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada de um terço, quando o agente recebeu a coisa:</p>	1940-12-07	\N	t
221	4	2	220	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em depósito necessário;</p>	1940-12-07	\N	t
222	4	2	220	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">na qualidade de tutor, curador, síndico, liquidatário, inventariante, testamenteiro ou depositário judicial;</p>	1940-12-07	\N	t
224	2	2	\N	Art.	168A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Deixar de repassar à previdência social as contribuições recolhidas dos contribuintes, no prazo e forma legal ou convencional;</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 5 (cinco) anos, e multa.</p>	1940-12-07	\N	t
225	3	2	224	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nas mesmas penas incorre quem deixar de:</p>	1940-12-07	\N	t
226	4	2	225	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">recolher, no prazo legal, contribuição ou outra importância destinada à previdência social que tenha sido descontada de pagamento efetuado a segurados, a terceiros ou arrecadada do público;</p>	1940-12-07	\N	t
227	4	2	225	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">recolher contribuições devidas à previdência social que tenham integrado despesas contábeis ou custos relativos à venda de produtos ou à prestação de serviços;</p>	1940-12-07	\N	t
228	4	2	225	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">pagar benefício devido a segurado, quando as respectivas cotas ou valores já tiverem sido reembolsados à empresa pela previdência social;</p>	1940-12-07	\N	t
229	2	2	\N	Art.	169	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Apropriar-se alguém de coisa alheia vinda ao seu poder por erro, caso fortuito ou força da natureza:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, ou multa.</p>	1940-12-07	\N	t
230	3	2	229	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na mesma pena incorre:</p>	1940-12-07	\N	t
231	4	2	230	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">quem acha tesouro em prédio alheio e se apropria, no todo ou em parte, da quota a que tem direito o proprietário do prédio;</p>	1940-12-07	\N	t
232	4	2	230	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">quem acha coisa alheia perdida e dela se apropria, total ou parcialmente, deixando de restituí-la ao dono ou legítimo possuidor ou de entregá-la à autoridade competente, dentro do prazo de quinze dias.</p>	1940-12-07	\N	t
260	4	2	254	\N	VI	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">o diretor ou o gerente que, na falta de balanço, em desacordo com este, ou mediante balanço falso, distribui lucros ou dividendos fictícios;</p>	1940-12-07	\N	t
233	2	2	\N	Art.	171	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Obter, para si ou para outrem, vantagem ilícita, em prejuízo alheio, induzindo ou mantendo alguém em erro, mediante artifício, ardil, ou qualquer outro meio fraudulento:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a cinco anos, e multa.</p>	1940-12-07	\N	t
235	3	2	233	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nas mesmas penas incorre quem:</p>	1940-12-07	\N	t
236	4	2	235	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">vende, permuta, dá em pagamento, em locação ou em garantia coisa alheia como própria;</p>	1940-12-07	\N	t
237	4	2	235	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">vende, permuta, dá em pagamento ou em garantia coisa própria inalienável, gravada de ônus ou litigiosa, ou imóvel que prometeu vender a terceiro, mediante pagamento em prestações, silenciando sobre qualquer dessas circunstâncias;</p>	1940-12-07	\N	t
238	4	2	235	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">defrauda, mediante alienação não consentida pelo credor ou por outro modo, a garantia pignoratícia, quando tem a posse do objeto empenhado;</p>	1940-12-07	\N	t
239	4	2	235	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">defrauda substância, qualidade ou quantidade de coisa que deve entregar a alguém;</p>	1940-12-07	\N	t
240	4	2	235	\N	V	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">destrói, total ou parcialmente, ou oculta coisa própria, ou lesa o próprio corpo ou a saúde, ou agrava as conseqüências da lesão ou doença, com o intuito de haver indenização ou valor de seguro;</p>	1940-12-07	\N	t
241	4	2	235	\N	VI	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">emite cheque, sem suficiente provisão de fundos em poder do sacado, ou lhe frustra o pagamento.</p>	1940-12-07	\N	t
242	3	2	233	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena aumenta-se de um terço, se o crime é cometido em detrimento de entidade de direito público ou de instituto de economia popular, assistência social ou beneficência.</p>	1940-12-07	\N	t
342	3	2	339	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido com o fim de lucro, aplica-se também multa.</p>	1940-12-07	\N	t
243	2	2	\N	Art.	172	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Emitir fatura, duplicata ou nota de venda que não corresponda à mercadoria vendida, em quantidade ou qualidade, ou ao serviço prestado.</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 2 (dois) a 4 (quatro) anos, e multa. (\\"Caput\\" do artigo com redação dada pela Lei nº 8.137, de 27/12/1990)</p>	1940-12-07	\N	t
244	3	2	243	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nas mesmas penas incorrerá aquêle que falsificar ou adulterar a escrituração do Livro de Registro de Duplicatas. (Parágrafo único acrescido pela Lei nº 5.474, de 18/7/1968)</p>	1940-12-07	\N	t
245	2	2	\N	Art.	173	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Abusar, em proveito próprio ou alheio, de necessidade, paixão ou inexperiência de menor, ou da alienação ou debilidade mental de outrem, induzindo qualquer deles à prática de ato suscetível de produzir efeito jurídico, em prejuízo próprio ou de terceiro:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a seis anos, e multa.</p>	1940-12-07	\N	t
246	2	2	\N	Art.	174	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Abusar, em proveito próprio ou alheio, da inexperiência ou da simplicidade ou inferioridade mental de outrem, induzindo-o à prática de jogo ou aposta, ou à especulação com títulos ou mercadorias, sabendo ou devendo saber que a operação é ruinosa:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a três anos, e multa.</p>	1940-12-07	\N	t
247	2	2	\N	Art.	175	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Enganar, no exercício de atividade comercial, o adquirente ou consumidor:</p>	1940-12-07	\N	t
248	4	2	247	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">vendendo, como verdadeira ou perfeita, mercadoria falsificada ou deteriorada;</p>	1940-12-07	\N	t
249	4	2	247	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">entregando uma mercadoria por outra:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, ou multa.</p>	1940-12-07	\N	t
250	3	2	247	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Alterar em obra que lhe é encomendada a qualidade ou o peso de metal ou substituir, no mesmo caso, pedra verdadeira por falsa ou por outra de menor valor; vender pedra falsa por verdadeira; vender, como precioso, metal de outra qualidade:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a cinco anos, e multa.</p>	1940-12-07	\N	t
251	3	2	247	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">É aplicável o disposto no art. 155, § 2º.</p>	1940-12-07	\N	t
252	2	2	\N	Art.	176	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Tomar refeição em restaurante, alojar-se em hotel ou utilizar-se de meio de transporte sem dispor de recursos para efetuar o pagamento:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de quinze dias a dois meses, ou multa.</p>	1940-12-07	\N	t
253	2	2	\N	Art.	177	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Promover a fundação de sociedade por ações, fazendo, em prospecto ou em comunicação ao público ou à assembléia, afirmação falsa sobre a constituição da sociedade, ou ocultando fraudulentamente fato a ela relativo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa, se o fato não constitui crime contra a economia popular.</p>	1940-12-07	\N	t
254	3	2	253	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorrem na mesma pena, se o fato não constitui crime contra a economia popular:</p>	1940-12-07	\N	t
255	4	2	254	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">o diretor, o gerente ou o fiscal de sociedade por ações, que, em prospecto, relatório, parecer, balanço ou comunicação ao público ou à assembléia, faz afirmação falsa sobre as condições econômicas da sociedade, ou oculta fraudulentamente, no todo ou em parte, fato a elas relativo;</p>	1940-12-07	\N	t
256	4	2	254	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">o diretor, o gerente ou o fiscal que promove, por qualquer artifício, falsa cotação das ações ou de outros títulos da sociedade;</p>	1940-12-07	\N	t
257	4	2	254	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">o diretor ou o gerente que toma empréstimo à sociedade ou usa, em proveito próprio ou de terceiro, dos bens ou haveres sociais, sem prévia autorização da assembléia geral;</p>	1940-12-07	\N	t
258	4	2	254	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">o diretor ou o gerente que compra ou vende, por conta da sociedade, ações por ela emitidas, salvo quando a lei o permite;</p>	1940-12-07	\N	t
261	4	2	254	\N	VII	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">o diretor, o gerente ou o fiscal que, por interposta pessoa, ou conluiado com acionista, consegue a aprovação de conta ou parecer;</p>	1940-12-07	\N	t
262	4	2	254	\N	VIII	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">o liquidante, nos casos dos ns. I, II, III, IV, V e VII;</p>	1940-12-07	\N	t
263	4	2	254	\N	IX	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">o representante da sociedade anônima estrangeira, autorizada a funcionar no país, que pratica os atos mencionados nos ns. I e II, ou dá falsa informação ao Governo.</p>	1940-12-07	\N	t
264	3	2	253	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre na pena de detenção, de seis meses a dois anos, e multa, o acionista que, afim de obter vantagem para si ou para outrem, negocia o voto nas deliberações de assembléia geral.</p>	1940-12-07	\N	t
265	2	2	\N	Art.	178	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Emitir conhecimento de depósito ou warrant, em desacordo com disposição legal:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa.</p>	1940-12-07	\N	t
266	2	2	\N	Art.	179	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Fraudar execução, alienando, desviando, destruindo ou danificando bens, ou simulando dívidas:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, ou multa.</p>	1940-12-07	\N	t
267	2	2	\N	Art.	180	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Adquirir, receber, transportar, conduzir ou ocultar, em proveito próprio ou alheio, coisa que sabe ser produto de crime, ou influir para que terceiro, de boa-fé, a adquira, receba ou oculte:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa. (\\"Caput\\" do artigo com redação dada pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
341	3	2	339	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido com emprego de violência, grave ameaça ou fraude:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a oito anos, além da pena correspondente à violência.</p>	1940-12-07	\N	t
268	3	2	267	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Adquirir, receber, transportar, conduzir, ocultar, ter em depósito, desmontar, montar, remontar, vender, expor à venda, ou de qualquer forma utilizar, em proveito próprio ou alheio, no exercício de atividade comercial ou industrial, coisa que deve saber ser produto de crime:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de três a oito anos, e multa. (arágrafo com redação dada pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
269	3	2	267	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Equipara-se à atividade comercial, para efeito do parágrafo anterior, qualquer forma de comércio irregular ou clandestino, inclusive o exercido em residência. (arágrafo com redação dada pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
270	3	2	267	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Adquirir ou receber coisa que, por sua natureza ou pela desproporção entre o valor e o preço, ou pela condição de quem a oferece, deve presumir-se obtida por meio criminoso:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, ou multa, ou ambas as penas. (arágrafo com redação dada pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
271	3	2	267	§	5	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na hipótese do § 3º, se o criminoso é primário, pode o juiz, tendo em consideração as circunstâncias, deixar de aplicar a pena. Na receptação dolosa aplica-se o disposto no § 2º do art. 155. (arágrafo acrescido pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
272	3	2	267	§	6	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Tratando-se de bens e instalações do patrimônio da União, Estado, Município, empresa concessionária de serviços públicos ou sociedade de economia mista, a pena prevista no caput deste artigo aplica-se em dobro. (arágrafo acrescido pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
273	2	2	\N	Art.	184	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Violar direitos de autor e os que lhe são conexos:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 3 (três) meses a 1 (um) ano, ou multa. (\\"Caput\\" do artigo com redação dada pela Lei nº 10.695, de 1/7/2003)</p>	1940-12-07	\N	t
274	3	2	273	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se a violação consistir em reprodução total ou parcial, com intuito de lucro direto ou indireto, por qualquer meio ou processo, de obra intelectual, interpretação, execução ou fonograma, sem autorização expressa do autor, do artista intérprete ou executante, do produtor, conforme o caso, ou de quem os represente:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 4 (quatro) anos, e multa. (arágrafo com redação dada pela Lei nº 10.695, de 1/7/2003)</p>	1940-12-07	\N	t
275	3	2	273	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na mesma pena do § 1º incorre quem, com o intuito de lucro direto ou indireto, distribui, vende, expõe à venda, aluga, introduz no País, adquire, oculta, tem em depósito, original ou cópia de obra intelectual ou fonograma reproduzido com violação do direito de autor, do direito de artista intérprete ou executante ou do direito do produtor de fonograma, ou, ainda, aluga original ou cópia de obra intelectual ou fonograma, sem a expressa autorização dos titulares dos direitos ou de quem os represente. (arágrafo com redação dada pela Lei nº 10.695, de 1/7/2003)</p>	1940-12-07	\N	t
276	3	2	273	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se a violação consistir no oferecimento ao público, mediante cabo, fibra ótica, satélite, ondas ou qualquer outro sistema que permita ao usuário realizar a seleção da obra ou produção para recebê-la em um tempo e lugar previamente determinados por quem formula a demanda, com intuito de lucro, direto ou indireto, sem autorização expressa, conforme o caso, do autor, do artista intérprete ou executante, do produtor de fonograma, ou de quem os represente:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 4 (quatro) anos, e multa. (arágrafo com redação dada pela Lei nº 10.695, de 1/7/2003)</p>	1940-12-07	\N	t
277	2	2	\N	Art.	197	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Constranger alguém, mediante violência ou grave ameaça:</p>	1940-12-07	\N	t
278	4	2	277	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">a exercer ou não exercer arte, ofício, profissão ou indústria, ou a trabalhar ou não trabalhar durante certo período ou em determinados dias:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, e multa, além da pena correspondente à violência;</p>	1940-12-07	\N	t
279	4	2	277	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">a abrir ou fechar o seu estabelecimento de trabalho, ou a participar de parede ou paralisação de atividade econômica:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, e multa, além da pena correspondente à violência.</p>	1940-12-07	\N	t
327	2	2	\N	Art.	221	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">(Revogado pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
280	2	2	\N	Art.	198	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Constranger alguém, mediante violência ou grave ameaça, a celebrar contrato de trabalho, ou a não fornecer a outrem ou não adquirir de outrem matéria prima ou produto industrial ou agrícola:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, e multa, além da pena correspondente à violência.</p>	1940-12-07	\N	t
281	2	2	\N	Art.	199	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Constranger alguém, mediante violência ou grave ameaça, a participar ou deixar de participar de determinado sindicato ou associação profissional:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, e multa, além da pena correspondente à violência.</p>	1940-12-07	\N	t
305	3	2	303	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se da conduta resulta morte:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 12 (doze) a 30 (trinta) anos. (arágrafo acrescido pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
282	2	2	\N	Art.	200	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Participar de suspensão ou abandono coletivo de trabalho, praticando violência contra pessoa ou contra coisa:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, e multa, além da pena correspondente à violência.</p>	1940-12-07	\N	t
283	2	2	\N	Art.	201	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Participar de suspensão ou abandono coletivo de trabalho, provocando a interrupção de obra pública ou serviço de interesse coletivo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, e multa.</p>	1940-12-07	\N	t
284	2	2	\N	Art.	202	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Invadir ou ocupar estabelecimento industrial, comercial ou agrícola, com o intuito de impedir ou embaraçar o curso normal do trabalho, ou com o mesmo fim danificar o estabelecimento ou as coisas nele existentes ou delas dispor:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a três anos, e multa.</p>	1940-12-07	\N	t
285	2	2	\N	Art.	203	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Frustrar, mediante fraude ou violência, direito assegurado pela legislação do trabalho:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de um ano a dois anos, e multa, além da pena correspondente à violência. (\\"Caput\\" do artigo com redação dada pela Lei nº 9.777, de 29/12/1998)</p>	1940-12-07	\N	t
286	3	2	285	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na mesma pena incorre quem:</p>	1940-12-07	\N	t
287	4	2	286	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">obriga ou coage alguém a usar mercadorias de determinado estabelecimento, para impossibilitar o desligamento do serviço em virtude de dívida;</p>	1940-12-07	\N	t
288	4	2	286	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">impede alguém de se desligar de serviços de qualquer natureza, mediante coação ou por meio da retenção de seus documentos pessoais ou contratuais. (arágrafo acrescido pela Lei nº 9.777, de 29/12/1998)</p>	1940-12-07	\N	t
289	3	2	285	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada de um sexto a um terço se a vítima é menor de dezoito anos, idosa, gestante, indígena ou portadora de deficiência física ou mental. (arágrafo acrescido pela Lei nº 9.777, de 29/12/1998)</p>	1940-12-07	\N	t
290	2	2	\N	Art.	204	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Frustrar, mediante fraude ou violência, obrigação legal relativa à nacionalização do trabalho:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, e multa, além da pena correspondente à violência.</p>	1940-12-07	\N	t
291	2	2	\N	Art.	205	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Exercer atividade, de que está impedido por decisão administrativa:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a dois anos, ou multa.</p>	1940-12-07	\N	t
292	2	2	\N	Art.	206	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Recrutar trabalhadores, mediante fraude, com o fim de levá-los para território estrangeiro.</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos e multa. (Artigo com redação dada pela Lei nº 8.683, de 15/7/1993)</p>	1940-12-07	\N	t
293	2	2	\N	Art.	207	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Aliciar trabalhadores, com o fim de levá-los de uma para outra localidade do território nacional:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de um a três anos, e multa. (\\"Caput\\" do artigo com redação dada pela Lei nº 9.777, de 29/12/1998)</p>	1940-12-07	\N	t
294	3	2	293	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre na mesma pena quem recrutar trabalhadores fora da localidade de execução do trabalho, dentro do território nacional, mediante fraude ou cobrança de qualquer quantia do trabalhador, ou, ainda, não assegurar condições do seu retorno ao local de origem. (arágrafo acrescido pela Lei nº 9.777, de 29/12/1998)</p>	1940-12-07	\N	t
295	3	2	293	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada de um sexto a um terço se a vítima é menor de dezoito anos, idosa, gestante, indígena ou portadora de deficiência física ou mental. (arágrafo acrescido pela Lei nº 9.777, de 29/12/1998)</p>	1940-12-07	\N	t
345	3	2	343	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido com emprego de violência, grave ameaça ou fraude:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de quatro a dez anos, além da pena correspondente à violência.</p>	1940-12-07	\N	t
296	2	2	\N	Art.	208	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Escarnecer de alguém publicamente, por motivo de crença ou função religiosa; impedir ou perturbar cerimônia ou prática de culto religioso; vilipendiar publicamente ato ou objeto de culto religioso:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, ou multa.</p>	1940-12-07	\N	t
297	3	2	296	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se há emprego de violência, a pena é aumentada de um terço, sem prejuízo da correspondente à violência.</p>	1940-12-07	\N	t
298	2	2	\N	Art.	209	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Impedir ou perturbar enterro ou cerimônia funerária:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, ou multa.</p>	1940-12-07	\N	t
299	3	2	298	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se há emprego de violência a pena é aumentada de um terço, sem prejuízo da correspondente à violência.</p>	1940-12-07	\N	t
328	2	2	\N	Art.	222	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">(Revogado pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
300	2	2	\N	Art.	210	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Violar ou profanar sepultura ou urna funerária:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a três anos, e multa.</p>	1940-12-07	\N	t
301	2	2	\N	Art.	211	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Destruir, subtrair ou ocultar cadáver ou parte dele:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a três anos, e multa.</p>	1940-12-07	\N	t
302	2	2	\N	Art.	212	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Vilipendiar cadáver ou suas cinzas:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos, e multa.</p>	1940-12-07	\N	t
303	2	2	\N	Art.	213	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Constranger alguém, mediante violência ou grave ameaça, a ter conjunção carnal ou a praticar ou permitir que com ele se pratique outro ato libidinoso:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 6 (seis) a 10 (dez) anos. (\\"Caput\\" do artigo com redação dada pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
304	3	2	303	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se da conduta resulta lesão corporal de natureza grave ou se a vítima é menor de 18 (dezoito) ou maior de 14 (catorze) anos:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 8 (oito) a 12 (doze) anos. (arágrafo acrescido pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
307	2	2	\N	Art.	215	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Ter conjunção carnal ou praticar outro ato libidinoso com alguém, mediante fraude ou outro meio que impeça ou dificulte a livre manifestação de vontade da vítima:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 6 (seis) anos. (\\"Caput\\" do artigo com redação dada pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
308	3	2	307	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido com o fim de obter vantagem econômica, aplica-se também multa. (Parágrafo único com redação dada pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
309	2	2	\N	Art.	216	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">(Revogado pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
310	3	2	309	§	Parágrafo Único	Preencher	1940-12-07	\N	t
396	2	2	\N	Art.	247	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Permitir alguém que menor de dezoito anos, sujeito a seu poder ou confiado a sua guarda ou vigilância:</p>	1940-12-07	\N	t
311	2	2	\N	Art.	216A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Constranger alguém com o intuito de obter vantagem ou favorecimento sexual, prevalecendo-se o agente da sua condição de superior hierárquico ou ascendência inerentes ao exercício de emprego, cargo ou função.</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 1 (um) a 2 (dois) anos. (\\"Caput\\" do artigo acrescido pela Lei nº 10.224, de 15/5/2001)</p>	1940-12-07	\N	t
312	3	2	311	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada em até um terço se a vítima é menor de 18 (dezoito) anos. (arágrafo acrescido pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
313	2	2	\N	Art.	217A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Ter conjunção carnal ou praticar outro ato libidinoso com menor de 14 (catorze) anos:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 8 (oito) a 15 (quinze) anos.</p>	1940-12-07	\N	t
314	3	2	313	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre na mesma pena quem pratica as ações descritas no caput com alguém que, por enfermidade ou deficiência mental, não tem o necessário discernimento para a prática do ato, ou que, por qualquer outra causa, não pode oferecer resistência.</p>	1940-12-07	\N	t
315	3	2	313	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se da conduta resulta lesão corporal de natureza grave:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 10 (dez) a 20 (vinte) anos.</p>	1940-12-07	\N	t
316	3	2	313	§	4	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se da conduta resulta morte:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 12 (doze) a 30 (trinta) anos. (Artigo acrescido pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
317	2	2	\N	Art.	218	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Induzir alguém menor de 14 (catorze) anos a satisfazer a lascívia de outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 5 (cinco) anos.</p>	1940-12-07	\N	t
318	2	2	\N	Art.	218A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Praticar, na presença de alguém menor de 14 (catorze) anos, ou induzi-lo a presenciar, conjunção carnal ou outro ato libidinoso, a fim de satisfazer lascívia própria ou de outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 4 (quatro) anos. (Artigo acrescido pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
346	3	2	343	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido com o fim de lucro, aplica-se também multa.</p>	1940-12-07	\N	t
319	2	2	\N	Art.	218B	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Submeter, induzir ou atrair à prostituição ou outra forma de exploração sexual alguém menor de 18 (dezoito) anos ou que, por enfermidade ou deficiência mental, não tem o necessário discernimento para a prática do ato, facilitá-la, impedir ou dificultar que a abandone:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 4 (quatro) a 10 (dez) anos.</p>	1940-12-07	\N	t
320	3	2	319	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é praticado com o fim de obter vantagem econômica, aplica-se também multa.</p>	1940-12-07	\N	t
321	3	2	319	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre nas mesmas penas:</p>	1940-12-07	\N	t
322	4	2	321	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">quem pratica conjunção carnal ou outro ato libidinoso com alguém menor de 18 (dezoito) e maior de 14 (catorze) anos na situação descrita no caput deste artigo;</p>	1940-12-07	\N	t
323	4	2	321	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">o proprietário, o gerente ou o responsável pelo local em que se verifiquem as práticas referidas no caput deste artigo.</p>	1940-12-07	\N	t
324	3	2	319	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na hipótese do inciso II do § 2º, constitui efeito obrigatório da condenação a cassação da licença de localização e de funcionamento do estabelecimento. (Artigo acrescido pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
325	2	2	\N	Art.	219	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">(Revogado pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
326	2	2	\N	Art.	220	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">(Revogado pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
333	5	2	331	\N	b	Preencher	1940-12-07	\N	t
527	3	2	524	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre nas mesmas penas:</p>	1940-12-07	\N	t
335	2	2	\N	Art.	226	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada: (\\"Caput\\" com redação dada pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
336	4	2	335	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">de quarta parte, se o crime é cometido com o concurso de 2 (duas) ou mais pessoas; (Inciso com redação dada pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
361	3	2	359	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada da metade se:</p>	1940-12-07	\N	t
337	4	2	335	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">de metade, se o agente é ascendente, padrasto ou madrasta, tio, irmão, cônjuge, companheiro, tutor, curador, preceptor ou empregador da vítima ou por qualquer outro título tem autoridade sobre ela; (Inciso com redação dada pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
338	4	2	335	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">(Revogado pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
339	2	2	\N	Art.	227	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Induzir alguém a satisfazer a lascívia de outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a três anos.</p>	1940-12-07	\N	t
367	2	2	\N	Art.	232	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">(Revogado pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
368	2	2	\N	Art.	233	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Praticar ato obsceno em lugar público, ou aberto ou exposto ao público:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, ou multa.</p>	1940-12-07	\N	t
340	3	2	339	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se a vítima é maior de 14 (catorze) e menor de 18 (dezoito) anos, ou se o agente é seu ascendente, descendente, cônjuge ou companheiro, irmão, tutor ou curador ou pessoa a quem esteja confiada para fins de educação, de tratamento ou de guarda:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a cinco anos. (arágrafo com redação dada pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
343	2	2	\N	Art.	228	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Induzir ou atrair alguém à prostituição ou outra forma de exploração sexual, facilitá-la, impedir ou dificultar que alguém a abandone:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 5 (cinco) anos, e multa. (\\"Caput\\" do artigo com redação dada pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
344	3	2	343	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o agente é ascendente, padrasto, madrasta, irmão, enteado, cônjuge, companheiro, tutor ou curador, preceptor ou empregador da vítima, ou se assumiu, por lei ou outra forma, obrigação de cuidado, proteção ou vigilância:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 3 (três) a 8 (oito) anos. (arágrafo com redação dada pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
347	2	2	\N	Art.	229	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Manter, por conta própria ou de terceiro, estabelecimento em que ocorra exploração sexual, haja, ou não, intuito de lucro ou mediação direta do proprietário ou gerente: (\\"Caput\\" do artigo com redação dada pela Lei nº 12.015, de 7/8/2009)</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a cinco anos, e multa.</p>	1940-12-07	\N	t
348	2	2	\N	Art.	230	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Tirar proveito da prostituição alheia, participando diretamente de seus lucros ou fazendo-se sustentar, no todo ou em parte, por quem a exerça:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa.</p>	1940-12-07	\N	t
349	3	2	348	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se a vítima é menor de 18 (dezoito) e maior de 14 (catorze) anos ou se o crime é cometido por ascendente, padrasto, madrasta, irmão, enteado, cônjuge, companheiro, tutor ou curador, preceptor ou empregador da vítima, ou por quem assumiu, por lei ou outra forma, obrigação de cuidado, proteção ou vigilância:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 3 (três) a 6 (seis) anos, e multa. (arágrafo com redação dada pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
350	3	2	348	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido mediante violência, grave ameaça, fraude ou outro meio que impeça ou dificulte a livre manifestação da vontade da vítima:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 8 (oito) anos, sem prejuízo da pena correspondente à violência. (arágrafo com redação dada pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
351	2	2	\N	Art.	231	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Promover ou facilitar a entrada, no território nacional, de alguém que nele venha a exercer a prostituição ou outra forma de exploração sexual, ou a saída de alguém que vá exercê-la no estrangeiro.</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 3 (três) a 8 (oito) anos. (\\"Caput\\" do artigo com redação dada pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
352	3	2	351	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre na mesma pena aquele que agenciar, aliciar ou comprar a pessoa traficada, assim como, tendo conhecimento dessa condição, transportá-la, transferi-la ou alojá-la. (arágrafo com redação dada pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
353	3	2	351	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada da metade se:</p>	1940-12-07	\N	t
354	4	2	353	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">a vítima é menor de 18 (dezoito) anos;</p>	1940-12-07	\N	t
355	4	2	353	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">a vítima, por enfermidade ou deficiência mental, não tem o necessário discernimento para a prática do ato;</p>	1940-12-07	\N	t
356	4	2	353	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se o agente é ascendente, padrasto, madrasta, irmão, enteado, cônjuge, companheiro, tutor ou curador, preceptor ou empregador da vítima, ou se assumiu, por lei ou outra forma, obrigação de cuidado, proteção ou vigilância; ou</p>	1940-12-07	\N	t
357	4	2	353	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">há emprego de violência, grave ameaça ou fraude. (arágrafo com redação dada pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
358	3	2	351	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido com o fim de obter vantagem econômica, aplica-se também multa. (arágrafo acrescido pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
359	2	2	\N	Art.	231A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Promover ou facilitar o deslocamento de alguém dentro do território nacional para o exercício da prostituição ou outra forma de exploração sexual:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 6 (seis) anos. (\\"Caput\\" do artigo acrescido pela Lei nº 11.106, de 28/3/2005 e com nova redação dada pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
528	4	2	527	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">quem faz uso do selo ou sinal falsificado;</p>	1940-12-07	\N	t
360	3	2	359	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre na mesma pena aquele que agenciar, aliciar, vender ou comprar a pessoa traficada, assim como, tendo conhecimento dessa condição, transportá-la, transferi-la ou alojá-la. (Parágrafo único acrescido pela Lei nº 11.106, de 28/3/2005 e transformado em § 1º com nova redação dada pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
362	4	2	361	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">a vítima é menor de 18 (dezoito) anos;</p>	1940-12-07	\N	t
363	4	2	361	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">a vítima, por enfermidade ou deficiência mental, não tem o necessário discernimento para a prática do ato;</p>	1940-12-07	\N	t
364	4	2	361	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se o agente é ascendente, padrasto, madrasta, irmão, enteado, cônjuge, companheiro, tutor ou curador, preceptor ou empregador da vítima, ou se assumiu, por lei ou outra forma, obrigação de cuidado, proteção ou vigilância; ou</p>	1940-12-07	\N	t
365	4	2	361	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">há emprego de violência, grave ameaça ou fraude. (arágrafo acrescido pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
366	3	2	359	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido com o fim de obter vantagem econômica, aplica-se também multa. (arágrafo acrescido pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
393	3	2	392	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é de 1 (um) a 4 (quatro) anos de reclusão, se o agente pratica delito para obter lucro, ou se o menor é enviado para o exterior. (arágrafo acrescido pela Lei nº 7.251, de 19/11/1984)</p>	1940-12-07	\N	t
369	2	2	\N	Art.	234	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Fazer, importar, exportar, adquirir ou ter sob sua guarda, para fim de comércio, de distribuição ou de exposição pública, escrito, desenho, pintura, estampa ou qualquer objeto obsceno:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, ou multa.</p>	1940-12-07	\N	t
370	3	2	369	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre na mesma pena quem:</p>	1940-12-07	\N	t
371	4	2	370	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">vende, distribui ou expõe à venda ou ao público qualquer dos objetos referidos neste artigo;</p>	1940-12-07	\N	t
372	4	2	370	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">realiza, em lugar público ou acessível ao público, representação teatral, ou exibição cinematográfica de caráter obsceno, ou qualquer outro espetáculo, que tenha o mesmo caráter;</p>	1940-12-07	\N	t
373	4	2	370	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">realiza, em lugar público ou acessível ao público, ou pelo rádio, audição ou recitação de caráter obsceno.</p>	1940-12-07	\N	t
374	2	2	\N	Art.	234A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nos crimes previstos neste Título a pena é aumentada:</p>	1940-12-07	\N	t
375	4	2	374	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">de metade, se do crime resultar gravidez; e</p>	1940-12-07	\N	t
376	4	2	374	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">de um sexto até a metade, se o agente transmite à vitima doença sexualmente transmissível de que sabe ou deveria saber ser portador. (Artigo acrescido pela Lei nº 12.015, de 7/8/2009)</p>	1940-12-07	\N	t
377	2	2	\N	Art.	235	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Contrair alguém, sendo casado, novo casamento:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a seis anos.</p>	1940-12-07	\N	t
378	3	2	377	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Aquele que, não sendo casado, contrai casamento com pessoa casada, conhecendo essa circunstância, é punido com reclusão ou detenção, de um a três anos.</p>	1940-12-07	\N	t
379	2	2	\N	Art.	236	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Contrair casamento, induzindo em erro essencial o outro contraente, ou ocultando-lhe impedimento que não seja casamento anterior:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos.</p>	1940-12-07	\N	t
380	2	2	\N	Art.	237	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Contrair casamento, conhecendo a existência de impedimento que lhe cause a nulidade absoluta:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano.</p>	1940-12-07	\N	t
381	2	2	\N	Art.	238	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Atribuir-se falsamente autoridade para celebração de casamento:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos, se o fato não constitui crime mais grave.</p>	1940-12-07	\N	t
382	2	2	\N	Art.	239	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Simular casamento mediante engano de outra pessoa:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos, se o fato não constitui elemento de crime mais grave.</p>	1940-12-07	\N	t
383	2	2	\N	Art.	240	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">(Revogado pela Lei nº 11.106, de 28/3/2005)</p>	1940-12-07	\N	t
384	3	2	383	§	1	Preencher	1940-12-07	\N	t
385	2	2	\N	Art.	241	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Promover no registo civil a inscrição de nascimento inexistente:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a seis anos.</p>	1940-12-07	\N	t
386	2	2	\N	Art.	242	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Dar parto alheio como próprio; registrar como seu o filho de outrem; ocultar recém-nascido ou substituí-lo, suprimindo ou alterando direito inerente ao estado civil:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a seis anos.</p>	1940-12-07	\N	t
387	3	2	386	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é praticado por motivo de reconhecida nobreza:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a dois anos, podendo o juiz deixar de aplicar a pena. (Artigo com redação dada pela Lei nº 6.898, de 30/3/1981)</p>	1940-12-07	\N	t
388	2	2	\N	Art.	243	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Deixar em asilo de expostos ou outra instituição de assistência filho próprio ou alheio, ocultando-lhe a filiação ou atribuindo-lhe outra, com o fim de prejudicar direito inerente ao estado civil:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a cinco anos, e multa.</p>	1940-12-07	\N	t
529	4	2	527	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">quem utiliza indevidamente o selo ou sinal verdadeiro em prejuízo de outrem ou em proveito próprio ou alheio.</p>	1940-12-07	\N	t
418	3	2	416	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">As penas aumentam-se de um terço, se ocorre qualquer das hipóteses previstas no § 1º, n. I, do artigo anterior, ou é visada ou atingida qualquer das coisas enumeradas no nº II do mesmo parágrafo.</p>	1940-12-07	\N	t
471	4	2	470	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">sem registro, quando exigível, no órgão de vigilância sanitária competente;</p>	1940-12-07	\N	t
390	2	2	\N	Art.	244	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Deixar, sem justa causa, de prover a subsistência do cônjuge, ou de filho menor de 18 (dezoito) anos ou inapto para o trabalho, ou de ascendente inválido ou maior de 60 (sessenta) anos, não lhes proporcionando os recursos necessários ou faltando ao pagamento de pensão alimentícia judicialmente acordada, fixada ou majorada; deixar, sem justa causa, de socorrer descendente ou ascendente, gravemente enfermo: (\\"Caput\\" do artigo com redação dada pela Lei nº 10.741, de 1/10/2003)</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de 1 (um) ano a 4 (quatro) anos e multa, de uma a dez vezes o maior salário-mínimo vigente no País. (Pena com redação dada pela Lei nº 5.478, de 25/7/1968)</p>	1940-12-07	\N	t
391	3	2	390	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nas mesmas penas incide quem, sendo solvente, frustra ou ilide, de qualquer modo, inclusive por abandono injustificado de emprego ou função, o pagamento de pensão alimentícia judicialmente acordada, fixada ou majorada. (Parágrafo único com redação dada pela Lei nº 5.478, de 25/7/1968)</p>	1940-12-07	\N	t
505	3	2	499	§	4	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nas mesmas penas incorre quem desvia e faz circular moeda, cuja circulação não estava ainda autorizada.</p>	1940-12-07	\N	t
392	2	2	\N	Art.	245	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Entregar filho menor de dezoito anos a pessoa em cuja companhia saiba ou deva saber que o menor fica moral ou materialmente em perigo.</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de 1 (um) a 2 (dois) anos. (\\"Caput\\" do artigo com redação dada pela Lei nº 7.251, de 19/11/1984)</p>	1940-12-07	\N	t
453	3	2	452	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se do fato resulta morte, a pena é aplicada em dobro.</p>	1940-12-07	\N	t
394	3	2	392	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre, também, na pena do parágrafo anterior quem, embora excluído o perigo moral ou material, auxilia a efetivação de ato destinado ao envio de menor para o exterior, com o fito de obter lucro. (arágrafo acrescido pela Lei nº 7.251, de 19/11/1984)</p>	1940-12-07	\N	t
395	2	2	\N	Art.	246	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Deixar, sem justa causa, de prover à instrução primária de filho em idade escolar:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de quinze dias a um mês, ou multa.</p>	1940-12-07	\N	t
397	4	2	396	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">freqüente casa de jogo ou mal-afamada, ou conviva com pessoa viciosa ou de má vida;</p>	1940-12-07	\N	t
398	4	2	396	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">freqüente espetáculo capaz de pervertê-lo ou de ofender-lhe o pudor, ou participe de representação de igual natureza;</p>	1940-12-07	\N	t
399	4	2	396	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">resida ou trabalhe em casa de prostituição;</p>	1940-12-07	\N	t
400	4	2	396	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">mendigue ou sirva a mendigo para excitar a comiseração pública:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três meses, ou multa.</p>	1940-12-07	\N	t
401	2	2	\N	Art.	248	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Induzir menor de dezoito anos, ou interdito, a fugir do lugar em que se acha por determinação de quem sobre ele exerce autoridade, em virtude de lei ou de ordem judicial; confiar a outrem sem ordem do pai, do tutor ou do curador algum menor de dezoito anos ou interdito, ou deixar, sem justa causa, de entregá-lo a quem legitimamente o reclame:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, ou multa.</p>	1940-12-07	\N	t
402	2	2	\N	Art.	249	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Subtrair menor de dezoito anos ou interdito ao poder de quem o tem sob sua guarda em virtude de lei ou de ordem judicial:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de dois meses a dois anos, se o fato não constitui elemento de outro crime.</p>	1940-12-07	\N	t
403	2	2	\N	Art.	250	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Causar incêndio, expondo a perigo a vida, a integridade física ou o patrimônio de outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de três a seis anos, e multa.</p>	1940-12-07	\N	t
404	3	2	403	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">As penas aumentam-se de um terço:</p>	1940-12-07	\N	t
405	4	2	404	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se o crime é cometido com intuito de obter vantagem pecuniária em proveito próprio ou alheio;</p>	1940-12-07	\N	t
406	4	2	404	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se o incêndio é:</p>	1940-12-07	\N	t
407	5	2	406	\N	a	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em casa habitada ou destinada a habitação;</p>	1940-12-07	\N	t
408	5	2	406	\N	b	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em edifício público ou destinado a uso público ou a obra de assistência social ou de cultura;</p>	1940-12-07	\N	t
409	5	2	406	\N	c	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em embarcação, aeronave, comboio ou veículo de transporte coletivo;</p>	1940-12-07	\N	t
410	5	2	406	\N	d	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em estação ferroviária ou aeródromo;</p>	1940-12-07	\N	t
411	5	2	406	\N	e	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em estaleiro, fábrica ou oficina;</p>	1940-12-07	\N	t
412	5	2	406	\N	f	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em depósito de explosivo, combustível ou inflamável;</p>	1940-12-07	\N	t
413	5	2	406	\N	g	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em poço petrolífero ou galeria de mineração;</p>	1940-12-07	\N	t
414	5	2	406	\N	h	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em lavoura, pastagem, mata ou floresta.</p>	1940-12-07	\N	t
415	3	2	403	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se culposo o incêndio, a pena é de detenção de seis meses a dois anos.</p>	1940-12-07	\N	t
416	2	2	\N	Art.	251	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Expor a perigo a vida, a integridade física ou o patrimônio de outrem, mediante explosão, arremesso ou simples colocação de engenho de dinamite ou de substância de efeitos análogos:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de três a seis anos, e multa.</p>	1940-12-07	\N	t
417	3	2	416	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se a substância utilizada não é dinamite ou explosivo de efeitos análogos:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa.</p>	1940-12-07	\N	t
419	3	2	416	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">No caso de culpa, se a explosão é de dinamite ou substância de efeitos análogos, a pena é de detenção, de seis meses a dois anos; nos demais casos, é de detenção, de três meses a um ano.</p>	1940-12-07	\N	t
420	2	2	\N	Art.	252	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Expor a perigo a vida, a integridade física ou o patrimônio de outrem, usando de gás tóxico ou asfixiante:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa.</p>	1940-12-07	\N	t
421	3	2	420	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é culposo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano.</p>	1940-12-07	\N	t
422	2	2	\N	Art.	253	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Fabricar, fornecer, adquirir, possuir ou transportar, sem licença da autoridade, substância ou engenho explosivo, gás tóxico ou asfixiante, ou material destinado à sua fabricação:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, e multa.</p>	1940-12-07	\N	t
423	2	2	\N	Art.	254	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Causar inundação, expondo a perigo a vida, a integridade física ou o patrimônio de outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de três a seis anos, e multa, no caso de dolo, ou detenção, de seis meses a dois anos, no caso de culpa.</p>	1940-12-07	\N	t
424	2	2	\N	Art.	255	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Remover, destruir ou inutilizar, em prédio próprio ou alheio, expondo a perigo a vida, a integridade física ou o patrimônio de outrem, obstáculo natural ou obra destinada a impedir inundação:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a três anos, e multa.</p>	1940-12-07	\N	t
425	2	2	\N	Art.	256	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Causar desabamento ou desmoronamento, expondo a perigo a vida, a integridade física ou o patrimônio de outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa.</p>	1940-12-07	\N	t
426	3	2	425	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é culposo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a um ano.</p>	1940-12-07	\N	t
427	2	2	\N	Art.	257	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Subtrair, ocultar ou inutilizar, por ocasião de incêndio, inundação, naufrágio, ou outro desastre ou calamidade, aparelho, material ou qualquer meio destinado a serviço de combate ao perigo, de socorro ou salvamento; ou impedir ou dificultar serviço de tal natureza:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a cinco anos, e multa.</p>	1940-12-07	\N	t
428	2	2	\N	Art.	258	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se do crime doloso de perigo comum resulta lesão corporal de natureza grave, a pena privativa de liberdade é aumentada de metade; se resulta morte, é aplicada em dobro. No caso de culpa, se do fato resulta lesão corporal, a pena aumenta-se de metade; se resulta morte, aplica-se a pena cominada ao homicídio culposo, aumentada de um terço.</p>	1940-12-07	\N	t
429	2	2	\N	Art.	259	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Difundir doença ou praga que possa causar dano a floresta, plantação ou animais de utilidade econômica:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a cinco anos, e multa.</p>	1940-12-07	\N	t
430	3	2	429	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">No caso de culpa, a pena é de detenção, de um a seis meses, ou multa.</p>	1940-12-07	\N	t
431	2	2	\N	Art.	260	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Impedir ou perturbar serviço de estrada de ferro:</p>	1940-12-07	\N	t
432	4	2	431	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">destruindo, danificando ou desarranjando, total ou parcialmente, linha férrea, material rodante ou de tração, obra de arte ou instalação;</p>	1940-12-07	\N	t
433	4	2	431	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">colocando obstáculo na linha;</p>	1940-12-07	\N	t
434	4	2	431	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">transmitindo falso aviso acerca do movimento dos veículos ou interrompendo ou embaraçando o funcionamento de telégrafo, telefone ou rádio-telegrafia;</p>	1940-12-07	\N	t
435	4	2	431	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">praticando outro ato de que possa resultar desastre:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a cinco anos, e multa.</p>	1940-12-07	\N	t
436	3	2	431	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se do fato resulta desastre:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de quatro a doze anos, e multa.</p>	1940-12-07	\N	t
437	3	2	431	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">No caso de culpa, ocorrendo desastre:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos.</p>	1940-12-07	\N	t
438	2	2	\N	Art.	261	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Expor a perigo embarcação ou aeronave, própria ou alheia, ou praticar qualquer ato tendente a impedir ou dificultar navegação marítima, fluvial ou aérea:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a cinco anos.</p>	1940-12-07	\N	t
439	3	2	438	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se do fato resulta naufrágio, submersão ou encalhe de embarcação ou a queda ou destruição de aeronave:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de quatro a doze anos.</p>	1940-12-07	\N	t
440	3	2	438	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Aplica-se, também, a pena de multa, se o agente pratica o crime com o intuito de obter vantagem econômica, para si ou para outrem.</p>	1940-12-07	\N	t
441	3	2	438	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">No caso de culpa, se ocorre o sinistro:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos.</p>	1940-12-07	\N	t
442	2	2	\N	Art.	262	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Expor a perigo outro meio de transporte público, impedir-lhe ou dificultar-lhe o funcionamento:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a dois anos.</p>	1940-12-07	\N	t
443	3	2	442	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se do fato resulta desastre, a pena é de reclusão, de dois a cinco anos.</p>	1940-12-07	\N	t
444	3	2	442	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">No caso de culpa, se ocorre desastre:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano.</p>	1940-12-07	\N	t
445	2	2	\N	Art.	263	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se de qualquer dos crimes previstos nos arts. 260 a 262, no caso de desastre ou sinistro, resulta lesão corporal ou morte, aplica-se o disposto no art. 258.</p>	1940-12-07	\N	t
446	2	2	\N	Art.	264	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Arremessar projetil contra veículo, em movimento, destinado ao transporte público por terra, por água ou pelo ar:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a seis meses.</p>	1940-12-07	\N	t
447	3	2	446	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se do fato resulta lesão corporal, a pena é de detenção, de seis meses a dois anos; se resulta morte, a pena é a do art. 121, § 3º, aumentada de um terço.</p>	1940-12-07	\N	t
448	2	2	\N	Art.	265	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Atentar contra a segurança ou o funcionamento de serviço de água, luz, força ou calor, ou qualquer outro de utilidade pública:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a cinco anos, e multa.</p>	1940-12-07	\N	t
449	3	2	448	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Aumentar-se-á a pena de 1/3 (um terço) até a metade, se o dano ocorrer em virtude de subtração de material essencial ao funcionamento dos serviços. (Parágrafo único acrescido pela Lei nº 5.346, de 3/11/1967)</p>	1940-12-07	\N	t
450	2	2	\N	Art.	266	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Interromper ou perturbar serviço telegráfico, radiotelegráfico ou telefônico, impedir ou dificultar-lhe o restabelecimento:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos, e multa.</p>	1940-12-07	\N	t
451	3	2	450	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Aplicam-se as penas em dobro, se o crime é cometido por ocasião de calamidade pública.</p>	1940-12-07	\N	t
452	2	2	\N	Art.	267	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Causar epidemia, mediante a propagação de germes patogênicos:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dez a quinze anos. (\\"Caput\\" do artigo com redação dada pela Lei nº 8.072, de 25/7/1990)</p>	1940-12-07	\N	t
454	3	2	452	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">No caso de culpa, a pena é de detenção, de um a dois anos, ou, se resulta morte, de dois a quatro anos.</p>	1940-12-07	\N	t
455	2	2	\N	Art.	268	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Infringir determinação do poder público, destinada a impedir introdução ou propagação de doença contagiosa:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, e multa.</p>	1940-12-07	\N	t
456	3	2	455	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada de um terço, se o agente é funcionário da saúde pública ou exerce a profissão de médico, farmacêutico, dentista ou enfermeiro.</p>	1940-12-07	\N	t
561	3	2	560	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o agente comete o crime no exercício da função pública ou em razão dela, a pena é aumentada de um terço. (arágrafo acrescido pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
457	2	2	\N	Art.	269	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Deixar o médico de denunciar à autoridade pública doença cuja notificação é compulsória:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, e multa.</p>	1940-12-07	\N	t
458	2	2	\N	Art.	270	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Envenenar água potável, de uso comum ou particular, ou substância alimentícia ou medicinal destinada a consumo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dez a quinze anos. (\\"Caput\\" do artigo com redação dada pela Lei nº 8.072, de 25/7/1990)</p>	1940-12-07	\N	t
459	3	2	458	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Está sujeito à mesma pena quem entrega a consumo ou tem em depósito, para o fim de ser distribuída, a água ou a substância envenenada.</p>	1940-12-07	\N	t
460	3	2	458	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é culposo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos.</p>	1940-12-07	\N	t
461	2	2	\N	Art.	271	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Corromper ou poluir água potável, de uso comum ou particular, tornando-a imprópria para consumo ou nociva à saúde:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a cinco anos.</p>	1940-12-07	\N	t
462	3	2	461	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é culposo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de dois meses a um ano.</p>	1940-12-07	\N	t
463	2	2	\N	Art.	272	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Corromper, adulterar, falsificar ou alterar substância ou produto alimentício destinado a consumo, tornando-o nocivo à saúde ou reduzindo-lhe o valor nutritivo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 4 (quatro) a 8 (oito) anos, e multa. (\\"Caput\\" do artigo com redação dada pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
464	3	2	463	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Está sujeito às mesmas penas quem pratica as ações previstas neste artigo em relação a bebidas, com ou sem teor alcoólico. (arágrafo com redação dada pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
465	3	2	463	§	1A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre nas penas deste artigo quem fabrica, vende, expõe à venda, importa, tem em depósito para vender ou, de qualquer forma, distribui ou entrega a consumo a substância alimentícia ou o produto falsificado, corrompido ou adulterado. (arágrafo acrescido pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
466	3	2	463	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é culposo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 1 (um) a 2 (dois) anos, e multa. (arágrafo com redação dada pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
467	2	2	\N	Art.	273	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Falsificar, corromper, adulterar ou alterar produto destinado a fins terapêuticos ou medicinais:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 10 (dez) a 15 (quinze) anos, e multa. (\\"Caput\\" do artigo com redação dada pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
468	3	2	467	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nas mesmas penas incorre quem importa, vende, expõe à venda, tem em depósito para vender ou, de qualquer forma, distribui ou entrega a consumo o produto falsificado, corrompido, adulterado ou alterado. (arágrafo com redação dada pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
469	3	2	467	§	1A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incluem-se entre os produtos a que se refere este artigo os medicamentos, as matérias-primas, os insumos farmacêuticos, os cosméticos, os saneantes e os de uso em diagnóstico. (arágrafo acrescido pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
470	3	2	467	§	1B	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Está sujeito às penas deste artigo quem pratica as ações previstas no § 1º em relação a produtos em qualquer das seguintes condições:</p>	1940-12-07	\N	t
472	4	2	470	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em desacordo com a fórmula constante do registro previsto no inciso anterior;</p>	1940-12-07	\N	t
473	4	2	470	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">sem as características de identidade e qualidade admitidas para a sua comercialização;</p>	1940-12-07	\N	t
474	4	2	470	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">com redução de seu valor terapêutico ou de sua atividade;</p>	1940-12-07	\N	t
475	4	2	470	\N	V	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">de procedência ignorada;</p>	1940-12-07	\N	t
476	4	2	470	\N	VI	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">adquiridos de estabelecimento sem licença da autoridade sanitária competente. (arágrafo acrescido pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
477	3	2	467	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é culposo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 1 (um) a 3 (três) anos, e multa. (arágrafo com redação dada pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
478	2	2	\N	Art.	274	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Empregar, no fabrico de produto destinado ao consumo, revestimento, gaseificação artificial, matéria corante, substância aromática, anti-séptica, conservadora ou qualquer outra não expressamente permitida pela legislação sanitária:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 1 (um) a 5 (cinco) anos, e multa. (Artigo com redação dada pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
479	2	2	\N	Art.	275	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Inculcar, em invólucro ou recipiente de produtos alimentícios, terapêuticos ou medicinais, a existência de substância que não se encontra em seu conteúdo ou que nele existe em quantidade menor que a mencionada:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 1 (um) a 5 (cinco) anos, e multa. (Artigo com redação dada pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
604	2	2	\N	Art.	330	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Desobedecer a ordem legal de funcionário público:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de quinze dias a seis meses, e multa.</p>	1940-12-07	\N	t
480	2	2	\N	Art.	276	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Vender, expor à venda, ter em depósito para vender ou, de qualquer forma, entregar a consumo produto nas condições dos arts. 274 e 275:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 1 (um) a 5 (cinco) anos, e multa. (Artigo com redação dada pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
481	2	2	\N	Art.	277	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Vender, expor à venda, ter em depósito ou ceder substância destinada à falsificação de produtos alimentícios, terapêuticos ou medicinais:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 1 (um) a 5 (cinco) anos, e multa. (Artigo com redação dada pela Lei nº 9.677, de 2/7/1998)</p>	1940-12-07	\N	t
482	2	2	\N	Art.	278	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Fabricar, vender, expor à venda, ter em depósito para vender ou, de qualquer forma, entregar a consumo coisa ou substância nociva à saúde, ainda que não destinada à alimentação ou a fim medicinal:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos, e multa.</p>	1940-12-07	\N	t
483	3	2	482	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é culposo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de dois meses a um ano.</p>	1940-12-07	\N	t
484	2	2	\N	Art.	280	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Fornecer substância medicinal em desacordo com receita médica:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos, ou multa.</p>	1940-12-07	\N	t
485	3	2	484	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é culposo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de dois meses a um ano.</p>	1940-12-07	\N	t
486	2	2	\N	Art.	282	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Exercer, ainda que a título gratuito, a profissão de médico, dentista ou farmacêutico, sem autorização legal ou excedendo-lhe os limites:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos.</p>	1940-12-07	\N	t
487	3	2	486	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é praticado com o fim de lucro, aplica-se também multa.</p>	1940-12-07	\N	t
488	2	2	\N	Art.	283	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Inculcar ou anunciar cura por meio secreto ou infalível:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, e multa.</p>	1940-12-07	\N	t
489	2	2	\N	Art.	284	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Exercer o curandeirismo:</p>	1940-12-07	\N	t
490	3	2	489	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é praticado mediante remuneração, o agente fica também sujeito à multa.</p>	1940-12-07	\N	t
491	4	2	489	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">prescrevendo, ministrando ou aplicando, habitualmente, qualquer substância;</p>	1940-12-07	\N	t
492	4	2	489	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">usando gestos, palavras ou qualquer outro meio;</p>	1940-12-07	\N	t
493	4	2	489	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">fazendo diagnósticos:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos.</p>	1940-12-07	\N	t
494	2	2	\N	Art.	285	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Aplica-se o disposto no art. 258 aos crimes previstos neste Capítulo, salvo quanto ao definido no art. 267.</p>	1940-12-07	\N	t
495	2	2	\N	Art.	286	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incitar, publicamente, a prática de crime:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três a seis meses, ou multa.</p>	1940-12-07	\N	t
496	2	2	\N	Art.	287	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Fazer, publicamente, apologia de fato criminoso ou de autor de crime:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três a seis meses, ou multa.</p>	1940-12-07	\N	t
497	2	2	\N	Art.	288	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Associarem-se mais de três pessoas, em quadrilha ou bando, para o fim de cometer crimes:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a três anos.</p>	1940-12-07	\N	t
498	3	2	497	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena aplica-se em dobro, se a quadrilha ou bando é armado.</p>	1940-12-07	\N	t
499	2	2	\N	Art.	289	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Falsificar, fabricando-a ou alterando-a, moeda metálica ou papel-moeda de curso legal no país ou no estrangeiro:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de três a doze anos, e multa.</p>	1940-12-07	\N	t
500	3	2	499	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nas mesmas penas incorre quem, por conta própria ou alheia, importa ou exporta, adquire, vende, troca, cede, empresta, guarda ou introduz na circulação moeda falsa.</p>	1940-12-07	\N	t
501	3	2	499	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Quem, tendo recebido de boa fé, como verdadeira, moeda falsa ou alterada, a restitui à circulação, depois de conhecer a falsidade, é punido com detenção, de seis meses a dois anos, e multa.</p>	1940-12-07	\N	t
502	3	2	499	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">É punido com reclusão, de três a quinze anos, e multa, o funcionário público ou diretor, gerente, ou fiscal de banco de emissão que fabrica, emite ou autoriza a fabricação ou emissão:</p>	1940-12-07	\N	t
503	4	2	502	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">de moeda com título ou peso inferior ao determinado em lei;</p>	1940-12-07	\N	t
504	4	2	502	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">de papel-moeda em quantidade superior à autorizada.</p>	1940-12-07	\N	t
506	2	2	\N	Art.	290	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Formar cédula, nota ou bilhete representativo de moeda com fragmentos de cédulas, notas ou bilhetes verdadeiros; suprimir em nota, cédula ou bilhete recolhidos, para o fim de restituí-los à circulação, sinal indicativo de sua inutilização; restituir à circulação cédula, nota ou bilhete em tais condições, ou já recolhidos para o fim de inutilização:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão de dois a oito anos, e multa.</p>	1940-12-07	\N	t
507	3	2	506	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">O máximo da reclusão é elevado a doze anos e o da multa, se o crime é cometido por funcionário que trabalha na repartição onde o dinheiro se achava recolhido, ou nela tem fácil ingresso, em razão do cargo. [DÚVIDA DA VERSÃO CORRETA]</p>	1940-12-07	\N	t
508	2	2	\N	Art.	291	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Fabricar, adquirir, fornecer, a título oneroso ou gratuito, possuir ou guardar maquinismo, aparelho, instrumento ou qualquer objeto especialmente destinado à falsificação de moeda:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a seis anos, e multa.</p>	1940-12-07	\N	t
509	2	2	\N	Art.	292	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Emitir, sem permissão legal, nota, bilhete, ficha, vale ou título que contenha promessa de pagamento em dinheiro ao portador ou a que falte indicação do nome da pessoa a quem deva ser pago:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de um a seis meses, ou multa.</p>	1940-12-07	\N	t
510	3	2	509	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Quem recebe ou utiliza como dinheiro qualquer dos documentos referidos neste artigo, incorre na pena de detenção, de quinze dias a três meses, ou multa.</p>	1940-12-07	\N	t
511	2	2	\N	Art.	293	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Falsificar, fabricando-os ou alterando-os:</p>	1940-12-07	\N	t
512	4	2	511	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">selo destinado a controle tributário, papel selado ou qualquer papel de emissão legal destinado à arrecadação de tributo; (Inciso com redação dada pela Lei nº 11.035, de 22/12/2004)</p>	1940-12-07	\N	t
513	4	2	511	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">papel de crédito público que não seja moeda de curso legal;</p>	1940-12-07	\N	t
514	4	2	511	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">vale postal;</p>	1940-12-07	\N	t
515	4	2	511	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">cautela de penhor, caderneta de depósito de caixa econômica ou de outro estabelecimento mantido por entidade de direito público;</p>	1940-12-07	\N	t
516	4	2	511	\N	V	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">talão, recibo, guia, alvará ou qualquer outro documento relativo a arrecadação de rendas públicas ou a depósito ou caução por que o poder público seja responsável;</p>	1940-12-07	\N	t
517	4	2	511	\N	VI	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">bilhete, passe ou conhecimento de empresa de transporte administrada pela União, por Estado ou por Município:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a oito anos, e multa.</p>	1940-12-07	\N	t
518	3	2	511	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre na mesma pena quem:</p>	1940-12-07	\N	t
519	3	2	511	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Suprimir, em qualquer desses papéis, quando legítimos, com o fim de torná-los novamente utilizáveis, carimbo ou sinal indicativo de sua inutilização:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa.</p>	1940-12-07	\N	t
520	3	2	511	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre na mesma pena quem usa, depois de alterado, qualquer dos papeis a que se refere o parágrafo anterior.</p>	1940-12-07	\N	t
521	3	2	511	§	4	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Quem usa ou restitui à circulação, embora recebido de boa fé, qualquer dos papeis falsificados ou alterados, a que se referem este artigo e o seu § 2º, depois de conhecer a falsidade ou alteração, incorre na pena de detenção, de seis meses a dois anos, ou multa.</p>	1940-12-07	\N	t
588	3	2	586	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o fato ocorre em lugar compreendido na faixa de fronteira:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos, e multa.</p>	1940-12-07	\N	t
522	2	2	\N	Art.	294	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Fabricar, adquirir, fornecer, possuir ou guardar objeto especialmente destinado à falsificação de qualquer dos papeis referidos no artigo anterior:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a três anos, e multa.</p>	1940-12-07	\N	t
523	2	2	\N	Art.	295	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o agente é funcionário público, e comete o crime prevalecendo-se do cargo, aumenta-se a pena de sexta parte.</p>	1940-12-07	\N	t
524	2	2	\N	Art.	296	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Falsificar, fabricando-os ou alterando-os:</p>	1940-12-07	\N	t
525	4	2	524	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">selo público destinado a autenticar atos oficiais da União, de Estado ou de Município;</p>	1940-12-07	\N	t
526	4	2	524	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">selo ou sinal atribuído por lei a entidade de direito público, ou a autoridade, ou sinal público de tabelião:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a seis anos, e multa.</p>	1940-12-07	\N	t
530	4	2	527	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">quem altera, falsifica ou faz uso indevido de marcas, logotipos, siglas ou quaisquer outros símbolos utilizados ou identificadores de órgãos ou entidades da Administração Pública. (Inciso acrescido pela Lei nº 9.983, de 14/7/2000)</p>	1940-12-07	\N	t
531	3	2	524	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o agente é funcionário público, e comete o crime prevalecendo-se do cargo, aumenta-se a pena de sexta parte.</p>	1940-12-07	\N	t
532	2	2	\N	Art.	297	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Falsificar, no todo ou em parte, documento público, ou alterar documento público verdadeiro:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a seis anos, e multa.</p>	1940-12-07	\N	t
533	3	2	532	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o agente é funcionário público, e comete o crime prevalecendo-se do cargo, aumenta-se a pena de sexta parte.</p>	1940-12-07	\N	t
534	3	2	532	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Para os efeitos penais, equiparam-se a documento público o emanado de entidade paraestatal, o título ao portador ou transmissível por endosso, as ações de sociedade comercial, os livros mercantis e o testamento particular.</p>	1940-12-07	\N	t
535	3	2	532	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nas mesmas penas incorre quem insere ou faz inserir:</p>	1940-12-07	\N	t
536	4	2	535	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">na folha de pagamento ou em documento de informações que seja destinado a fazer prova perante a previdência social, pessoa que não possua a qualidade de segurado obrigatório;</p>	1940-12-07	\N	t
537	4	2	535	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">na Carteira de Trabalho e Previdência Social do empregado ou em documento que deva produzir efeito perante a previdência social, declaração falsa ou diversa da que deveria ter sido escrita;</p>	1940-12-07	\N	t
651	4	2	649	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">prolonga a execução de pena ou de medida de segurança, deixando de expedir em tempo oportuno ou de executar imediatamente a ordem de liberdade;</p>	1940-12-07	\N	t
538	4	2	535	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">em documento contábil ou em qualquer outro documento relacionado com as obrigações da empresa perante a previdência social, declaração falsa ou diversa da que deveria ter constado. (arágrafo acrescido pela Lei nº 9.983, de 14/7/2000)</p>	1940-12-07	\N	t
539	3	2	532	§	4	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nas mesmas penas incorre quem omite, nos documentos mencionados no § 3º, nome do segurado e seus dados pessoais, a remuneração, a vigência do contrato de trabalho ou de prestação de serviços. (arágrafo acrescido pela Lei nº 9.983, de 14/7/2000)</p>	1940-12-07	\N	t
540	2	2	\N	Art.	298	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Falsificar, no todo ou em parte, documento particular ou alterar documento particular verdadeiro:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a cinco anos, e multa.</p>	1940-12-07	\N	t
560	2	2	\N	Art.	311	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Adulterar ou remarcar número de chassi ou qualquer sinal identificador de veículo automotor, de seu componente ou equipamento:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de três a seis anos, e multa. (\\"Caput\\" do artigo com redação dada pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
541	2	2	\N	Art.	299	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Omitir, em documento público ou particular, declaração que dele devia constar, ou nele inserir ou fazer inserir declaração falsa ou diversa da que devia ser escrita, com o fim de prejudicar direito, crear, obrigação ou alterar a verdade sobre fato juridicamente relevante:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a cinco anos, e multa, se o documento é público, e reclusão, de um a três anos, e multa, se o documento é particular.</p>	1940-12-07	\N	t
542	3	2	541	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o agente é funcionário público, e comete o crime prevalecendo-se do cargo, ou se a falsificação ou alteração é de assentamento de registo civil, aumenta-se a pena de sexta parte.</p>	1940-12-07	\N	t
543	2	2	\N	Art.	300	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Reconhecer, como verdadeira, no exercício de função pública, firma ou letra que o não seja:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a cinco anos, e multa, se o documento é público; e de um a três anos, e multa, se o documento é particular.</p>	1940-12-07	\N	t
544	2	2	\N	Art.	301	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Atestar ou certificar falsamente, em razão de função pública, fato ou circunstância que habilite alguém a obter cargo público, isenção de ônus ou de serviço de caráter público, ou qualquer outra vantagem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de dois meses a um ano.</p>	1940-12-07	\N	t
545	3	2	544	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Falsificar, no todo ou em parte, atestado ou certidão, ou alterar o teor de certidão ou de atestado verdadeiro, para prova de fato ou circunstância que habilite alguém a obter cargo público, isenção de ônus ou de serviço de caráter público, ou qualquer outra vantagem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a dois anos.</p>	1940-12-07	\N	t
546	3	2	544	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é praticado com o fim de lucro, aplica-se, além da pena privativa de liberdade, a multa. [DÚVIDA DA VERSÃO CORRETA]</p>	1940-12-07	\N	t
547	2	2	\N	Art.	302	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Dar o médico, no exercício da sua profissão, atestado falso:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano.</p>	1940-12-07	\N	t
548	3	2	547	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é cometido com o fim de lucro, aplica-se também multa.</p>	1940-12-07	\N	t
549	2	2	\N	Art.	303	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Reproduzir ou alterar selo ou peça filatélica que tenha valor para coleção, salvo quando a reprodução ou a alteração está visivelmente anotada na face ou no verso do selo ou peça:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos, e multa.</p>	1940-12-07	\N	t
550	3	2	549	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na mesma pena incorre quem, para fins de comércio, faz uso do selo ou peça filatélica.</p>	1940-12-07	\N	t
551	2	2	\N	Art.	304	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Fazer uso de qualquer dos papéis falsificados ou alterados, a que se referem os artigos 297 a 302:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">a cominada à falsificação ou à alteração.</p>	1940-12-07	\N	t
552	2	2	\N	Art.	305	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Destruir, suprimir ou ocultar, em benefício próprio ou de outrem, ou em prejuízo alheio, documento público ou particular verdadeiro, de que não podia dispor:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a seis anos, e multa, se o documento é público, e reclusão, de um a cinco anos, e multa, se o documento é particular.</p>	1940-12-07	\N	t
652	4	2	649	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">submete pessoa que está sob sua guarda ou custódia a vexame ou a constrangimento não autorizado em lei;</p>	1940-12-07	\N	t
553	2	2	\N	Art.	306	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Falsificar, fabricando-o ou alterando-o, marca ou sinal empregado pelo poder público no contraste de metal precioso ou na fiscalização alfandegária, ou usar marca ou sinal dessa natureza, falsificado por outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a seis anos, e multa.</p>	1940-12-07	\N	t
554	3	2	553	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se a marca ou sinal falsificado é o que usa a autoridade pública para o fim de fiscalização sanitária, ou para autenticar ou encerrar determinados objetos, ou comprovar o cumprimento de formalidade legal:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão ou detenção, de um a três anos, e multa.</p>	1940-12-07	\N	t
575	3	2	573	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o funcionário desvia, em proveito próprio ou de outrem, o que recebeu indevidamente para recolher aos cofres públicos:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a doze anos, e multa.</p>	1940-12-07	\N	t
555	2	2	\N	Art.	307	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Atribuir-se ou atribuir a terceiro falsa identidade para obter vantagem, em proveito próprio ou alheio, ou para causar dano a outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, ou multa, se o fato não constitui elementos de crime mais grave.</p>	1940-12-07	\N	t
556	2	2	\N	Art.	308	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Usar, como próprio, passaporte, título de eleitor, caderneta de reservista ou qualquer documento de identidade alheia ou ceder a outrem, para que dele se utilize, documento dessa natureza, próprio ou de terceiro:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de quatro meses a dois anos, e multa, se o fato não constitui elemento de crime mais grave.</p>	1940-12-07	\N	t
557	2	2	\N	Art.	309	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Usar o estrangeiro, para entrar ou permanecer no território nacional, nome que não é o seu:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três anos, e multa.</p>	1940-12-07	\N	t
558	3	2	557	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Atribuir a estrangeiro falsa qualidade para promover-lhe a entrada em território nacional:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa. (Parágrafo único acrescido pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
559	2	2	\N	Art.	310	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Prestar-se a figurar como proprietário ou possuidor de ação, título ou valor pertencente a estrangeiro, nos casos em que a este é vedada por lei a propriedade ou a posse de tais bens:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a três anos, e multa. (Artigo com redação dada pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
633	3	2	631	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é diminuída de metade, se a imputação é de prática de contravenção.</p>	1940-12-07	\N	t
562	3	2	560	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre nas mesmas penas o funcionário público que contribui para o licenciamento ou registro do veículo remarcado ou adulterado, fornecendo indevidamente material ou informação oficial. (arágrafo acrescido pela Lei nº 9.426, de 24/12/1996)</p>	1940-12-07	\N	t
563	2	2	\N	Art.	312	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Apropriar-se o funcionário público de dinheiro, valor ou qualquer outro bem móvel, público ou particular, de que tem a posse em razão do cargo, ou desviá-lo, em proveito próprio ou alheio:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a doze anos, e multa.</p>	1940-12-07	\N	t
564	3	2	563	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Aplica-se a mesma pena, se o funcionário público, embora não tendo a posse do dinheiro, valor ou bem, o subtrai, ou concorre para que seja subtraído, em proveito próprio ou alheio, valendo-se de facilidade que lhe proporciona a qualidade de funcionário.</p>	1940-12-07	\N	t
565	3	2	563	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o funcionário concorre culposamente para o crime de outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano.</p>	1940-12-07	\N	t
566	3	2	563	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">No caso do parágrafo anterior, a reparação do dano, se precede a sentença irrecorrível, extingue a punibilidade; se lhe é posterior, reduz de metade a pena imposta.</p>	1940-12-07	\N	t
567	2	2	\N	Art.	313	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Apropriar-se de dinheiro ou qualquer utilidade que, no exercício do cargo, recebeu por erro de outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa.</p>	1940-12-07	\N	t
568	2	2	\N	Art.	313A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Inserir ou facilitar, o funcionário autorizado, a inserção de dados falsos, alterar ou excluir indevidamente dados corretos nos sistemas informatizados ou bancos de dados da Administração Pública com o fim de obter vantagem indevida para si ou para outrem ou para causar dano;</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 12 (doze) anos, e multa. (Artigo acrescido pela Lei nº 9.983, de 14/7/2000)</p>	1940-12-07	\N	t
569	2	2	\N	Art.	313B	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Modificar ou alterar, o funcionário, sistema de informações ou programa de informática sem autorização ou solicitação de autoridade competente:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 3 (três) meses a 2 (dois) anos, e multa.</p>	1940-12-07	\N	t
570	3	2	569	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">As penas são aumentadas de um terço até a metade se da modificação ou alteração resulta dano para a Administração Pública ou para o administrado. (Artigo acrescido pela Lei nº 9.983, de 14/7/2000)</p>	1940-12-07	\N	t
571	2	2	\N	Art.	314	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Extraviar livro oficial ou qualquer documento, de que tem a guarda em razão do cargo; sonegá-lo ou inutilizá-lo, total ou parcialmente:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, se o fato não constitui crime mais grave.</p>	1940-12-07	\N	t
572	2	2	\N	Art.	315	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Dar às verbas ou rendas públicas aplicação diversa da estabelecida em lei:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a três meses, ou multa.</p>	1940-12-07	\N	t
573	2	2	\N	Art.	316	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Exigir, para si ou para outrem, direta ou indiretamente, ainda que fora da função ou antes de assumí-la, mas em razão dela, vantagem indevida:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a oito anos, e multa.</p>	1940-12-07	\N	t
574	3	2	573	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o funcionário exige tributo ou contribuição social que sabe ou deveria saber indevido, ou, quando devido, emprega na cobrança meio vexatório ou gravoso, que a lei não autoriza;</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 3 (três) a 8 (oito) anos, e multa. (arágrafo com redação dada pela Lei nº 8.137, de 27/12/1990)</p>	1940-12-07	\N	t
576	2	2	\N	Art.	317	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Solicitar ou receber, para si ou para outrem, direta ou indiretamente, ainda que fora da função ou antes de assumí-la, mas em razão dela, vantagem indevida, ou aceitar promessa de tal vantagem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 12 (doze) anos, e multa. (\\"Caput\\" do artigo com redação dada pela Lei nº 10.763, de 12/11/2003)</p>	1940-12-07	\N	t
577	3	2	576	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada de um terço, se, em conseqüência da vantagem ou promessa, o funcionário retarda ou deixa de praticar qualquer ato de ofício ou o pratica infringindo dever funcional.</p>	1940-12-07	\N	t
578	3	2	576	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o funcionário pratica, deixa de praticar ou retarda ato de ofício, com infração de dever funcional, cedendo a pedido ou influência de outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, ou multa.</p>	1940-12-07	\N	t
579	2	2	\N	Art.	318	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Facilitar, com infração de dever funcional, a prática de contrabando ou descaminho (art. 334):</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 3 (três) a 8 (oito) anos, e multa. (\\"Caput\\" do artigo com redação dada pela Lei nº 8.137, de 27/12/1990)</p>	1940-12-07	\N	t
650	4	2	649	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">ilegalmente recebe e recolhe alguém a prisão, ou a estabelecimento destinado a execução de pena privativa de liberdade ou de medida de segurança;</p>	1940-12-07	\N	t
580	2	2	\N	Art.	319	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Retardar ou deixar de praticar, indevidamente, ato de ofício, ou praticá-lo contra disposição expressa de lei, para satisfazer interesse ou sentimento pessoal:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, e multa.</p>	1940-12-07	\N	t
581	2	2	\N	Art.	319A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Deixar o Diretor de Penitenciária e/ou agente público, de cumprir seu dever de vedar ao preso o acesso a aparelho telefônico, de rádio ou similar, que permita a comunicação com outros presos ou com o ambiente externo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 3 (três) meses a 1 (um) ano. (Artigo acrescido pela Lei nº 11.466, de 28/3/2007)</p>	1940-12-07	\N	t
582	2	2	\N	Art.	320	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Deixar o funcionário, por indulgência, de responsabilizar subordinado que cometeu infração no exercício do cargo ou, quando lhe falte competência, não levar o fato ao conhecimento da autoridade competente:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de quinze dias a um mês, ou multa.</p>	1940-12-07	\N	t
583	2	2	\N	Art.	321	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Patrocinar, direta ou indiretamente, interesse privado perante a administração pública, valendo-se da qualidade de funcionário:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de um a três meses, ou multa.</p>	1940-12-07	\N	t
584	3	2	583	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o interesse é ilegítimo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de três meses a um ano, além da multa.</p>	1940-12-07	\N	t
585	2	2	\N	Art.	322	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Praticar violência, no exercício de função ou a pretexto de exercê-la:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a três anos, além da pena correspondente à violência.</p>	1940-12-07	\N	t
586	2	2	\N	Art.	323	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Abandonar cargo público, fora dos casos permitidos em lei:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de quinze dias a um mês, ou multa.</p>	1940-12-07	\N	t
587	3	2	586	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se do fato resulta prejuízo público:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, e multa.</p>	1940-12-07	\N	t
589	2	2	\N	Art.	324	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Entrar no exercício de função pública antes de satisfeitas as exigências legais, ou continuar a exercê-la, sem autorização, depois de saber oficialmente que foi exonerado, removido, substituído ou suspenso:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de quinze dias a um mês, ou multa.</p>	1940-12-07	\N	t
590	2	2	\N	Art.	325	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Revelar fato de que tem ciência em razão do cargo e que deva permanecer em segredo, ou facilitar-lhe a revelação:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, ou multa, se o fato não constitui crime mais grave.</p>	1940-12-07	\N	t
591	3	2	590	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Nas mesmas penas deste artigo incorre quem:</p>	1940-12-07	\N	t
592	4	2	591	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">permite ou facilita, mediante atribuição, fornecimento e empréstimo de senha ou qualquer outra forma, o acesso de pessoas não autorizadas a sistemas de informações ou banco de dados da Administração Pública;</p>	1940-12-07	\N	t
593	4	2	591	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">se utiliza, indevidamente, do acesso restrito. (arágrafo acrescido pela Lei nº 9.983, de 14/7/2000)</p>	1940-12-07	\N	t
594	3	2	590	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se da ação ou omissão resulta dano à Administração Pública ou a outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 6 (seis) anos, e multa. (arágrafo acrescido pela Lei nº 9.983, de 14/7/2000)</p>	1940-12-07	\N	t
595	2	2	\N	Art.	326	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Devassar o sigilo de proposta de concorrência pública, ou proporcionar a terceiro o ensejo de devassá-lo:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a um ano, e multa.</p>	1940-12-07	\N	t
596	2	2	\N	Art.	327	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Considera-se funcionário público, para os efeitos penais, quem, embora transitoriamente ou sem remuneração, exerce cargo, emprego ou função pública.</p>	1940-12-07	\N	t
597	3	2	596	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Equipara-se a funcionário público quem exerce cargo, emprego ou função em entidade paraestatal, e quem trabalha para empresa prestadora de serviço contratada ou conveniada para a execução de atividade típica da Administração Pública. (Parágrafo único transformado em § 1º pela Lei nº 6.799, de 23/6/1980 e com nova redação dada pela Lei nº 9.983, de 14/7/2000)</p>	1940-12-07	\N	t
598	3	2	596	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena será aumentada da terça parte quando os autores dos crimes previstos neste capítulo forem ocupantes de cargos em comissão ou de função de direção ou assessoramento de órgão da administração direta, sociedade de economia mista, empresa pública ou fundação instituída pelo poder público. (arágrafo acrescido pela Lei nº 6.799, de 23/6/1980)</p>	1940-12-07	\N	t
599	2	2	\N	Art.	328	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Usurpar o exercício de função pública:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a dois anos, e multa.</p>	1940-12-07	\N	t
600	3	2	599	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se do fato o agente aufere vantagem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a cinco anos, e multa.</p>	1940-12-07	\N	t
601	2	2	\N	Art.	329	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Opor-se à execução de ato legal, mediante violência ou ameaça a funcionário competente para executá-lo ou a quem lhe esteja prestando auxílio:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de dois meses a dois anos.</p>	1940-12-07	\N	t
602	3	2	601	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o ato, em razão da resistência, não se executa:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a três anos.</p>	1940-12-07	\N	t
603	3	2	601	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">As penas deste artigo são aplicáveis sem prejuízo das correspondentes à violência.</p>	1940-12-07	\N	t
605	2	2	\N	Art.	331	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Desacatar funcionário público no exercício da função ou em razão dela:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, ou multa.</p>	1940-12-07	\N	t
606	2	2	\N	Art.	332	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Solicitar, exigir, cobrar ou obter, para si ou para outrem, vantagem ou promessa de vantagem, a pretexto de influir em ato praticado por funcionário público no exercício da função.</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">Reclusão, de dois a cinco anos, e multa.</p>	1940-12-07	\N	t
607	3	2	606	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada da metade, se o agente alega ou insinua que a vantagem é também destinada ao funcionário. (Artigo com redação dada pela Lei nº 9.127, de 16/11/1995)</p>	1940-12-07	\N	t
608	2	2	\N	Art.	333	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Oferecer ou prometer vantagem indevida a funcionário público, para determiná-lo a praticar, omitir ou retardar ato de ofício:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 12 (doze) anos, e multa. (\\"Caput\\" do artigo com redação dada pela Lei nº 10.763, de 12/11/2003)</p>	1940-12-07	\N	t
609	3	2	608	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada de um terço, se, em razão da vantagem ou promessa, o funcionário retarda ou omite ato de ofício, ou o pratica infringindo dever funcional.</p>	1940-12-07	\N	t
610	2	2	\N	Art.	334	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Importar ou exportar mercadoria proibida ou iludir, no todo ou em parte, o pagamento de direito ou imposto devido pela entrada, pela saída ou pelo consumo de mercadoria:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos.</p>	1940-12-07	\N	t
611	3	2	610	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre na mesma pena quem:</p>	1940-12-07	\N	t
612	5	2	611	\N	a	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">pratica navegação de cabotagem, fora dos casos permitidos em lei;</p>	1940-12-07	\N	t
613	5	2	611	\N	b	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">pratica fato assimilado, em lei especial, a contrabando ou descaminho;</p>	1940-12-07	\N	t
614	5	2	611	\N	c	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">vende, expõe à venda, mantém em depósito ou, de qualquer forma, utiliza em proveito próprio ou alheio, no exercício de atividade comercial ou industrial, mercadoria de procedência estrangeira que introduziu clandestinamente no País ou importou fraudulentamente ou que sabe ser produto de introdução clandestina no território nacional ou de importação fraudulenta por parte de outrem;</p>	1940-12-07	\N	t
615	5	2	611	\N	d	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">adquire, recebe ou oculta, em proveito próprio ou alheio, no exercício de atividade comercial ou industrial, mercadoria de procedência estrangeira, desacompanhada de documentação legal, ou acompanhada de documentos que sabe serem falsos. (arágrafo com redação dada pela Lei nº 4.729, de 14/7/1965)</p>	1940-12-07	\N	t
616	3	2	610	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena aplica-se em dobro, se o crime de contrabando ou descaminho é praticado em transporte aéreo. (arágrafo acrescido pela Lei nº 4.729, de 14/7/1965)</p>	1940-12-07	\N	t
617	2	2	\N	Art.	335	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Impedir, perturbar ou fraudar concorrência pública ou venda em hasta pública, promovida pela administração federal, estadual ou municipal, ou por entidade paraestatal; afastar ou procurar afastar concorrente ou licitante, por meio de violência, grave ameaça, fraude ou oferecimento de vantagem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, ou multa, além da pena correspondente à violência.</p>	1940-12-07	\N	t
618	3	2	617	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre na mesma pena quem se abstém de concorrer ou licitar, em razão da vantagem oferecida.</p>	1940-12-07	\N	t
619	2	2	\N	Art.	336	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Rasgar ou, de qualquer forma inutilizar ou conspurcar edital afixado por ordem de funcionário público; violar ou inutilizar selo ou sinal empregado, por determinação legal ou por ordem de funcionário público, para identificar ou cerrar qualquer objeto:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano, ou multa.</p>	1940-12-07	\N	t
620	2	2	\N	Art.	337	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Subtrair, ou inutilizar, total ou parcialmente, livro oficial, processo ou documento confiado à custódia de funcionário, em razão de ofício ou de particular em serviço público:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a cinco anos, se o fato não constitue crime grave.</p>	1940-12-07	\N	t
621	2	2	\N	Art.	337A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Suprimir ou reduzir contribuição social previdenciária e qualquer acessório, mediante as seguintes condutas:</p>	1940-12-07	\N	t
622	4	2	621	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">omitir de folha de pagamento da empresa ou de documento de informações previsto pela legislação previdenciária segurados empregado, empresário, trabalhador avulso ou trabalhador autônomo ou a este equiparado que lhe prestem serviços;</p>	1940-12-07	\N	t
623	4	2	621	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">deixar de lançar mensalmente nos títulos próprios da contabilidade da empresa as quantias descontadas dos segurados ou as devidas pelo empregador ou pelo tomador de serviços;</p>	1940-12-07	\N	t
624	4	2	621	\N	III	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">omitir, total ou parcialmente, receitas ou lucros auferidos, remunerações pagas ou creditadas e demais fatos geradores de contribuições sociais previdenciárias:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 5 (cinco) anos, e multa.</p>	1940-12-07	\N	t
625	3	2	621	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o empregador não é pessoa jurídica e sua folha de pagamento mensal não ultrapassa R$ 1.510,00 (um mil, quinhentos e dez reais), o juiz poderá reduzir a pena de um terço até a metade ou aplicar apenas a de multa.</p>	1940-12-07	\N	t
626	2	2	\N	Art.	337B	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Prometer, oferecer ou dar, direta ou indiretamente, vantagem indevida a funcionário público estrangeiro, ou a terceira pessoa, para determiná-lo a praticar, omitir ou retardar ato de ofício relacionado à transação comercial internacional:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 1 (um) a 8 (oito) anos, e multa.</p>	1940-12-07	\N	t
627	3	2	626	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada de 1/3 (um terço), se, em razão da vantagem ou promessa, o funcionário público estrangeiro retarda ou omite o ato de ofício, ou o pratica infringindo dever funcional. (Artigo acrescido pela Lei nº 10.467, de 11/6/2002)</p>	1940-12-07	\N	t
628	2	2	\N	Art.	337C	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Solicitar, exigir, cobrar ou obter, para si ou para outrem, direta ou indiretamente, vantagem ou promessa de vantagem a pretexto de influir em ato praticado por funcionário público estrangeiro no exercício de suas funções, relacionado a transação comercial internacional:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 2 (dois) a 5 (cinco) anos, e multa.</p>	1940-12-07	\N	t
629	3	2	628	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada da metade, se o agente alega ou insinua que a vantagem é também destinada a funcionário estrangeiro. (Artigo acrescido pela Lei nº 10.467, de 11/6/2002)</p>	1940-12-07	\N	t
630	2	2	\N	Art.	338	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Reingressar no território nacional o estrangeiro que dele foi expulso:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, sem prejuízo de nova expulsão após o cumprimento da pena.</p>	1940-12-07	\N	t
631	2	2	\N	Art.	339	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Dar causa à instauração de investigação policial, de processo judicial, instauração de investigação administrativa, inquérito civil ou ação de improbidade administrativa contra alguém, imputando-lhe crime de que o sabe inocente:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dois a oito anos, e multa. (\\"Caput\\" do artigo com redação dada pela Lei nº 10.028, de 19/10/2000)</p>	1940-12-07	\N	t
632	3	2	631	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é aumentada de sexta parte, se o agente se serve de anonimato ou de nome suposto.</p>	1940-12-07	\N	t
634	2	2	\N	Art.	340	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Provocar a ação de autoridade, comunicando-lhe a ocorrência de crime ou de contravenção que sabe não se ter verificado:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a seis meses, ou multa.</p>	1940-12-07	\N	t
635	2	2	\N	Art.	341	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Acusar-se, perante a autoridade, de crime inexistente ou praticado por outrem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a dois anos, ou multa.</p>	1940-12-07	\N	t
636	2	2	\N	Art.	342	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Fazer afirmação falsa, ou negar ou calar a verdade como testemunha, perito, contador, tradutor ou intérprete em processo judicial, ou administrativo, inquérito policial, ou em juízo arbitral:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a três anos, e multa.</p>	1940-12-07	\N	t
637	3	2	636	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">As penas aumentam-se de um sexto a um terço, se o crime é praticado mediante suborno ou se cometido com o fim de obter prova destinada a produzir efeito em processo penal, ou em processo civil em que for parte entidade da administração pública direta ou indireta.</p>	1940-12-07	\N	t
638	2	2	\N	Art.	343	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Dar, oferecer ou prometer dinheiro ou qualquer outra vantagem a testemunha, perito, contador, tradutor ou intérprete, para fazer afirmação falsa, negar ou calar a verdade em depoimento, perícia, cálculos, tradução ou interpretação:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de três a quatro anos, e multa.</p>	1940-12-07	\N	t
639	2	2	\N	Art.	344	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Usar de violência ou grave ameaça, com o fim de favorecer interesse próprio ou alheio, contra autoridade, parte, ou qualquer outra pessoa que funciona ou é chamada a intervir em processo judicial, policial ou administrativo, ou em juízo arbitral:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, e multa, além da pena correspondente à violência.</p>	1940-12-07	\N	t
640	2	2	\N	Art.	345	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Fazer justiça pelas próprias mãos, para satisfazer pretensão, embora legítima, salvo quando a lei o permite:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de quinze dias a um mês, ou multa, além da pena correspondente à violência.</p>	1940-12-07	\N	t
641	2	2	\N	Art.	346	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Tirar, suprimir, destruir ou danificar coisa própria, que se acha em poder de terceiro por determinação judicial ou convenção:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, e multa.</p>	1940-12-07	\N	t
642	2	2	\N	Art.	347	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Inovar artificiosamente, na pendência de processo civil ou administrativo, o estado de lugar, de coisa ou de pessoa, com o fim de induzir a erro o juiz ou o perito:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a dois anos, e multa.</p>	1940-12-07	\N	t
643	3	2	642	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se a inovação se destina a produzir efeito em processo penal, ainda que não iniciado, as penas aplicam-se em dobro.</p>	1940-12-07	\N	t
644	2	2	\N	Art.	348	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Auxiliar a subtrair-se à ação de autoridade pública autor de crime a que é cominada pena de reclusão:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a seis meses, e multa.</p>	1940-12-07	\N	t
645	3	2	644	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se ao crime não é cominada pena de reclusão:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de quinze dias a três meses, e multa.</p>	1940-12-07	\N	t
646	2	2	\N	Art.	349	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Prestar a criminoso, fora dos casos de co-autoria ou de receptação, auxílio destinado a tornar seguro o proveito do crime:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um a seis meses, e multa.</p>	1940-12-07	\N	t
647	2	2	\N	Art.	349A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Ingressar, promover, intermediar, auxiliar ou facilitar a entrada de aparelho telefônico de comunicação móvel, de rádio ou similar, sem autorização legal, em estabelecimento prisional.</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 3 (três) meses a 1 (um) ano. (Artigo acrescido pela Lei nº 12.012, de 6/8/2009)</p>	1940-12-07	\N	t
648	2	2	\N	Art.	350	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Ordenar ou executar medida privativa de liberdade individual, sem as formalidades legais ou com abuso de poder:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de um mês a um ano.</p>	1940-12-07	\N	t
649	3	2	648	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Na mesma pena incorre o funcionário que:</p>	1940-12-07	\N	t
653	4	2	649	\N	IV	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">efetua, com abuso de poder, qualquer diligência.</p>	1940-12-07	\N	t
654	2	2	\N	Art.	351	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Promover ou facilitar a fuga de pessoa legalmente presa ou submetida a medida de segurança detentiva:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos.</p>	1940-12-07	\N	t
655	3	2	654	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o crime é praticado à mão armada, ou por mais de uma pessoa, ou mediante arrombamento, a pena é de reclusão, de dois a seis anos.</p>	1940-12-07	\N	t
656	3	2	654	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se há emprego de violência contra pessoa, aplica-se também a pena correspondente à violência.</p>	1940-12-07	\N	t
657	3	2	654	§	3	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">A pena é de reclusão, de um a quatro anos, se o crime é praticado por pessoa sob cuja custódia ou guarda está o preso ou o internado.</p>	1940-12-07	\N	t
658	3	2	654	§	4	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">No caso de culpa do funcionário incumbido da custódia ou guarda, aplica-se a pena de detenção, de três meses a um ano, ou multa.</p>	1940-12-07	\N	t
659	2	2	\N	Art.	352	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Evadir-se ou tentar evadir-se o preso ou o indivíduo submetido a medida de segurança detentiva, usando de violência contra a pessoa:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de três meses a um ano, além da pena correspondente à violência.</p>	1940-12-07	\N	t
660	2	2	\N	Art.	353	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Arrebatar preso, afim de maltratá-lo, do poder de quem o tenha sob custódia ou guarda:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a quatro anos, além da pena correspondente à violência.</p>	1940-12-07	\N	t
661	2	2	\N	Art.	354	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Amotinarem-se presos, perturbando a ordem ou disciplina da prisão:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a dois anos, além da pena correspondente à violência.</p>	1940-12-07	\N	t
662	2	2	\N	Art.	355	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Trair, na qualidade de advogado ou procurador, o dever profissional, prejudicando interesse, cujo patrocínio, em juízo, lhe é confiado:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a três anos e multa.</p>	1940-12-07	\N	t
663	3	2	662	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incorre na pena deste artigo o advogado ou procurador judicial que defende na mesma causa, simultânea ou sucessivamente, partes contrárias.</p>	1940-12-07	\N	t
664	2	2	\N	Art.	356	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Inutilizar, total ou parcialmente, ou deixar de restituir autos, documento ou objeto de valor probatório, que recebeu na qualidade de advogado ou procurador:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de seis meses a três anos, e multa.</p>	1940-12-07	\N	t
665	2	2	\N	Art.	357	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Solicitar ou receber dinheiro ou qualquer outra utilidade, a pretexto de influir em juiz, jurado, órgão do ministério público, funcionário de justiça, perito, tradutor, intérprete ou testemunha:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de um a cinco anos, e multa.</p>	1940-12-07	\N	t
666	3	2	665	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">As penas aumentam-se de um terço, se o agente alega ou insinua que o dinheiro ou utilidade também se destina a qualquer das pessoas referidas neste artigo.</p>	1940-12-07	\N	t
667	2	2	\N	Art.	358	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Impedir, perturbar ou fraudar arrematação judicial; afastar ou procurar afastar concorrente ou licitante, por meio de violência, grave ameaça, fraude ou oferecimento de vantagem:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção de dois meses a um ano, ou multa, além da pena correspondente à violência.</p>	1940-12-07	\N	t
668	2	2	\N	Art.	359	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Exercer função, atividade, direito, autoridade ou múnus, de que foi suspenso ou privado por decisão judicial:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de três meses a dois anos, ou multa.</p>	1940-12-07	\N	t
669	2	2	\N	Art.	359A	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Ordenar, autorizar ou realizar operação de crédito, interno ou externo, sem prévia autorização legislativa:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 1 (um) a 2 (dois) anos.</p>	1940-12-07	\N	t
670	3	2	669	§	Parágrafo Único	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Incide na mesma pena quem ordena, autoriza ou realiza operação de crédito, interno ou externo:</p>	1940-12-07	\N	t
671	4	2	670	\N	I	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">com inobservância de limite, condição ou montante estabelecido em lei ou em resolução do Senado Federal;</p>	1940-12-07	\N	t
672	4	2	670	\N	II	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">quando o montante da dívida consolidada ultrapassa o limite máximo autorizado por lei. (Artigo acrescido pela Lei nº 10.028, de 19/10/2000)</p>	1940-12-07	\N	t
673	2	2	\N	Art.	359B	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Ordenar ou autorizar a inscrição em restos a pagar, de despesa que não tenha sido previamente empenhada ou que exceda limite estabelecido em lei:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 6 (seis) meses a 2 (dois) anos. (Artigo acrescido pela Lei nº 10.028, de 19/10/2000)</p>	1940-12-07	\N	t
674	2	2	\N	Art.	359C	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Ordenar ou autorizar a assunção de obrigação, nos dois últimos quadrimestres do último ano do mandato ou legislatura, cuja despesa não possa ser paga no mesmo exercício financeiro ou, caso reste parcela a ser paga no exercício seguinte, que não tenha contrapartida suficiente de disponibilidade de caixa:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 1 (um) a 4 (quatro) anos. (Artigo acrescido pela Lei nº 10.028, de 19/10/2000)</p>	1940-12-07	\N	t
675	2	2	\N	Art.	359D	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Ordenar despesa não autorizada por lei:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 1 (um) a 4 (quatro) anos. (Artigo acrescido pela Lei nº 10.028, de 19/10/2000)</p>	1940-12-07	\N	t
676	2	2	\N	Art.	359E	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Prestar garantia em operação de crédito sem que tenha sido constituída contragarantia em valor igual ou superior ao valor da garantia prestada, na forma da lei:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 3(três) meses a 1 (um) ano. (Artigo acrescido pela Lei nº 10.028, de 19/10/2000)</p>	1940-12-07	\N	t
677	2	2	\N	Art.	359F	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Deixar de ordenar, de autorizar ou de promover o cancelamento do montante de restos a pagar inscrito em valor superior ao permitido em lei:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">detenção, de 6 (seis) meses a 2 (dois) anos. (Artigo acrescido pela Lei nº 10.028, de 19/10/2000)</p>	1940-12-07	\N	t
678	2	2	\N	Art.	359G	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Ordenar, autorizar ou executar ato que acarrete aumento de despesa total com pessoal, nos cento e oitenta dias anteriores ao final do mandato ou da legislatura:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 1 (um) a 4 (quatro) anos. (Artigo acrescido pela Lei nº 10.028, de 19/10/2000)</p>	1940-12-07	\N	t
679	2	2	\N	Art.	359H	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Ordenar, autorizar ou promover a oferta pública ou a colocação no mercado financeiro de títulos da dívida pública sem que tenham sido criados por lei ou sem que estejam registrados em sistema centralizado de liquidação e de custódia:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de 1 (um) a 4 (quatro) anos. (Artigo acrescido pela Lei nº 10.028, de 19/10/2000)</p>	1940-12-07	\N	t
199	3	2	198	§	1	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se o seqüestro dura mais de 24 (vinte e quatro) horas, se o seqüestrado é menor de 18 (dezoito) ou maior de 60 (sessenta) anos, ou se o crime é cometido por bando ou quadrilha.</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de doze a vinte anos. (arágrafo com redação dada pela Lei nº 10.741, de 1/10/2003)</p>	1940-12-07	\N	t
200	3	2	198	§	2	<p xmlns=\\"http://www.lexml.gov.br/1.0\\">Se do fato resulta lesão corporal de natureza grave:</p>  Pena - <p xmlns=\\"http://www.lexml.gov.br/1.0\\">reclusão, de dezesseis a vinte e quatro anos. (arágrafo com redação dada pela Lei nº 8.072, de 25/7/1990)</p>	1940-12-07	\N	t
\.


--
-- TOC entry 2853 (class 0 OID 1133307)
-- Dependencies: 208
-- Data for Name: tb_informacao_criminal; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_informacao_criminal (id_informacao_criminal, id_processo, ds_conteudo, id_parte) FROM stdin;
\.


--
-- TOC entry 2854 (class 0 OID 1133314)
-- Dependencies: 209
-- Data for Name: tb_motivo_prisao; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_motivo_prisao (id_motivo_prisao, ds_motivo_prisao, in_ativo) FROM stdin;
1	Prisão em flagrante	t
2	Recomendação de prisão	t
3	Manutenção de prisão	t
\.


--
-- TOC entry 2855 (class 0 OID 1133319)
-- Dependencies: 210
-- Data for Name: tb_motivo_soltura; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_motivo_soltura (id_motivo_soltura, ds_motivo_soltura, in_ativo) FROM stdin;
1	Fiança	t
2	Relaxamento de prisão	t
3	Liberdade provisória com fiança	t
4	Liberdade provisória sem fiança	t
5	Revogação da prisão	t
6	Habeas corpus	t
\.


--
-- TOC entry 2900 (class 0 OID 1134622)
-- Dependencies: 255
-- Data for Name: tb_motivo_suspensao_processo; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_motivo_suspensao_processo (id_motivo_suspensao, ds_motivo_suspensao, in_ativo) FROM stdin;
1	Lei n. 9099/95	t
2	art. 366, CPP	t
7	Lei n. 9099/95	t
8	art. 366, CPP	t
\.


--
-- TOC entry 2856 (class 0 OID 1133324)
-- Dependencies: 211
-- Data for Name: tb_municipio; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_municipio (id_municipio, ds_municipio, cd_municipio_ibge, cd_uf) FROM stdin;
1	Brasilia	001	DF
2	BRASíLIA	5300108	DF
3	MONTE ALEGRE DE SERGIPE	2804201	SE
\.


--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 212
-- Name: tb_municipio_id_municipio_seq; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('tb_municipio_id_municipio_seq', 1, false);


--
-- TOC entry 2858 (class 0 OID 1133329)
-- Dependencies: 213
-- Data for Name: tb_natureza_prisao; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_natureza_prisao (id_natureza_prisao, ds_natureza_prisao, in_ativo) FROM stdin;
\.


--
-- TOC entry 2859 (class 0 OID 1133334)
-- Dependencies: 214
-- Data for Name: tb_norma; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_norma (id_norma, nr_norma, nm_norma, ds_sigla, dt_inicio_vigencia, dt_fim_vigencia, id_tipo_norma, in_ativo) FROM stdin;
2	2848	Código Penal	CP	1940-12-07	\N	3	t
\.


--
-- TOC entry 2860 (class 0 OID 1133339)
-- Dependencies: 215
-- Data for Name: tb_org_prcdmnto_originario; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_org_prcdmnto_originario (id_org_procedimento_originario, id_tipo_origem, ds_cod_origem, ds_nome_orgao, nm_logradouro, nm_bairro, ds_telefone, in_ativo, ds_ddd, nm_numero, nm_complemento, id_municipio, nr_cep) FROM stdin;
\.


--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 216
-- Name: tb_org_prcdmnto_originario_id_org_procedimento_originario_seq; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('tb_org_prcdmnto_originario_id_org_procedimento_originario_seq', 1, false);


--
-- TOC entry 2862 (class 0 OID 1133347)
-- Dependencies: 217
-- Data for Name: tb_parte; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_parte (id_parte, id_processo, in_situacao_parte, id_processo_parte_legacy, id_pessoa_legacy, ds_rji) FROM stdin;
\.


--
-- TOC entry 2863 (class 0 OID 1133352)
-- Dependencies: 218
-- Data for Name: tb_processo; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_processo (id_processo, nr_processo, dt_local_fato, ds_local_fato, ds_longitude, ds_latitude, nr_protocolo_policia, id_municipio, nm_logradouro, nm_bairro, nm_numero, nm_complemento, nr_cep, ds_pje_origem) FROM stdin;
\.


--
-- TOC entry 2864 (class 0 OID 1133359)
-- Dependencies: 219
-- Data for Name: tb_processo_proc_origem; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_processo_proc_origem (id_processo_proc_origem, id_processo, id_org_procedimento_originario, id_tipo_procedimento_origem, dt_instauracao, nr_procedimento, nr_ano, id_tipo_origem, in_ativo, dt_lavratura, in_retombamento_redistribuicao, id_processo_proc_origem_retombado, nr_protocolo_policia, uf) FROM stdin;
\.


--
-- TOC entry 2865 (class 0 OID 1133364)
-- Dependencies: 220
-- Data for Name: tb_tipo_dispositivo; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_tipo_dispositivo (id_tipo_dispositivo, ds_tipo_dispositivo, in_ativo) FROM stdin;
2	Artigo	t
3	Parágrafo	t
4	Inciso	t
5	Alínea	t
6	Item	t
7	Parte	t
\.


--
-- TOC entry 2866 (class 0 OID 1133368)
-- Dependencies: 221
-- Data for Name: tb_tipo_ec_movimento; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_tipo_ec_movimento (id_tipo_ec_movimento, ds_cod_movimento, id_tipo_ec) FROM stdin;
\.


--
-- TOC entry 2882 (class 0 OID 1133597)
-- Dependencies: 237
-- Data for Name: tb_tipo_evento_criminal; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_tipo_evento_criminal (id_tipo_evento_criminal, in_ativo, cd_tipo_evento_criminal, ds_tipo_evento_criminal, in_exige_tipificacao_delito) FROM stdin;
1	t	PRI	Prisão	f
3	t	SOL	Soltura	f
4	t	FUG	Fuga	f
9	t	SUS	Suspensão do Processo	f
10	t	SCO	Sentença Condenatória	f
11	t	DIS	Decisão em Instância Superior	f
12	t	SAP	Sentença Absolutória	f
\.


--
-- TOC entry 2867 (class 0 OID 1133377)
-- Dependencies: 222
-- Data for Name: tb_tipo_norma; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_tipo_norma (id_tipo_norma, ds_tipo_norma, in_ativo) FROM stdin;
2	Lei	t
4	Lei Complementar	t
5	Lei Delegada	t
3	Decreto Lei	t
\.


--
-- TOC entry 2868 (class 0 OID 1133381)
-- Dependencies: 223
-- Data for Name: tb_tipo_origem; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_tipo_origem (id_tipo_origem, ds_tipo_origem, in_obrigatorio_numero_origem, in_ativo) FROM stdin;
\.


--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 224
-- Name: tb_tipo_origem_id_tipo_origem_seq; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('tb_tipo_origem_id_tipo_origem_seq', 1, false);


--
-- TOC entry 2870 (class 0 OID 1133386)
-- Dependencies: 225
-- Data for Name: tb_tipo_prisao; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_tipo_prisao (id_tipo_prisao, ds_tipo_prisao, in_ativo) FROM stdin;
1	Preventiva	t
2	Preventiva determinada ou mantida em decisão condenatória recorrível	t
3	Temporária	t
4	Flagrante	t
5	Provisória	t
6	Para fins de deportação	t
7	Para fins de expulsão	t
8	Para fins de extradição	t
9	Definitiva	t
\.


--
-- TOC entry 2902 (class 0 OID 1134748)
-- Dependencies: 257
-- Data for Name: tb_tipo_recurso; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_tipo_recurso (id_tipo_recurso, cod_tipo_recurso, ds_tipo_recurso, in_ativo) FROM stdin;
1	DAS	Anulação de Sentença	t
2	DAP	Absolvição	t
3	DAI	Absolvição Imprópria	t
4	DEP	Extinção da Punibilidade	t
5	SCS	Sentença Condenatória	t
\.


--
-- TOC entry 2871 (class 0 OID 1133391)
-- Dependencies: 226
-- Data for Name: tb_tipo_soltura; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_tipo_soltura (id_tipo_soltura, ds_tipo_soltura, in_ativo) FROM stdin;
1	Relaxamento	t
2	Revogação da Prisão	t
3	Final do Prazo da Temporária	t
4	Liberdade Provisória	t
5	Fiança	t
\.


--
-- TOC entry 2872 (class 0 OID 1133396)
-- Dependencies: 227
-- Data for Name: tb_tp_org_tp_proced_origem; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_tp_org_tp_proced_origem (id_tipo_origem, id_tipo_procedimento_origem) FROM stdin;
\.


--
-- TOC entry 2873 (class 0 OID 1133399)
-- Dependencies: 228
-- Data for Name: tb_tp_procedimento_origem; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_tp_procedimento_origem (id_tipo_procedimento_origem, ds_tipo_procedimento, in_ativo) FROM stdin;
1	Auto de Prisão em Flagrante	t
2	Boletim de Ocorrência	t
3	Inquérito Policial	t
4	Termo Circunstanciado	t
6	Termo de teste	f
\.


--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 229
-- Name: tb_tp_procedimento_origem_id_tipo_procedimento_origem_seq; Type: SEQUENCE SET; Schema: core; Owner: postgres
--

SELECT pg_catalog.setval('tb_tp_procedimento_origem_id_tipo_procedimento_origem_seq', 4, true);


--
-- TOC entry 2875 (class 0 OID 1133404)
-- Dependencies: 230
-- Data for Name: tb_unidade_prisional; Type: TABLE DATA; Schema: core; Owner: postgres
--

COPY tb_unidade_prisional (id_unidade_prisional, ds_unidade_prisional, ds_apelido_unidade, ds_jurisdicao, ds_email, nr_fax, nr_telefone, nr_cep, id_municipio, nm_bairro, nm_logradouro, in_ativo) FROM stdin;
1	PENITENCIÁRIA DO DISTRITO FEDERAL	PENITENCIÁRIA DO DISTRITO FEDERAL	Brasilia	pen@com.br	(61)6161-6161	(61)6161-6161	70610-200	2	Setores Complementares	Setor SPO	t
\.


SET search_path = public, pg_catalog;

--
-- TOC entry 2876 (class 0 OID 1133411)
-- Dependencies: 231
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY books (id, data) FROM stdin;
1	{ "name": "Book the First", "author": { "first_name": "Bob", "last_name": "White" } }
2	{ "name": "Book the Second", "author": { "first_name": "Charles", "last_name": "Xavier" } }
3	{ "name": "Book the Third", "author": { "first_name": "Jim", "last_name": "Brown" } }
\.


--
-- TOC entry 2877 (class 0 OID 1133417)
-- Dependencies: 232
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	init	SQL	V1__init.sql	1702425219	postgres	2018-02-26 13:21:24.227988	2	t
2	2	CRIACAO ENTIDADE PROCESSO CRIMINAL	SQL	V2__CRIACAO_ENTIDADE_PROCESSO_CRIMINAL.sql	-1834449343	postgres	2018-03-19 15:00:30.036182	58	t
3	3	CRIACAO ENTIDADES	SQL	V3__CRIACAO_ENTIDADES.sql	-381391779	postgres	2018-03-19 15:02:08.975982	17	t
4	4	CRIACAO ENTIDADE UNIDADE PRISIONAL	SQL	V4__CRIACAO_ENTIDADE_UNIDADE_PRISIONAL.sql	-1083087863	postgres	2018-03-22 15:15:01.531041	122	t
5	5	CRIACAO ENTIDADE NATUREZA PRISAO	SQL	V5__CRIACAO_ENTIDADE_NATUREZA_PRISAO.sql	804707149	postgres	2018-03-22 15:15:01.692407	5	t
\.


--
-- TOC entry 2878 (class 0 OID 1133424)
-- Dependencies: 233
-- Data for Name: schema_version; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY schema_version (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	<< Flyway Baseline >>	BASELINE	<< Flyway Baseline >>	\N	postgres	2018-04-03 12:01:22.830834	0	t
2	2	CRIACAO ENTIDADE PROCESSO CRIMINAL	SQL	V2__CRIACAO_ENTIDADE_PROCESSO_CRIMINAL.sql	-903721657	postgres	2018-04-03 12:01:22.911035	38	t
3	3	CRIACAO ENTIDADES	SQL	V3__CRIACAO_ENTIDADES.sql	1321977903	postgres	2018-04-03 12:01:22.985104	11	t
4	4	CRIACAO ENTIDADE UNIDADE PRISIONAL	SQL	V4__CRIACAO_ENTIDADE_UNIDADE_PRISIONAL.sql	-205027075	postgres	2018-04-03 12:01:23.009427	5	t
5	5	CRIACAO ENTIDADE NATUREZA PRISAO	SQL	V5__CRIACAO_ENTIDADE_NATUREZA_PRISAO.sql	-1765809074	postgres	2018-04-03 12:01:23.024081	3	t
6	6	CRIACAO TABELAS	SQL	V6__CRIACAO_TABELAS.sql	454282631	pje2	2018-04-13 18:07:32.045553	232	t
7	7	INCLUSAO DE COLUNAS ENTIDADE PROCESSO	SQL	V7__INCLUSAO_DE_COLUNAS_ENTIDADE_PROCESSO.sql	370538117	pje2	2018-04-19 14:03:56.636276	65	t
8	8	CRIACAO TABELA TB PAERTE	SQL	V8__CRIACAO_TABELA_TB_PAERTE.sql	1545385186	pje2	2018-04-24 05:14:35.906178	69	t
9	9	CRIACAO TABELA TB TIPO EVENTO CRIMINAL	SQL	V9__CRIACAO_TABELA_TB_TIPO_EVENTO_CRIMINAL.sql	1743376005	pje2	2018-06-12 05:14:29.669493	204	t
10	10	CRIACAO TABELAS AUDITORIA	SQL	V10__CRIACAO_TABELAS_AUDITORIA.sql	0	postgres	2018-06-27 08:42:58.33446	5	t
11	11	CRICAO TABELA MOTIVO SUSPENSAO PROCESSO UPDATES	SQL	V11__CRICAO_TABELA_MOTIVO_SUSPENSAO_PROCESSO_UPDATES.sql	-1726826197	postgres	2018-11-27 16:53:54.148518	16	t
12	12	CRICAO TABELA DECISAO INSTANCIA SUPERIOR UPDATES	SQL	V12__CRICAO_TABELA_DECISAO_INSTANCIA_SUPERIOR_UPDATES.sql	1327520499	postgres	2019-01-29 16:04:45.861746	25	t
13	13	CRICAO EVENTO CRIMINAL SENTENCA ABSOLUTORIA	SQL	V13__CRICAO_EVENTO_CRIMINAL_SENTENCA_ABSOLUTORIA.sql	310698191	postgres	2019-01-30 17:53:43.59511	51	t
14	14	CRIACAO COLUNA TB PROCESSO	SQL	V14__CRIACAO_COLUNA_TB_PROCESSO.sql	1817675117	postgres	2019-01-31 23:35:10.204766	109	t
\.


--
-- TOC entry 2898 (class 0 OID 1134610)
-- Dependencies: 253
-- Data for Name: schema_version_2; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY schema_version_2 (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	<< Flyway Baseline >>	BASELINE	<< Flyway Baseline >>	\N	postgres	2018-10-29 16:27:47.14268	0	t
2	2	CRIACAO ENTIDADE PROCESSO CRIMINAL	SQL	V2__CRIACAO_ENTIDADE_PROCESSO_CRIMINAL.sql	-1834449343	postgres	2018-10-29 16:27:47.202718	15	t
3	3	CRIACAO ENTIDADES	SQL	V3__CRIACAO_ENTIDADES.sql	-788523171	postgres	2018-10-29 16:27:47.233487	7	t
4	4	CRIACAO ENTIDADE UNIDADE PRISIONAL	SQL	V4__CRIACAO_ENTIDADE_UNIDADE_PRISIONAL.sql	-1083087863	postgres	2018-10-29 16:27:47.248628	2	t
5	5	CRIACAO ENTIDADE NATUREZA PRISAO	SQL	V5__CRIACAO_ENTIDADE_NATUREZA_PRISAO.sql	804707149	postgres	2018-10-29 16:27:47.257415	2	t
6	6	CRIACAO TABELAS	SQL	V6__CRIACAO_TABELAS.sql	-1790382310	postgres	2018-10-29 16:27:47.267978	5	t
7	7	INCLUSAO DE COLUNAS ENTIDADE PROCESSO	SQL	V7__INCLUSAO_DE_COLUNAS_ENTIDADE_PROCESSO.sql	1188666892	postgres	2018-10-29 16:27:47.277493	12	t
8	8	CRIACAO TABELA TB PAERTE	SQL	V8__CRIACAO_TABELA_TB_PAERTE.sql	624557041	postgres	2018-10-29 16:27:47.295363	5	t
9	9	CRIACAO TABELA TB TIPO EVENTO CRIMINAL	SQL	V9__CRIACAO_TABELA_TB_TIPO_EVENTO_CRIMINAL.sql	1081008236	postgres	2018-10-29 16:27:47.306852	3	t
10	10	CRIACAO TABELAS AUDITORIA	SQL	V10__CRIACAO_TABELAS_AUDITORIA.sql	-76967942	postgres	2018-10-29 16:30:12.550511	21	t
11	11	CRICAO TABELA MOTIVO SUSPENSAO PROCESSO UPDATES	SQL	V11__CRICAO_TABELA_MOTIVO_SUSPENSAO_PROCESSO_UPDATES.sql	-1726826197	postgres	2018-10-29 16:30:12.588691	25	t
\.


--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 234
-- Name: sq_tb_tp_procedimento_origem; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sq_tb_tp_procedimento_origem', 1, false);


--
-- TOC entry 2880 (class 0 OID 1133433)
-- Dependencies: 235
-- Data for Name: temp_blockgroups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY temp_blockgroups (id_tipo_norma_penal) FROM stdin;
2
4
5
3
\.


SET search_path = audit, pg_catalog;

--
-- TOC entry 2641 (class 2606 OID 1134356)
-- Name: revinfo revinfo_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY revinfo
    ADD CONSTRAINT revinfo_pkey PRIMARY KEY (id);


--
-- TOC entry 2661 (class 2606 OID 1134499)
-- Name: tb_informacao_criminal_aud tb_informacao_criminal_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_informacao_criminal_aud
    ADD CONSTRAINT tb_informacao_criminal_aud_pkey PRIMARY KEY (id_informacao_criminal, rev);


--
-- TOC entry 2643 (class 2606 OID 1134371)
-- Name: tb_municipio_aud tb_municipio_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_municipio_aud
    ADD CONSTRAINT tb_municipio_aud_pkey PRIMARY KEY (id_municipio, rev);


--
-- TOC entry 2645 (class 2606 OID 1134384)
-- Name: tb_org_prcdmnto_originario_aud tb_org_prcdmnto_originario_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_org_prcdmnto_originario_aud
    ADD CONSTRAINT tb_org_prcdmnto_originario_aud_pkey PRIMARY KEY (id_org_procedimento_originario, rev);


--
-- TOC entry 2647 (class 2606 OID 1134394)
-- Name: tb_parte_aud tb_parte_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_parte_aud
    ADD CONSTRAINT tb_parte_aud_pkey PRIMARY KEY (id_parte, rev);


--
-- TOC entry 2649 (class 2606 OID 1134407)
-- Name: tb_processo_aud tb_processo_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_processo_aud
    ADD CONSTRAINT tb_processo_aud_pkey PRIMARY KEY (id_processo, rev);


--
-- TOC entry 2651 (class 2606 OID 1134420)
-- Name: tb_processo_proc_origem_aud tb_processo_proc_origem_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem_aud
    ADD CONSTRAINT tb_processo_proc_origem_aud_pkey PRIMARY KEY (id_processo_proc_origem, rev);


--
-- TOC entry 2653 (class 2606 OID 1134433)
-- Name: tb_tipo_evento_criminal_aud tb_tipo_evento_criminal_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_evento_criminal_aud
    ADD CONSTRAINT tb_tipo_evento_criminal_aud_pkey PRIMARY KEY (id_tipo_evento_criminal, rev);


--
-- TOC entry 2655 (class 2606 OID 1134443)
-- Name: tb_tipo_origem_aud tb_tipo_origem_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_origem_aud
    ADD CONSTRAINT tb_tipo_origem_aud_pkey PRIMARY KEY (id_tipo_origem, rev);


--
-- TOC entry 2657 (class 2606 OID 1134453)
-- Name: tb_tp_org_tp_proced_origem_aud tb_tp_org_tp_proced_origem_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_tp_org_tp_proced_origem_aud
    ADD CONSTRAINT tb_tp_org_tp_proced_origem_aud_pkey PRIMARY KEY (rev, id_tipo_procedimento_origem, id_tipo_origem);


--
-- TOC entry 2659 (class 2606 OID 1134463)
-- Name: tb_tp_procedimento_origem_aud tb_tp_procedimento_origem_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_tp_procedimento_origem_aud
    ADD CONSTRAINT tb_tp_procedimento_origem_aud_pkey PRIMARY KEY (id_tipo_procedimento_origem, rev);


SET search_path = core, pg_catalog;

--
-- TOC entry 2598 (class 2606 OID 1134756)
-- Name: tb_processo nr_processo_unico; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo
    ADD CONSTRAINT nr_processo_unico UNIQUE (nr_processo, ds_pje_origem);


--
-- TOC entry 2602 (class 2606 OID 1133443)
-- Name: tb_processo_proc_origem pk_tb_processo_proc_origem; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem
    ADD CONSTRAINT pk_tb_processo_proc_origem PRIMARY KEY (id_processo_proc_origem);


--
-- TOC entry 2637 (class 2606 OID 1133625)
-- Name: tb_classe_judicial tb_classe_judicial_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_classe_judicial
    ADD CONSTRAINT tb_classe_judicial_pkey PRIMARY KEY (id_classe_judicial);


--
-- TOC entry 2580 (class 2606 OID 1133445)
-- Name: tb_dispositivo tb_dispositivo_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_dispositivo
    ADD CONSTRAINT tb_dispositivo_pkey PRIMARY KEY (id_dispositivo);


--
-- TOC entry 2582 (class 2606 OID 1133447)
-- Name: tb_informacao_criminal tb_informacao_criminal_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_informacao_criminal
    ADD CONSTRAINT tb_informacao_criminal_pkey PRIMARY KEY (id_informacao_criminal);


--
-- TOC entry 2584 (class 2606 OID 1133449)
-- Name: tb_motivo_prisao tb_motivo_prisao_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_motivo_prisao
    ADD CONSTRAINT tb_motivo_prisao_pkey PRIMARY KEY (id_motivo_prisao);


--
-- TOC entry 2586 (class 2606 OID 1133451)
-- Name: tb_motivo_soltura tb_motivo_soltura_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_motivo_soltura
    ADD CONSTRAINT tb_motivo_soltura_pkey PRIMARY KEY (id_motivo_soltura);


--
-- TOC entry 2666 (class 2606 OID 1134628)
-- Name: tb_motivo_suspensao_processo tb_motivo_suspensao_processo_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_motivo_suspensao_processo
    ADD CONSTRAINT tb_motivo_suspensao_processo_pkey PRIMARY KEY (id_motivo_suspensao);


--
-- TOC entry 2588 (class 2606 OID 1133453)
-- Name: tb_municipio tb_municio_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_municipio
    ADD CONSTRAINT tb_municio_pkey PRIMARY KEY (id_municipio);


--
-- TOC entry 2590 (class 2606 OID 1133455)
-- Name: tb_natureza_prisao tb_natureza_prisao_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_natureza_prisao
    ADD CONSTRAINT tb_natureza_prisao_pkey PRIMARY KEY (id_natureza_prisao);


--
-- TOC entry 2592 (class 2606 OID 1133457)
-- Name: tb_norma tb_norma_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_norma
    ADD CONSTRAINT tb_norma_pkey PRIMARY KEY (id_norma);


--
-- TOC entry 2594 (class 2606 OID 1133459)
-- Name: tb_org_prcdmnto_originario tb_orgao_procedimento_originario_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_org_prcdmnto_originario
    ADD CONSTRAINT tb_orgao_procedimento_originario_pkey PRIMARY KEY (id_org_procedimento_originario);


--
-- TOC entry 2596 (class 2606 OID 1133461)
-- Name: tb_parte tb_parte_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_parte
    ADD CONSTRAINT tb_parte_pkey PRIMARY KEY (id_parte);


--
-- TOC entry 2600 (class 2606 OID 1133463)
-- Name: tb_processo tb_processo_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo
    ADD CONSTRAINT tb_processo_pkey PRIMARY KEY (id_processo);


--
-- TOC entry 2604 (class 2606 OID 1133465)
-- Name: tb_tipo_dispositivo tb_tipo_dispositivo_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_dispositivo
    ADD CONSTRAINT tb_tipo_dispositivo_pkey PRIMARY KEY (id_tipo_dispositivo);


--
-- TOC entry 2606 (class 2606 OID 1133467)
-- Name: tb_tipo_ec_movimento tb_tipo_ec_movimento_ds_cod_movimento_key; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_ec_movimento
    ADD CONSTRAINT tb_tipo_ec_movimento_ds_cod_movimento_key UNIQUE (ds_cod_movimento);


--
-- TOC entry 2608 (class 2606 OID 1133469)
-- Name: tb_tipo_ec_movimento tb_tipo_ec_movimento_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_ec_movimento
    ADD CONSTRAINT tb_tipo_ec_movimento_pkey PRIMARY KEY (id_tipo_ec_movimento);


--
-- TOC entry 2633 (class 2606 OID 1133606)
-- Name: tb_tipo_evento_criminal tb_tipo_evento_criminal_cd_tipo_evento_criminal_key; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_evento_criminal
    ADD CONSTRAINT tb_tipo_evento_criminal_cd_tipo_evento_criminal_key UNIQUE (cd_tipo_evento_criminal);


--
-- TOC entry 2635 (class 2606 OID 1133604)
-- Name: tb_tipo_evento_criminal tb_tipo_evento_criminal_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_evento_criminal
    ADD CONSTRAINT tb_tipo_evento_criminal_pkey PRIMARY KEY (id_tipo_evento_criminal);


--
-- TOC entry 2612 (class 2606 OID 1133475)
-- Name: tb_tipo_norma tb_tipo_norma_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_norma
    ADD CONSTRAINT tb_tipo_norma_pkey PRIMARY KEY (id_tipo_norma);


--
-- TOC entry 2614 (class 2606 OID 1133477)
-- Name: tb_tipo_origem tb_tipo_origem_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_origem
    ADD CONSTRAINT tb_tipo_origem_pkey PRIMARY KEY (id_tipo_origem);


--
-- TOC entry 2620 (class 2606 OID 1133479)
-- Name: tb_tp_org_tp_proced_origem tb_tipo_origem_tipo_procedimento_origem_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tp_org_tp_proced_origem
    ADD CONSTRAINT tb_tipo_origem_tipo_procedimento_origem_pkey PRIMARY KEY (id_tipo_origem, id_tipo_procedimento_origem);


--
-- TOC entry 2616 (class 2606 OID 1133481)
-- Name: tb_tipo_prisao tb_tipo_prisao_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_prisao
    ADD CONSTRAINT tb_tipo_prisao_pkey PRIMARY KEY (id_tipo_prisao);


--
-- TOC entry 2622 (class 2606 OID 1133483)
-- Name: tb_tp_procedimento_origem tb_tipo_procedimento_origem_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tp_procedimento_origem
    ADD CONSTRAINT tb_tipo_procedimento_origem_pkey PRIMARY KEY (id_tipo_procedimento_origem);


--
-- TOC entry 2668 (class 2606 OID 1134754)
-- Name: tb_tipo_recurso tb_tipo_recurso_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_recurso
    ADD CONSTRAINT tb_tipo_recurso_pkey PRIMARY KEY (id_tipo_recurso);


--
-- TOC entry 2618 (class 2606 OID 1133485)
-- Name: tb_tipo_soltura tb_tipo_soltura_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_soltura
    ADD CONSTRAINT tb_tipo_soltura_pkey PRIMARY KEY (id_tipo_soltura);


--
-- TOC entry 2624 (class 2606 OID 1133487)
-- Name: tb_unidade_prisional tb_unidade_prisional_pkey; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_unidade_prisional
    ADD CONSTRAINT tb_unidade_prisional_pkey PRIMARY KEY (id_unidade_prisional);


--
-- TOC entry 2639 (class 2606 OID 1133627)
-- Name: tb_classe_judicial uk_8x5q0l1ts6gfjb97ig0040rwl; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_classe_judicial
    ADD CONSTRAINT uk_8x5q0l1ts6gfjb97ig0040rwl UNIQUE (cd_classe_judicial);


--
-- TOC entry 2610 (class 2606 OID 1133489)
-- Name: tb_tipo_ec_movimento uk_tb_tipo_ec_movimento; Type: CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_ec_movimento
    ADD CONSTRAINT uk_tb_tipo_ec_movimento UNIQUE (ds_cod_movimento);


SET search_path = public, pg_catalog;

--
-- TOC entry 2626 (class 2606 OID 1133491)
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- TOC entry 2663 (class 2606 OID 1134618)
-- Name: schema_version_2 schema_version_2_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schema_version_2
    ADD CONSTRAINT schema_version_2_pk PRIMARY KEY (installed_rank);


--
-- TOC entry 2630 (class 2606 OID 1133493)
-- Name: schema_version schema_version_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schema_version
    ADD CONSTRAINT schema_version_pk PRIMARY KEY (version);


--
-- TOC entry 2627 (class 1259 OID 1133494)
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON flyway_schema_history USING btree (success);


--
-- TOC entry 2664 (class 1259 OID 1134619)
-- Name: schema_version_2_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX schema_version_2_s_idx ON schema_version_2 USING btree (success);


--
-- TOC entry 2628 (class 1259 OID 1133495)
-- Name: schema_version_ir_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX schema_version_ir_idx ON schema_version USING btree (installed_rank);


--
-- TOC entry 2631 (class 1259 OID 1133496)
-- Name: schema_version_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX schema_version_s_idx ON schema_version USING btree (success);


SET search_path = audit, pg_catalog;

--
-- TOC entry 2713 (class 2606 OID 1134444)
-- Name: tb_tipo_origem_aud fk1ubuk9iko7nr4uycdo7fdewvq; Type: FK CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_origem_aud
    ADD CONSTRAINT fk1ubuk9iko7nr4uycdo7fdewvq FOREIGN KEY (rev) REFERENCES revinfo(id);


--
-- TOC entry 2710 (class 2606 OID 1134408)
-- Name: tb_processo_aud fk3qqpsahjc40x7o4xnwyjqvkvm; Type: FK CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_processo_aud
    ADD CONSTRAINT fk3qqpsahjc40x7o4xnwyjqvkvm FOREIGN KEY (rev) REFERENCES revinfo(id);


--
-- TOC entry 2712 (class 2606 OID 1134434)
-- Name: tb_tipo_evento_criminal_aud fk92d0vxgyo6c39q8nywxodm7xg; Type: FK CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_evento_criminal_aud
    ADD CONSTRAINT fk92d0vxgyo6c39q8nywxodm7xg FOREIGN KEY (rev) REFERENCES revinfo(id);


--
-- TOC entry 2715 (class 2606 OID 1134464)
-- Name: tb_tp_procedimento_origem_aud fkfvichta1ncvgilyw30kqq3rqa; Type: FK CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_tp_procedimento_origem_aud
    ADD CONSTRAINT fkfvichta1ncvgilyw30kqq3rqa FOREIGN KEY (rev) REFERENCES revinfo(id);


--
-- TOC entry 2709 (class 2606 OID 1134395)
-- Name: tb_parte_aud fkh0oni81bt4ae28cuf3b4nu78c; Type: FK CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_parte_aud
    ADD CONSTRAINT fkh0oni81bt4ae28cuf3b4nu78c FOREIGN KEY (rev) REFERENCES revinfo(id);


--
-- TOC entry 2714 (class 2606 OID 1134454)
-- Name: tb_tp_org_tp_proced_origem_aud fkha40hl5e8grd8i89kvtcj58s9; Type: FK CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_tp_org_tp_proced_origem_aud
    ADD CONSTRAINT fkha40hl5e8grd8i89kvtcj58s9 FOREIGN KEY (rev) REFERENCES revinfo(id);


--
-- TOC entry 2711 (class 2606 OID 1134421)
-- Name: tb_processo_proc_origem_aud fkmi3wgr10912a4y1fjylqjowew; Type: FK CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem_aud
    ADD CONSTRAINT fkmi3wgr10912a4y1fjylqjowew FOREIGN KEY (rev) REFERENCES revinfo(id);


--
-- TOC entry 2716 (class 2606 OID 1134500)
-- Name: tb_informacao_criminal_aud fkpaxvvma8ff4nem0326wyd9xk1; Type: FK CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_informacao_criminal_aud
    ADD CONSTRAINT fkpaxvvma8ff4nem0326wyd9xk1 FOREIGN KEY (rev) REFERENCES revinfo(id);


--
-- TOC entry 2708 (class 2606 OID 1134385)
-- Name: tb_org_prcdmnto_originario_aud fkst5yq54hje8xgqwbvrup5ssx4; Type: FK CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_org_prcdmnto_originario_aud
    ADD CONSTRAINT fkst5yq54hje8xgqwbvrup5ssx4 FOREIGN KEY (rev) REFERENCES revinfo(id);


--
-- TOC entry 2707 (class 2606 OID 1134372)
-- Name: tb_municipio_aud fkt2aex1ct8c2ttqrngrbelgr3g; Type: FK CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY tb_municipio_aud
    ADD CONSTRAINT fkt2aex1ct8c2ttqrngrbelgr3g FOREIGN KEY (rev) REFERENCES revinfo(id);


SET search_path = core, pg_catalog;

--
-- TOC entry 2681 (class 2606 OID 1133498)
-- Name: tb_org_prcdmnto_originario Ref_tb_origem_processo_to_tb_tipo_origem; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_org_prcdmnto_originario
    ADD CONSTRAINT "Ref_tb_origem_processo_to_tb_tipo_origem" FOREIGN KEY (id_tipo_origem) REFERENCES tb_tipo_origem(id_tipo_origem);


--
-- TOC entry 2700 (class 2606 OID 1133503)
-- Name: tb_tp_org_tp_proced_origem Ref_tb_tipo_origem_tipo_procedimento_origem_to_tb_dispositivo_n; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tp_org_tp_proced_origem
    ADD CONSTRAINT "Ref_tb_tipo_origem_tipo_procedimento_origem_to_tb_dispositivo_n" FOREIGN KEY (id_tipo_origem) REFERENCES tb_tipo_origem(id_tipo_origem);


--
-- TOC entry 2701 (class 2606 OID 1133508)
-- Name: tb_tp_org_tp_proced_origem Ref_tb_tipo_origem_tipo_procedimento_origem_to_tb_tipificacao_d; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tp_org_tp_proced_origem
    ADD CONSTRAINT "Ref_tb_tipo_origem_tipo_procedimento_origem_to_tb_tipificacao_d" FOREIGN KEY (id_tipo_procedimento_origem) REFERENCES tb_tp_procedimento_origem(id_tipo_procedimento_origem);


--
-- TOC entry 2673 (class 2606 OID 1133649)
-- Name: tb_dispositivo fk13maaw0579gt8r6mt97522scq; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_dispositivo
    ADD CONSTRAINT fk13maaw0579gt8r6mt97522scq FOREIGN KEY (id_norma) REFERENCES tb_norma(id_norma);


--
-- TOC entry 2686 (class 2606 OID 1133684)
-- Name: tb_parte fk2mjnmit7sk08ud1qpgmml0yxl; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_parte
    ADD CONSTRAINT fk2mjnmit7sk08ud1qpgmml0yxl FOREIGN KEY (id_processo) REFERENCES tb_processo(id_processo);


--
-- TOC entry 2706 (class 2606 OID 1133639)
-- Name: tb_classe_judicial fk3qv0u4qa6g8cjo5nx0cuaih91; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_classe_judicial
    ADD CONSTRAINT fk3qv0u4qa6g8cjo5nx0cuaih91 FOREIGN KEY (id_tipo_ic) REFERENCES tb_tipo_evento_criminal(id_tipo_evento_criminal);


--
-- TOC entry 2703 (class 2606 OID 1133729)
-- Name: tb_tp_org_tp_proced_origem fk3rt6j6vkdvoli5vx2va6d8ys5; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tp_org_tp_proced_origem
    ADD CONSTRAINT fk3rt6j6vkdvoli5vx2va6d8ys5 FOREIGN KEY (id_tipo_origem) REFERENCES tb_tipo_origem(id_tipo_origem);


--
-- TOC entry 2674 (class 2606 OID 1133654)
-- Name: tb_dispositivo fk4xf7p2vrsjcnpveh8j3b5fn57; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_dispositivo
    ADD CONSTRAINT fk4xf7p2vrsjcnpveh8j3b5fn57 FOREIGN KEY (id_tipo_dispositivo) REFERENCES tb_tipo_dispositivo(id_tipo_dispositivo);


--
-- TOC entry 2705 (class 2606 OID 1133734)
-- Name: tb_unidade_prisional fk5k6yp6c4v7y4qtluh0bvx305r; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_unidade_prisional
    ADD CONSTRAINT fk5k6yp6c4v7y4qtluh0bvx305r FOREIGN KEY (id_municipio) REFERENCES tb_municipio(id_municipio);


--
-- TOC entry 2672 (class 2606 OID 1133644)
-- Name: tb_dispositivo fk7c1q3ob9p5s67rvcp7hqvxi51; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_dispositivo
    ADD CONSTRAINT fk7c1q3ob9p5s67rvcp7hqvxi51 FOREIGN KEY (id_dispositivo_pai) REFERENCES tb_dispositivo(id_dispositivo);


--
-- TOC entry 2677 (class 2606 OID 1133659)
-- Name: tb_informacao_criminal fk81b999kue9k1bfm15d7mtelnt; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_informacao_criminal
    ADD CONSTRAINT fk81b999kue9k1bfm15d7mtelnt FOREIGN KEY (id_parte) REFERENCES tb_parte(id_parte);


--
-- TOC entry 2696 (class 2606 OID 1133704)
-- Name: tb_processo_proc_origem fk8pc371cuts8q9ruptx3p22exe; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem
    ADD CONSTRAINT fk8pc371cuts8q9ruptx3p22exe FOREIGN KEY (id_processo_proc_origem_retombado) REFERENCES tb_processo_proc_origem(id_processo_proc_origem);


--
-- TOC entry 2669 (class 2606 OID 1133513)
-- Name: tb_dispositivo fk_tb_dispositivo_to_tb_norma; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_dispositivo
    ADD CONSTRAINT fk_tb_dispositivo_to_tb_norma FOREIGN KEY (id_norma) REFERENCES tb_norma(id_norma);


--
-- TOC entry 2670 (class 2606 OID 1133518)
-- Name: tb_dispositivo fk_tb_dispositivo_to_tb_tipo_dispositivo; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_dispositivo
    ADD CONSTRAINT fk_tb_dispositivo_to_tb_tipo_dispositivo FOREIGN KEY (id_tipo_dispositivo) REFERENCES tb_tipo_dispositivo(id_tipo_dispositivo);


--
-- TOC entry 2671 (class 2606 OID 1133523)
-- Name: tb_dispositivo fk_tb_dispositivo_to_tb_tipo_dispositivo_pai; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_dispositivo
    ADD CONSTRAINT fk_tb_dispositivo_to_tb_tipo_dispositivo_pai FOREIGN KEY (id_dispositivo_pai) REFERENCES tb_dispositivo(id_dispositivo);


--
-- TOC entry 2675 (class 2606 OID 1133528)
-- Name: tb_informacao_criminal fk_tb_informacao_criminal_tb_parte; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_informacao_criminal
    ADD CONSTRAINT fk_tb_informacao_criminal_tb_parte FOREIGN KEY (id_parte) REFERENCES tb_parte(id_parte);


--
-- TOC entry 2676 (class 2606 OID 1133533)
-- Name: tb_informacao_criminal fk_tb_informacao_criminal_tb_processo; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_informacao_criminal
    ADD CONSTRAINT fk_tb_informacao_criminal_tb_processo FOREIGN KEY (id_processo) REFERENCES tb_processo(id_processo);


--
-- TOC entry 2679 (class 2606 OID 1133538)
-- Name: tb_norma fk_tb_norma_to_tb_tipo_norma; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_norma
    ADD CONSTRAINT fk_tb_norma_to_tb_tipo_norma FOREIGN KEY (id_tipo_norma) REFERENCES tb_tipo_norma(id_tipo_norma);


--
-- TOC entry 2685 (class 2606 OID 1133543)
-- Name: tb_parte fk_tb_parte_tb_processo; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_parte
    ADD CONSTRAINT fk_tb_parte_tb_processo FOREIGN KEY (id_processo) REFERENCES tb_processo(id_processo);


--
-- TOC entry 2689 (class 2606 OID 1133548)
-- Name: tb_processo_proc_origem fk_tb_processo_proc_origem_tb_org_proc_orig; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem
    ADD CONSTRAINT fk_tb_processo_proc_origem_tb_org_proc_orig FOREIGN KEY (id_org_procedimento_originario) REFERENCES tb_org_prcdmnto_originario(id_org_procedimento_originario);


--
-- TOC entry 2690 (class 2606 OID 1133553)
-- Name: tb_processo_proc_origem fk_tb_processo_proc_origem_tb_processo; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem
    ADD CONSTRAINT fk_tb_processo_proc_origem_tb_processo FOREIGN KEY (id_processo) REFERENCES tb_processo(id_processo);


--
-- TOC entry 2691 (class 2606 OID 1133558)
-- Name: tb_processo_proc_origem fk_tb_processo_proc_origem_tb_processo_proc_origem; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem
    ADD CONSTRAINT fk_tb_processo_proc_origem_tb_processo_proc_origem FOREIGN KEY (id_processo_proc_origem_retombado) REFERENCES tb_processo_proc_origem(id_processo_proc_origem);


--
-- TOC entry 2692 (class 2606 OID 1133563)
-- Name: tb_processo_proc_origem fk_tb_processo_proc_origem_tb_tipo_proc_orig; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem
    ADD CONSTRAINT fk_tb_processo_proc_origem_tb_tipo_proc_orig FOREIGN KEY (id_tipo_procedimento_origem) REFERENCES tb_tp_procedimento_origem(id_tipo_procedimento_origem);


--
-- TOC entry 2704 (class 2606 OID 1133568)
-- Name: tb_unidade_prisional fk_tb_unidade_prisional_id_municipio; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_unidade_prisional
    ADD CONSTRAINT fk_tb_unidade_prisional_id_municipio FOREIGN KEY (id_municipio) REFERENCES tb_municipio(id_municipio);


--
-- TOC entry 2688 (class 2606 OID 1133689)
-- Name: tb_processo fkaold9chdahmdm4g0ryn79qpvg; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo
    ADD CONSTRAINT fkaold9chdahmdm4g0ryn79qpvg FOREIGN KEY (id_municipio) REFERENCES tb_municipio(id_municipio);


--
-- TOC entry 2702 (class 2606 OID 1133724)
-- Name: tb_tp_org_tp_proced_origem fkda8k139xjxhorfkyvpjpbqu9t; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tp_org_tp_proced_origem
    ADD CONSTRAINT fkda8k139xjxhorfkyvpjpbqu9t FOREIGN KEY (id_tipo_procedimento_origem) REFERENCES tb_tp_procedimento_origem(id_tipo_procedimento_origem);


--
-- TOC entry 2698 (class 2606 OID 1133714)
-- Name: tb_processo_proc_origem fkfby0h635ea8egwbar6ro1pemw; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem
    ADD CONSTRAINT fkfby0h635ea8egwbar6ro1pemw FOREIGN KEY (id_tipo_procedimento_origem) REFERENCES tb_tp_procedimento_origem(id_tipo_procedimento_origem);


--
-- TOC entry 2694 (class 2606 OID 1133694)
-- Name: tb_processo_proc_origem fkk6apjja243evd0ov5ebxx1gac; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem
    ADD CONSTRAINT fkk6apjja243evd0ov5ebxx1gac FOREIGN KEY (id_org_procedimento_originario) REFERENCES tb_org_prcdmnto_originario(id_org_procedimento_originario);


--
-- TOC entry 2697 (class 2606 OID 1133709)
-- Name: tb_processo_proc_origem fkmwsbr111t6i1443ak60fglx7u; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem
    ADD CONSTRAINT fkmwsbr111t6i1443ak60fglx7u FOREIGN KEY (id_tipo_origem) REFERENCES tb_tipo_origem(id_tipo_origem);


--
-- TOC entry 2684 (class 2606 OID 1133679)
-- Name: tb_org_prcdmnto_originario fkn52nan9air3h98m6vnrrrerff; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_org_prcdmnto_originario
    ADD CONSTRAINT fkn52nan9air3h98m6vnrrrerff FOREIGN KEY (id_tipo_origem) REFERENCES tb_tipo_origem(id_tipo_origem);


--
-- TOC entry 2678 (class 2606 OID 1133664)
-- Name: tb_informacao_criminal fkpbtivjsn8qxtidx6tv7av6tex; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_informacao_criminal
    ADD CONSTRAINT fkpbtivjsn8qxtidx6tv7av6tex FOREIGN KEY (id_processo) REFERENCES tb_processo(id_processo);


--
-- TOC entry 2683 (class 2606 OID 1133674)
-- Name: tb_org_prcdmnto_originario fkqhmn72q7a82o8xnccvj1e0c2; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_org_prcdmnto_originario
    ADD CONSTRAINT fkqhmn72q7a82o8xnccvj1e0c2 FOREIGN KEY (id_municipio) REFERENCES tb_municipio(id_municipio);


--
-- TOC entry 2680 (class 2606 OID 1133669)
-- Name: tb_norma fkr91p2fj626sk0p2ctbysunf68; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_norma
    ADD CONSTRAINT fkr91p2fj626sk0p2ctbysunf68 FOREIGN KEY (id_tipo_norma) REFERENCES tb_tipo_norma(id_tipo_norma);


--
-- TOC entry 2699 (class 2606 OID 1133719)
-- Name: tb_tipo_ec_movimento fksvc28uhr3m2pm0ac0ttma8pby; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_tipo_ec_movimento
    ADD CONSTRAINT fksvc28uhr3m2pm0ac0ttma8pby FOREIGN KEY (id_tipo_ec) REFERENCES tb_tipo_evento_criminal(id_tipo_evento_criminal);


--
-- TOC entry 2695 (class 2606 OID 1133699)
-- Name: tb_processo_proc_origem fkteq2t5rq7ha6geteun3ktr3mm; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem
    ADD CONSTRAINT fkteq2t5rq7ha6geteun3ktr3mm FOREIGN KEY (id_processo) REFERENCES tb_processo(id_processo);


--
-- TOC entry 2682 (class 2606 OID 1133573)
-- Name: tb_org_prcdmnto_originario tb_org_prcdmnto_originario_id_municipio_fkey; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_org_prcdmnto_originario
    ADD CONSTRAINT tb_org_prcdmnto_originario_id_municipio_fkey FOREIGN KEY (id_municipio) REFERENCES tb_municipio(id_municipio);


--
-- TOC entry 2687 (class 2606 OID 1133578)
-- Name: tb_processo tb_processo_id_municipio_fkey; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo
    ADD CONSTRAINT tb_processo_id_municipio_fkey FOREIGN KEY (id_municipio) REFERENCES tb_municipio(id_municipio);


--
-- TOC entry 2693 (class 2606 OID 1133583)
-- Name: tb_processo_proc_origem tb_processo_proc_origem_fk; Type: FK CONSTRAINT; Schema: core; Owner: postgres
--

ALTER TABLE ONLY tb_processo_proc_origem
    ADD CONSTRAINT tb_processo_proc_origem_fk FOREIGN KEY (id_tipo_origem) REFERENCES tb_tipo_origem(id_tipo_origem) ON UPDATE CASCADE ON DELETE RESTRICT;


-- Completed on 2019-02-10 00:05:07 -02

--
-- PostgreSQL database dump complete
--

