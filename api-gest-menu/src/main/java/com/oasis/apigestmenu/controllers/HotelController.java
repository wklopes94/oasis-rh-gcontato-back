package com.oasis.apigestmenu.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.beans.BeanUtils;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.oasis.apigestmenu.dtos.HotelDto;
import com.oasis.apigestmenu.models.HotelModel;

import com.oasis.apigestmenu.repositories.HotelRepository;
import com.oasis.apigestmenu.services.HotelService;



import java.util.Optional;
import java.util.UUID;


@CrossOrigin(origins = "*", maxAge = 3600)
@RepositoryRestController
@BasePathAwareController
public class HotelController {
	
	final HotelService hotelservice;

	public HotelController(HotelService hotelservice) {
		this.hotelservice = hotelservice;
	}
	
	@Autowired
	HotelRepository repositorioHotel;
	@ResponseBody
	@RequestMapping(value = "hotels", path="hotels", method = RequestMethod.POST, produces = 
	 "application/hal+json")
	public ResponseEntity<PersistentEntityResource> saveHotel(@RequestBody @Valid HotelDto hotelDto, PersistentEntityResourceAssembler assembler){
		
	  var hotelModel = new HotelModel();
	  BeanUtils.copyProperties(hotelDto, hotelModel);
	  hotelModel.setDataRegisto(LocalDateTime.now(ZoneId.of("UTC")));
	  hotelModel.setEstado("a");
	  hotelModel.setUtilizadorReg("Samuel Lopes");
	   
	  HotelModel hotelSave = hotelservice.save(hotelModel);
	  
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
	@PutMapping(value = "hotels/{id}", path="hotels/{id}")
	public ResponseEntity<Object> updateHotel(@PathVariable(value="id") UUID id, @RequestBody @Valid HotelDto hotelDto, PersistentEntityResourceAssembler assembler){
		Optional<HotelModel> HotelModelOptional = hotelservice.findById(id);
		if(!HotelModelOptional.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel Not Found.");
	
		}
		
		var hotelModel = HotelModelOptional.get(); 
		BeanUtils.copyProperties(hotelDto, hotelModel);
		hotelModel.setDataRegisto(LocalDateTime.now(ZoneId.of("UTC")));
		hotelModel.setEstado("a");
		hotelModel.setUtilizadorReg("Samuel Lopes");
		return ResponseEntity.status(HttpStatus.OK).body(hotelservice.save(hotelModel));
		
	}
	
	@ResponseBody
	@DeleteMapping(value = "hotels/{id}", path="hotels/{id}")
	public ResponseEntity<Object> deleteHotels(@PathVariable(value="id") UUID id, PersistentEntityResourceAssembler assembler){
		Optional<HotelModel> HotelModelOptional = hotelservice.findById(id);
		if(!HotelModelOptional.isPresent()) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Colaborador Not Found.");
			
		}
		
		hotelservice.delete(HotelModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Tipo de Colaborador deleted successfuly");
		
	}
}
