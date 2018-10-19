package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.JabatanDb;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
	
	@Autowired
	private JabatanDb jabatanDb;

	@Override
	public void addJabatan(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		jabatanDb.save(jabatan);
	}

	@Override
	public void deleteJabatan(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		jabatanDb.delete(jabatan);
	}

	@Override
	public void updateJabatan(JabatanModel jabatan, long id) {
		// TODO Auto-generated method stub
		JabatanModel baru = jabatanDb.getOne(id);
		baru.setDeskripsi(jabatan.getDeskripsi());
		baru.setGaji_pokok(jabatan.getGaji_pokok());
		baru.setNama(jabatan.getNama());
		jabatanDb.save(baru);
		
	}

	@Override
	public List<Object[]> findAllName() {
		// TODO Auto-generated method stub
		return jabatanDb.findNama();
	}

	@Override
	public JabatanModel getJabatanDetailByNama(String nama) {
		// TODO Auto-generated method stub
		return jabatanDb.findByNama(nama) ;
	}

	@Override
	public List<JabatanModel> findAllJabatan() {
		// TODO Auto-generated method stub
		return jabatanDb.findAll();
	}

	@Override
	public JabatanModel getJabatanDetailById(long id) {
		// TODO Auto-generated method stub
		return jabatanDb.getOne(id);
	}

	@Override
	public List<PegawaiModel> getAllPegawai(long id) {
		// TODO Auto-generated method stub
		return jabatanDb.getOne(id).getPegawai();
	}
	

}
