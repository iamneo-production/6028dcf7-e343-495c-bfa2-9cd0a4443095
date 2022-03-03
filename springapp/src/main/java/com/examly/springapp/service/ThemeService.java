package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Theme;

public interface ThemeService {
	//admin theme
	String saveTheme(Theme theme);
	Boolean existsByThemeName(String themeName);
//	String editTheme(Integer id, Theme theme);
	
	// user theme
	List<Theme> getAllThemes();
}
