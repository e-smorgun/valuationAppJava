package com.Controllers;
import com.Models.SupportRequest;
import com.Services.PropertyService;
import com.Services.SupportRequestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SupportRequestControllerTest {

    @InjectMocks
    private SupportRequestController supportRequestController;

    @Mock
    private SupportRequestService supportRequestService;

    @Test
    public void testCreateSupportRequest() {
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setId(1L);
        supportRequest.setMessage("Test Message");
        supportRequest.setUserId(1L);

        Mockito.when(supportRequestService.createSupportRequest(Mockito.any(SupportRequest.class)))
                .thenReturn(supportRequest);

        ResponseEntity<SupportRequest> response = supportRequestController.createSupportRequest(supportRequest);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(supportRequest, response.getBody());
    }

    @Test
    public void testGetSupportRequestById() {
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setId(1L);
        supportRequest.setMessage("Test Message");
        supportRequest.setUserId(1L);

        Mockito.when(supportRequestService.getSupportRequestById(Mockito.eq(1L)))
                .thenReturn(supportRequest);

        ResponseEntity<SupportRequest> response = supportRequestController.getSupportRequestById(1L);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(supportRequest, response.getBody());
    }

    @Test
    public void testGetAllSupportRequests() {
        List<SupportRequest> supportRequests = new ArrayList<>();
        SupportRequest supportRequest1 = new SupportRequest();
        supportRequest1.setId(1L);
        supportRequest1.setMessage("Test Message");
        supportRequest1.setUserId(1L);
        supportRequests.add(supportRequest1);
        SupportRequest supportRequest2 = new SupportRequest();
        supportRequest2.setId(1L);
        supportRequest2.setMessage("Test Message");
        supportRequest2.setUserId(1L);
        supportRequests.add(supportRequest2);

        Mockito.when(supportRequestService.getAllSupportRequests())
                .thenReturn(supportRequests);

        ResponseEntity<List<SupportRequest>> response = supportRequestController.getAllSupportRequests();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(supportRequests, response.getBody());
    }

    @Test
    public void testUpdateSupportRequest() {
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setId(1L);
        supportRequest.setMessage("Test Message");
        supportRequest.setUserId(1L);

        Mockito.when(supportRequestService.updateSupportRequest(Mockito.eq(1L), Mockito.any(SupportRequest.class)))
                .thenReturn(supportRequest);

        ResponseEntity<SupportRequest> response = supportRequestController.updateSupportRequest(1L, supportRequest);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(supportRequest, response.getBody());
    }

    @Test
    public void testDeleteSupportRequestById() {
        ResponseEntity<Void> response = supportRequestController.deleteSupportRequestById(1L);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Mockito.verify(supportRequestService, Mockito.times(1)).deleteSupportRequestById(Mockito.eq(1L));
    }
}
