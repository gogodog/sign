package com.pw.sign.common.utils;

import org.springframework.util.Base64Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class ImgUtils {

    private static final String BASE64_IMG_PREFIX = "data:image/%s;base64,";

    public static String tranUrlToBase64String(String url) {
        try {
            URL urlImg = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlImg.openConnection();
            httpURLConnection.addRequestProperty("User-Agent", "Mozilla / 4.76");
            InputStream is = httpURLConnection.getInputStream();
            //定义字节数组大小；
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int rc = 0;
            while ((rc = is.read(buffer, 0, 100)) > 0) {
                byteArrayOutputStream.write(buffer, 0, rc);
            }
            buffer = byteArrayOutputStream.toByteArray();
            return Base64.getEncoder().encodeToString(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBase64FromUrl(String url) {
        String suffix = getSuffix(url);
        String base64Img = tranUrlToBase64String(url);
        return suffix + base64Img;
    }

    private static String getSuffix(String url) {
        int lastIndexOf = url.lastIndexOf(".");
        if (lastIndexOf <= 0) {
            return "data:image/jpeg;base64,";
        }
        String urlSuffix = url.substring(lastIndexOf + 1);
        if ("jpg".equals(urlSuffix)) {
            return String.format(BASE64_IMG_PREFIX, "jpeg");
        }
        return String.format(BASE64_IMG_PREFIX, "png");
    }
}


