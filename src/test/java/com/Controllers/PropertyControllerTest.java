package com.Controllers;

import com.Models.Property;
import com.Services.PropertyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PropertyControllerTest {

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;

    @Test
    public void getPropertyById_validId_returnProperty() {
        // Arrange
        Long id = 1L;
        Property expectedProperty = new Property();
        expectedProperty.setId(id);
        when(propertyService.getPropertyById(id)).thenReturn(expectedProperty);

        // Act
        ResponseEntity<Property> response = propertyController.getPropertyById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProperty, response.getBody());
    }

    @Test
    public void getPropertyById_invalidId_returnNotFound() {
        // Arrange
        Long id = 1L;
        when(propertyService.getPropertyById(id)).thenReturn(null);

        // Act
        ResponseEntity<Property> response = propertyController.getPropertyById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void addProperty_validProperty_returnCreated() {
        // Arrange
        Property property = new Property();
        Property savedProperty = new Property();
        savedProperty.setId(1L);
        when(propertyService.addProperty(property)).thenReturn(savedProperty);

        // Act
        ResponseEntity<Property> response = propertyController.addProperty(property);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(URI.create("/properties/" + savedProperty.getId()), response.getHeaders().getLocation());
        assertEquals(savedProperty, response.getBody());
    }

    @Test
    public void updateProperty_validIdAndProperty_returnProperty() {
        // Arrange
        Long id = 1L;
        Property property = new Property();
        property.setId(id);
        Property updatedProperty = new Property();
        updatedProperty.setId(id);
        when(propertyService.updateProperty(id, property)).thenReturn(updatedProperty);

        // Act
        ResponseEntity<Property> response = propertyController.updateProperty(id, property);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProperty, response.getBody());
    }

    @Test
    public void updateProperty_invalidId_returnNotFound() {
        // Arrange
        Long id = 1L;
        Property property = new Property();
        when(propertyService.updateProperty(id, property)).thenReturn(null);

        // Act
        ResponseEntity<Property> response = propertyController.updateProperty(id, property);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteProperty_shouldReturnNoContent() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<Void> response = propertyController.deleteProperty(id);

        // Assert
        verify(propertyService).deleteProperty(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
