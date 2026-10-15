DO $$ 
BEGIN

/*FUGA*/
--Cria e atualiza a dataCadastro a partir da dtFuga ou data
UPDATE core.tb_informacao_criminal
SET ds_conteudo =  (
   SELECT jsonb_set(ds_conteudo
                  , '{fugas}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dtFuga' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dtFuga', '999999999999999999999')))
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'fugas') elem
   ) 
WHERE 
	ds_conteudo ->> 'fugas' IS NOT NULL AND ds_conteudo ->> 'fugas' <> '[]';

	
--Atualiza a dtFuga de acordo com a dataCadastro
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{fugas}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dtFuga' IS NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{dtFuga}', to_jsonb(to_number(elem->>'dataCadastro', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'fugas') elem
   ) 
WHERE 
	ds_conteudo ->> 'fugas' IS NOT NULL AND ds_conteudo ->> 'fugas' <> '[]';
	
	
--Limpa o campo data caso os campos dtFuga e dataCadastro estejam preenchidos
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{fugas}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dtFuga' IS NOT NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{data}', to_jsonb('null'::jsonb))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'fugas') elem
   ) 
WHERE 
	ds_conteudo ->> 'fugas' IS NOT NULL AND ds_conteudo ->> 'fugas' <> '[]';
	
/*------------------------------------------------------------------------------------------------------------------------*/

/*OFERECIMENTO DA DENUNCIA*/

--Cria e atualiza a dataCadastro a partir da data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{oferecimentoDenuncia}'
                  , jsonb_agg(CASE 
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'oferecimentoDenuncia') elem
   ) 
WHERE 
	ds_conteudo ->> 'oferecimentoDenuncia' IS NOT NULL AND ds_conteudo ->> 'oferecimentoDenuncia' <> '[]';

/*------------------------------------------------------------------------------------------------------------------------*/

/*RECEBIMENTO DA DENUNCIA*/
--Cria e atualiza a dataCadastro a partir da data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{recebimentoDenuncia}'
                  , jsonb_agg(CASE 
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'recebimentoDenuncia') elem
   ) 
WHERE 
	ds_conteudo ->> 'recebimentoDenuncia' IS NOT NULL AND ds_conteudo ->> 'recebimentoDenuncia' <> '[]';


/*------------------------------------------------------------------------------------------------------------------------*/

/*PRISAO*/

--Cria e atualiza a dataCadastro a partir da dtPrisao ou da data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{prisoes}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dtPrisao' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dtPrisao', '999999999999999999999')))
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'prisoes') elem
   ) 
WHERE 
	ds_conteudo ->> 'prisoes' IS NOT NULL AND ds_conteudo ->> 'prisoes' <> '[]';

--Atualiza a dtPrisao de acordo com a dataCadastro
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{prisoes}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dtPrisao' IS NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{dtPrisao}', to_jsonb(to_number(elem->>'dataCadastro', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'prisoes') elem
   ) 
WHERE 
	ds_conteudo ->> 'prisoes' IS NOT NULL AND ds_conteudo ->> 'prisoes' <> '[]';


--Limpa o campo data caso os campos dtPrisao e dataCadastro estejam preenchidos
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{prisoes}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dtPrisao' IS NOT NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{data}', to_jsonb('null'::jsonb))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'prisoes') elem
   ) 
WHERE 
	ds_conteudo ->> 'prisoes' IS NOT NULL AND ds_conteudo ->> 'prisoes' <> '[]';


/*------------------------------------------------------------------------------------------------------------------------*/

/*INDICIAMENTO*/

--Cria e atualiza a dataCadastro a partir da data

UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{indiciamento}'
                  , jsonb_agg(CASE 
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'indiciamento') elem
   ) 
WHERE 
	ds_conteudo ->> 'indiciamento' IS NOT NULL AND ds_conteudo ->> 'indiciamento' <> '[]';
	
/*------------------------------------------------------------------------------------------------------------------------*/

/*SOLTURA*/	

--Cria e atualiza a dataCadastro a partir da dtSoltura ou data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{solturas}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dtSoltura' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dtSoltura', '999999999999999999999')))
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'solturas') elem
   )
