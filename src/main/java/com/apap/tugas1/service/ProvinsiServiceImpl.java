package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.ProvinsiModel;

import com.apap.tugas1.repository.ProvinsiDb;
@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {

	
	@Autowired
	private ProvinsiDb provinsiDb;
	
	@Override
	public List<ProvinsiModel> findAllProvinsi() {
		// TODO Auto-generated method stub
		return provinsiDb.findAll();
	}

	@Override
	public Optional<ProvinsiModel> getProvinsiById(long id) {
		// TODO Auto-generated method stub
		return provinsiDb.findById(id);
	}

}
