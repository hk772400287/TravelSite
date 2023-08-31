package com.ggw.travel.daoTest;

import com.ggw.travel.dao.ProvinceMapper;
import com.ggw.travel.dao.SpotMapper;
import com.ggw.travel.entity.Spot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class SpotMapperTest {
    @Autowired
    SpotMapper spotMapper;

    @Autowired
    ProvinceMapper provinceMapper;

    @Test
    public void testFindByProvince() {
        List<Spot> spots = spotMapper.findByProvince("3");
        spots.forEach(System.out::println);
    }

    @Test
    public void testSave() {
        Spot spot = new Spot();
        spot.setSpotName("Tokyo Tower").setPicpath("/img/gug.jpg").setPeakSeason(LocalDate.now()).
                setPeakTicket(LocalTime.of(19, 0)).setLowTicket(LocalTime.of(20, 0)).setSpotDesc("tower");
        spot.setProvince(provinceMapper.findById("3"));
        spotMapper.save(spot);
    }
}
