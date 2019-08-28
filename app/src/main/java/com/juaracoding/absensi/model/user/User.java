
package com.juaracoding.absensi.model.user;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id_personel")
    @Expose
    private Integer idPersonel;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("personel")
    @Expose
    private List<Personel> personel = null;

    public Integer getIdPersonel() {
        return idPersonel;
    }

    public void setIdPersonel(Integer idPersonel) {
        this.idPersonel = idPersonel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Personel> getPersonel() {
        return personel;
    }

    public void setPersonel(List<Personel> personel) {
        this.personel = personel;
    }

}
