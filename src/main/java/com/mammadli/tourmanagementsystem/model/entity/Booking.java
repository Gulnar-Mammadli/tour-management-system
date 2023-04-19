package com.mammadli.tourmanagementsystem.model.entity;

import com.mammadli.tourmanagementsystem.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "bookings")
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long tourId;
    private LocalDateTime date;

    @Enumerated(value = EnumType.STRING)
    private BookingStatus status;

}
