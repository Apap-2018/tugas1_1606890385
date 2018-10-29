package com.apap.tugas1.service;


import java.util.List;
import java.util.Optional;

/*import com.apap.tutorial4.model.CarModel;*/
import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;

public interface InstansiService {
	Optional<InstansiModel> getInstansiDetailById(Long id);
	List<InstansiModel> findAllInstansi();
	List<InstansiModel> getInstansiByProvinsi(ProvinsiModel provinsi);
	Optional<InstansiModel> findInstansiById(long id);
	List<InstansiModel> viewByNama(String nama);
	

}
