package br.jus.cnj.pje.criminal.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

	private static final DateTimeFormatter FORMATADOR_PADRAO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter FORMATADOR_PADRAO_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private static final DateTimeFormatter FORMATADOR_PADRAO_HORA = DateTimeFormatter.ofPattern("HH:mm");

	private static String dateToString(Date date, DateTimeFormatter formatador) {
		LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		return formatador.format(dateTime);
	}

	public static String dateToString(Date date) {
		return dateToString(date, FORMATADOR_PADRAO);
	}

	public static String dateHourToString(Date date) {
		return dateToString(date, FORMATADOR_PADRAO_DATA_HORA);
	}

	public static String dateToHour(Date date) {
		return dateToString(date, FORMATADOR_PADRAO_HORA);
	}

	public static String dateToString(Date date, String padrao) {
		return dateToString(date, DateTimeFormatter.ofPattern(padrao));
	}

}
