package com.Controllers;

import com.Models.Property;
import com.Repo.PropertyRepository;
import com.Services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weka.classifiers.Classifier;
import weka.core.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    private PropertyRepository propertyRepository;


    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.getPropertyById(id);
        if (property != null) {
            return ResponseEntity.ok(property);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        Property savedProperty = propertyService.addProperty(property);
        return ResponseEntity.created(URI.create("/properties/" + savedProperty.getId())).body(savedProperty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        Property updatedProperty = propertyService.updateProperty(id, property);
        if (updatedProperty != null) {
            return ResponseEntity.ok(updatedProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/predict")
    public ResponseEntity<Double> predictPropertyPrice(@RequestBody Property property) throws Exception {
        Random random = new Random();

        // загрузка модели машинного обучения
        Classifier cls = (Classifier) SerializationHelper.read("path/to/model/file");

        // создание вектора атрибутов
        FastVector<Attribute> attributes = new FastVector<Attribute>();
        attributes.addElement(new Attribute("address"));
        attributes.addElement(new Attribute("isBalcony"));
        attributes.addElement(new Attribute("cost_per_square_meter"));
        attributes.addElement(new Attribute("isFinishing_work"));
        attributes.addElement(new Attribute("floor"));
        attributes.addElement(new Attribute("full_cost"));
        attributes.addElement(new Attribute("material"));
        attributes.addElement(new Attribute("metro_distance"));
        attributes.addElement(new Attribute("region"));
        attributes.addElement(new Attribute("square"));
        attributes.addElement(new Attribute("userID"));
        attributes.addElement(new Attribute("view_from_window"));

        // создание экземпляра класса Instances
        Instances data = new Instances("PropertyData", attributes, 0);
        data.setClassIndex(data.numAttributes() - 1);

        // добавление объекта Property в экземпляр класса Instances
        DenseInstance instance = new DenseInstance(attributes.size());
        instance.setValue(attributes.get(0), property.getAddress());
        instance.setValue(attributes.get(1), property.isBalcony(random.nextBoolean()) ? "true" : "false");
        instance.setValue(attributes.get(2), property.getCostPerSquareMeter());
        instance.setValue(attributes.get(3), property.isFinishingWork(random.nextBoolean()) ? "true" : "false");
        instance.setValue(attributes.get(4), property.getFloor());
        instance.setValue(attributes.get(5), property.getFullCost());
        instance.setValue(attributes.get(6), property.getMaterial());
        instance.setValue(attributes.get(7), property.getMetroDistance());
        instance.setValue(attributes.get(8), property.getRegion());
        instance.setValue(attributes.get(9), property.getSquare());
        instance.setValue(attributes.get(10), property.getUserId());
        instance.setValue(attributes.get(11), property.getViewFromWindow());
        data.add(instance);

        // предсказание стоимости недвижимости
        double predictedPrice = 0.0;
        try {
            predictedPrice = cls.classifyInstance(data.firstInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // возврат результата запроса
        return ResponseEntity.ok(predictedPrice);
    }
}

