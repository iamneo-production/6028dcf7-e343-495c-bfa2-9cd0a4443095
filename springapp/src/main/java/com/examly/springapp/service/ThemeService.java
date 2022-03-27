package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Theme;

public interface ThemeService {
	//admin theme
	String saveTheme(Theme theme);
	Boolean existsByThemeName(String themeName);
	List<Theme> getTheme();
	String editTheme(Integer id, Theme theme);
	String deleteTheme(Integer id);
	Theme getThemeById(int id);

	// user theme
	List<Theme> getAllThemes();
}
