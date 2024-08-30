package tech.joelf.ms_user.dtos.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import tech.joelf.ms_user.utils.DateUtils;

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;

    @JsonFormat(pattern = DateUtils.DATE_FORMAT, locale = "America/Fortaleza", shape = JsonFormat.Shape.STRING)
    private LocalDate birthDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
