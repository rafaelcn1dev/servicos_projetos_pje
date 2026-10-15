package br.jus.cnj.pje.criminal.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.jus.cnj.pje.criminal.enums.TipoPenaExecucaoEnum;

public class TipoPenaExecucaoEnumSerializer extends StdSerializer<TipoPenaExecucaoEnum> {

	protected TipoPenaExecucaoEnumSerializer() {
		super(TipoPenaExecucaoEnum.class);
	}

	@Override
	public void serialize(TipoPenaExecucaoEnum value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		gen.writeStartObject();
		gen.writeFieldName("cod");
		gen.writeNumber(value.getCod());
		gen.writeFieldName("label");
		gen.writeString(value.getLabel());
		gen.writeEndObject();
	}

}
