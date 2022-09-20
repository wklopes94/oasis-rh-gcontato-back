package com.oasis.apigestmenu.repositories;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.oasis.apigestmenu.models.ColaboradorModel;
import com.oasis.apigestmenu.models.DepartamentoModel;
import com.oasis.apigestmenu.models.ExtensaoModel;
import com.oasis.apigestmenu.models.HotelModel;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "colaboradores", path = "colaboradores")
public interface ColaboradorRepository extends JpaRepository<ColaboradorModel, UUID>{
	
	// delete operation isn't exposed via rest
    @Override
    @RestResource(exported = false)
    void deleteById(UUID id);
 
    @Override
    @RestResource(exported = false)
    void delete(ColaboradorModel hotelModel);
 

    @Override
    @RestResource(exported=false)
    ColaboradorModel save(ColaboradorModel hotelModel);
	
	
	
	
	// FIND PARA O SELET DO CAMPO COLBORADOR
	List<ColaboradorModel> findByNomeColab(String nome);
	 
	// FIND PARA O SELET DO CAMPO DEPARTAMENTO
	Page<ColaboradorModel> findByDepartamentoFkNome(String nome, Pageable pageable);
	
	// FIND PARA O SELET DO CAMPO HOTEL
	Page<ColaboradorModel> findByDepartamentoFkHotelFkNome(String nome, Pageable pageable);
	
	Page<ColaboradorModel> findByDepartamentoFkHotelFkNomeAndDepartamentoFkNome(String hotel, String departamento, Pageable pageable);
	
	Page<ColaboradorModel> findByDepartamentoFkHotelFkNomeAndDepartamentoFkNomeAndNomeColab(String hotel, String departamento, String colaborador, Pageable pageable);
	
	Page<ColaboradorModel>  findByNomeColabAndDepartamentoFkNome(String nome, String departamento, Pageable pageable);

}