WHERE 
	ds_conteudo ->> 'solturas' IS NOT NULL AND ds_conteudo ->> 'solturas' <> '[]';


--Atualiza a dtSoltura de acordo com a dataCadastro
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{solturas}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dtSoltura' IS NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{dtSoltura}', to_jsonb(to_number(elem->>'dataCadastro', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'solturas') elem
   )
WHERE 
	ds_conteudo ->> 'solturas' IS NOT NULL AND ds_conteudo ->> 'solturas' <> '[]';
	
	
--Limpa o campo data caso os campos dtSoltura e dataCadastro estejam preenchidos	
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{solturas}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dtSoltura' IS NOT NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{data}', to_jsonb('null'::jsonb))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'solturas') elem
   ) 
WHERE 
	ds_conteudo ->> 'solturas' IS NOT NULL AND ds_conteudo ->> 'solturas' <> '[]';
	
/*------------------------------------------------------------------------------------------------------------------------*/

/*SUSPENSAO DO PROCESSO*/	

--Cria e atualiza a dataCadastro a partir da dataInicio ou data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{suspensoesProcesso}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataInicio' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dataInicio', '999999999999999999999')))
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'suspensoesProcesso') elem
   ) 
WHERE 
	ds_conteudo ->> 'suspensoesProcesso' IS NOT NULL AND ds_conteudo ->> 'suspensoesProcesso' <> '[]';

--Limpa o campo data caso os campos dataInicio e dataCadastro estejam preenchidos	
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{suspensoesProcesso}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataInicio' IS NOT NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{data}', to_jsonb('null'::jsonb))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'suspensoesProcesso') elem
   ) 
WHERE 
	ds_conteudo ->> 'suspensoesProcesso' IS NOT NULL AND ds_conteudo ->> 'suspensoesProcesso' <> '[]';
	
/*------------------------------------------------------------------------------------------------------------------------*/

/*SENTENCA CONDENATORIA*/

--Cria e atualiza a dataCadastro a partir da dataSentenca
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{sentencasCondenatorias}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataSentenca' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dataSentenca', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'sentencasCondenatorias') elem
   ) 
WHERE 
	ds_conteudo ->> 'sentencasCondenatorias' IS NOT NULL AND ds_conteudo ->> 'sentencasCondenatorias' <> '[]';
/*------------------------------------------------------------------------------------------------------------------------*/


/*ADITAMENTO DA DENUNCIA*/

--Cria e atualiza a dataCadastro a partir da data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{aditamentoDenuncia}'
                  , jsonb_agg(CASE 
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'aditamentoDenuncia') elem
   ) 
WHERE 
	ds_conteudo ->> 'aditamentoDenuncia' IS NOT NULL AND ds_conteudo ->> 'aditamentoDenuncia' <> '[]';

/*------------------------------------------------------------------------------------------------------------------------*/


/*DECISAO EM INSTANCIA SUPERIOR*/


/*DECISAO EM INSTANCIA SUPERIOR*/

--Cria e atualiza a dataDecisao a partir da data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{decisoesInstanciaSuperior}'
                  , jsonb_agg(CASE 
								WHEN elem->>'data' IS NOT NULL THEN jsonb_set(elem, '{dataDecisao}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'decisoesInstanciaSuperior') elem
   ) 
WHERE 
	ds_conteudo ->> 'decisoesInstanciaSuperior' IS NOT NULL AND ds_conteudo ->> 'decisoesInstanciaSuperior' <> '[]';

--Cria e atualiza a dataCadastro a partir da dataDecisao, dataPublicacao ou data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{decisoesInstanciaSuperior}'
                  , jsonb_agg(CASE 
								WHEN elem->>'dataDecisao' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dataDecisao', '999999999999999999999')))
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))								
			                    WHEN elem->>'dataPublicacao' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dataPublicacao', '999999999999999999999')))								
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'decisoesInstanciaSuperior') elem
   ) 
WHERE 
	ds_conteudo ->> 'decisoesInstanciaSuperior' IS NOT NULL AND ds_conteudo ->> 'decisoesInstanciaSuperior' <> '[]';

