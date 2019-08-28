
package com.juaracoding.absensi.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("projectId")
    @Expose
    private Integer projectId;
    @SerializedName("minimumPersonnel")
    @Expose
    private Integer minimumPersonnel;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("attendanceQrCode")
    @Expose
    private String attendanceQrCode;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("personel")
    @Expose
    private Integer personel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getMinimumPersonnel() {
        return minimumPersonnel;
    }

    public void setMinimumPersonnel(Integer minimumPersonnel) {
        this.minimumPersonnel = minimumPersonnel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttendanceQrCode() {
        return attendanceQrCode;
    }

    public void setAttendanceQrCode(String attendanceQrCode) {
        this.attendanceQrCode = attendanceQrCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPersonel() {
        return personel;
    }

    public void setPersonel(Integer personel) {
        this.personel = personel;
    }

}
