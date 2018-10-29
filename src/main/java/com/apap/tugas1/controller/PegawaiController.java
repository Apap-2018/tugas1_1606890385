package com.apap.tugas1.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
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
			model.addAttribute("jabatan", pegawai.getListJabatanSortByGaji());
			model.addAttribute("gaji", pegawai.getGaji()); 
			return "lihat_pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(provinsiService.findAllProvinsi().get(0));
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setListJabatan(new ArrayList<JabatanModel>());
		pegawai.getListJabatan().add(new JabatanModel());
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listInstansi", listInstansi);
		model.addAttribute("listJabatan", jabatanService.findAllJabatan());
		model.addAttribute("allProvinsi", provinsiService.findAllProvinsi());
		return "tambah_pegawai";
	}
	
	@RequestMapping(value="/pegawai/tambah", params={"addRow"}, method = RequestMethod.POST)
	public String addRowAdd(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
		
		List<ProvinsiModel> listProv = provinsiService.findAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.findAllJabatan();
		
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("allProvinsi", listProv);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", listInstansi);
		
		ProvinsiModel provinsi = pegawai.getInstansi().getProvinsi();
		
		model.addAttribute("selectedItem", provinsi);
		
		pegawai.getListJabatan().add(new JabatanModel());
	    model.addAttribute("pegawai", pegawai);
	    return "tambah_pegawai";
	}
	
	@RequestMapping(value="/pegawai/tambah", params={"deleteRow"}, method = RequestMethod.POST)
	public String deleteRowAdd(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, HttpServletRequest req,Model model) {
	
		List<ProvinsiModel> listProv = provinsiService.findAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.findAllJabatan();
		
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("listProvinsi", listProv);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", listInstansi);
		
		Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
		pegawai.getListJabatan().remove(rowId.intValue());
	    model.addAttribute("pegawai", pegawai);
	    return "tambah_pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		
		String kode = pegawai.getInstansi().getId().toString();
		
		SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yy");
		String tanggalLahir = newFormat.format(pegawai.getTanggalLahir()).replaceAll("-", "");
		
		String tahunKerja = pegawai.getTahunMasuk();

		int urutan = pegawaiService.getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(pegawai.getInstansi(), pegawai.getTanggalLahir(), pegawai.getTahunMasuk()).size()+1;
		
		String strUrutan;
		if(urutan<10) strUrutan="0"+urutan;
		else strUrutan=""+urutan;
		
		String nip = kode + tanggalLahir + tahunKerja + strUrutan;
		
		pegawai.setNip(nip);
		
		String result = "Pegawai dengan NIP "+ nip+" berhasil ditambahkan";
		model.addAttribute("result",result);
		pegawaiService.addPegawai(pegawai);
		
		return "tambah_sukses";
	}
	
	@RequestMapping(value="/pegawai/ubah", method = RequestMethod.GET)
	public String updatePegawai(@RequestParam("nip") String nip, Model model) {
		
		List<ProvinsiModel> listProv = provinsiService.findAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.findAllJabatan();
		
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("allProvinsi", listProv);
		
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);

		List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", listInstansi);
		
	    model.addAttribute("pegawai", pegawai);
	    return "update_pegawai";
	}
	
	@RequestMapping(value="/pegawai/ubah", params={"addRow"}, method = RequestMethod.POST)
	public String addRowUpdate(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
		
		List<ProvinsiModel> listProv = provinsiService.findAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.findAllJabatan();
		
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("allProvinsi", listProv);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", listInstansi);
		
		ProvinsiModel provinsi = pegawai.getInstansi().getProvinsi();
		
		model.addAttribute("selectedItem", provinsi);
		
		pegawai.getListJabatan().add(new JabatanModel());
	    model.addAttribute("pegawai", pegawai);
	    return "update_pegawai";
	}
	
	@RequestMapping(value="/pegawai/ubah", params={"deleteRow"}, method = RequestMethod.POST)
	public String deleteRowUpdate(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, HttpServletRequest req,Model model) {
		
		List<ProvinsiModel> listProv = provinsiService.findAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.findAllJabatan();
		
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("listProvinsi", listProv);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", listInstansi);
		
		Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
		pegawai.getListJabatan().remove(rowId.intValue());
	    model.addAttribute("pegawai", pegawai);
	    return "update_pegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
	private String updatePegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		String oldNip = pegawai.getNip();
		PegawaiModel oldPegawai = pegawaiService.getPegawaiDetailByNip(oldNip);
		
		String newNip;
		if((!oldPegawai.getTahunMasuk().equals(pegawai.getTahunMasuk())) || 
				(!oldPegawai.getTanggalLahir().equals(pegawai.getTanggalLahir())) || 
				(!oldPegawai.getInstansi().equals(pegawai.getInstansi()))) {
			
			String kode = pegawai.getInstansi().getId().toString();
			
			SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yy");
			String tanggalLahir = newFormat.format(pegawai.getTanggalLahir()).replaceAll("-", "");
			
			String tahunKerja = pegawai.getTahunMasuk();
			
			int urutan = pegawaiService.getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(pegawai.getInstansi(), pegawai.getTanggalLahir(), pegawai.getTahunMasuk()).size()+1;
			
			String strUrutan;
			if(urutan<10) strUrutan="0"+urutan;
			else strUrutan=""+urutan;
			
			newNip = kode + tanggalLahir + tahunKerja + strUrutan;
			pegawai.setNip(newNip);
		}
		else {
			 newNip = oldNip;
			 pegawai.setNip(oldNip);
		}
		
		
		pegawaiService.updatePegawai(oldNip, pegawai);
		
		String result = "Pegawai dengan NIP "+newNip+" berhasil diubah";
		model.addAttribute("result",result);
		return "tambah_sukses";
	}
	
	@RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
	private String findPegawai(@RequestParam(value="idProvinsi", required = false) Optional<Long> idProvinsi, 
			@RequestParam(value="idInstansi", required = false) Optional<Long> idInstansi, 
			@RequestParam(value="idJabatan", required = false) Optional<Long> idJabatan, 
			Model model) {
		List<ProvinsiModel> listAllProv = provinsiService.findAllProvinsi();
		List<JabatanModel> listAllJabatan = jabatanService.findAllJabatan();
		List<InstansiModel> listAllInstansi = instansiService.findAllInstansi();
		
		model.addAttribute("listInstansi", listAllInstansi);
		model.addAttribute("listJabatan", listAllJabatan);
		model.addAttribute("listProvinsi", listAllProv);
		
		List<PegawaiModel> pegawai = new ArrayList<PegawaiModel>();
		if(idInstansi.isPresent()) {
			
				InstansiModel instansi = instansiService.getInstansiDetailById(idInstansi.get()).get();
			if(idJabatan.isPresent()) {
				JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan.get());
				
				pegawai = pegawaiService.getPegawaiByInstansiAndJabatan(instansi, jabatan);
			}else {
				pegawai = pegawaiService.getPegawaiByInstansi(instansi);
			}
		}
		else{
			List<PegawaiModel> pegawaiTemp = new ArrayList<PegawaiModel>();
			if(idProvinsi.isPresent()) {
				ProvinsiModel provinsi = provinsiService.getProvinsiById(idProvinsi.get()).get();
				
				List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(provinsi);
				if(idJabatan.isPresent()) {
					JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan.get());
					
					for(InstansiModel instansi : listInstansi) {
						pegawaiTemp = pegawaiService.getPegawaiByInstansiAndJabatan(instansi, jabatan);
					}
					pegawai = pegawaiTemp;
					
				}else {
					for(InstansiModel instansi : listInstansi) {
						pegawaiTemp = pegawaiService.getPegawaiByInstansi(instansi);
					}
					pegawai = pegawaiTemp;
				}
			}else if(idJabatan.isPresent()){
				JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan.get());
				pegawai = pegawaiService.getPegawaiByListjabatan(jabatan);
			}
		}
		
		model.addAttribute("listPegawai", pegawai);
		return "cari_pegawai";
	}
	
	

}
