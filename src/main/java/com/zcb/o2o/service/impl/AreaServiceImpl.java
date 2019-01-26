package com.zcb.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcb.o2o.dao.AreaDao;
import com.zcb.o2o.entity.Area;
import com.zcb.o2o.service.AreaService;

/**
 * 在psring-service.xml中已经配置扫描这些service类，但是必须在这些类加上@Service注解，否则不能正确进行依赖注入
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
