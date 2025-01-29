package com.devops.Expense;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;


@Entity
@Table(name = "Expense")
public class Expense {
    @Id
//    @JsonProperty("expenseId")
    private String expenseId;
    private Float amount;
    private String description;
    private String category;
    private Timestamp transactionDate ;
    private String transactionType;
    private String expenseType;
    public Expense() {
        this.expenseId = UUID.randomUUID().toString();
    }
    public Expense(String expenseId, Float amount, String description, String category,
                    String transactionType, String expenseType) {
        this.expenseId = expenseId!=null?expenseId:UUID.randomUUID().toString();
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.transactionType = transactionType;
        this.expenseType = expenseType;
    }
    @PrePersist
    public void prePersist() {
        if (this.expenseId == null) {
            this.expenseId = UUID.randomUUID().toString();  // Ensures UUID is generated before saving to DB
        }
        if(this.transactionDate==null)
            this.transactionDate = Timestamp.from(
                    ZonedDateTime.now(ZoneOffset.ofHoursMinutes(5,30)).toInstant());
    }
    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }
    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }
}
