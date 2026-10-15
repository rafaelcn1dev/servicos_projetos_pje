package br.jus.cnj.pje.criminal.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.jus.cnj.pje.criminal.enums.DispositivoEnum;
import br.jus.cnj.pje.criminal.enums.TipoPenaExecucaoEnum;

public class DispositivoEnumSerializer extends StdSerializer<DispositivoEnum> {

	protected DispositivoEnumSerializer() {
		super(DispositivoEnum.class);
	}

	@Override
	public void serialize(DispositivoEnum value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		gen.writeStartObject();
		gen.writeFieldName("cod");
		gen.writeNumber(value.getCod());
		gen.writeEndObject();
	}

}
