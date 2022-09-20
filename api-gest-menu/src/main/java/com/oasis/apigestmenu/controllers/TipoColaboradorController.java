package com.oasis.apigestmenu.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oasis.apigestmenu.dtos.HotelDto;
import com.oasis.apigestmenu.dtos.TipoColaboradorDto;
import com.oasis.apigestmenu.models.HotelModel;
import com.oasis.apigestmenu.models.TipoColaboradorModel;
import com.oasis.apigestmenu.repositories.HotelRepository;
import com.oasis.apigestmenu.repositories.TipoColaboradorRepository;
import com.oasis.apigestmenu.services.TipoColaboradorService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RepositoryRestController
@BasePathAwareController
public class TipoColaboradorController {

	final TipoColaboradorService tipocolaboradorservice;

	public TipoColaboradorController(TipoColaboradorService service) {
		this.tipocolaboradorservice = service;
	}
	
	

	@RequestMapping(value = "tipocolaboradores", path="tipocolaboradores", method = RequestMethod.POST, produces = 
	 "application/hal+json")
	public ResponseEntity<PersistentEntityResource> saveHotel(@RequestBody @Valid TipoColaboradorDto tipoColaboradorDto, PersistentEntityResourceAssembler assembler){
		
	  var tipoColaboradorModel = new TipoColaboradorModel();
	  BeanUtils.copyProperties(tipoColaboradorDto, tipoColaboradorModel);
	  tipoColaboradorModel.setDataRegisto(LocalDateTime.now(ZoneId.of("UTC")));
	  tipoColaboradorModel.setUtilizador("Samuel Lopes");
	  tipoColaboradorModel.setEstado("a");
	   
	  TipoColaboradorModel hotelSave = tipocolaboradorservice.save(tipoColaboradorModel);
	  
	  HttpHeaders headers = new HttpHeaders();
      if (hotelSave != null && hotelSave.getId() != null) {
          //Caso o item for inserido na BD 
          return new ResponseEntity<>(
          		assembler.toFullResource(hotelSave),
                  headers,
                  HttpStatus.OK);
      } else {
          //Caso algum valor for NULL
      	return new ResponseEntity<>(
      			assembler.toFullResource(hotelSave), HttpStatus.NO_CONTENT);
      }
		
		
	}
	
	
	@ResponseBody
	@DeleteMapping(value = "tipocolaboradores/{id}", path="tipocolaboradores/{id}")
	public ResponseEntity<Object> deleteTipoColaborador(@PathVariable(value="id") UUID id){
		Optional<TipoColaboradorModel> tipoColaboradorModelOptional = tipocolaboradorservice.findById(id);
		if(!tipoColaboradorModelOptional.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Colaborador Not Found.");
			
		}
		
		tipocolaboradorservice.delete(tipoColaboradorModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Tipo de Colaborador deleted successfuly");
		
	}

	@ResponseBody
	@PutMapping(value = "tipocolaboradores/{id}", path="tipocolaboradores/{id}")
	public ResponseEntity<Object> updateTipoColaborador(@PathVariable(value="id") UUID id, @RequestBody @Valid TipoColaboradorDto tipoColaboradorDto){
		Optional<TipoColaboradorModel> tipoColaboradorModelOptional = tipocolaboradorservice.findById(id);
		if(!tipoColaboradorModelOptional.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Colaborador Not Found.");
			
		}
		
		
		var tipoColaboradorModel = tipoColaboradorModelOptional.get(); 
		BeanUtils.copyProperties(tipoColaboradorDto, tipoColaboradorModel);
		tipoColaboradorModel.setUtilizador("Samuel Lopes");
		tipoColaboradorModel.setEstado("a");
	
		
		return ResponseEntity.status(HttpStatus.OK).body(tipocolaboradorservice.save(tipoColaboradorModel));
		
	}

}
