package br.jus.cnj.pje.criminal.aop;

import br.jus.cnj.pje.criminal.model.entities.CodigoTribunal;

public class TribunalContext {

    private static ThreadLocal<CodigoTribunal> codigoTribunal = new InheritableThreadLocal<>();
    private static ThreadLocal<String> siglaTribunal = new InheritableThreadLocal<>();

    public static CodigoTribunal codigoTribunal() {
        return codigoTribunal.get();
    }

    public static String siglaTribunal() {
        return siglaTribunal.get();
    }

    public static void setCodigoTribunal(CodigoTribunal codigo) {
    	if(codigo == null) {
            throw new IllegalArgumentException(
                    "O código do tribunal não pode ser nulo.");
    	}
        codigoTribunal.set(codigo);
        siglaTribunal.set(codigo.siglaTribunal());
    }

    public static void clear() {
        codigoTribunal.remove();
        siglaTribunal.remove();
    }

}
