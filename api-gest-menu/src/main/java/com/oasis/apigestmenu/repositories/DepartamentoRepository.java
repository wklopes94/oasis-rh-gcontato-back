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

import com.oasis.apigestmenu.models.DepartamentoModel;
import com.oasis.apigestmenu.models.HotelModel;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "departamentos", path = "departamentos")
public interface DepartamentoRepository extends JpaRepository<DepartamentoModel, UUID>{
	
	// delete operation isn't exposed via rest
    @Override
    @RestResource(exported = false)
    void deleteById(UUID id);
 
    @Override
    @RestResource(exported = false)
    void delete(DepartamentoModel departaemntoModel);
 

    @Override
    @RestResource(exported=false)
    DepartamentoModel save(DepartamentoModel departaemntoModel);
	
	// Assinatura para fazer find de Nome do Departamento
	
	Optional<DepartamentoModel> findByid(UUID id);
	
	Page<DepartamentoModel> findBynome(String nome, Pageable pageable);
	
	
	
	Page<DepartamentoModel> findByEstado(String estado, Pageable pageable);
	
	Page<DepartamentoModel> findByDataRegisto(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
										LocalDateTime dataregisto, Pageable pageable);
	
	// FIND PARA TRAZER TODOS OS DEPARTAMENTOS ASSOCIADO A UM DETERMINADO NOMEHOTEL
	Page<DepartamentoModel>  findByhotelFkNome(String nome, Pageable pageable);
	
	Page<DepartamentoModel>  findByNomeAndHotelFkNome(String nome, String hotel, Pageable pageable);
	
	
	
	

	
	
	
	
	

}
