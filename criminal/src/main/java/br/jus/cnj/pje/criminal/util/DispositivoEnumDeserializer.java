package br.jus.cnj.pje.criminal.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import br.jus.cnj.pje.criminal.enums.DispositivoEnum;

public class DispositivoEnumDeserializer extends StdDeserializer<DispositivoEnum> {

	protected DispositivoEnumDeserializer() {
		super(DispositivoEnum.class);
	}

	@Override
	public DispositivoEnum deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = p.getCodec().readTree(p);
		int cod = node.get("cod").asInt();
		return DispositivoEnum.getPorCod(Long.valueOf(cod));
	}


}
