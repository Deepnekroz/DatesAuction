package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by deepnekro on 22.07.15.
 */
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "bday")
    private Date bday;
    @Column(name = "vk_id")
    private long vkId;
    @Column(name = "fb_id")
    private long fbId;
    @Column(name = "gp_id")
    private long gpId;
    @Column(name = "email")
    private String email;
    @Column(name = "city")
    private String city;
    @Column(name = "gender")
    private byte gender;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", bday=" + bday +
                ", vkId=" + vkId +
                ", fbId=" + fbId +
                ", gpId=" + gpId +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", gender=" + gender +
                '}';
    }
}
