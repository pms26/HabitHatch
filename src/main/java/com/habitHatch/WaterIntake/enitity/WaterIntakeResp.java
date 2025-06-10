package com.habitHatch.WaterIntake.enitity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Water Intake")
public class WaterIntakeResp {
    private String message;

    public WaterIntakeResp() {
    }
    public WaterIntakeResp(String message) {
        this.message = message;
    }

}
