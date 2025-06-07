package com.habitHatch.WaterIntake.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Water Intake")
public class WaterIntake {
    private String userId;
    private String waterIntakeInMl;
    private String waterToDrinkInMl;

    public WaterIntake() {
    }
    @JsonCreator
    public WaterIntake(@JsonProperty("userId") String userId,
                       @JsonProperty("waterIntakeInMl") String waterIntakeInMl,
                       @JsonProperty("waterToDrinkInMl") String waterToDrinkInMl) {
        this.userId = userId;
        this.waterIntakeInMl = waterIntakeInMl;
        this.waterToDrinkInMl = waterToDrinkInMl;
    }
}
