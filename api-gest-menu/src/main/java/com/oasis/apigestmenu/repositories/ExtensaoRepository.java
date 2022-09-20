package com.oasis.apigestmenu.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.oasis.apigestmenu.models.DepartamentoModel;
import com.oasis.apigestmenu.models.ExtensaoModel;
import com.oasis.apigestmenu.models.HotelModel;
@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "extensoes", path = "extensoes")
public interface ExtensaoRepository extends JpaRepository<ExtensaoModel, UUID>{
	
	// delete operation isn't exposed via rest
    @Override
    @RestResource(exported = false)
    void deleteById(UUID id);
 
    @Override
    @RestResource(exported = false)
    void delete(ExtensaoModel hotelModel);
 

    @Override
    @RestResource(exported=false)
    ExtensaoModel save(ExtensaoModel hotelModel);
    
    Page<ExtensaoModel> findByDepartamentoFkHotelFkNome(String nome, Pageable pageable);
    
    Page<ExtensaoModel> findByDepartamentoFkNome(String nome, Pageable pageable);
    
    Page<ExtensaoModel> findByNumero (String numero, Pageable pageable);
    
    Page<ExtensaoModel>  findByNumeroAndDepartamentoFkNome(String numero, String departamento, Pageable pageable);
    
    

}
