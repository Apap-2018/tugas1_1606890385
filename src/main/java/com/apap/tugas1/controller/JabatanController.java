package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;

@Controller
public class JabatanController {

	@Autowired
	private JabatanService jabatanService;

	@Autowired
	private InstansiService instansiService;
	
	@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("allJabatan", jabatanService.findAllJabatan());
		model.addAttribute("allInstansi", instansiService.findAllInstansi());
		return "home";
	}

	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("jabatan", new JabatanModel());
		System.out.println("masooooook");
		return "tambah_jabatan";
	}

	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan) {
		jabatanService.addJabatan(jabatan);
		return "tambah_sukses";
	}

	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String viewJabatan(@RequestParam("idJabatan") Long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id);
		model.addAttribute("jabatan", jabatan);
		System.out.println(jabatan.getPegawai().size());
		model.addAttribute("jmlhPegawai", jabatan.getPegawai().size());
		return "lihat_jabatan";
	}

	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	private String update(@RequestParam("idJabatan") long idJabatan, Model model) {
		System.out.println("masuk");
		// Long id_num = Long.parseLong(id);
		JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan);
		model.addAttribute("jabatan", jabatan);
		return "update_jabatan";
	}

	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	private String updateDone(@RequestParam(value = "idJabatan") long id, @ModelAttribute JabatanModel jabatan) {
		jabatanService.updateJabatan(jabatan, id);
		return "update_sukses";

	}

	// Method untuk menghapus dealer
	@RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
	private String deleteJabatan(@ModelAttribute JabatanModel jabatan, Model model) {
		JabatanModel jabatan2 = jabatanService.getJabatanDetailById(jabatan.getId());
		if (jabatan2.getPegawai().size() > 0) {
			return "hapus_gagal";
		} else {
			jabatanService.deleteJabatan(jabatan2);
			return "hapus_sukses";
		}

	}

	@RequestMapping(value = "/jabatan/lihatSemua")
	private String viewAll(Model model) {
		List<JabatanModel> semuaJabatan = jabatanService.findAllJabatan();
		model.addAttribute("semuaJabatan", semuaJabatan);
		return "lihat_semua_jabatan";
	}
}
