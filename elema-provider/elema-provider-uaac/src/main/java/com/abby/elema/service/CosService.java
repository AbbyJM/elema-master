package com.abby.elema.service;

import com.qcloud.cos.model.COSObjectInputStream;

/**
 * author: Abby
 */
public interface CosService {
    /**
     * get the input stream of a object
     * @param key the key of the object
     * @return the input stream
     */
    COSObjectInputStream downloadFile(String key);

    /**
     * obtain the download url of a object
     * @param key the object key,the format likes xxxx/xxx.png
     * @return the download url
     */
    String getDownloadUrl(String key);
}
