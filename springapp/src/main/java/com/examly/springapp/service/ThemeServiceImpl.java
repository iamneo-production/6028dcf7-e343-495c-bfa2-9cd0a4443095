package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.DuplicateValueException;
import com.examly.springapp.exception.ResourceNotFoundException;
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
		return "Theme added";
	}

	@Override
	public Boolean existsByThemeName(String themeName) {
		return themeRepository.existsByThemeName(themeName);
	}

	@Override
	public List<Theme> getAllThemes() {
		return themeRepository.findAll();
	}

	@Override
	public List<Theme> getTheme() {
		return themeRepository.findAll();
	}


	@Override
	public String editTheme(Integer id,Theme theme) {
		Theme newTheme = themeRepository.findById(id).orElse(null);
		System.out.println(newTheme);
		if(newTheme==null) {
			throw new ResourceNotFoundException("Theme not found");
		}
		if(existsByThemeName(theme.getThemeName()))
			throw new DuplicateValueException("Theme", "themeName", theme.getThemeName());
		newTheme.setThemeName(theme.getThemeName());
		newTheme.setThemeImageURL(theme.getThemeImageURL());
		newTheme.setThemeDescription(theme.getThemeDescription());
		newTheme.setThemePhotographer(theme.getThemePhotographer());
		newTheme.setThemeVideographer(theme.getThemeVideographer());
		newTheme.setThemeReturnGift(theme.getThemeReturnGift());
		newTheme.setThemeCost(theme.getThemeCost());
		
		themeRepository.save(newTheme);
		return "Theme edited";
	}

	@Override
	public String deleteTheme(Integer id) {
		Theme theme = themeRepository.findById(id).get();
		if(theme==null) {
			throw new ResourceNotFoundException("Theme not found");
		}
		themeRepository.deleteById(id);
		return "Theme deleted";
	}

	@Override
	public Theme getThemeById(int id) {
		return themeRepository.findById(id).get();
	}

}
