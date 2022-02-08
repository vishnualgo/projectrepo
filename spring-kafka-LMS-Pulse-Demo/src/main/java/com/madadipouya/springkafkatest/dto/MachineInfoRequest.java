package com.madadipouya.springkafkatest.dto;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MachineInfoRequest implements Serializable {
    private String machineName;
    private String fixture;
    private String hoopJig;


}
