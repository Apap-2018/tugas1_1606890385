package com.apap.tugas1.service;

import java.util.List;


import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;


public interface PegawaiService {
	void addPegawai(PegawaiModel pegawai);
	
	PegawaiModel getPegawaiDetailByNip(String nip);
	
	void deletePegawai(PegawaiModel pegawai);
	
	void updatePegawai(long id, PegawaiModel pegawai);
	
	PegawaiModel getPegawaiDetailById(long id);
	
	List<PegawaiModel> findInstansiOrderByTanggallahirAsc(InstansiModel instansi);

	List<PegawaiModel> findAllPegawai();
}
