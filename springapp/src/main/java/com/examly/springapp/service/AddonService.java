package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.AddOn;

public interface AddonService {
	String addAddon(AddOn addon);
	List<AddOn> getAllAddons();
	String deleteAddon(Integer id);
	String editAddon(AddOn addOn, Integer id);
	AddOn getAddonById(Integer id);
}
