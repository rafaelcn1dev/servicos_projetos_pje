package br.jus.cnj.pje.criminal.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import br.jus.cnj.pje.criminal.enums.OrgaoJulgadorEnum;

public class OrgaoJulgadorEnumDeserializer extends StdDeserializer<OrgaoJulgadorEnum> {

	protected OrgaoJulgadorEnumDeserializer() {
		super(OrgaoJulgadorEnum.class);
	}

	@Override
	public OrgaoJulgadorEnum deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = p.getCodec().readTree(p);
		int cod = node.get("cod").asInt();
		return OrgaoJulgadorEnum.getPorCod(cod);
	}

}
