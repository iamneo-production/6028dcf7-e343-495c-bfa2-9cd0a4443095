package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.DuplicateValueException;
import com.examly.springapp.model.Theme;
import com.examly.springapp.repository.ThemeRepository;

@Service
public class ThemeServiceImpl implements ThemeService {
	
	@Autowired
	ThemeRepository themeRepository;

	@Override
	public String saveTheme(Theme theme) {
		if(existsByThemeName(theme.getThemeName())) {
			throw new DuplicateValueException("Theme", "themeName", theme.getThemeName());
		}
		themeRepository.save(theme);
		return "Theme Added";
	}

	@Override
	public List<Theme> getAllThemes() {
		return themeRepository.findAll();
	}

	@Override
	public Boolean existsByThemeName(String themeName) {
		return themeRepository.existsByThemeName(themeName);
	}

//	@Override
//	public String editTheme(Integer id, Theme theme) {
//		if(themeRepository.findById(id)==null) {
//			throw new ResourceNotFoundException("Theme with id "+id+" not found.");
//		}
//		themeRepository.findById(id).map(
//				
//			)
//	}

}
