package com.apap.tugas1.model;

import java.io.Serializable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.apap.tugas1.model.InstansiModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name="pegawai")
public class PegawaiModel implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max=255)
	@Column(name = "nip", unique = true, nullable = false)
	private String nip;

	@NotNull
	@Size(max=255)
	@Column(name="nama", nullable = false)
	private String nama;

	@NotNull
	@Size(max=255)
	@Column(name="tempat_lahir", nullable = false)
	private String tempat_lahir;

	@NotNull
	@Column(name="tanggalLahir", nullable = false)
	private Date tanggalLahir;

	@NotNull
	@Size(max=255)
	@Column(name="tahunMasuk", nullable = false)
	private String tahunMasuk;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name= "id_instansi", referencedColumnName= "id", nullable=false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private InstansiModel instansi;

	@ManyToMany()
	@JoinTable(name = "jabatan_pegawai", joinColumns = { @JoinColumn(name = "id_pegawai") }, inverseJoinColumns = { @JoinColumn(name = "id_jabatan") })
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private List<JabatanModel> listjabatan= new ArrayList<JabatanModel>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getTempat_lahir() {
		return tempat_lahir;
	}

	public void setTempat_lahir(String tempat_lahir) {
		this.tempat_lahir = tempat_lahir;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getTahunMasuk() {
		return tahunMasuk;
	}

	public void setTahunMasuk(String tahun_masuk) {
		this.tahunMasuk = tahun_masuk;
	}

	public InstansiModel getInstansi() {
		return instansi;
	}

	public void setInstansi(InstansiModel instansi) {
		this.instansi = instansi;
	}

	public List<JabatanModel> getListJabatan() {
		return listjabatan;
	}
	
	public List<JabatanModel> getListJabatanSortByGaji() {
		if(listjabatan.size()>1) {
			Collections.sort(listjabatan, new SortByGajiPokok());
		}
		return listjabatan;
	}

	public void setListJabatan(List<JabatanModel> listjabatan) {
		this.listjabatan = listjabatan;
	}

	public int getGaji() {
		JabatanModel jabatan = listjabatan.get(listjabatan.size()-1);
		double tunjangan = (this.getInstansi().getProvinsi().getPresentase_tunjangan()/100);
		return (int) ( jabatan.getGaji_pokok() + (tunjangan * jabatan.getGaji_pokok()));
	}

	class SortByGajiPokok implements Comparator<JabatanModel>{
		public int compare (JabatanModel a, JabatanModel b) {
			return (int) (a.getGaji_pokok()-b.getGaji_pokok());
		}
	}

	


}
