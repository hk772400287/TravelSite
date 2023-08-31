package com.ggw.travel.controller;


import com.ggw.travel.controller.utils.R;
import com.ggw.travel.entity.Province;
import com.ggw.travel.entity.Spot;
import com.ggw.travel.service.ProvinceService;
import com.ggw.travel.service.SpotService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/spots")
public class SpotController {

    @Autowired
    private SpotService spotService;

    @Autowired
    private ProvinceService provinceService;

    @Value("${upload.dir}")
    private String realPath;

    @GetMapping("{pageNum}/{pageSize}")
    public R getByProvinceByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize, String provId) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 3 : pageSize;
        PageInfo<Spot> pageInfo = spotService.getByProvinceAndPage(provId, pageNum, pageSize);
        return new R(true, pageInfo);
    }

    @GetMapping("{id}")
    public R getById(@PathVariable String id) {
        Spot spot = spotService.getById(id);
        return new R(true, spot);
    }

    @PostMapping
    public R addSpot(MultipartFile pic, Spot spot, String provinceId) throws IOException {
        fileUpload(pic, spot);
        Province province = provinceService.getById(provinceId);
        spot.setProvince(province);
        spotService.addSpot(spot);
        return new R(true, "Adding successful");
    }

    @DeleteMapping("{id}")
    public R deleteSpot(@PathVariable String id) {
        spotService.deleteSpot(id);
        return new R(true, "Deletion successful");
    }

    @PutMapping
    public R updateSpot(MultipartFile pic, Spot spot, String provinceId) throws IOException {
        if (pic != null) {
            fileUpload(pic, spot);
        }
        Province province = provinceService.getById(provinceId);
        spot.setProvince(province);
        spotService.updateSpot(spot);
        return new R(true, "Updating successful");
    }

    private void fileUpload(MultipartFile pic, Spot spot) throws IOException {
        spot.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
        String originalFilename = pic.getOriginalFilename();
        String newName = FilenameUtils.getBaseName(originalFilename) +
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                "." + FilenameUtils.getExtension(originalFilename);
        File savePath = new File(realPath);
        pic.transferTo(new File(savePath, newName));
    }
}
