package com.cms.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private Long id;
    private Long clubId;
    private String type; // INCOME, EXPENSE
    private BigDecimal amount;
    private String description;
    private Long creatorId;
    private Date createTime;

    public Transaction() {
    }

    public Transaction(Long id, Long clubId, String type, BigDecimal amount, String description, Long creatorId,
                       Date createTime) {
        this.id = id;
        this.clubId = clubId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.creatorId = creatorId;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
