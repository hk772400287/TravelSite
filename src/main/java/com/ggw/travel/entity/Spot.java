package com.ggw.travel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Spot {
    private String id;
    private String spotName;
    private String picpath;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate peakSeason;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime peakTicket;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime lowTicket;
    private String spotDesc;
    private Province province;
}
