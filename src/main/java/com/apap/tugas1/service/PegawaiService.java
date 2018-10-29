package com.apap.tugas1.service;

import java.sql.Date;
import java.util.List;


import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;


public interface PegawaiService {
	void addPegawai(PegawaiModel pegawai);
	
	PegawaiModel getPegawaiDetailByNip(String nip);
	
	void deletePegawai(PegawaiModel pegawai);
	
	void updatePegawai(String nip, PegawaiModel pegawai);
	
	PegawaiModel getPegawaiDetailById(long id);
	
	List<PegawaiModel> findInstansiOrderByTanggallahirAsc(InstansiModel instansi);

	List<PegawaiModel> findAllPegawai();

	List<PegawaiModel> getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansi, Date tanggalLahir,
			String tahunMasuk);

	void setNipPegawai(PegawaiModel pegawai);
	
	List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan);

	List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi);
	
	List<PegawaiModel> getPegawaiByListjabatan(JabatanModel jabatan);
}
