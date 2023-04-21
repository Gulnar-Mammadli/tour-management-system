package com.mammadli.tourmanagementsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "user_name"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "user_name")
    @JsonProperty("user_name")
    private String userName;

    @Size(max = 50)
    @NotNull
    private String email;

    @Size(min = 6)
    @Column(nullable = false)
    private String password;


    @Size(min = 10)
    @Column(nullable = false,name = "phone_number")
    @JsonProperty("phone_number")
    private String phoneNumber;

    private String roles;
    private boolean deleted=false;

}
