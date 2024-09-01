package tech.joelf.ms_category.dtos.request;

import javax.validation.constraints.NotBlank;

public class CreateCategoryRequest {
    @NotBlank
    private String name;

    public CreateCategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
