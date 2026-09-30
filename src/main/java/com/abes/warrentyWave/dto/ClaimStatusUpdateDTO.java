package com.abes.warrentyWave.dto;

import java.io.Serializable;

public class ClaimStatusUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;
    private String remarks;

    public ClaimStatusUpdateDTO() {
    }

    public ClaimStatusUpdateDTO(String status, String remarks) {
        this.status = status;
        this.remarks = remarks;
    }

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
