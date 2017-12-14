package com.projects.security.models;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;
 
@Entity
@Table(name="user_profile")
public class UserProfile extends BaseModel{
 
    
    @Column(length=15, unique=true, nullable=false)
    private String type = UserProfileType.USER.getUserProfileType();
     
     
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
 
        
 
}
