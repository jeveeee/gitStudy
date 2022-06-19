package com.ideamerry.repair.service.impl;

import com.ideamerry.repair.entity.WxUser;
import com.ideamerry.repair.dao.WxUserDao;
import com.ideamerry.repair.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.List;

@Service
public class WxUserServiceImpl implements WxUserService {

    @Autowired
    private WxUserDao wxUserDao;

    /**
    * 通过ID查询单条数据
    *
    * @param id 主键
    * @return 实例对象
    */
    public WxUser queryById(Integer id){
        return this.wxUserDao.queryById(id);
    }

    /**
    * 查询多条数据
    *
    * @param offset 查询起始位置
    * @param limit 查询条数
    * @return 对象列表
    */
    public List<WxUser> queryAllByLimit(int offset, int limit){
        return this.wxUserDao.queryAllByLimit(offset,limit);
    }

    /**
    * 通过实体作为筛选条件查询
    *
    * @param wxUser 实例对象
    * @return 对象列表
    */
    public List<WxUser> queryAll(WxUser wxUser){
        return this.wxUserDao.queryAll(wxUser);
    }

    /**
    * 分页查询多条数据
    *
    * @param wxUser 实例对象
    * @param pageNum 查询起始位置
    * @param pageSize 每页查询的条数
    * @return 对象列表
    */
    public PageInfo<WxUser> queryAllByPage(WxUser wxUser, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<WxUser> list = this.wxUserDao.queryAll(wxUser);
        PageInfo<WxUser> p = new PageInfo<WxUser>(list);
        return p;
    }

    /**
    * 新增数据
    *
    * @param wxUser 实例对象
    * @return 实例对象
    */
    public boolean insert(WxUser wxUser){
        return this.wxUserDao.insert(wxUser)>0;
    }

    /**
    * 批量新增数据
    *
    * @param list 实例对象
    * @return 影响行数
    */
    public boolean insertBatch(List<WxUser> list){
        return this.wxUserDao.insertBatch(list)>0;
    }

    /**
    * 修改数据
    *
    * @param wxUser 实例对象
    * @return 实例对象
    */
    public boolean update(WxUser wxUser){
        return this.wxUserDao.update(wxUser)>0;
    }

    /**
    * 批量修改数据
    *
    * @param list 实例对象
    * @return 影响行数
    */
    public boolean updateBatch(List<WxUser> list){
        return this.wxUserDao.updateBatch(list)>0;
    }

    /**
    * 通过主键删除数据
    *
    * @param id 主键
    * @return 是否成功
    */
    public boolean deleteById(Integer id){
        return this.wxUserDao.deleteById(id)>0;
    }
}
