package com.example.atmostask.entities;

import com.example.atmostask.entities.base.BaseEntity;
import com.example.atmostask.models.enums.NewsStatus;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class News extends BaseEntity {
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Size(max = 500)
    @Column(columnDefinition = "TEXT", length = 500)
    private String description;

    private boolean approved = false;
    @Enumerated(EnumType.STRING)
    private NewsStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedDate;

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public NewsStatus getStatus() {
        return status;
    }

    public void setStatus(NewsStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }
}
