package com.ideamerry.repair.api;

import com.ideamerry.repair.entity.RepairInfo;
import com.ideamerry.repair.service.RepairInfoService;
import com.ideamerry.repair.util.ExcelUtil;
import com.ideamerry.repair.entity.JsonResult;
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
import java.util.List;

/**
 * RepairInfo表控制层
 */
@Controller
@RequestMapping("/api/repairInfo")
public class RepairInfoApiController {

    /**
     * 服务对象
     */
    @Resource
    private RepairInfoService repairInfoService;
    @Value("${upload.path}")
    private String path;

    @Value("${domain}")
    private String domain;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("queryById")
    @ResponseBody
    public RepairInfo queryById(Integer id) {
        return this.repairInfoService.queryById(id);
    }

    /**
     * 添加RepairInfo
     *
     * @param request
     * @param repairInfo
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public JsonResult add(HttpServletRequest request, RepairInfo repairInfo) {
        boolean result = this.repairInfoService.insert(repairInfo);
        if (result) {
            return new JsonResult(200, "添加成功");
        } else {
            return new JsonResult(500, "添加失败");
        }
    }

    /**
     * 修改RepairInfo
     *
     * @param request    请求体
     * @param repairInfo 实体
     * @return
     */
    @RequestMapping("edit")
    @ResponseBody
    public JsonResult edit(HttpServletRequest request,  RepairInfo repairInfo) {
        try {
            this.repairInfoService.update(repairInfo);
            return new JsonResult(200, "修改成功");
        } catch (Exception e) {
            return new JsonResult(500, "修改失败");
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
                         @PathVariable Integer pageSize, RepairInfo repairInfo) throws IOException, TemplateException {
        if(repairInfo.getType()!=null){
            if(repairInfo.getType()!=2) {
                repairInfo.setOpenid(null);
            }
            repairInfo.setType(null);
        }
        return this.repairInfoService.queryAllByPage(repairInfo, pageNum, pageSize);
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
        boolean result = this.repairInfoService.deleteById(id);
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
    public JsonResult exportRepairInfo(HttpServletRequest request, HttpServletResponse response, RepairInfo baseDepartment) throws IOException {
        List<RepairInfo> list = this.repairInfoService.queryAll(baseDepartment);
        String fileName = System.currentTimeMillis() + ".xls";
        ExcelUtil<RepairInfo> excelUtil = new ExcelUtil<>(RepairInfo.class);
        JsonResult returnResult = excelUtil.exportExcelToPath(list, "RepairInfo报表", path + "/upload/" + fileName);
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
        ExcelUtil<RepairInfo> excelUtil = new ExcelUtil<>(RepairInfo.class);
        List<RepairInfo> list = excelUtil.importExcel("", in);
        boolean result = this.repairInfoService.insertBatch(list);
        if (result) {
            return new JsonResult(200, "批量导入成功");
        } else {
            return new JsonResult(500, "批量导入失败，请重试！");
        }
    }
}
