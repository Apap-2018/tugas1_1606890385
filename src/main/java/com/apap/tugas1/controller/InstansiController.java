package com.apap.tugas1.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class InstansiController {
	
	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@RequestMapping(value="pegawai/termuda-tertua", method = RequestMethod.GET)
	private String pegawaiMudaTua (@RequestParam ("idInstansi") long id, Model model) {
		InstansiModel instansi = instansiService.getInstansiDetailById(id).get();
		List<PegawaiModel> pegawai_instansi = pegawaiService.findInstansiOrderByTanggallahirAsc(instansi);
		model.addAttribute("pegawaiMuda",pegawai_instansi.get(0));
		model.addAttribute("pegawaiTua", pegawai_instansi.get(pegawai_instansi.size()-1));
		model.addAttribute("jabatanMuda", pegawai_instansi.get(0).getListJabatan());
		model.addAttribute("jabatanTua",pegawai_instansi.get(pegawai_instansi.size()-1).getListJabatan());
		return "pegawai_tertua_termuda";
		
	}
	
	@RequestMapping(value = "/instansi/getInstansiByProvinsi", method = RequestMethod.GET)
	@ResponseBody
	public List<InstansiModel> getInstansi(@RequestParam (value = "idProvinsi", required = true) long idProvinsi) {
		ProvinsiModel provinsi = provinsiService.getProvinsiById(idProvinsi).get();
	    return instansiService.getInstansiByProvinsi(provinsi);
	}
	
	
	

}
