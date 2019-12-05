package com.dgpt.submitdata;

public class UserInfo {
    private int user_id;
    private String userName;
    private String password;
    private String sex;
    private String interests;
    private String my_picture;
    private String remark;

    public UserInfo() {
    }

    public UserInfo(int user_id, String userName, String password, String sex, String interests, String my_picture, String remark) {
        this.user_id = user_id;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.interests = interests;
        this.my_picture = my_picture;
        this.remark = remark;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getMy_picture() {
        return my_picture;
    }

    public void setMy_picture(String my_picture) {
        this.my_picture = my_picture;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_id=" + user_id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", interests='" + interests + '\'' +
                ", my_picture='" + my_picture + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
