package br.jus.cnj.pje.criminal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.model.entities.MotivoSuspensaoProcesso;

public interface MotivoSuspensaoProcessoService {
	MotivoSuspensaoProcesso criarMotivoSuspensaoProcesso(MotivoSuspensaoProcesso motivoSuspensaoProcesso);
	List<MotivoSuspensaoProcesso> recuperarMotivosSuspensaoProcessoAtivos();
	Long countMotivosSuspensaoProcesso();
	Long countMotivosSuspensaoProcesso(MotivoSuspensaoProcesso motivoSuspensaoProcesso);
	Page<MotivoSuspensaoProcesso> recuperarMotivosSuspensaoProcessoPorPagina(MotivoSuspensaoProcesso motivoSuspensaoProcesso, Pageable pagina);
	Page<MotivoSuspensaoProcesso> recuperarMotivosSuspensaoProcessoPorPagina(Pageable pagina);
	MotivoSuspensaoProcesso recuperarMotivoSuspensaoProcesso(Long idMotivoSuspensaoProcesso);
	MotivoSuspensaoProcesso alterarMotivoSuspensaoProcesso(MotivoSuspensaoProcesso motivoSuspensaoProcesso);
	MotivoSuspensaoProcesso inativarMotivoSuspensaoProcesso(MotivoSuspensaoProcesso motivoSuspensaoProcesso);
}
