package com.madadipouya.springkafkatest.dto;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MachineJobInfoRequest implements Serializable {
    private String facility;
    private String jobId;
    private String userId;
    private String logDate;
    private String actionTaken;
    private String productCode;
    private String customerOrderNumber;
    private  MachineInfoRequest machineInfo;
    private String pzIndicator;
    private String stationName;
}
