package com.abes.warrentyWave.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Response DTO for returning Finance Contract details to clients.
 */
public class FinanceContractResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String contractNumber;
    private Long customerId;
    private String customerName;
    private Long vehicleId;
    private String vehicleInfo;
    private Double loanAmount;
    private Double downPayment;
    private Double financedAmount;
    private Double interestRate;
    private Integer termMonths;
    private Double monthlyPayment;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    // Default Constructor
    public FinanceContractResponseDTO() {
    }

    // Parameterized Constructor
    public FinanceContractResponseDTO(Long id, String contractNumber, Long customerId,
                                     String customerName, Long vehicleId, String vehicleInfo,
                                     Double loanAmount, Double downPayment, Double financedAmount,
                                     Double interestRate, Integer termMonths, Double monthlyPayment,
                                     LocalDate startDate, LocalDate endDate, String status) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.customerId = customerId;
        this.customerName = customerName;
        this.vehicleId = vehicleId;
        this.vehicleInfo = vehicleInfo;
        this.loanAmount = loanAmount;
        this.downPayment = downPayment;
        this.financedAmount = financedAmount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.monthlyPayment = monthlyPayment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(String vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Double downPayment) {
        this.downPayment = downPayment;
    }

    public Double getFinancedAmount() {
        if (financedAmount == null && loanAmount != null && downPayment != null) {
            return loanAmount - downPayment;
        }
        return financedAmount;
    }

    public void setFinancedAmount(Double financedAmount) {
        this.financedAmount = financedAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FinanceContractResponseDTO{" +
                "id=" + id +
                ", contractNumber='" + contractNumber + '\'' +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", vehicleId=" + vehicleId +
                ", vehicleInfo='" + vehicleInfo + '\'' +
                ", loanAmount=" + loanAmount +
                ", downPayment=" + downPayment +
                ", financedAmount=" + getFinancedAmount() +
                ", interestRate=" + interestRate +
                ", termMonths=" + termMonths +
                ", monthlyPayment=" + monthlyPayment +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}
