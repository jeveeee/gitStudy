package com.ideamerry.repair.service.impl;

import com.ideamerry.repair.entity.RepairInfo;
import com.ideamerry.repair.dao.RepairInfoDao;
import com.ideamerry.repair.service.RepairInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RepairInfoServiceImpl implements RepairInfoService {

    @Autowired
    private RepairInfoDao repairInfoDao;

    /**
    * 通过ID查询单条数据
    *
    * @param id 主键
    * @return 实例对象
    */
    public RepairInfo queryById(Integer id){
        return this.repairInfoDao.queryById(id);
    }

    /**
    * 查询多条数据
    *
    * @param offset 查询起始位置
    * @param limit 查询条数
    * @return 对象列表
    */
    public List<RepairInfo> queryAllByLimit(int offset, int limit){
        return this.repairInfoDao.queryAllByLimit(offset,limit);
    }

    /**
    * 通过实体作为筛选条件查询
    *
    * @param repairInfo 实例对象
    * @return 对象列表
    */
    public List<RepairInfo> queryAll(RepairInfo repairInfo){
        return this.repairInfoDao.queryAll(repairInfo);
    }

    /**
    * 分页查询多条数据
    *
    * @param repairInfo 实例对象
    * @param pageNum 查询起始位置
    * @param pageSize 每页查询的条数
    * @return 对象列表
    */
    public PageInfo<RepairInfo> queryAllByPage(RepairInfo repairInfo, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<RepairInfo> list = this.repairInfoDao.queryAll(repairInfo);
        PageInfo<RepairInfo> p = new PageInfo<RepairInfo>(list);
        return p;
    }

    /**
    * 新增数据
    *
    * @param repairInfo 实例对象
    * @return 实例对象
    */
    public boolean insert(RepairInfo repairInfo){
        repairInfo.setStatus(1);
        repairInfo.setRepairDate(new Date());
        return this.repairInfoDao.insert(repairInfo)>0;
    }

    /**
    * 批量新增数据
    *
    * @param list 实例对象
    * @return 影响行数
    */
    public boolean insertBatch(List<RepairInfo> list){
        return this.repairInfoDao.insertBatch(list)>0;
    }

    /**
    * 修改数据
    *
    * @param repairInfo 实例对象
    * @return 实例对象
    */
    public boolean update(RepairInfo repairInfo){
        return this.repairInfoDao.update(repairInfo)>0;
    }

    /**
    * 批量修改数据
    *
    * @param list 实例对象
    * @return 影响行数
    */
    public boolean updateBatch(List<RepairInfo> list){
        return this.repairInfoDao.updateBatch(list)>0;
    }

    /**
    * 通过主键删除数据
    *
    * @param id 主键
    * @return 是否成功
    */
    public boolean deleteById(Integer id){
        return this.repairInfoDao.deleteById(id)>0;
    }
}
