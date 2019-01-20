package com.abby.elema.service.impl;

import com.abby.elema.cos.CosProperties;
import com.abby.elema.service.CosService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.GetObjectRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URL;
import java.util.Date;

/**
 * author: Abby
 */
@Service
public class CosServiceImpl implements CosService {
    @Resource
    private COSClient cosClient;

    @Resource
    private CosProperties cosProperties;

    @Override
    public COSObjectInputStream downloadFile(String key) {
        String bucketName=cosProperties.getBucket();
        GetObjectRequest request=new GetObjectRequest(bucketName,key);
        COSObject object=cosClient.getObject(request);
        return object.getObjectContent();
    }

    @Override
    public String getDownloadUrl(String key) {
        String bucketName=cosProperties.getBucket();
        GeneratePresignedUrlRequest request=new GeneratePresignedUrlRequest(bucketName,key,HttpMethodName.GET);
        Date expiredIn=new Date(System.currentTimeMillis()+1000L*60L*60L*48L);
        request.setExpiration(expiredIn);
        URL downloadUrl=cosClient.generatePresignedUrl(request);
        return downloadUrl==null?null:downloadUrl.toString();
    }
}