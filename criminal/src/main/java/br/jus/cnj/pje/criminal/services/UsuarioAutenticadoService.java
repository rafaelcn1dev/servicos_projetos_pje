package br.jus.cnj.pje.criminal.services;

import br.jus.cnj.pje.criminal.model.beans.PjeUser;
import br.jus.cnj.pje.criminal.model.entities.CodigoTribunal;


public interface UsuarioAutenticadoService{
	public PjeUser recuperarUsuarioAutenticado();
	public CodigoTribunal codigoTribunal();
	public Boolean usuarioSistema();
	public Boolean permissaoTribunal(CodigoTribunal codigoTribunal);
}