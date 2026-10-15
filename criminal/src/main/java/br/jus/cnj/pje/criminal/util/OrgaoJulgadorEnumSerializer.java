package br.jus.cnj.pje.criminal.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.jus.cnj.pje.criminal.enums.OrgaoJulgadorEnum;

public class OrgaoJulgadorEnumSerializer extends StdSerializer<OrgaoJulgadorEnum> {

	protected OrgaoJulgadorEnumSerializer() {
		super(OrgaoJulgadorEnum.class);
	}

	@Override
	public void serialize(OrgaoJulgadorEnum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeFieldName("cod");
		gen.writeNumber(value.getCod());
		gen.writeFieldName("label");
		gen.writeString(value.getLabel());
		gen.writeEndObject();
	}

}
