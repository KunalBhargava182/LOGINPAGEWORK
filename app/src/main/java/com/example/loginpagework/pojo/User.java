package com.example.loginpagework.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("users_id")
    @Expose
    private String usersId;
    @SerializedName("role_id")
    @Expose
    private String roleId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("profile_photo")
    @Expose
    private Object profilePhoto;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("age")
    @Expose
    private Object age;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("dor")
    @Expose
    private String dor;
    @SerializedName("status")
    @Expose
    private String status;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getProfilePhoto() {
        return  profilePhoto;
    }

    public void setProfilePhoto(Object profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Object getGender() {
        return  gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getAge() {
        return  age;
    }

    public void setAge(Object age) {
        this.age = age;
    }

    public Object getAddress() {
        return  address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public String getDor() {
        return dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

