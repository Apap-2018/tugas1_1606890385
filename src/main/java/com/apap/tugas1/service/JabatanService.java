package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;

public interface JabatanService {
	void addJabatan(JabatanModel jabatan);
	void deleteJabatan(JabatanModel jabatan);
	void updateJabatan(JabatanModel jabatan, long id);
	JabatanModel getJabatanDetailByNama(String nama);
	List<Object[]> findAllName();
	List<JabatanModel> findAllJabatan();
	JabatanModel getJabatanDetailById(long id);
	List<PegawaiModel> getAllPegawai(long id);
}
