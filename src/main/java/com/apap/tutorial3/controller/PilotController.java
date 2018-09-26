package com.apap.tutorial3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

@Controller
public class PilotController {
@Autowired
private PilotService pilotService;

@RequestMapping("/pilot/add")
public String add(@RequestParam(value = "id",required=true) String id,
@RequestParam(value = "licenseNumber",required=true) String licenseNumber,
@RequestParam(value = "name",required=true) String name,
@RequestParam(value = "flyHour",required=true) String flyHour) {
PilotModel pilot = new PilotModel(id,licenseNumber,name,Integer.parseInt(flyHour));
pilotService.addPilot(pilot);
return "add";
}
@RequestMapping("/pilot/view")
public String view(@RequestParam("licenseNumber") String licenseNumber,Model model) {
	PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
	if (archive!=null) {
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	model.addAttribute("error", "License number tidak ditemukan:(");
	return "error";
	
}
@RequestMapping("/pilot/viewall")
public String view(Model model) {
	List<PilotModel> archive = pilotService.getPilotList();
	model.addAttribute("listPilot", archive);
	return "viewall-pilot";
}
@RequestMapping(path="/pilot/view/license-number/{number}")
public String view2(@PathVariable("number") String licenseNumber, Model model) {
	PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
	if (archive!=null) {
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	model.addAttribute("error", "License number tidak ditemukan:(");
	return "error";
}
@RequestMapping(path="/pilot/update/license-number/{number}/fly-hour/{hour}")
public String view2(@PathVariable("number") String licenseNumber,@PathVariable("hour") String flyHour, Model model) {
	PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
	if (archive!=null) {
		Integer fly=Integer.parseInt(flyHour);
		archive.setFlyHour(fly);
		model.addAttribute("pilot", archive);
		return "view-pilot-update";
	}
	model.addAttribute("error", "Gagal melakukan update, license number tidak ditemukan:(");
	return "error";
}
@RequestMapping(path="/pilot/delete/id/{number}")
public String delete(@PathVariable("number") String id, Model model) {
	PilotModel archive = pilotService.getPilotDetailById(id);
	if (archive!=null) {
		pilotService.deletePilot(archive);
		model.addAttribute("pilot", archive);
		return "view-pilot-delete";
	}
	model.addAttribute("error", "Gagal melakukan delete, id number tidak ditemukan:(");
	return "error";
}
}
