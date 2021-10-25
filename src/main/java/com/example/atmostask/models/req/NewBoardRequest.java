package com.example.atmostask.models.req;

import com.example.atmostask.models.custom.ResponseMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewBoardRequest {
    @NotBlank
    @Size(min = 1, max = 500, message = ResponseMessages.MANY_CHARACTERS)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
