package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
