package com.cow.horse.service.impl;

import com.cow.horse.service.TypeService;
import com.cow.horse.dao.IdleItemDao;
import com.cow.horse.dao.TypeDao;
import com.cow.horse.model.TypeModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    private TypeDao typeDao;

    @Resource
    private IdleItemDao idleItemDao;

    @Override
    public List<TypeModel> listByCondition(int begin, int nums) {
        return typeDao.listByCondition(begin, nums);
    }
}
