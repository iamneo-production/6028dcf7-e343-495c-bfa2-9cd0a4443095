package com.examly.springapp.controller;

// import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.examly.springapp.exception.MissingRequiredFieldException;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.Theme;
import com.examly.springapp.service.ThemeService;

@RestController
public class ThemeController {

	@Autowired
	ThemeService themeService;

	// ========= ADMIN THEME
	
	@PostMapping("/admin/addTheme")
	public ResponseEntity<?> addTheme(@RequestBody Theme theme) {
		if (theme.getThemeName() == null) {
			throw new MissingRequiredFieldException("Theme","themeName");
		}
		if (theme.getThemeImageURL() == null) {
			throw new MissingRequiredFieldException("Theme","themeImageURL");
		}
		if (theme.getThemeCost() == null) {
			throw new MissingRequiredFieldException("Theme","themeCost");
		}
		if (theme.getThemeDescription() == null) {
			throw new MissingRequiredFieldException("Theme","themeDescription");
		}

		return new ResponseEntity(themeService.saveTheme(theme), HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/getTheme")
	public ResponseEntity<?> getTheme(Integer id){
		Theme theme = themeService.getTheme(id);
		if(theme==null) {
			throw new ResourceNotFoundException("Theme not found");
		}
		return new ResponseEntity(themeService.saveTheme(theme), HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/editTheme/{id}")
	public ResponseEntity<?> editTheme(@PathVariable Integer id, @RequestBody Theme theme){
		return new ResponseEntity(themeService.editTheme(id, theme), HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/deleteTheme/{id}")
	public ResponseEntity<?> deleteTheme(Integer id){
		return new ResponseEntity(themeService.deleteTheme(id), HttpStatus.NO_CONTENT);
	}

	// ========= USER THEME
	
	@RequestMapping(value={"/user/getAllThemes", "/admin/getTheme"}, method = RequestMethod.GET)
	public ResponseEntity<?> getAllThemes(){
		return new ResponseEntity(themeService.getAllThemes(), HttpStatus.OK);
	}
}
