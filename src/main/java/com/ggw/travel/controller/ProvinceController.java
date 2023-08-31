package com.ggw.travel.controller;

import com.ggw.travel.controller.utils.R;
import com.ggw.travel.entity.Province;
import com.ggw.travel.service.ProvinceService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("{pageNum}/{pageSize}")
    public R getByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 5 : pageSize;
        PageInfo<Province> pageInfo = provinceService.getBYPage(pageNum, pageSize);
        return new R(true, pageInfo);
    }

    @PostMapping
    public R addProvince(@RequestBody Province province) {
        provinceService.addProvince(province);
        return new R(true, "Adding province successful");
    }

    @DeleteMapping("{id}")
    public R deleteProvince(@PathVariable String id) {
        provinceService.deleteProvince(id);
        return new R(true, "Deletion successful");
    }

    @GetMapping("{id}")
    public R getById(@PathVariable String id) {
        Province province = provinceService.getById(id);
        return new R(true, province);
    }

    @PutMapping
    public R updateProvince(@RequestBody Province province) {
        provinceService.updateProvince(province);
        return new R(true, "Updating province successful");
    }
}
