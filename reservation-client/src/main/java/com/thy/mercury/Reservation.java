package com.thy.mercury;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Reservation {
    private Long id;
    private String reservationName;
}