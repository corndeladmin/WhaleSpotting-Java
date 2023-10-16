package com.whalespottingjava.models.requests;

public class AdminApprovalRequest {

    private Long SightingId;
    private Boolean approved;

    public void setId(Long SightingId) {
        this.SightingId = SightingId;
    }

    public Long getId() {
        return SightingId;
    }

    public void setApproved(int value) { this.approved = (value == 1); }

    public Boolean getApproved() {
        return approved;
    }
}
