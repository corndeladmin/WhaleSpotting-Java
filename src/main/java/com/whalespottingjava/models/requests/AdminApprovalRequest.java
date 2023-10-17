package com.whalespottingjava.models.requests;

public class AdminApprovalRequest {

    private Long sightingId;
    private String approved;

    public AdminApprovalRequest() {}
    public void setId(Long sightingId) {
        this.sightingId = sightingId;
    }

    public Long getId() {
        return sightingId;
    }

    public void setApproved(String approved) { this.approved = approved; }

    public String getApproved() {
        return approved;
    }
}
