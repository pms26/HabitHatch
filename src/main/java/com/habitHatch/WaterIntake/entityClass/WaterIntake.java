package com.habitHatch.WaterIntake.entityClass;

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
    private Double waterIntakeInMl;
    private Double waterToDrinkInMl;
    private Double waterReminder;
    private Double waterConsumedinML;

    public WaterIntake() {
    }

    @JsonCreator
    public WaterIntake(@JsonProperty("userId") String userId,
                       @JsonProperty("waterIntakeInMl") Double waterIntakeInMl,
                       @JsonProperty("waterToDrinkInMl") Double waterToDrinkInMl,
                       @JsonProperty("waterReminder") Double waterReminder,
                       @JsonProperty("waterConsumedinML") Double waterConsumedinML) {
        this.userId = userId;
        this.waterIntakeInMl = waterIntakeInMl;
        this.waterToDrinkInMl = waterToDrinkInMl;
        this.waterReminder = waterReminder;
        this.waterConsumedinML = waterConsumedinML;
    }
}
