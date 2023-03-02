package com.Controllers;

import com.Models.SupportRequest;
import com.Services.SupportRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/support-requests")
public class SupportRequestController {

    private final SupportRequestService supportRequestService;

    @Autowired
    public SupportRequestController(SupportRequestService supportRequestService) {
        this.supportRequestService = supportRequestService;
    }

    @PostMapping
    public ResponseEntity<SupportRequest> createSupportRequest(@RequestBody SupportRequest supportRequest) {
        SupportRequest createdRequest = supportRequestService.createSupportRequest(supportRequest);
        return ResponseEntity.ok(createdRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportRequest> getSupportRequestById(@PathVariable Long id) {
        SupportRequest supportRequest = supportRequestService.getSupportRequestById(id);
        return ResponseEntity.ok(supportRequest);
    }

    @GetMapping
    public ResponseEntity<List<SupportRequest>> getAllSupportRequests() {
        List<SupportRequest> supportRequests = supportRequestService.getAllSupportRequests();
        return ResponseEntity.ok(supportRequests);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportRequest> updateSupportRequest(@PathVariable Long id, @RequestBody SupportRequest supportRequest) {
        SupportRequest updatedRequest = supportRequestService.updateSupportRequest(id, supportRequest);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupportRequestById(@PathVariable Long id) {
        supportRequestService.deleteSupportRequestById(id);
        return ResponseEntity.ok().build();
    }
}

