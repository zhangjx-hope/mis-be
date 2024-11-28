package org.jeecg.modules.runningdata.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.runningdata.entity.TbRunningdata;
import org.jeecg.modules.runningdata.service.ITbRunningdataService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 运动记录
 * @Author: jeecg-boot
 * @Date: 2024-10-05
 * @Version: V1.0
 */
@Api(tags = "运动记录")
@RestController
@RequestMapping("/runningdata/tbRunningdata")
@Slf4j
public class TbRunningdataController extends JeecgController<TbRunningdata, ITbRunningdataService> {
    @Autowired
    private ITbRunningdataService tbRunningdataService;

    /**
     * 分页列表查询
     *
     * @param tbRunningdata
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "运动记录-分页列表查询")
    @ApiOperation(value = "运动记录-分页列表查询", notes = "运动记录-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<TbRunningdata>> queryPageList(TbRunningdata tbRunningdata,
                                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                      HttpServletRequest req) {
        QueryWrapper<TbRunningdata> queryWrapper = QueryGenerator.initQueryWrapper(tbRunningdata, req.getParameterMap());
        Page<TbRunningdata> page = new Page<TbRunningdata>(pageNo, pageSize);
        IPage<TbRunningdata> pageList = tbRunningdataService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 运动数据持久化接
     *
     * @param tbRunningdata
     * @return
     */
    @AutoLog(value = "运动记录-运动数据持久化接")
    @ApiOperation(value = "运动记录-运动数据持久化接", notes = "运动记录-运动数据持久化接")
    @PostMapping(value = "/saveRunningData")
    public Result<String> saveRunningData(@RequestBody TbRunningdata tbRunningdata) {
        tbRunningdataService.save(tbRunningdata);
        return Result.OK("添加成功！");
    }

    /**
     * 同步设备到云端
     *
     * @param tbRunningdata
     * @return
     */
    @AutoLog(value = "运动记录-同步设备到云端")
    @ApiOperation(value = "运动记录-同步设备到云端", notes = "运动记录-同步设备到云端")
    @PostMapping(value = "/syncToCloud")
    public Result<String> syncToCloudsyncToCloud(@RequestBody TbRunningdata tbRunningdata) {
        try {
            // 这里假设云端接收数据的接口地址
            URL url = new URL("http://your-cloud-server-url.com/upload");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content - Type", "application/json; charset=UTF - 8");
            con.setDoOutput(true);

            // 将数据转换为字节数组（如果是更复杂的数据结构，可能需要进行序列化操作，如JSON序列化）
            byte[] postData = tbRunningdata.toString().getBytes("UTF-8");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }

            int responseCode = con.getResponseCode();
            System.out.println("同步数据到云端的响应码: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    System.out.println("云端响应内容: " + response.toString());
                    return Result.OK("已同步");
                }
            } else {
                System.out.println("同步数据到云端失败");
                return Result.error("同步失败！");
            }

        } catch (IOException e) {
            return Result.error(e.toString());
        }
    }


    /**
     * 编辑
     *
     * @param tbRunningdata
     * @return
     */
    @AutoLog(value = "运动记录-编辑")
    @ApiOperation(value = "运动记录-编辑", notes = "运动记录-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody TbRunningdata tbRunningdata) {
        tbRunningdataService.updateById(tbRunningdata);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "运动记录-通过id删除")
    @ApiOperation(value = "运动记录-通过id删除", notes = "运动记录-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        tbRunningdataService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "运动记录-批量删除")
    @ApiOperation(value = "运动记录-批量删除", notes = "运动记录-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tbRunningdataService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "运动记录-通过id查询")
    @ApiOperation(value = "运动记录-通过id查询", notes = "运动记录-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<TbRunningdata> queryById(@RequestParam(name = "id", required = true) String id) {
        TbRunningdata tbRunningdata = tbRunningdataService.getById(id);
        if (tbRunningdata == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(tbRunningdata);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param tbRunningdata
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TbRunningdata tbRunningdata) {
        return super.exportXls(request, tbRunningdata, TbRunningdata.class, "运动记录");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TbRunningdata.class);
    }

}
