package com.oasis.apigestmenu.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.oasis.apigestmenu.models.TipoColaboradorModel;
import com.oasis.apigestmenu.repositories.TipoColaboradorRepository;

@Service
public class TipoColaboradorService {
	final TipoColaboradorRepository tipocolaboradorrepository;
	
	public TipoColaboradorService(TipoColaboradorRepository tipocolaboradorrepository) {
		this.tipocolaboradorrepository = tipocolaboradorrepository;
	}
	
	@Transactional
	public TipoColaboradorModel save(TipoColaboradorModel tipoColaboradorModel) {
		
		return tipocolaboradorrepository.save(tipoColaboradorModel);
	}
	
	public List<TipoColaboradorModel> findAll() {
		
		return tipocolaboradorrepository.findAll();
		
	}
	
	public Optional<TipoColaboradorModel> findById(UUID id){
		
		return tipocolaboradorrepository.findById(id);
		
	}
	
	@Transactional
	public void delete(TipoColaboradorModel tipoColaboradorModel) {
		
		tipocolaboradorrepository.delete(tipoColaboradorModel);
		
	}


}
