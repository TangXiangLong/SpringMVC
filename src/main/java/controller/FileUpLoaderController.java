package controller;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class FileUpLoaderController {


    /*SpringMVC文件上传*/
    @RequestMapping("/fileupload2")
    public String fileupload2(HttpServletRequest request, MultipartFile upload11) throws Exception {
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //说明上传文件项
        //获取上传名称
        String filename = upload11.getOriginalFilename();
        //把文件名称设置为唯一值
        String uuid =  UUID.randomUUID().toString().replaceAll("-","");
        filename =   uuid + "_" + filename;
        //完成文件上传
        upload11.transferTo(new File(path, filename));
        return "success";
    }



    /*跨服务器文件上传*/
    @RequestMapping("/fileupload3")
    public String fileupload3(MultipartFile upload11) throws Exception {

        //定义上传服务器路径
        String path = "http://localhost:9090/uploads/";

        //说明上传文件项
        //获取上传名称
        String filename = upload11.getOriginalFilename();
        //把文件名称设置为唯一值
        String uuid =  UUID.randomUUID().toString().replaceAll("-","");
        filename =   uuid + "_" + filename;
        //完成文件上传，跨服务器
        //创建客户端对象
        Client client = Client.create();
        //和图片服务器进行连接
        WebResource webResource = client.resource(path+filename);
        //上传文件
        webResource.put(upload11.getBytes());
        return "success";
    }


    /*传统方式文件上传*/
    @RequestMapping("/fileupload1")
    public String fileupload1(HttpServletRequest request) throws Exception {
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> items =  upload.parseRequest(request);
        //遍历
        for(FileItem item : items){
            if(item.isFormField()){
                //说明普通表单项
            }
            else {
                //说明上传文件项
                //获取上传名称
                String filename = item.getName();
                //把文件名称设置为唯一值
                String uuid =  UUID.randomUUID().toString().replaceAll("-","");
                filename =   uuid + "_" + filename;
                //完成文件上传
                item.write(new File(path,filename));
                //删除临时文件
                item.delete();
            }
        }
        return "success";
    }





}
