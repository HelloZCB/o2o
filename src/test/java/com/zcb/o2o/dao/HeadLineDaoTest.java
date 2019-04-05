package com.zcb.o2o.dao;

import com.zcb.o2o.BaseTest;
import com.zcb.o2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testQueryHeadLine(){
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(0);
        List<HeadLine> headLineList = headLineDao.queryHeadLine(headLineCondition);
        assertEquals(1, ((List) headLineList).size());
    }
}
