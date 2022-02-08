package com.madadipouya.springkafkatest.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PulseRequestModel {
    private ArrayList<MachineJobInfoRequest> jobs = new ArrayList<MachineJobInfoRequest>();
}
