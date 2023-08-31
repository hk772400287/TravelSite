package com.ggw.travel.service;

import com.ggw.travel.entity.Province;
import com.github.pagehelper.PageInfo;


import java.util.List;

public interface ProvinceService {
    PageInfo<Province> getBYPage(Integer pageNum, Integer pageSize);
    void addProvince(Province province);
    void deleteProvince(String id);
    Province getById(String id);
    void updateProvince(Province province);
}
