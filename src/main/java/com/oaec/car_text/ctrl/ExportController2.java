package com.oaec.car_text.ctrl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import com.oaec.car_text.entity.Log;
import com.oaec.car_text.entity.Logl;
import com.oaec.car_text.service.LogService;
import com.oaec.car_text.entity.User;
import com.oaec.car_text.sevice.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController

public class ExportController2 {
        @Autowired
        UserService userService;
        @Autowired
        LogService logService;
@GetMapping("/exportExcel2")

public void exportExcel(HttpServletRequest request, HttpServletResponse response) {

        try {

        HSSFWorkbook wb = new HSSFWorkbook();

// 根据页面index 获取sheet页

        HSSFSheet sheet = wb.createSheet("人员基本信息");
                HSSFRow fd=sheet.createRow(0);//创建第一行
                HSSFCell cell=fd.createCell(0);
                cell.setCellValue("姓名");
                fd.createCell(1).setCellValue("手机号码");
                fd.createCell(2).setCellValue("用户类型");
                fd.createCell(3).setCellValue("家庭地址");

                List<User> userList = userService.queryAll();
                int i=0;
                for (User user : userList) {

// 创建HSSFRow对象

        HSSFRow row = sheet.createRow(i+1);

// 创建HSSFCell对象 设置单元格的值

        row.createCell(0).setCellValue(user.getUsername()+" ");

        row.createCell(1).setCellValue(user.getUserphone()+" ");

        row.createCell(2).setCellValue(user.getUserstatus()+"  ");

        row.createCell(3).setCellValue(user.getUseraddress()+" ");

        i++;

        }

                sheet.autoSizeColumn((short)0);
                sheet.autoSizeColumn((short)1);
                sheet.autoSizeColumn((short)2);
                sheet.autoSizeColumn((short)3);
// 输出Excel文件

        OutputStream output = response.getOutputStream();

        response.reset();

// 设置文件头

        response.setHeader("Content-Disposition",

        "attchement;filename=" + new String("人员信息.xls".getBytes("gb2312"), "ISO8859-1"));

        response.setContentType("application/msexcel");

        wb.write(output);

        wb.close();

        } catch (Exception e) {

        e.printStackTrace();

        }

        }


        @GetMapping("/exportExcel3")

        public void exportExcel1(HttpServletRequest request, HttpServletResponse response) {

                try {

                        HSSFWorkbook wb = new HSSFWorkbook();

// 根据页面index 获取sheet页

                        HSSFSheet sheet = wb.createSheet("订单基本信息");
                        HSSFRow fd1=sheet.createRow(0);//创建第一行
                        HSSFCell cell=fd1.createCell(0);
                        cell.setCellValue("订单编号");
                        fd1.createCell(1).setCellValue("姓名");
                        fd1.createCell(2).setCellValue("用户电话");
                        fd1.createCell(3).setCellValue("品牌");
                        fd1.createCell(4).setCellValue("数量");
                        fd1.createCell(5).setCellValue("车辆单价（万） ");
                        fd1.createCell(6).setCellValue("总价格（万） ");

                        List<Log> querylog = logService.querylog();
                        int i=0;
                        for (Log log : querylog) {
                        if (log.getLogstatus().equals("已支付")) {
// 创建HSSFRow对象
// 创建HSSFCell对象 设置单元格的值
                                List<Logl> logls = logService.querylog_l(log.getLogid());
                                for (Logl logl : logls) {

                                        HSSFRow row = sheet.createRow(i + 1);

                                        row.createCell(0).setCellValue(log.getLogid() + " ");

                                        row.createCell(1).setCellValue(log.getLogusername() + "  ");

                                        row.createCell(2).setCellValue(log.getLoguserphone() + " ");

                                        row.createCell(3).setCellValue(logl.getCarbrand() + "  ");

                                        row.createCell(4).setCellValue(logl.getCarnum() + " ");

                                        row.createCell(5).setCellValue(logl.getCarprice() + " ");

                                        row.createCell(6).setCellValue(log.getLogprice() + " ");

                                        i++;
                                }
                        }

                        }
                        sheet.autoSizeColumn((short)0);
                        sheet.autoSizeColumn((short)1);
                        sheet.autoSizeColumn((short)2);
                        sheet.autoSizeColumn((short)3);
                        sheet.autoSizeColumn((short)4);
                        sheet.autoSizeColumn((short)5);
                        sheet.autoSizeColumn((short)6);

// 输出Excel文件

                        OutputStream output = response.getOutputStream();

                        response.reset();

// 设置文件头

                        response.setHeader("Content-Disposition",

                                "attchement;filename=" + new String("订单信息.xls".getBytes("gb2312"), "ISO8859-1"));

                        response.setContentType("application/msexcel");

                        wb.write(output);

                        wb.close();

                } catch (Exception e) {

                        e.printStackTrace();

                }

        }

}