package com.edilre.preventivatore.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.edilre.preventivatore.model.PreventivoModel;
import com.edilre.preventivatore.model.ProdottoPreventivoModel;
import com.lowagie.text.DocumentException;

@Service
public class StampaService {

	public ByteArrayOutputStream generatePdfFromHtml(PreventivoModel model) throws DocumentException, IOException {
//		String outputFolder = System.getProperty("user.home") + File.separator + "preventivo.pdf";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(parseThymeleafTemplate(model));
		renderer.layout();
		renderer.createPDF(outputStream);

		outputStream.close();
		
		return outputStream;
	}

	private String parseThymeleafTemplate(PreventivoModel model) throws FileNotFoundException, UnsupportedEncodingException {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setOrder(0);

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		Context context = new Context();
		context.setVariable("model", model);
		context.setVariable("total", model.getDatiPreventivo().getProdotti().stream().map(ProdottoPreventivoModel::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
		context.setVariable("today", model.getDatiPreventivo().getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		String html = templateEngine.process("preventivo", context);
		
		return convertToXhtml(html);
	}
	
	private String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString("UTF-8");
    }

}
