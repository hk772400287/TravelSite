package com.ggw.travel.service.impl;

import com.ggw.travel.dao.SpotMapper;
import com.ggw.travel.entity.Province;
import com.ggw.travel.entity.Spot;
import com.ggw.travel.entity.exception.ProvinceException;
import com.ggw.travel.entity.exception.SpotException;
import com.ggw.travel.service.ProvinceService;
import com.ggw.travel.service.SpotService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpotServiceImpl implements SpotService {

    @Autowired
    SpotMapper spotMapper;

    @Autowired
    ProvinceService provinceService;


    @Override
    public PageInfo<Spot> getByProvinceAndPage(String provId, Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Spot> spots = spotMapper.findByProvince(provId);
            return new PageInfo<>(spots);
        } catch (Exception e) {
            throw new SpotException("Getting failure");
        }
    }

    @Override
    public void addSpot(Spot spot) {
        try {
            spotMapper.save(spot);
            Province province = spot.getProvince();
            province.setSpotsCount(province.getSpotsCount() + 1);
            provinceService.updateProvince(province);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SpotException("Adding failure");
        }

    }

    @Override
    public void deleteSpot(String id) {
        try {
            Spot spot = spotMapper.findById(id);
            Province province = spot.getProvince();
            spotMapper.delete(id);
            province.setSpotsCount(province.getSpotsCount() - 1);
            provinceService.updateProvince(province);
        } catch (Exception e) {
            throw new SpotException("Deletion failure");
        }
    }

    @Override
    public Spot getById(String id) {
        try {
            return spotMapper.findById(id);
        } catch (Exception e) {
            throw new SpotException("Getting failure");
        }
    }

    @Override
    public void updateSpot(Spot spot) {
        try {
            spotMapper.update(spot);
        } catch (Exception e) {
            throw new SpotException("Updating failure");
        }
    }
}
