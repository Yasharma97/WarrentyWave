package com.abes.warrentyWave.dto;

import java.io.Serializable;

/**
 * Data Transfer Object for updating Warranty Claim status.
 * Used for approve, reject, or status change requests.
 */
public class ClaimStatusUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;   // APPROVED, REJECTED, IN_REVIEW, PENDING
    private String remarks;  // Reason for status change or approval notes

    // Default Constructor
    public ClaimStatusUpdateDTO() {
    }

    // Parameterized Constructor
    public ClaimStatusUpdateDTO(String status, String remarks) {
        this.status = status;
        this.remarks = remarks;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "ClaimStatusUpdateDTO{" +
                "status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
