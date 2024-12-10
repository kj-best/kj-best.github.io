package com.cow.horse.dao;

import com.cow.horse.model.TypeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypeDao {

    List<TypeModel> listByCondition(@Param("begin") int begin, @Param("nums") int nums);
}
