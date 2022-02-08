package com.madadipouya.springkafkatest.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class PulseToLMSResponseModel {

    private String uuid;

    private String firstName;

    private String lastName;

    public PulseToLMSResponseModel() {

    }

    public PulseToLMSResponseModel(String uuid, String firstName, String lastName) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
