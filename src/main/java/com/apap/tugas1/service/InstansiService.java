package com.apap.tugas1.service;


import java.util.List;
import java.util.Optional;

/*import com.apap.tutorial4.model.CarModel;*/
import com.apap.tugas1.model.InstansiModel;

public interface InstansiService {
	/*Optional<InstansiModel> getInstansiDetailByNama(String nama);*/
	void addInstansi(InstansiModel instansi);
	void deleteInstansi(InstansiModel instansi);
	void updateInstansi(InstansiModel instansi, long id);
	Optional<InstansiModel> getInstansiDetailById(Long id);
	List<InstansiModel> findAllInstansi();
	

}
