package com.edilre.preventivatore.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edilre.preventivatore.model.PreventivoModel;
import com.edilre.preventivatore.service.MailService;
import com.edilre.preventivatore.service.PreventivoService;
import com.edilre.preventivatore.service.StampaService;
import com.lowagie.text.DocumentException;

@RestController
@RequestMapping(value = "/api/preventivi")
public class PreventivoController {
	
	@Autowired
	private PreventivoService service;
	
	@Autowired
	private StampaService stampaService;
	
	@Autowired
	private MailService mailService;
	
	@PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PreventivoModel> salvaPreventivo(@RequestBody PreventivoModel preventivo) {
		
		return new ResponseEntity<>(this.service.save(preventivo), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<PreventivoModel>> getPreventivi(@RequestParam(value = "search", required = false) String search) {
		
		return new ResponseEntity<>(this.service.getAll(search), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PreventivoModel> getPreventivo(@PathVariable("id") Integer id) {
		
		return new ResponseEntity<>(this.service.getById(id), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}/", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PreventivoModel> aggiornaPreventivo(@RequestBody PreventivoModel preventivo, @PathVariable("id") Integer id) {
	
		preventivo.setId(id);
		
		return new ResponseEntity<>(this.service.update(preventivo), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/stampa")
	public ResponseEntity<Resource> stampaPreventivo(@PathVariable("id") Integer id) throws DocumentException, IOException {
		
		PreventivoModel model = this.service.getById(id);
		
		ByteArrayOutputStream pdf = this.stampaService.generatePdfFromHtml(model);
		
		ByteArrayResource resource = new ByteArrayResource(pdf.toByteArray());
		
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-type", MediaType.APPLICATION_PDF_VALUE);
	    headers.set("Content-Disposition","inline; filename=\"preventivo.pdf\"");
		
	    return ResponseEntity.status(HttpStatus.OK).headers(headers).body(resource);
	}
	
	@GetMapping(value = "/{id}/mail")
	public ResponseEntity<Void> inviaMail(@PathVariable("id") Integer id) throws DocumentException, IOException, MessagingException {
		PreventivoModel model = this.service.getById(id);

		ByteArrayOutputStream pdf = this.stampaService.generatePdfFromHtml(model);

		this.mailService.sendMessageWithAttachment(model.getDatiCliente().getEmail(), "Preventivo Costruzioni P&G", "Buongiorno,\ncome concordato, in allegato il preventivo da Lei richiesto.\n\nLa ringraziamo e Le auguriamo una buona giornata.", pdf.toByteArray());

	    return ResponseEntity.ok().build();
	}
	

}
