package com.uwjx.springexportcsv.controller;

import com.alibaba.fastjson.JSON;
import com.uwjx.springexportcsv.bean.ReqBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author wanghuan
 * @link https://huan.uwjx.com
 * @date 2021/10/14 13:36
 */
@RestController
@RequestMapping("export")
@Slf4j
public class ExportCsvController {

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


    @GetMapping(value = "csv2")
    public ResponseEntity<byte[]> exportCsv2(){
        try {
            String fileName = "导出的文件.csv";
            File file = new File("/Users/wanghuan/Documents/tanli_workspace/tanli-levo-uniapp/push.sh");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName,"utf-8"));
            return new ResponseEntity<>
                            (FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "csv3")
    public ResponseEntity<byte[]> exportCsv3(@RequestBody ReqBean reqBean){
        try {
            log.warn("JSOS:{}"  , JSON.toJSONString(reqBean));
            String fileName = "导出的文件.csv";
            File file = new File("/Users/wanghuan/Documents/tanli_workspace/tanli-levo-uniapp/push.sh");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName,"utf-8"));
            return new ResponseEntity<>
                    (FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "csv4")
    public ResponseEntity<byte[]> exportCsv4(@RequestBody ReqBean reqBean){
        try {
            log.warn("JSOS:{}"  , JSON.toJSONString(reqBean));

            String filePath = "/Users/wanghuan/Documents/rx_workspace/spring-export-csv/a.csv";

            FileOutputStream fos = new FileOutputStream(filePath);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");

            CSVFormat csvFormat = CSVFormat.Builder.create()
                    .setHeader("head1" , "head2")
                    .build();
            CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);

            for (int i = 0; i < 10; i++) {
                csvPrinter.printRecord("张三"+i, 20+i);
            }

            csvPrinter.flush();
            csvPrinter.close();
            fos.close();
            osw.close();

            String fileName = "导出的文件.csv";
            File file = new File(filePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName,"utf-8"));
            return new ResponseEntity<>
                    (FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "csv5")
    public ResponseEntity<byte[]> exportCsv5(){
        try {
            String filePath = "/Users/wanghuan/Documents/rx_workspace/spring-export-csv/a.csv";

            FileOutputStream fos = new FileOutputStream(filePath);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");

            CSVFormat csvFormat = CSVFormat.Builder.create()
                    .setHeader("head1" , "head2")
                    .build();
            CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);

            for (int i = 0; i < 10; i++) {
                csvPrinter.printRecord("张三"+i, 20+i);
            }

            csvPrinter.flush();
            csvPrinter.close();
            fos.close();
            osw.close();

            String fileName = "导出的文件.csv";
            File file = new File(filePath);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName,"utf-8"));
            return new ResponseEntity<>
                    (FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
