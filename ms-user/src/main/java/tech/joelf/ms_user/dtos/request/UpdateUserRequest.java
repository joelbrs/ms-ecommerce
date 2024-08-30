package tech.joelf.ms_user.dtos.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import tech.joelf.ms_user.utils.DateUtils;

public class UpdateUserRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotNull
    @JsonFormat(pattern = DateUtils.DATE_FORMAT, timezone = "America/Fortaleza", shape = JsonFormat.Shape.STRING)
    private LocalDate birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
