package com.ggw.travel.dao;

import com.ggw.travel.entity.Province;
import com.ggw.travel.entity.Spot;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.Alias;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Mapper
public interface SpotMapper extends BaseMapper<Spot, String> {



    @Insert("INSERT INTO t_spot VALUES(#{id}, #{spotName}, #{picpath}, #{peakSeason}, " +
            "#{peakTicket}, #{lowTicket}, #{spotDesc}, #{province.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Spot spot);

    @Update("UPDATE t_spot SET spotName=#{spotName}, picpath=#{picpath}, peakSeason=#{peakSeason}, peakTicket=#{peakTicket}, " +
            "lowTicket=#{lowTicket}, spotDesc=#{spotDesc}, provinceId=#{province.id} WHERE id=#{id}")
    void update(Spot spot);

    @Delete("DELETE FROM t_spot WHERE id=#{id}")
    void delete(String id);

    @Select("SELECT * FROM t_spot WHERE id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "spotName", property = "spotName"),
            @Result(column = "picpath", property = "picpath"),
            @Result(column = "peakSeason", property = "peakSeason"),
            @Result(column = "peakTicket", property = "peakTicket"),
            @Result(column = "lowTicket", property = "lowTicket"),
            @Result(column = "spotDesc", property = "spotDesc"),
            @Result(
                    property = "province",
                    column = "provinceId",
                    javaType = Province.class,
                    one = @One(select = "com.ggw.travel.dao.ProvinceMapper.findById")
            )
    })
    Spot findById(String id);


    @Select("SELECT * FROM t_spot WHERE provinceId=#{provinceId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "spotName", property = "spotName"),
            @Result(column = "picpath", property = "picpath"),
            @Result(column = "peakSeason", property = "peakSeason"),
            @Result(column = "peakTicket", property = "peakTicket"),
            @Result(column = "lowTicket", property = "lowTicket"),
            @Result(column = "spotDesc", property = "spotDesc"),
            @Result(
                    property = "province",
                    column = "provinceId",
                    javaType = Province.class,
                    one = @One(select = "com.ggw.travel.dao.ProvinceMapper.findById")
            )
    })
    List<Spot> findByProvince(String provinceId);
}

