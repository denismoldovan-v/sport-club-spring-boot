package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "role_upgrade_requests")
public class RoleUpgradeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Reference to the User who made the request

    @Column(name = "requested_role", nullable = false, length = 10)
    private String requestedRole; // The role requested (e.g., ROLE_ADMIN)

    @Column(name = "status", nullable = false, length = 10)
    private String status; // PENDING, APPROVED, DECLINED

    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDate; // When the request was created

    @ManyToOne
    @JoinColumn(name = "reviewed_by")
    private User reviewedBy; // Admin who approved/declined the request

    @Column(name = "review_date")
    private LocalDateTime reviewDate; // When the request was reviewed

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRequestedRole() {
        return requestedRole;
    }

    public void setRequestedRole(String requestedRole) {
        this.requestedRole = requestedRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public User getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(User reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }
}
