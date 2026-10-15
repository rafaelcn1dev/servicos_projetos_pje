package br.jus.cnj.pje.criminal.util;

import java.text.Normalizer;
import java.util.Collection;

public class StringUtil {
	public static String listToString(Collection<?> c) {
		return c.toString().replaceAll("\\[", "").replaceAll("\\]", "");
	}
	
	public static boolean isEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}
	
	public static String normalize(String txt) {
		if (txt != null) {
			txt = Normalizer.normalize(txt, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		}
		return txt;
	}
}
