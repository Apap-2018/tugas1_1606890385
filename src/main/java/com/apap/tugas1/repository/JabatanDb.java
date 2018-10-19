package com.apap.tugas1.repository;

import java.util.List;


import com.apap.tugas1.model.JabatanModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JabatanDb extends JpaRepository<JabatanModel, Long> {
	JabatanModel findByNama(String nama);
	
	public static final String FIND_NAMA = "SELECT nama FROM jabatan ";
	@Query(value=FIND_NAMA, nativeQuery = true)
	public List<Object[]> findNama();
	
	
}
