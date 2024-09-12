// package com.example.farm.demo.customer;
// import java.util.Date;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;

// import com.fasterxml.jackson.annotation.JsonProperty;

// @Document(collection = "customers")
// public class Customer {

//   @Id 
//   @JsonProperty("id")
//   private String id;

//   private String name;
//   private String phoneNumber;
//   private String profileImage;
//   private Date createdAt;
//   private Date updatedAt;

//   public String getName() {
//     return name;
//   }

//   public void setName(String name) {
//     this.name = name;
//   }

//   public String getPhoneNumber() {
//     return phoneNumber;
//   }

//   public void setPhoneNumber(String phoneNumber) {
//     this.phoneNumber = phoneNumber;
//   }

//   public String getProfileImage() {
//     return profileImage;
//   }

//   public void setProfileImage(String profileImage) {
//     this.profileImage = profileImage;
//   }

//   public Date getCreatedAt() {
//     return createdAt;
//   }

//   public void setCreatedAt(Date createdAt) {
//     this.createdAt = createdAt;
//   }
  
//   public Date getUpdatedAt() {
//     return updatedAt;
//   }

//   public void setUpdatedAt(Date updatedAt) {
//     this.updatedAt = updatedAt;
//   }

// }