package com.oasis.apigestmenu.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.oasis.apigestmenu.models.ExtensaoModel;
import com.oasis.apigestmenu.models.HotelModel;
import com.oasis.apigestmenu.models.TipoColaboradorModel;
@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "tipocolaboradores", path = "tipocolaboradores")
public interface TipoColaboradorRepository extends JpaRepository<TipoColaboradorModel, UUID> {
	
	// delete operation isn't exposed via rest
    @Override
    @RestResource(exported = false)
    void deleteById(UUID id);
 
    @Override
    @RestResource(exported = false)
    void delete(TipoColaboradorModel hotelModel);
 

    @Override
    @RestResource(exported=false)
    TipoColaboradorModel save(TipoColaboradorModel hotelModel);
    
    Page<TipoColaboradorModel> findByEstado (String estado, Pageable pageable);
    
    Page<TipoColaboradorModel> findByTipoColaboradorAndEstado (String tipoColaborador,String estado ,Pageable pageable);
    
    
    
    
    

}
