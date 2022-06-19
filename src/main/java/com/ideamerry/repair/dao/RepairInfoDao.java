package com.ideamerry.repair.dao;

import com.ideamerry.repair.entity.RepairInfo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RepairInfoDao {

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
    * 新增数据
    *
    * @param repairInfo 实例对象
    * @return 实例对象
    */
    int insert(RepairInfo repairInfo);

    /**
    * 批量新增数据
    *
    * @param list 实例对象
    * @return 影响行数
    */
    int insertBatch(List<RepairInfo> list);

    /**
    * 修改数据
    *
    * @param repairInfo 实例对象
    * @return 实例对象
    */
    int update(RepairInfo repairInfo);

    /**
    * 批量修改数据
    *
    * @param list 实例对象
    * @return 影响行数
    */
    int updateBatch(List<RepairInfo> list);

    /**
    * 通过主键删除数据
    *
    * @param id 主键
    * @return 是否成功
    */
    int deleteById(Integer id);

}