package com.mammadli.tourmanagementsystem.model.entity;


import com.mammadli.tourmanagementsystem.model.enums.TourStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private BigDecimal price;
    private String duration;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    @Enumerated(value = EnumType.STRING)
    private TourStatus tourStatus = TourStatus.AVAILABLE;

    private String category;

    @Max(20)
    private int maxSize;


}
