package com.habitHatch.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "users")

public class Users {
    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile_number")
    private Long mobileNumber;
    @Column(name = "password")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).+$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one special character")
    private String password;
    @Column(name = "is_premium")
    private Boolean isPremium;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "height")
    private Integer height;
    @Column(name = "age")
    private Integer age;
    @Column(name="gender")
    private String gender;
    @Column(name = "heart_rate")
    private Integer heartRate;
    @Column(name="bmi")
    private Double bmi;
    @Column(name="water_intake")
    private Double waterIntake;
    @Column(name = "water_consumed")
    private Double waterConsumed;

}
