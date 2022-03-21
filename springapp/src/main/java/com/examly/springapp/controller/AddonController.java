package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.AddOn;
import com.examly.springapp.service.AddonService;

@RestController
public class AddonController {
	@Autowired
	AddonService addonService;
	
	@PostMapping("/dev/addAddon")
	public ResponseEntity<?> addAddon(@RequestBody AddOn addOn){
		return new ResponseEntity(addonService.addAddon(addOn), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/dev/deleteAddon/{id}")
	public ResponseEntity<?> deleteAddon(@PathVariable Integer id){
		return new ResponseEntity(addonService.deleteAddon(id), HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/dev/getAddon")
	public ResponseEntity<?> getAddon(){
		return new ResponseEntity(addonService.getAllAddons(), HttpStatus.OK);
	}
	
	@GetMapping("/dev/getAddon/{id}")
	public ResponseEntity<?> getAddonById(@PathVariable Integer id){
		return new ResponseEntity(addonService.getAddonById(id), HttpStatus.OK);
	}
	
	@PutMapping("/dev/editAddon/{id}")
	public ResponseEntity<?> editAddon(@PathVariable Integer id, @RequestBody AddOn addOn){
		return new ResponseEntity(addonService.editAddon(addOn, id), HttpStatus.OK);
	}
}