--Limpa o campo data caso os campos dataDecisao e dataCadastro estejam preenchidos	
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{decisoesInstanciaSuperior}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataDecisao' IS NOT NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{data}', to_jsonb('null'::jsonb))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'decisoesInstanciaSuperior') elem
   ) 
WHERE 
	ds_conteudo ->> 'decisoesInstanciaSuperior' IS NOT NULL AND ds_conteudo ->> 'decisoesInstanciaSuperior' <> '[]';
	
/*------------------------------------------------------------------------------------------------------------------------*/


/*SENTENCA ABSOLUTORIA*/

--Cria e atualiza a dataCadastro a partir da dataSentenca ou data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{sentencasAbsolutorias}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataSentenca' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dataSentenca', '999999999999999999999')))
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'sentencasAbsolutorias') elem
   ) 
WHERE 
	ds_conteudo ->> 'sentencasAbsolutorias' IS NOT NULL AND ds_conteudo ->> 'sentencasAbsolutorias' <> '[]';


--Limpa o campo data caso os campos dataSentenca e dataCadastro estejam preenchidos	
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{sentencasAbsolutorias}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataSentenca' IS NOT NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{data}', to_jsonb('null'::jsonb))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'sentencasAbsolutorias') elem
   ) 
WHERE 
	ds_conteudo ->> 'sentencasAbsolutorias' IS NOT NULL AND ds_conteudo ->> 'sentencasAbsolutorias' <> '[]';
/*------------------------------------------------------------------------------------------------------------------------*/


/*SENTENCA DE IMPRONUNCIA*/
--Cria e atualiza a dataCadastro a partir da dataImpronuncia ou data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{sentencasImpronuncia}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataImpronuncia' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dataImpronuncia', '999999999999999999999')))
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'sentencasImpronuncia') elem
   )
WHERE 
	ds_conteudo ->> 'sentencasImpronuncia' IS NOT NULL AND ds_conteudo ->> 'sentencasImpronuncia' <> '[]';


--Limpa o campo data caso os campos dataImpronuncia e dataCadastro estejam preenchidos	
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{sentencasImpronuncia}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataImpronuncia' IS NOT NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{data}', to_jsonb('null'::jsonb))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'sentencasImpronuncia') elem
   )
WHERE 
	ds_conteudo ->> 'sentencasImpronuncia' IS NOT NULL AND ds_conteudo ->> 'sentencasImpronuncia' <> '[]';

/*------------------------------------------------------------------------------------------------------------------------*/


/*SENTENCA DE PRONUNCIA*/ 

--Cria e atualiza a dataCadastro a partir da dataPronuncia ou data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{sentencasPronuncia}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataPronuncia' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dataPronuncia', '999999999999999999999')))
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'sentencasPronuncia') elem
   ) 
WHERE 
	ds_conteudo ->> 'sentencasPronuncia' IS NOT NULL AND ds_conteudo ->> 'sentencasPronuncia' <> '[]';


--Limpa o campo data caso os campos dataPronuncia e dataCadastro estejam preenchidos	
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{sentencasPronuncia}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataPronuncia' IS NOT NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{data}', to_jsonb('null'::jsonb))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'sentencasPronuncia') elem
   ) 
WHERE 
	ds_conteudo ->> 'sentencasPronuncia' IS NOT NULL AND ds_conteudo ->> 'sentencasPronuncia' <> '[]';

/*------------------------------------------------------------------------------------------------------------------------*/

/*DESCLASSIFICACAO DO PROCESSO*/

--Cria e atualiza a dataCadastro a partir da dataDesclassificacao ou data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{desclassificacoesProcesso}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataDesclassificacao' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dataDesclassificacao', '999999999999999999999')))
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'desclassificacoesProcesso') elem
   ) 
WHERE 
	ds_conteudo ->> 'desclassificacoesProcesso' IS NOT NULL AND ds_conteudo ->> 'desclassificacoesProcesso' <> '[]';


--Atualiza a dataDesclassificacao de acordo com a dataCadastro
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{desclassificacoesProcesso}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataDesclassificacao' IS NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{dataDesclassificacao}', to_jsonb(to_number(elem->>'dataCadastro', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'desclassificacoesProcesso') elem
   ) 
