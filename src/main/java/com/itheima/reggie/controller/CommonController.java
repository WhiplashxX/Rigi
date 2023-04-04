package com.itheima.reggie.controller;

import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author HuangQX
 * @create 2023-03-30-10:04
 * 文件上传和下载
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成会会自动删除
        String name1 = file.getOriginalFilename();//"abc.jpg"
        String suffix = name1.substring(name1.lastIndexOf("."));


        //使用uuid
        String name = UUID.randomUUID().toString()+suffix;

        //创建一个目录对象
        File dir = new File(basePath);
        //判断当前目录是否存在
        if(!dir.exists()){
            dir.mkdir();
        }
        try {
            //将临时文件转存
            file.transferTo(new File(basePath+ name));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(name);
    }


    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try {
            //输入流，读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath+name));
            //输出流，将文件写回浏览器，在浏览器展示
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len=0;
            byte[] bytes = new byte[1024];
            while((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            outputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
