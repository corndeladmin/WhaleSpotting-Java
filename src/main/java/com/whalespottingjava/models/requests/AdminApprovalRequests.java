package com.whalespottingjava.models.requests;

import java.util.List;

public class AdminApprovalRequests {

    private List<AdminApprovalRequest> adminApprovalRequests;

    public AdminApprovalRequests() {}

    public void setAdminApprovalRequests(List<AdminApprovalRequest> adminApprovalRequests) {
        this.adminApprovalRequests = adminApprovalRequests;
    }

    public List<AdminApprovalRequest>getAdminApprovalRequests() {
        return adminApprovalRequests;
    }
}
