package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.repository.InstansiDb;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService {
	@Autowired
	private InstansiDb instansiDb;

	@Override
	public Optional<InstansiModel> getInstansiDetailById(Long id) {
		// TODO Auto-generated method stub
		return instansiDb.findById(id);
	}

	@Override
	public void addInstansi(InstansiModel instansi) {
		// TODO Auto-generated method stub
		instansiDb.save(instansi);
		
	}

	@Override
	public void deleteInstansi(InstansiModel instansi) {
		// TODO Auto-generated method stub
		instansiDb.delete(instansi);
	}

	@Override
	public void updateInstansi(InstansiModel instansi, long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<InstansiModel> findAllInstansi() {
		// TODO Auto-generated method stub
		return instansiDb.findAll();
	}

	/*@Override
	public Optional<InstansiModel> getInstansiDetailByNama(String nama) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	
	
	

}
