package com.mammadli.tourmanagementsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Column(name = "user_name")
    @JsonProperty(namespace = "user_name")
    private String userName;

    @Size(max = 30)
    @Email
    private String email;

    @Size(min = 6 , max = 20)
    private String password;

    @NotBlank
    @Size(min = 10)
    @Column(name = "phone_number")
    @JsonProperty(namespace = "phone_number")
    private String phoneNumber;

    private String roles;
    private boolean deleted=false;

}
