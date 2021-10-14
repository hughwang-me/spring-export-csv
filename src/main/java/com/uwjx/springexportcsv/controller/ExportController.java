package com.uwjx.springexportcsv.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/10/14 13:36
 */
@RestController
@RequestMapping("export")
@Slf4j
public class ExportController {

    @GetMapping(value = "csv")
    public void exportCsv(HttpServletRequest request , HttpServletResponse response){
        try {
            String fileName = "导出的文件.csv";
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            File file = new File("/Users/wanghuan/Documents/tanli_workspace/tanli-levo-uniapp/push.sh");
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            OutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int read = bufferedInputStream.read(bytes);
            while (read != -1){
                outputStream.write(read);
                read = bufferedInputStream.read(bytes);
                log.warn("read --- 1024");
            }
            log.warn("read over");
            fileInputStream.close();
            bufferedInputStream.close();
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
