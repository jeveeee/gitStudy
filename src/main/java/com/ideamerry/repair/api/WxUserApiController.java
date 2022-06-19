package com.ideamerry.repair.api;

import com.google.gson.Gson;
import com.ideamerry.repair.entity.Jscode2session;
import com.ideamerry.repair.entity.SysUser;
import com.ideamerry.repair.entity.WxUser;
import com.ideamerry.repair.service.SysUserService;
import com.ideamerry.repair.service.WxUserService;
import com.ideamerry.repair.util.ExcelUtil;
import com.ideamerry.repair.entity.JsonResult;
import com.ideamerry.repair.util.HttpClientUtil;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * WxUser表控制层
 */
@Controller
@RequestMapping("/api/wxUser")
public class WxUserApiController {

    /**
     * 服务对象
     */
    @Resource
    private WxUserService wxUserService;
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;
    @Value("${upload.path}")
    private String path;
    @Value("${domain}")
    private String domain;
    @Value("${appId}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;

    /**
     * 小程序登录
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult loginXcx(String code) {
        String wxUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        String wxResult = HttpClientUtil.doGet(wxUrl);
        JsonResult jsonResult = new JsonResult(500, "获取openid失败");
        if (!ObjectUtils.isEmpty(wxResult)) {
            try {
                Jscode2session jscode2session = new Gson().fromJson(wxResult, Jscode2session.class);
                if (jscode2session != null) {
                    WxUser wxUser = new WxUser();
                    wxUser.setOpenid(jscode2session.getOpenid());
                    List<WxUser> wxUsers = wxUserService.queryAll(wxUser);
                    if (wxUsers != null && wxUsers.size() > 0) {
                        wxUser = wxUsers.get(0);
                    } else {
                        wxUser = null;
                    }
                    jsonResult.setCode(200);
                    jsonResult.setMsg(jscode2session.getOpenid());
                    jsonResult.setObj(wxUser);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonResult;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("queryById")
    @ResponseBody
    public WxUser queryById(Integer id) {
        return this.wxUserService.queryById(id);
    }

    /**
     * 添加WxUser
     *
     * @param request
     * @param wxUser
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public JsonResult add(HttpServletRequest request, WxUser wxUser) {
        wxUser.setCreateTime(new Date());
        wxUser.setIsDelete(0);
        boolean result = this.wxUserService.insert(wxUser);
        if (result) {
            return new JsonResult(200, "添加成功");
        } else {
            return new JsonResult(500, "添加失败");
        }
    }

    /**
     * 修改WxUser
     *
     * @param request 请求体
     * @param wxUser  实体
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public JsonResult edit(HttpServletRequest request, WxUser wxUser) {
        boolean result = this.wxUserService.update(wxUser);
        if (result) {
            return new JsonResult(200, "修改成功");
        } else {
            return new JsonResult(500, "修改失败");
        }
    }

    @RequestMapping("binding")
    @ResponseBody
    public JsonResult binding(HttpServletRequest request, WxUser wxUser) {
        //查询用户角色，存在则绑定成功，不存在则绑定失败
        SysUser sysUser = new SysUser();
        sysUser.setMobile(wxUser.getMobile());
        sysUser.setUserName(wxUser.getRealName());
        List<SysUser> sysUsers = this.sysUserService.queryAll(sysUser);
        if (sysUsers.size() > 0) {
            Integer userType = sysUsers.get(0).getUserType();
            wxUser.setType(userType);
            this.wxUserService.update(wxUser);
            return new JsonResult(200, "绑定成功",userType);
        } else {
            return new JsonResult(500, "姓名或手机号错误，绑定失败!");
        }
    }

    /**
     * 分页查询列表数据
     *
     * @param request
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    @RequestMapping(value = "list/{pageNum}/{pageSize}", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo list(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer pageNum,
                         @PathVariable Integer pageSize, WxUser wxUser) throws IOException, TemplateException {
        return this.wxUserService.queryAllByPage(wxUser, pageNum, pageSize);
    }

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return jsonResult
     */
    @RequestMapping("deleteById")
    @ResponseBody
    public JsonResult deleteById(Integer id) {
        boolean result = this.wxUserService.deleteById(id);
        if (result) {
            return new JsonResult(200, "删除成功");
        } else {
            return new JsonResult(500, "删除失败");
        }
    }


    /**
     * 导出数据报表
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "exportExcel", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult exportWxUser(HttpServletRequest request, HttpServletResponse response, WxUser baseDepartment) throws IOException {
        List<WxUser> list = this.wxUserService.queryAll(baseDepartment);
        String fileName = System.currentTimeMillis() + ".xls";
        ExcelUtil<WxUser> excelUtil = new ExcelUtil<>(WxUser.class);
        JsonResult returnResult = excelUtil.exportExcelToPath(list, "WxUser报表", path + "/upload/" + fileName);
        returnResult.setMsg(domain + "/upload/" + fileName);
        return returnResult;
    }

    /**
     * 导入Excel数据
     *
     * @param request
     * @return
     */
    @RequestMapping("importExcel")
    @ResponseBody
    public JsonResult importExcel(MultipartHttpServletRequest request) throws Exception {
        MultipartFile file = request.getFile("file");
        if (file.isEmpty()) {
            return new JsonResult(500, "批量导入失败，文件不存在！");
        }
        InputStream in = file.getInputStream();
        ExcelUtil<WxUser> excelUtil = new ExcelUtil<>(WxUser.class);
        List<WxUser> list = excelUtil.importExcel("", in);
        boolean result = this.wxUserService.insertBatch(list);
        if (result) {
            return new JsonResult(200, "批量导入成功");
        } else {
            return new JsonResult(500, "批量导入失败，请重试！");
        }
    }
}
