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

import com.oasis.apigestmenu.models.ColaboradorModel;
import com.oasis.apigestmenu.models.DepartamentoModel;
import com.oasis.apigestmenu.models.TipoColaboradorModel;
import com.oasis.apigestmenu.repositories.ColaboradorRepository;

@Service
public class ColaboradorService {
	final ColaboradorRepository colaboradorrepository;
	
	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private TipoColaboradorService tipoColaboradorService;
	
	public ColaboradorService(ColaboradorRepository colaboradorrepository) {
		this.colaboradorrepository = colaboradorrepository;
	}
	
	public List<ColaboradorModel> findAll() {

		return colaboradorrepository.findAll();

	}
	
	public Optional<ColaboradorModel> findById(UUID id) {

		return colaboradorrepository.findById(id);

	}
	
	@Transactional
	public void delete(ColaboradorModel colaboradorModel) {

		colaboradorrepository.delete(colaboradorModel);

	}
	
	@Transactional
	public ColaboradorModel save(UUID id_departamento, UUID id_tipo_colaborador, ColaboradorModel colaboradorModel) {

		colaboradorModel.setId(null);
		Optional<DepartamentoModel> departamento = departamentoService.findById(id_departamento);
		Optional<TipoColaboradorModel> tipoColaborador = tipoColaboradorService.findById(id_tipo_colaborador);

		colaboradorModel.setDepartamentoFk(departamento.get());
		colaboradorModel.setDataRegisto(LocalDateTime.now(ZoneId.of("UTC")));
		colaboradorModel.setTipoColabFk(tipoColaborador.get());
		colaboradorModel.setUtilizador("Samuel Lopes");


		return colaboradorrepository.save(colaboradorModel);

	}
	
	@Transactional
	public ColaboradorModel saves(ColaboradorModel colaboradorModel) {

		return colaboradorrepository.save(colaboradorModel);
	}
	
	public ColaboradorModel findByIds(UUID id) {
		Optional<ColaboradorModel> obj = colaboradorrepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(id, null));

	}


}
