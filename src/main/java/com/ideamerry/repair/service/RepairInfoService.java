package com.ideamerry.repair.service;

import com.ideamerry.repair.entity.RepairInfo;
import java.util.List;
import com.github.pagehelper.PageInfo;

public interface RepairInfoService {

    /**
    * 通过ID查询单条数据
    *
    * @param id 主键
    * @return 实例对象
    */
    RepairInfo queryById(Integer id);

    /**
    * 查询多条数据
    *
    * @param offset 查询起始位置
    * @param limit 查询条数
    * @return 对象列表
    */
    List<RepairInfo> queryAllByLimit(int offset, int limit);

    /**
    * 通过实体作为筛选条件查询
    *
    * @param repairInfo 实例对象
    * @return 对象列表
    */
    List<RepairInfo> queryAll(RepairInfo repairInfo); 
        
    /**
    * 分页查询多条数据
    *
    * @param repairInfo 实例对象
    * @param pageNum 查询起始位置
    * @param pageSize 每页查询的条数
    * @return 对象列表
    */
    PageInfo<RepairInfo> queryAllByPage(RepairInfo repairInfo, int pageNum, int pageSize);

    /**
    * 新增数据
    *
    * @param repairInfo 实例对象
    * @return 实例对象
    */
    boolean insert(RepairInfo repairInfo);

    /**
    * 批量新增数据
    *
    * @param list 实例对象
    * @return 影响行数
    */
    boolean insertBatch(List<RepairInfo> list);

    /**
    * 修改数据
    *
    * @param repairInfo 实例对象
    * @return 实例对象
    */
    boolean update(RepairInfo repairInfo);

    /**
    * 批量修改数据
    *
    * @param list 实例对象
    * @return 影响行数
    */
    boolean updateBatch(List<RepairInfo> list);

    /**
    * 通过主键删除数据
    *
    * @param id 主键
    * @return 是否成功
    */
    boolean deleteById(Integer id);

}