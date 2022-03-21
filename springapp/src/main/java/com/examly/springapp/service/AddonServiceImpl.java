package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.DuplicateValueException;
import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.AddOn;
import com.examly.springapp.model.FoodItem;
import com.examly.springapp.repository.AddonRepository;

@Service
public class AddonServiceImpl implements AddonService {

	@Autowired
	AddonRepository addonRepository;
	
	@Override
	public String addAddon(AddOn addon) {		
		if(addonRepository.existsByAddonName(addon.getAddonName())) {
			AddOn existingAddon = addonRepository.findByAddonName(addon.getAddonName()).get();
			if(existingAddon.isActive()) {
				throw new DuplicateValueException("AddOn", "addonName", addon.getAddonName());
			}
			existingAddon.setActive(true);
			existingAddon.setAddonPrice(addon.getAddonPrice());
			existingAddon.setAddonDescription(addon.getAddonDescription());
			addonRepository.save(existingAddon);
			return "Addon Added";
		}
		addonRepository.save(addon);
		return "Addon Added";
	}

	@Override
	public List<AddOn> getAllAddons() {
		List<AddOn> result = new ArrayList<>();
		addonRepository.findAll().stream().filter(ele->ele.isActive()).forEach(result::add);
		return result;
	}
	
	@Override
	public AddOn getAddonById(Integer id) {
		Optional<AddOn> addOn = addonRepository.findById(id);
		if(addOn.isEmpty()) throw new ResourceNotFoundException("Item not found");
		return addOn.get();
	}
	
	@Override
	public String deleteAddon(Integer id) {
		Optional<AddOn> fOptional = addonRepository.findById(id);
		if (fOptional.isEmpty()) {
			throw new ResourceNotFoundException("Item not found");
		}

		AddOn addon = fOptional.get();
		if (!addon.isActive()) {
			throw new ResourceNotFoundException("Item not found");
		}
		addon.setActive(false);
		addonRepository.save(addon);
		return "Addon deleted";
	}
	
	@Override
	public String editAddon(AddOn addOn, Integer id) {
		if (!addonRepository.existsById(id) || !addonRepository.findById(id).get().isActive()) {
			throw new ResourceNotFoundException("Item not found");
		}
		AddOn existingAddon = addonRepository.findById(id).get();

		// If request has new addon - proceed
		// If addon is not new, check if addon with another id wants that old name
		// if yes throw error else implies its changing its own properties, in this case allow
		if (addonRepository.existsByAddonName(addOn.getAddonName())
				&& addonRepository.findByAddonName(addOn.getAddonName()).get()
						.getAddonId() != existingAddon.getAddonId()) {
			throw new DuplicateValueException("AddOn", "addonName", addOn.getAddonName());
		}

		existingAddon.setAddonName(addOn.getAddonName());
		existingAddon.setAddonDescription(addOn.getAddonDescription());
		existingAddon.setAddonPrice(addOn.getAddonPrice());
		
		return "Addon edited";
	}
	
	

}
