package com.example.salonSearcher.dto;

import com.example.salonSearcher.entity.Timeslot;
import java.time.LocalDateTime;

public record TimeslotResponse(
        Long id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Boolean isAvailable,
        Long providerId) {

    public static TimeslotResponse from(Timeslot timeslot) {
         return new TimeslotResponse(timeslot.getId(), timeslot.getStartTime(), timeslot.getEndTime(),
                timeslot.getIsAvailable(), timeslot.getProvider().getId());
    }
}