WHERE 
	ds_conteudo ->> 'desclassificacoesProcesso' IS NOT NULL AND ds_conteudo ->> 'desclassificacoesProcesso' <> '[]';
	
	
--Limpa o campo data caso os campos dataDesclassificacao e dataCadastro estejam preenchidos	
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{desclassificacoesProcesso}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataDesclassificacao' IS NOT NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{data}', to_jsonb('null'::jsonb))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'desclassificacoesProcesso') elem
   ) 
WHERE 
	ds_conteudo ->> 'desclassificacoesProcesso' IS NOT NULL AND ds_conteudo ->> 'desclassificacoesProcesso' <> '[]';
	
/*------------------------------------------------------------------------------------------------------------------------*/ 
/*NAO RECEBIMENTO DA DENUNCIA*/

--Cria e atualiza a dataCadastro a partir da data
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{naoRecebimentoDenuncia}'
                  , jsonb_agg(CASE 
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'naoRecebimentoDenuncia') elem
   ) 
WHERE 
	ds_conteudo ->> 'naoRecebimentoDenuncia' IS NOT NULL AND ds_conteudo ->> 'naoRecebimentoDenuncia' <> '[]';

/*------------------------------------------------------------------------------------------------------------------------*/

/*CITACAO*/ 

--Cria e atualiza a dataCadastro a partir da dataCitacaoIntimicaoReu, data ou dataCitacaoEdital
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{citacoes}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataCitacaoIntimicaoReu' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dataCitacaoIntimicaoReu', '999999999999999999999')))
                  				WHEN elem->>'data' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'data', '999999999999999999999')))
								WHEN elem->>'dataCitacaoEdital' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dataCitacaoEdital', '999999999999999999999')))                  				
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'citacoes') elem
   ) 
WHERE 
	ds_conteudo ->> 'citacoes' IS NOT NULL AND ds_conteudo ->> 'citacoes' <> '[]';

--Atualiza a dataCitacaoIntimicaoReu de acordo com a dataCadastro
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{citacoes}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataCitacaoIntimicaoReu' IS NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{dataCitacaoIntimicaoReu}', to_jsonb(to_number(elem->>'dataCadastro', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'citacoes') elem
   ) 
WHERE 
	ds_conteudo ->> 'citacoes' IS NOT NULL AND ds_conteudo ->> 'citacoes' <> '[]';

--Limpa o campo data caso os campos dataCitacaoIntimicaoReu e dataCadastro estejam preenchidos	
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{citacoes}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataCitacaoIntimicaoReu' IS NOT NULL AND elem->>'dataCadastro' IS NOT NULL THEN jsonb_set(elem, '{data}', to_jsonb('null'::jsonb))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM jsonb_array_elements(ds_conteudo->'citacoes') elem
   ) 
WHERE 
	ds_conteudo ->> 'citacoes' IS NOT NULL AND ds_conteudo ->> 'citacoes' <> '[]';

/*------------------------------------------------------------------------------------------------------------------------*/

/*RECEBIMENTO DO ADITAMENTO DA DENUNCIA*/

--Cria e atualiza a dataCadastro a partir da dataRecebimentoAditamentoDenuncia
UPDATE core.tb_informacao_criminal
SET ds_conteudo = (
   SELECT jsonb_set(ds_conteudo
                  , '{recebimentosAditamentoDenuncia}'
                  , jsonb_agg(CASE 
			                    WHEN elem->>'dataRecebimentoAditamentoDenuncia' IS NOT NULL AND elem->>'dataCadastro' IS NULL THEN jsonb_set(elem, '{dataCadastro}', to_jsonb(to_number(elem->>'dataRecebimentoAditamentoDenuncia', '999999999999999999999')))
                                ELSE elem
                             END),true) AS coluna_com_novas_informacoes
   FROM   jsonb_array_elements(ds_conteudo->'recebimentosAditamentoDenuncia') elem
   ) 
WHERE 
	ds_conteudo ->> 'recebimentosAditamentoDenuncia' IS NOT NULL AND ds_conteudo ->> 'recebimentosAditamentoDenuncia' <> '[]';

END $$;