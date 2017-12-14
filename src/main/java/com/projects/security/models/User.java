package com.projects.security.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity
@Table(name="app_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseModel{
 
    @Column(unique=true, nullable=false)
    private String username;
   
	@Column(nullable=false)
    private String password;
    
    @Column(unique=true, nullable=false)
    private String email;
 
    @Column(nullable=false)
    private String state=State.ACTIVE.getState();
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date dateRegistered;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;
 
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app_user_user_profile", 
             joinColumns = { @JoinColumn(name = "user_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "user_profile_id") })
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
 
   public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getState() {
        return state;
    }
 
    public void setState(String state) {
        this.state = state;
    }
 
    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }
 
    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }
        
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

 
    public Date getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	}
