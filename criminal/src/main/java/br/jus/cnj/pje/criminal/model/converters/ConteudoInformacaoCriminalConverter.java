package br.jus.cnj.pje.criminal.model.converters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.cnj.pje.criminal.model.beans.ConteudoInformacaoCriminalBean;

@Converter
public class ConteudoInformacaoCriminalConverter implements AttributeConverter<ConteudoInformacaoCriminalBean, String>{

	private static final Logger LOGGER = Logger.getLogger(ConteudoInformacaoCriminalConverter.class.getName());
	
	@Override
	public String convertToDatabaseColumn(ConteudoInformacaoCriminalBean informacaoCriminalObject) {
		
		String ret = "";
		
		try {
			ret = this.getObjectMapper().writeValueAsString(informacaoCriminalObject);
		} catch (JsonProcessingException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
		
		return ret;
	}

	@Override
	public ConteudoInformacaoCriminalBean convertToEntityAttribute(String informacaoCriminalString) {
		ConteudoInformacaoCriminalBean informacaoCriminal = null;
		
		try {
			informacaoCriminal = this.getObjectMapper().readValue(informacaoCriminalString, ConteudoInformacaoCriminalBean.class);
		} catch (JsonParseException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
		
		return informacaoCriminal;
	}
	
	private ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		return mapper;
	}

}
