package com.sergeev.datesauction.model.user;

import com.sergeev.datesauction.model.AbstractModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deepnekro on 22.07.15.
 */

@Table(name = "users")
public class User extends AbstractModel {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @Column(name = "bday")
    private Date bday;
    @Column(name = "vk_id") //are we need this 3 params?
    private long vkId;
    @Column(name = "fb_id")
    private long fbId;
    @Column(name = "gp_id")
    private long gpId;
    @Column(name = "email")
    private String email;
    @Column(name = "city")
    private String city;
    @Column(name = "gender") //0 - female, 1 - man
    private byte gender;

    public User() {
    }
    //TODO implement builder pattern
    public User(String name, String lastname, String password, UserRole role, boolean enabled, Date bday, long vkId, long fbId, long gpId, String email,
                String city, byte gender) {
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
        this.bday = bday;
        this.vkId = vkId;
        this.fbId = fbId;
        this.gpId = gpId;
        this.email = email;
        this.city = city;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public long getVkId() {
        return vkId;
    }

    public void setVkId(long vkId) {
        this.vkId = vkId;
    }

    public long getFbId() {
        return fbId;
    }

    public void setFbId(long fbId) {
        this.fbId = fbId;
    }

    public long getGpId() {
        return gpId;
    }

    public void setGpId(long gpId) {
        this.gpId = gpId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                ", bday=" + bday +
                ", vkId=" + vkId +
                ", fbId=" + fbId +
                ", gpId=" + gpId +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (enabled != user.enabled) return false;
        if (vkId != user.vkId) return false;
        if (fbId != user.fbId) return false;
        if (gpId != user.gpId) return false;
        if (gender != user.gender) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) return false;
        if (!password.equals(user.password)) return false;
        if (role != user.role) return false;
        if (bday != null ? !bday.equals(user.bday) : user.bday != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return !(city != null ? !city.equals(user.city) : user.city != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (bday != null ? bday.hashCode() : 0);
        result = 31 * result + (int) (vkId ^ (vkId >>> 32));
        result = 31 * result + (int) (fbId ^ (fbId >>> 32));
        result = 31 * result + (int) (gpId ^ (gpId >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (int) gender;
        return result;
    }
}
