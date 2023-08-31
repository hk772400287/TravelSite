package com.ggw.travel.service;

import com.ggw.travel.entity.Province;
import com.ggw.travel.entity.Spot;
import com.github.pagehelper.PageInfo;

public interface SpotService {
    PageInfo<Spot> getByProvinceAndPage(String provId, Integer pageNum, Integer pageSize);
    void addSpot(Spot spot);
    void deleteSpot(String id);
    Spot getById(String id);
    void updateSpot(Spot spot);
}
