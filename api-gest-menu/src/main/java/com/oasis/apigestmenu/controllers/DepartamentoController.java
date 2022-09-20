package com.oasis.apigestmenu.controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oasis.apigestmenu.dtos.DepartamentoDto;
import com.oasis.apigestmenu.dtos.HotelDto;
import com.oasis.apigestmenu.models.DepartamentoModel;
import com.oasis.apigestmenu.models.HotelModel;
import com.oasis.apigestmenu.repositories.DepartamentoRepository;
import com.oasis.apigestmenu.repositories.HotelRepository;
import com.oasis.apigestmenu.services.DepartamentoService;
import com.oasis.apigestmenu.services.HotelService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RepositoryRestController
@BasePathAwareController
public class DepartamentoController {
	
	final DepartamentoService departamentoservice;

	public DepartamentoController(DepartamentoService departamentoservice) {
		this.departamentoservice = departamentoservice;
	}

	@Autowired
	DepartamentoRepository repositorioDepartamemto;
	@ResponseBody
	@RequestMapping(value = "departamentos", path="departamentos", method = RequestMethod.POST, produces = 
	 "application/hal+json")
	public ResponseEntity<PersistentEntityResource> saveDepartamento(@RequestBody @Valid DepartamentoDto departamentoDto, PersistentEntityResourceAssembler assembler){
		
	  var departamentoModel1 = new DepartamentoModel();
	  BeanUtils.copyProperties(departamentoDto, departamentoModel1);
	  departamentoModel1.setDataRegisto(LocalDateTime.now(ZoneId.of("UTC")));
	  departamentoModel1.setEstado("a");
	  departamentoModel1.setUtilizador("Samuel Lopes");
	   
	  DepartamentoModel hotelSave = departamentoservice.saves(departamentoModel1);
	  
	  HttpHeaders headers = new HttpHeaders();
      if (hotelSave != null && hotelSave.getId() != null) {
          // Caso o item for inserido na BD 
          return new ResponseEntity<>(
          		assembler.toFullResource(hotelSave),
                  headers,
                  HttpStatus.OK);
      } else {
          // Caso algum valor for NULL
      	return new ResponseEntity<>(
      			assembler.toFullResource(hotelSave), HttpStatus.NO_CONTENT);
      }
		
		
	}
	
	
	

	@ResponseBody
	@PutMapping(value = "departamentos/{id}", path="departamentos/{id}")
	public ResponseEntity<Object> updateDepartamento(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid DepartamentoDto departamentoDto, PersistentEntityResourceAssembler assembler) {
		System.out.println("O Requeste Enviado é: "+ departamentoDto.toString());
		Optional<DepartamentoModel> departamentoModelOptional = departamentoservice.findById(id);
		if (!departamentoModelOptional.isPresent()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento Not Found.");

		}

		var departamentoModel = departamentoModelOptional.get();
		System.out.println("O Requeste Enviado é: "+ departamentoDto.toString());
		BeanUtils.copyProperties(departamentoDto, departamentoModel);
		departamentoModel.setDataRegisto(LocalDateTime.now(ZoneId.of("UTC")));
		departamentoModel.setEstado("a");
		departamentoModel.setUtilizador("Samuel Lopes");
		//return ResponseEntity.status(HttpStatus.OK).body(departamentoservice.saves(departamentoModel));
		
		 DepartamentoModel hotelSave = departamentoservice.saves(departamentoModel);
		
		  HttpHeaders headers = new HttpHeaders();
	      if (hotelSave != null && hotelSave.getId() != null) {
	          // Caso o item for inserido na BD 
	          return new ResponseEntity<>(
	          		assembler.toFullResource(hotelSave),
	                  headers,
	                  HttpStatus.OK);
	      } else {
	          // Caso algum valor for NULL
	      	return new ResponseEntity<>(
	      			assembler.toFullResource(hotelSave), HttpStatus.NO_CONTENT);
	      }

	}
	

	@ResponseBody
	@DeleteMapping(value = "departamentos/{id}", path="departamentos/{id}")
	public ResponseEntity<Object> deleteDepartamento(@PathVariable(value = "id") UUID id) {
		Optional<DepartamentoModel> DepartamentoModelOptional = departamentoservice.findById(id);
		if (!DepartamentoModelOptional.isPresent()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel Not Found.");

		}

		departamentoservice.delete(DepartamentoModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Hotel deleted successfuly");

	}
	

}