package com.Services;

import com.Models.SupportRequest;
import com.Repo.SupportRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class SupportRequestService {

    private SupportRequestRepository supportRequestRepository;

    @Autowired
    public SupportRequestService(SupportRequestRepository supportRequestRepository) {
        this.supportRequestRepository = supportRequestRepository;
    }

    public List<SupportRequest> getAllSupportRequests() {
        return supportRequestRepository.findAll();
    }

    public SupportRequest createSupportRequest(SupportRequest supportRequest) {
        return supportRequestRepository.save(supportRequest);
    }

    public SupportRequest getSupportRequestById(Long id) {
        return supportRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Support Request not found"));
    }

    public SupportRequest updateSupportRequest(Long id, SupportRequest updatedSupportRequest) {
        SupportRequest existingSupportRequest = getSupportRequestById(id);
        existingSupportRequest.setMessage(updatedSupportRequest.getMessage());
        return supportRequestRepository.save(existingSupportRequest);
    }

    public void deleteSupportRequestById(Long id) {
        supportRequestRepository.deleteById(id);
    }

}

