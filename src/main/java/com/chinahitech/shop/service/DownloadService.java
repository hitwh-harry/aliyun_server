package com.chinahitech.shop.service;

import com.chinahitech.shop.mapper.TablesMapper;
import com.chinahitech.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class DownloadService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TablesMapper tablesMapper;

    @Value("${filepath}")
    private String filepath;

    public int getUserTimes(String username){
        int times = userMapper.getTimes(username);
        return times;
    }

    public void minusUserTimes(String username){
        userMapper.minusTimes(username);
        return;
    }

    public void downLoadFile(HttpServletResponse response) {
        String[] list = new File(filepath).list();
        File file = new File(filepath + "/" + list[0]);
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            //文件是否存在
            if (file.exists()) {
                //设置响应
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                response.setHeader("Content-Disposition", "attachment;filename=" + list[0]);
                response.setCharacterEncoding("UTF-8");
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                while (bis.read(buffer) != -1) {
                    os.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        List<Map<String,Object>>l= tablesMapper.getAll("orders");
//        for(int i=0;i<l.size();i++){
//            Map<String,Object> m=l.get(i);
//            for (String key:m.keySet()){
//                System.out.println("key= "+key+" and value= "+m.get(key));
//            }
//            System.out.println();
//        }
    }

    public String getFileName() {

        String[] list = new File(filepath).list();
//        System.out.println(list[0]);

        return list[0];
    }
}
