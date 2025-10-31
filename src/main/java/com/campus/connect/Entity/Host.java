package com.campus.connect.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "hosts_pending_approval") // Create a new table
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Link back to the user who requested the host role
    // Using @OneToOne is ideal if only one User can be a Host entry
    @OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private Users user;

    @Column(name = "approval_status")
    private String approvalStatus = "PENDING"; // Values: PENDING, APPROVED, REJECTED

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Host(Long id, Users user, String approvalStatus) {
		super();
		this.id = id;
		this.user = user;
		this.approvalStatus = approvalStatus;
	}

	public Host() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Host [id=" + id + ", user=" + user + ", approvalStatus=" + approvalStatus + "]";
	}
    
    // Constructors, Getters, and Setters
    // (You will need to implement these)
    
   
}
