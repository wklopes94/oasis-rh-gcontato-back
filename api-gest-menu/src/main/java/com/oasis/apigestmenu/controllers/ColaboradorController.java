package com.oasis.apigestmenu.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;

import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.oasis.apigestmenu.dtos.ColaboradorDto;

import com.oasis.apigestmenu.models.ColaboradorModel;

import com.oasis.apigestmenu.services.ColaboradorService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RepositoryRestController
@BasePathAwareController
public class ColaboradorController {

	// define a location
	public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

	final ColaboradorService service;

	public ColaboradorController(ColaboradorService colaboradorservice) {
		this.service = colaboradorservice;
	}

	@ResponseBody
	@RequestMapping(value = "colaboradores", path = "colaboradores", method = RequestMethod.POST, produces = "application/hal+json")
	public ResponseEntity<PersistentEntityResource> saveColaboradore(@RequestBody @Valid ColaboradorDto colaboradorDto,
			PersistentEntityResourceAssembler assembler) {

		System.out.println("Este é O Request: " + colaboradorDto.toString());
		var colaboradorModel = new ColaboradorModel();
		BeanUtils.copyProperties(colaboradorDto, colaboradorModel);
		colaboradorModel.setDataRegisto(LocalDateTime.now(ZoneId.of("UTC")));
		colaboradorModel.setUtilizador("Samuel Lopes");
		colaboradorModel.setEstado("a");
		System.out.println("Este é O Request: " + colaboradorModel.toString());
		ColaboradorModel hotelSave = service.saves(colaboradorModel);

		HttpHeaders headers = new HttpHeaders();
		if (hotelSave != null && hotelSave.getId() != null) {
			// Caso o item for inserido na BD
			return new ResponseEntity<>(assembler.toFullResource(hotelSave), headers, HttpStatus.OK);
		} else {
			// Caso algum valor for NULL
			return new ResponseEntity<>(assembler.toFullResource(hotelSave), HttpStatus.NO_CONTENT);
		}
	}

	@ResponseBody
	@PutMapping(value = "colaboradores/{id}", path = "colaboradores/{id}")
	public ResponseEntity<Object> updateColaborador(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid ColaboradorDto colaboradorDto) {
		Optional<ColaboradorModel> colaboradorModelOptional = service.findById(id);
		if (!colaboradorModelOptional.isPresent()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colaborador Not Found.");

		}

		var colaboradorModel = colaboradorModelOptional.get();
		BeanUtils.copyProperties(colaboradorDto, colaboradorModel);
		colaboradorModel.setDataRegisto(LocalDateTime.now(ZoneId.of("UTC")));
		colaboradorModel.setUtilizador("Samuel Lopes");
		colaboradorModel.setEstado("a");
		return ResponseEntity.status(HttpStatus.OK).body(service.saves(colaboradorModel));

	}

	@ResponseBody
	@DeleteMapping(value = "colaboradores/{id}", path = "colaboradores/{id}")
	public ResponseEntity<Object> deleteColaborador(@PathVariable(value = "id") UUID id) {
		Optional<ColaboradorModel> colaboradorModelOptional = service.findById(id);
		if (!colaboradorModelOptional.isPresent()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel Not Found.");

		}

		service.delete(colaboradorModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Hotel deleted successfuly");

	}

}
