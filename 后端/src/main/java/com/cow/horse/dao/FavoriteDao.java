package com.cow.horse.dao;

import com.cow.horse.model.FavoriteModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavoriteDao {
    int deleteByPrimaryKey(Long id);

    int insert(FavoriteModel record);

    int insertSelective(FavoriteModel record);

    FavoriteModel selectByPrimaryKey(Long id);

    List<FavoriteModel> getMyFavorite(Long userId);

    Integer checkFavorite(Long userId,Long idleId);

    int updateByPrimaryKeySelective(FavoriteModel record);

    int updateByPrimaryKey(FavoriteModel record);
}
