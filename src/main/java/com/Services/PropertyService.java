package com.Services;

import com.Models.Property;
import com.Repo.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Property updateProperty(Long id, Property updatedProperty) {
        Property property = propertyRepository.findById(id).orElse(null);
        if (property != null) {
            property.setAddress(updatedProperty.getAddress());
            Random random = new Random();
            property.setBalcony(updatedProperty.isBalcony(random.nextBoolean()));
            // Обновляем остальные поля
            return propertyRepository.save(property);
        } else {
            return null;
        }
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
