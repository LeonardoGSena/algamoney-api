package com.sena.leonardo.algamoneyapi.domain.repositories.projection;

import com.sena.leonardo.algamoneyapi.domain.models.RegisterType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RegisterSummary {
    private Long id;
    private String description;
    private LocalDate dueDate;
    private LocalDate payday;
    private BigDecimal amount;
    private RegisterType type;
    private String category;
    private String person;

    public RegisterSummary(Long id, String description, LocalDate dueDate, LocalDate payday, BigDecimal amount, RegisterType type, String category, String person) {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.payday = payday;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getPayday() {
        return payday;
    }

    public void setPayday(LocalDate payday) {
        this.payday = payday;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public RegisterType getType() {
        return type;
    }

    public void setType(RegisterType type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
