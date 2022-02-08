package com.madadipouya.springkafkatest.dto;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PulseJobInfoRequest implements Serializable {
   private ArrayList<MachineJobInfoRequest> jobs = new ArrayList<MachineJobInfoRequest>();
}
