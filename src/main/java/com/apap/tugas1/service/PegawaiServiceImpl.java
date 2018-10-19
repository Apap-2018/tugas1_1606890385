package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {

	@Autowired 
	private PegawaiDb pegawaiDb;
	
	
	@Override
	public void addPegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		pegawaiDb.save(pegawai);
	}

	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		// TODO Auto-generated method stub
		System.out.println("masuk");
		System.out.println(nip);
		System.out.println(pegawaiDb.findByNip(nip).get().getNama());
		return pegawaiDb.findByNip(nip).get();
	
	}

	@Override
	public void deletePegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		pegawaiDb.delete(pegawai);
		
	}

	@Override
	public void updatePegawai(long id, PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		PegawaiModel baru = pegawaiDb.getOne(id);
		baru.setTanggalLahir(pegawai.getTanggalLahir());
		baru.setInstansi(pegawai.getInstansi());
		baru.setTahun_masuk(pegawai.getTahun_masuk());
		pegawaiDb.save(baru);
		
	}

	@Override
	public PegawaiModel getPegawaiDetailById(long id) {
		// TODO Auto-generated method stub
		return pegawaiDb.getOne(id);
	}

	@Override
	public List<PegawaiModel> findInstansiOrderByTanggallahirAsc(InstansiModel instansi) {
		// TODO Auto-generated method stub
		return pegawaiDb.findByInstansiOrderByTanggalLahirAsc(instansi);
	}

	@Override
	public List<PegawaiModel> findAllPegawai() {
		// TODO Auto-generated method stub
		return pegawaiDb.findAll();
	}

}
