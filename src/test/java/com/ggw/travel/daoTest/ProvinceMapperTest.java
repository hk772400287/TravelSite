package com.ggw.travel.daoTest;

import com.ggw.travel.dao.ProvinceMapper;
import com.ggw.travel.entity.Province;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProvinceMapperTest {
    @Autowired
    ProvinceMapper provinceMapper;

    @Test
    public void insertTest() {
        Province province = new Province();
        province.setProvName("TOkyo").setTags("aa").setSpotsCount(5);
        provinceMapper.save(province);
    }

    @Test
    public void updateTest() {
        Province province = new Province();
        province.setProvName("Tokyo").setTags("ggg").setSpotsCount(5).setId("1");
        provinceMapper.update(province);
    }

    @Test
    public void deleteTest() {
        provinceMapper.delete("1");
    }

    @Test
    public void findAllTest() {
        List<Province> provinceList = provinceMapper.findAll();
        provinceList.forEach(System.out::println);
    }

    @Test
    public void findByIdTest() {
        Province province = provinceMapper.findById("3");
        System.out.println(province);
    }
}
