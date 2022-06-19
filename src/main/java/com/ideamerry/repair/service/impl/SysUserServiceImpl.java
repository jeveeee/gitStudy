package com.ideamerry.repair.service.impl;

import com.ideamerry.repair.dao.WxUserDao;
import com.ideamerry.repair.entity.SysUser;
import com.ideamerry.repair.dao.SysUserDao;
import com.ideamerry.repair.entity.WxUser;
import com.ideamerry.repair.service.SysUserService;
import com.ideamerry.repair.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private WxUserDao wxUserDao;
    /**
    * 通过ID查询单条数据
    *
    * @param id 主键
    * @return 实例对象
    */
    public SysUser queryById(Integer id){
        return this.sysUserDao.queryById(id);
    }

    /**
    * 查询多条数据
    *
    * @param offset 查询起始位置
    * @param limit 查询条数
    * @return 对象列表
    */
    public List<SysUser> queryAllByLimit(int offset, int limit){
        return this.sysUserDao.queryAllByLimit(offset,limit);
    }

    /**
    * 通过实体作为筛选条件查询
    *
    * @param sysUser 实例对象
    * @return 对象列表
    */
    public List<SysUser> queryAll(SysUser sysUser){
        return this.sysUserDao.queryAll(sysUser);
    }

    /**
    * 分页查询多条数据
    *
    * @param sysUser 实例对象
    * @param pageNum 查询起始位置
    * @param pageSize 每页查询的条数
    * @return 对象列表
    */
    public PageInfo<SysUser> queryAllByPage(SysUser sysUser, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> list = this.sysUserDao.queryAll(sysUser);
        PageInfo<SysUser> p = new PageInfo<SysUser>(list);
        return p;
    }

    /**
    * 新增数据
    *
    * @param sysUser 实例对象
    * @return 实例对象
    */
    public boolean insert(SysUser sysUser){
        sysUser.setIsDelete(0);
        if(!ObjectUtils.isEmpty(sysUser.getUserPassword())){
            sysUser.setUserPassword(MD5Util.md5(sysUser.getUserPassword()));
        }
        return this.sysUserDao.insert(sysUser)>0;
    }

    /**
    * 批量新增数据
    *
    * @param list 实例对象
    * @return 影响行数
    */
    public boolean insertBatch(List<SysUser> list){
        return this.sysUserDao.insertBatch(list)>0;
    }

    /**
    * 修改数据
    *
    * @param sysUser 实例对象
    * @return 实例对象
    */
    @Transactional
    public boolean update(SysUser sysUser){
        if(!ObjectUtils.isEmpty(sysUser.getUserPassword())){
            sysUser.setUserPassword(MD5Util.md5(sysUser.getUserPassword()));
        }
        WxUser wxUser = new WxUser();
        wxUser.setRealName(sysUser.getUserName());
        wxUser.setMobile(sysUser.getMobile());
        wxUser.setType(sysUser.getUserType());
        wxUserDao.updateByUserName(wxUser);
        return this.sysUserDao.update(sysUser)>0;
    }

    /**
    * 批量修改数据
    *
    * @param list 实例对象
    * @return 影响行数
    */
    public boolean updateBatch(List<SysUser> list){
        return this.sysUserDao.updateBatch(list)>0;
    }

    /**
    * 通过主键删除数据
    *
    * @param id 主键
    * @return 是否成功
    */
    public boolean deleteById(Integer id){
        return this.sysUserDao.deleteById(id)>0;
    }
}
