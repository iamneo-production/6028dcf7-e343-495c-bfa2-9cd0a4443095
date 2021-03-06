package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.AddOn;

public interface AddonRepository extends JpaRepository<AddOn, Integer>{
	boolean existsByAddonName(String name);
	Optional<AddOn> findByAddonName(String name);
}
