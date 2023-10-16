package com.whalespottingjava.models.requests;

import com.whalespottingjava.models.enums.ApprovalStatus;

public class AdminApproval {

    private Long SightingId;
    private ApprovalStatus approved;

    public void setId(Long SightingId) {
        this.SightingId = SightingId;
    }

    public Long getId() {
        return SightingId;
    }

    public void setApproved(ApprovalStatus status) { this.approved = status; }

    public ApprovalStatus getApproved() {
        return approved;
    }
}
