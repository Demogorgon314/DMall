package com.deepdream.dmall.thirdparty;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class DmallThirdPartyApplicationTests {
    @Autowired
    OSS ossClient;
    @Test
    public void testUpload() throws FileNotFoundException {
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "oss-cn-shenzhen.aliyuncs.com";
//        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
//        String accessKeyId = "LTAI4G1R4HTorWjiz48qg9Ko";
//        String accessKeySecret = "wslKxmQgCr0mEZknHxV3H08eP1vbLy";
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        // 上传文件流。
        InputStream inputStream = new FileInputStream("/Users/wangkai/Pictures/pap.er/OgB2ZHA49bU.jpg");
        ossClient.putObject("dmall-springcloud", "hhh1.jpg", inputStream);
//
//        // 关闭OSSClient。
//        ossClient.shutdown();
    }
    @Test
    void contextLoads() {
    }

}
