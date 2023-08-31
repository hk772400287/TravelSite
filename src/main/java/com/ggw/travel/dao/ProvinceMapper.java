package com.ggw.travel.dao;

import com.ggw.travel.entity.Province;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Mapper
public interface ProvinceMapper extends BaseMapper<Province,String> {
    @Insert("INSERT INTO t_province VALUES(#{id}, #{provName}, #{tags}, #{spotsCount})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Province province);

    @Update("UPDATE t_province SET provName=#{provName}, tags=#{tags}, spotsCount=#{spotsCount} WHERE id=#{id}")
    void update(Province province);

    @Delete("DELETE FROM t_province WHERE id=#{id}")
    void delete(String id);

    @Select("SELECT * FROM t_province")
    List<Province> findAll();


    @Select("SELECT * FROM t_province WHERE id=#{id}")
    Province findById(String id);


}
