package top.liyf.springboot.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.liyf.springboot.demo.result.ResultBean;
import top.liyf.springboot.demo.result.ResultCode;

import java.io.*;

/**
 * @author liyf
 * @description
 * @date Created in 2018\11\6
 */
@Controller
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping("/file")
    public String file() {

        return "/uploadFile";
    }

    /**
     * 功能描述: 文件上传具体实现方法
     * 
     * @param [file]
     * @return top.liyf.springboot.demo.result.ResultBean<java.lang.String>
     * @author liyf
     */
    @RequestMapping("/upload")
    @ResponseBody
    public ResultBean<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            logger.info("上传失败，因为文件是空的.");
            return new ResultBean<>(ResultCode.UPLOAD_FAIL, "文件为空");
        }

        String path = "C:/test";
        String fileName = file.getOriginalFilename();
        File dest = new File(path + "/" + fileName);

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //保存文件
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultBean<>(e);
        }
        return new ResultBean<>();

    }
}
