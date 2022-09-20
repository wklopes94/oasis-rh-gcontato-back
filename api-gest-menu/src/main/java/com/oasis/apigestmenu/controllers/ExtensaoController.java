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
import com.oasis.apigestmenu.dtos.ExtensaoDto;
import com.oasis.apigestmenu.models.DepartamentoModel;
import com.oasis.apigestmenu.models.ExtensaoModel;
import com.oasis.apigestmenu.repositories.DepartamentoRepository;
import com.oasis.apigestmenu.services.DepartamentoService;
import com.oasis.apigestmenu.services.ExtensaoService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RepositoryRestController
@BasePathAwareController
public class ExtensaoController {

	@Autowired
	private ExtensaoService service;
	
	public ExtensaoController(ExtensaoService departamentoservice) {
		this.service = departamentoservice;
	}

	@ResponseBody
	@RequestMapping(value = "extensoes", path="extensoes", method = RequestMethod.POST, produces = 
	 "application/hal+json")
	public ResponseEntity<PersistentEntityResource> saveDepartamento(@RequestBody @Valid ExtensaoDto extensaoDto, PersistentEntityResourceAssembler assembler){
		
	  var extensaoModel = new ExtensaoModel();
	  BeanUtils.copyProperties(extensaoDto, extensaoModel);
	  extensaoModel.setDataRegisto(LocalDateTime.now(ZoneId.of("UTC")));
	  extensaoModel.setEstado("a");
	  extensaoModel.setUtilizadorReg("Samuel Lopes");
	   
	  ExtensaoModel ExtensaoSave = service.saves(extensaoModel);
	  
	  HttpHeaders headers = new HttpHeaders();
      if (ExtensaoSave != null && ExtensaoSave.getId() != null) {
          // Caso o item for inserido na BD 
          return new ResponseEntity<>(
          		assembler.toFullResource(ExtensaoSave),
                  headers,
                  HttpStatus.OK);
      } else {
          // Caso algum valor for NULL
      	return new ResponseEntity<>(
      			assembler.toFullResource(ExtensaoSave), HttpStatus.NO_CONTENT);
      }
		
		
	}


	@ResponseBody
	@PutMapping(value = "extensoes/{id}", path="extensoes/{id}")
	public ResponseEntity<Object> updateExtensao(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid ExtensaoDto extensaoDto) {
		Optional<ExtensaoModel> extensaoModelOptional = service.findById(id);
		if (!extensaoModelOptional.isPresent()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Extensão Not Found.");

		}

		var extensaoModel = extensaoModelOptional.get();
		BeanUtils.copyProperties(extensaoDto, extensaoModel);
		extensaoModel.setDataRegisto(LocalDateTime.now(ZoneId.of("UTC")));
		extensaoModel.setEstado("a");
		extensaoModel.setUtilizadorReg("Samuel Lopes");
		return ResponseEntity.status(HttpStatus.OK).body(service.saves(extensaoModel));

	}
	

	@ResponseBody
	@DeleteMapping(value = "extensoes/{id}", path="extensoes/{id}")
	public ResponseEntity<Object> deleteExtensao(@PathVariable(value = "id") UUID id) {
		Optional<ExtensaoModel> ExtensaoModelOptional = service.findById(id);
		if (!ExtensaoModelOptional.isPresent()) {
	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Extensão Not Found.");
	
		}
	
		service.delete(ExtensaoModelOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Extensão deleted successfuly");
	
	}
	
}
