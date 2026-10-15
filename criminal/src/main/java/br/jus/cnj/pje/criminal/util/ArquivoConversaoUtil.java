package br.jus.cnj.pje.criminal.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

public class ArquivoConversaoUtil {

	public static ByteArrayOutputStream getPDFFromConteudoHTML(String html) throws DocumentException, UnsupportedEncodingException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Tidy tidy = new Tidy();
		org.w3c.dom.Document doc;
		ITextRenderer renderer = new ITextRenderer();
		tidy.setShowWarnings(false);
		tidy.setOnlyErrors(true);
		tidy.setQuiet(true);
		doc = tidy.parseDOM(new ByteArrayInputStream(html.getBytes("UTF-8")), null); //ISO-8859-1
		renderer.setDocument(doc, null);
		renderer.layout();
		renderer.createPDF(out);
		return out;
	}

	
}
