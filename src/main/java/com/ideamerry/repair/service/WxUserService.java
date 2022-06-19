package com.ideamerry.repair.service;

import com.ideamerry.repair.entity.WxUser;
import java.util.List;
import com.github.pagehelper.PageInfo;

public interface WxUserService {

    /**
    * 通过ID查询单条数据
    *
    * @param id 主键
    * @return 实例对象
    */
    WxUser queryById(Integer id);

    /**
    * 查询多条数据
    *
    * @param offset 查询起始位置
    * @param limit 查询条数
    * @return 对象列表
    */
    List<WxUser> queryAllByLimit(int offset, int limit);

    /**
    * 通过实体作为筛选条件查询
    *
    * @param wxUser 实例对象
    * @return 对象列表
    */
    List<WxUser> queryAll(WxUser wxUser); 
        
    /**
    * 分页查询多条数据
    *
    * @param wxUser 实例对象
    * @param pageNum 查询起始位置
    * @param pageSize 每页查询的条数
    * @return 对象列表
    */
    PageInfo<WxUser> queryAllByPage(WxUser wxUser, int pageNum, int pageSize);

    /**
    * 新增数据
    *
    * @param wxUser 实例对象
    * @return 实例对象
    */
    boolean insert(WxUser wxUser);

    /**
    * 批量新增数据
    *
    * @param list 实例对象
    * @return 影响行数
    */
    boolean insertBatch(List<WxUser> list);

    /**
    * 修改数据
    *
    * @param wxUser 实例对象
    * @return 实例对象
    */
    boolean update(WxUser wxUser);

    /**
    * 批量修改数据
    *
    * @param list 实例对象
    * @return 影响行数
    */
    boolean updateBatch(List<WxUser> list);

    /**
    * 通过主键删除数据
    *
    * @param id 主键
    * @return 是否成功
    */
    boolean deleteById(Integer id);

}