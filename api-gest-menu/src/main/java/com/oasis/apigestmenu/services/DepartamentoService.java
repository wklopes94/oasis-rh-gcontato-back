package com.oasis.apigestmenu.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.oasis.apigestmenu.models.DepartamentoModel;
import com.oasis.apigestmenu.models.HotelModel;
import com.oasis.apigestmenu.repositories.DepartamentoRepository;

@Service
public class DepartamentoService {
	@Autowired
	private DepartamentoRepository departamentorepository;

	@Autowired
	private HotelService hotelService;

	public DepartamentoService(DepartamentoRepository departamentorepository) {
		this.departamentorepository = departamentorepository;
	}

	public List<DepartamentoModel> findAll() {

		return departamentorepository.findAll();

	}

	public Optional<DepartamentoModel> findById(UUID id) {

		return departamentorepository.findById(id);

	}

	@Transactional
	public void delete(DepartamentoModel departamentoModel) {

		departamentorepository.delete(departamentoModel);

	}

	@Transactional
	public DepartamentoModel save(UUID id_hotel, DepartamentoModel departamentoModel) {

		departamentoModel.setId(null);
		Optional<HotelModel> hotel = hotelService.findById(id_hotel);
		

		departamentoModel.setHotelFk(hotel.get());
		departamentoModel.setDataRegisto(LocalDateTime.now(ZoneId.of("UTC")));
		departamentoModel.setUtilizador("Samuel Lopes");
		departamentoModel.setEstado("A");

		return departamentorepository.save(departamentoModel);

	}

	@Transactional
	public DepartamentoModel saves(DepartamentoModel departamentoModel) {

		return departamentorepository.save(departamentoModel);
	}

	public DepartamentoModel findByIds(UUID id) {
		Optional<DepartamentoModel> obj = departamentorepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(id, null));

	}

}
