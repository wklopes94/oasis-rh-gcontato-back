package com.oasis.apigestmenu.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasis.apigestmenu.models.DepartamentoModel;
import com.oasis.apigestmenu.models.ExtensaoModel;
import com.oasis.apigestmenu.repositories.ExtensaoRepository;

@Service
public class ExtensaoService {
	final ExtensaoRepository extensaorepository;

	@Autowired
	private DepartamentoService departamentoService;

	public ExtensaoService(ExtensaoRepository extensaorepository) {
		this.extensaorepository = extensaorepository;
	}

	public List<ExtensaoModel> findAll() {

		return extensaorepository.findAll();

	}

	public Optional<ExtensaoModel> findById(UUID id) {

		return extensaorepository.findById(id);

	}

	@Transactional
	public void delete(ExtensaoModel extensaoModel) {

		extensaorepository.delete(extensaoModel);

	}

	@Transactional
	public ExtensaoModel save(UUID id_hotel, ExtensaoModel extensaoModel) {

		extensaoModel.setId(null);
		Optional<DepartamentoModel> hotel = departamentoService.findById(id_hotel);

		extensaoModel.setDepartamentoFk(hotel.get());

		return extensaorepository.save(extensaoModel);

	}

	@Transactional
	public ExtensaoModel saves(ExtensaoModel extensaoModel) {
		
		return extensaorepository.save(extensaoModel);
	}

	public ExtensaoModel findByIds(UUID id) {
		Optional<ExtensaoModel> obj = extensaorepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(id, null));

	}

}
