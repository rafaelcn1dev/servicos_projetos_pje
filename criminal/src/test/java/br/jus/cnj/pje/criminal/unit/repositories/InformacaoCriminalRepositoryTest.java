package br.jus.cnj.pje.criminal.unit.repositories;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import br.jus.cnj.pje.criminal.repositories.InformacaoCriminalRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.jus.cnj.pje.criminal.model.entities.InformacaoCriminal;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class InformacaoCriminalRepositoryTest {
	
	@Autowired
	private InformacaoCriminalRepository icRepo;
	
	@Test
	public void pesquisarInformacaoCriminalPorIdProcessoEPorIdProcessoParteLegacy() {
		List<InformacaoCriminal> ics = new ArrayList<InformacaoCriminal>();
		ics = icRepo.findByProcessoIdAndParteIdParteLegacy(85L, Long.valueOf(172331));

		
		assertNotNull(ics);
	}

}
