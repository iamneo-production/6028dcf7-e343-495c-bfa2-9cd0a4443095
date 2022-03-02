package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {
	Boolean existsByThemeName(String themeName);
}
