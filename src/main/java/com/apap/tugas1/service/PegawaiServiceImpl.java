package com.apap.tugas1.service;

import java.sql.Date;
import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
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
		System.out.println(pegawai.getListJabatan().size());
		pegawaiDb.save(pegawai);
	}

	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		// TODO Auto-generated method stub
		/*System.out.println("masuk");
		System.out.println(nip);
		System.out.println(pegawaiDb.findByNip(nip).get().getNama());
		System.out.println(pegawaiDb.findByNip(nip).get().getListJabatan().size());
		System.out.println(pegawaiDb.findByNip(nip).get().getListJabatanSortByGaji().size());*/
		return pegawaiDb.findByNip(nip).get();
	
	}

	@Override
	public void deletePegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		pegawaiDb.delete(pegawai);
		
	}

	@Override
	public void updatePegawai(String nip, PegawaiModel pegawai) {
		PegawaiModel updatePegawai = pegawaiDb.findByNip(nip).get();
		updatePegawai.setNama(pegawai.getNama());
		updatePegawai.setNip(pegawai.getNip());
		updatePegawai.setTanggalLahir(pegawai.getTanggalLahir());
		updatePegawai.setTempat_lahir(pegawai.getTempat_lahir());
		updatePegawai.setTahunMasuk(pegawai.getTahunMasuk());
		updatePegawai.setInstansi(pegawai.getInstansi());
		updatePegawai.setListJabatan(pegawai.getListJabatan());
		pegawaiDb.save(updatePegawai);
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

	@Override
	public List<PegawaiModel> getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansi, Date tanggalLahir, String tahunMasuk) {
		return pegawaiDb.findByInstansiAndTanggalLahirAndTahunMasuk(instansi, tanggalLahir, tahunMasuk);
	}

	@Override
	public void setNipPegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
String nipTglLahir = "";
		
		Date tglLahir = pegawai.getTanggalLahir();
		String[] tanggalLahir = (String.valueOf(tglLahir).split("-"));
		for (int i = 0; i < tanggalLahir.length; i++) {
			nipTglLahir = tanggalLahir[i].substring(tanggalLahir[i].length()-2, tanggalLahir[i].length()) + nipTglLahir;
		}
		
		List<PegawaiModel> listPegawai = pegawaiDb.findByInstansiAndTanggalLahirAndTahunMasukOrderByNipAsc(pegawai.getInstansi(), pegawai.getTanggalLahir(), pegawai.getTahunMasuk());
		int nomorPegawaiTemp = 0;
		if (listPegawai.isEmpty()) {
			nomorPegawaiTemp = 1;
		} else {
			PegawaiModel lastPegawai = listPegawai.get(listPegawai.size()-1);
			nomorPegawaiTemp = Integer.valueOf(lastPegawai.getNip().substring(lastPegawai.getNip().length()-2)) + 1;
		}
		String nomorPegawai = (nomorPegawaiTemp < 10 ? "0" : "") + nomorPegawaiTemp;
		
		String nip = pegawai.getInstansi().getId() + nipTglLahir + pegawai.getTahunMasuk() + nomorPegawai;
		
		pegawai.setNip(nip);
		
	}

	@Override
	public List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan) {
		// TODO Auto-generated method stub
		return  pegawaiDb.findByListjabatan(jabatan);
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi) {
		return pegawaiDb.findByInstansi(instansi);
	}

	@Override
	public List<PegawaiModel> getPegawaiByListjabatan(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		return pegawaiDb.findByListjabatan(jabatan);
	}

}
