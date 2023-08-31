package com.ggw.travel.service.impl;

import com.ggw.travel.dao.ProvinceMapper;
import com.ggw.travel.entity.Province;
import com.ggw.travel.entity.exception.ProvinceException;
import com.ggw.travel.service.ProvinceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public PageInfo<Province> getBYPage(Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Province> provinceList = provinceMapper.findAll();
            return new PageInfo<>(provinceList);
        } catch (Exception e) {
            throw new ProvinceException("Getting failure");
        }
    }

    @Override
    public void addProvince(Province province) {
        province.setSpotsCount(0);
        try {
            provinceMapper.save(province);
        } catch (Exception e) {
            throw new ProvinceException("Adding failure");
        }
    }

    @Override
    public void deleteProvince(String id) {
        try {
            provinceMapper.delete(id);
        } catch (Exception e) {
            throw new ProvinceException("Deletion failure");
        }
    }

    @Override
    public Province getById(String id) {
        try {
            return provinceMapper.findById(id);
        } catch (Exception e) {
            throw new ProvinceException("Getting failure");
        }
    }

    @Override
    public void updateProvince(Province province) {
        try {
            provinceMapper.update(province);
        } catch (Exception e) {
            throw new ProvinceException("Updating failure");
        }
    }
}
