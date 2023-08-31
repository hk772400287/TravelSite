package com.ggw.travel.serviceTest;

import com.ggw.travel.entity.Province;
import com.ggw.travel.service.ProvinceService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProvinceServiceTest {
    @Autowired
    private ProvinceService provinceService;

    @Test
    public void getBYPageTest() {
        PageInfo<Province> pageInfo = provinceService.getBYPage(1, 1);
        pageInfo.getList().forEach(System.out::println);

    }

}
