package com.abes.warrentyWave.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class WarrantyClaimDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long customerId;
    private Long vehicleId;
    private Long contractId;
    private LocalDate claimDate;
    private String status;
    private String description;
    private Double claimAmount;
    private String remarks;

    public WarrantyClaimDTO() {
    }

    public WarrantyClaimDTO(Long id, Long customerId, Long vehicleId, Long contractId,
                            LocalDate claimDate, String status, String description,
                            Double claimAmount, String remarks) {
        this.id = id;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.contractId = contractId;
        this.claimDate = claimDate;
        this.status = status;
        this.description = description;
        this.claimAmount = claimAmount;
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "WarrantyClaimDTO{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", vehicleId=" + vehicleId +
                ", contractId=" + contractId +
                ", claimDate=" + claimDate +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", claimAmount=" + claimAmount +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
