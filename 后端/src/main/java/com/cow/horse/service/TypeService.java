package com.cow.horse.service;

import com.cow.horse.model.TypeModel;

import java.util.List;

public interface TypeService {

    List<TypeModel> listByCondition(int begin, int nums);
}
