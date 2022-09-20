package com.oasis.apigestmenu.services;


import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oasis.apigestmenu.models.HotelModel;
import com.oasis.apigestmenu.repositories.HotelRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class HotelService {
	
	final HotelRepository hotelrepository;
	
	public HotelService(HotelRepository hotelrepository) {
		this.hotelrepository = hotelrepository;
	}
	
	@Transactional
	public HotelModel save(HotelModel hotelModel) {
		
		return hotelrepository.save(hotelModel);
	}
	
	public List<HotelModel> findAll() {

		return hotelrepository.findAll();
		
	}
	
	public Optional<HotelModel> findById(UUID id){
		
		return hotelrepository.findById(id);
		
	}
	
	@Transactional
	public void delete(HotelModel hotelModel) {
		
		 hotelrepository.delete(hotelModel);
		
	}

}
