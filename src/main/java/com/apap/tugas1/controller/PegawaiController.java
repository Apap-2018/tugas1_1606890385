package com.apap.tugas1.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class PegawaiController {
	
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	
	
	@RequestMapping(value="/pegawai", method = RequestMethod.GET)
	private String viewPegawai (@RequestParam ("nip") String nip, Model model) {
			PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
			model.addAttribute("pegawai", pegawai);
			model.addAttribute("instansi", pegawai.getInstansi().getNama());
			model.addAttribute("provinsi", pegawai.getInstansi().getProvinsi().getNama());
			model.addAttribute("jabatan", pegawai.getListJabatan());
			model.addAttribute("gaji", pegawai.getGaji()); 
			return "lihat_pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("allInstansi", instansiService.findAllInstansi());
		model.addAttribute("allJabatan", jabatanService.findAllJabatan());
		model.addAttribute("allProvinsi", provinsiService.findAllProvinsi() );
		model.addAttribute("pegawai", new PegawaiModel());
		System.out.println("masooooook");
		return "tambah_pegawai";
	}

	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai) {
		pegawaiService.addPegawai(pegawai);
		return "tambah_sukses";
	}
	
	@RequestMapping (value="/pegawai/cari", method = RequestMethod.GET)
	private String cariPegawai(/*@RequestParam(value = "idProvinsi",required = false) long idProvinsi, @RequestParam(value="idInstansi",required= false) long idInstansi , @RequestParam(value="idJabatan", required = false) Long idJabatan,*/ Model model) {
		model.addAttribute("allInstansi", instansiService.findAllInstansi());
		model.addAttribute("allJabatan", jabatanService.findAllJabatan());
		model.addAttribute("allProvinsi", provinsiService.findAllProvinsi() );
		List<PegawaiModel> semuapegawai = pegawaiService.findAllPegawai();
		model.addAttribute("semuaPegawai",semuapegawai);
		return "cari_pegawai";
	}

}
