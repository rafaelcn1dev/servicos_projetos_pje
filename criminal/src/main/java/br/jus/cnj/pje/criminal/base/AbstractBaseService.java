package br.jus.cnj.pje.criminal.base;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.cnj.pje.criminal.repositories.CustomRepository;

public abstract class AbstractBaseService<E extends AbstractEntity<I>, I> implements BaseService<E, I>{


    @Override
    public E recuperarRecurso(I idRecurso) {
    	Optional<E> res = this.getRepository().getById(idRecurso);
        E recurso = null;
        if(res != null && res.isPresent()) {
        	recurso = res.get();
        }

        return recurso;
    }

    @Override
    public Page<E> pesquisarRecurso(Example<E> exemploRecurso, Pageable pageable) {

        Page<E> pagina = this.getRepository().findAll(exemploRecurso, pageable);

        return pagina;
    }

    @Override
    public E criarRecurso(E recurso) {

        recurso = this.getRepository().save(recurso);

        return recurso;
    }

    @Override
    public E alterarRecurso(E recurso) throws NoSuchElementException {

        if(recurso != null && recurso.getId() != null){
            this.recuperarRecurso(recurso.getId());
            recurso = this.getRepository().save(recurso);
        }

        return recurso;
    }

    protected abstract CustomRepository<E, I> getRepository();
}
