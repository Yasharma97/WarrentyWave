package com.abes.warrentyWave.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FinanceContractRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String contractNumber;
    private Long customerId;
    private Long vehicleId;
    private Double loanAmount;
    private Double downPayment;
    private Double interestRate;
    private Integer termMonths;
    private Double monthlyPayment;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public FinanceContractRequestDTO() {
    }

    public FinanceContractRequestDTO(String contractNumber, Long customerId, Long vehicleId,
                                    Double loanAmount, Double downPayment, Double interestRate,
                                    Integer termMonths, Double monthlyPayment,
                                    LocalDate startDate, LocalDate endDate, String status) {
        this.contractNumber = contractNumber;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.loanAmount = loanAmount;
        this.downPayment = downPayment;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.monthlyPayment = monthlyPayment;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
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
        return "FinanceContractRequestDTO{" +
                "contractNumber='" + contractNumber + '\'' +
                ", customerId=" + customerId +
                ", vehicleId=" + vehicleId +
                ", loanAmount=" + loanAmount +
                ", downPayment=" + downPayment +
                ", interestRate=" + interestRate +
                ", termMonths=" + termMonths +
                ", monthlyPayment=" + monthlyPayment +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}
