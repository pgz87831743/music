package jx.pgz.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class MainTest {

    public static String getInputStreamFromUrl(String urlStr) {
        String base64 = "";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Charsert", "UTF-8");
//	      conn.setRequestProperty("Content-Type", "application/pdf; charset=UTF-8");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            conn.connect();


            byte[] bytes = readStream(conn.getInputStream());
            base64 = Base64.getEncoder().encodeToString(bytes);


            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return base64;
    }

    public static void main(String[] args) {
        String urlStr = "http://fp.baiwang.com/fp/d?d=DDF974148C8072F955A306A4625C528FC5278A7378E6BE2587DA467D0DF9FD3A";
        //pdf转64
        String base64 = getInputStreamFromUrl(urlStr);
        byte[] decode = Base64.getDecoder().decode(base64);
        System.out.println(base64);

        //base64 转pdf
        fileToBytes(decode, "C:\\Users\\pengmaofang\\Desktop\\music\\src\\main\\resources", "dd.pdf");

    }


    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    public static void fileToBytes(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file;
        try {

            file = new File(filePath + fileName);
            if (!file.getParentFile().exists()) {
                //文件夹不存在 生成
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}