package com.oasis.apigestmenu.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.oasis.apigestmenu.dtos.HotelDto;
import com.oasis.apigestmenu.models.HotelModel;
@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "hotels", path = "hotels")
public interface HotelRepository extends JpaRepository<HotelModel, UUID>{
	
	
	// delete operation isn't exposed via rest
    @Override
    @RestResource(exported = false)
    void deleteById(UUID id);
 
    @Override
    @RestResource(exported = false)
    void delete(HotelModel hotelModel);
 

    @Override
    @RestResource(exported=false)
    HotelModel save(HotelModel hotelModel);
	
    Page<HotelModel> findBynome(String nome , Pageable pageable);
	
	@Override
	List<HotelModel> findAll();
	
	Page<HotelModel> findBynumeroFixo(String numeroFixo, Pageable pageable);
	
	
	
	//FIND PARA PRENCHER SELECT COLABORADOR
	Page<HotelModel> findByestado(String estado, Pageable pageable);
	
	Page<HotelModel> findBydataRegisto(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
										LocalDateTime dataregisto, Pageable pageable);
	
	
	// FIND PARA TRAZER TODOS OS HOTEIS ASSOCIADO A UM DETERMINADO DEPARATAMENTONOME
	List<HotelModel> findBydepartamentosModelNome(String nome);
	
}
