package com.javaschool.komarov.eventclient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long id;
    private EventStatus eventStatus;
    private String cancellationReason;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;
    private String patientName;
    private String patientInsurance;
    private String therapy;
    private Integer therapyDose;
    private String doctorName;
    private Long prescriptionItemId;
    private String executorName;
}