package com.apap.tugas1.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.apap.tugas1.model.InstansiModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name="jabatan")
public class JabatanModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max=255)
	@Column(name="deskripsi", nullable = false)
	private String deskripsi;
	
	@NotNull
	@Column(name="gaji_pokok", nullable = false)
	private double gaji_pokok;

	@ManyToMany()
	@JoinTable(name = "jabatan_pegawai", joinColumns = { @JoinColumn(name = "id_jabatan") }, inverseJoinColumns = { @JoinColumn(name = "id_pegawai") })
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private List<PegawaiModel> pegawai=new ArrayList<PegawaiModel>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public double getGaji_pokok() {
		return gaji_pokok;
	}

	public void setGaji_pokok(double gaji_pokok) {
		this.gaji_pokok = gaji_pokok;
	}

	public List<PegawaiModel> getPegawai() {
		return pegawai;
	}

	public void setPegawai(List<PegawaiModel> pegawai) {
		this.pegawai = pegawai;
	}
	
	public int getJmlhPegawai() {
		return this.pegawai.size();
	}
	
	
			
			
}
