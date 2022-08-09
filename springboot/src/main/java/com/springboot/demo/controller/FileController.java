package com.springboot.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.springboot.demo.Config.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author LiZeyuan
 * @date 2022/4/13 15:59
 */
@RestController
@RequestMapping("/files")

@CrossOrigin(origins = "http://localhost:9876/", maxAge = 3600)

public class FileController {
    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost";

    /**
     * 文件上传（前端传到后端）
     */

    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        //获取源文件的名称
        String originalFilename = file.getOriginalFilename();
        //自定义文件前缀（唯一标识，防止重复覆盖）
        //1:当前时间 多线程不能保证
        System.currentTimeMillis();
        //2：UUID
        String uuid = IdUtil.fastSimpleUUID();
        //把文件路径设置到当前项目的files文件夹下，文件名就是originalFilename
        //获取上传路径
        String rootFilePath = System.getProperty("user.dir")
                + "/springboot/src/main/resources/files/" + uuid + "_" + originalFilename;
        //使用 hu-tool工具类的IO工具类把冲前端传过来的文件写入到rootFilePath
        FileUtil.writeBytes(file.getBytes(), rootFilePath);

        //返回结果url
        return Result.success(ip + ":" + port + "/files/" + uuid);
    }

    /**
     * 文件下载（后端传到前端）
     */

    @GetMapping("/{uuid}")
    public void getFiles(@PathVariable String uuid, HttpServletResponse response) {
        OutputStream outputStream;  //new Obj of outputStream

        //定义文件上传根路径
        String bathPath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/";
        //获取所有文件名称
        List<String> listFileNames = FileUtil.listFileNames(bathPath);
        //寻找跟参数一致的文件
        String fileName = listFileNames.stream().filter(name -> name.contains(uuid)).findAny().orElse("");
        //如果找到了 就说明文件是可以下载的 通过读取文件路径获取字节流
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(bathPath + fileName);
                outputStream = response.getOutputStream(); //通过输出流返回文件
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }
}