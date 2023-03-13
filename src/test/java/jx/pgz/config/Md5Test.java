package jx.pgz.config;


import org.apache.shiro.crypto.hash.SimpleHash;

public class Md5Test {
    public static void main(String[] args) {
        //要加密的字符串
        String password = "123456";
        //盐
        String salt = "ywz";
        //散列次数
        Integer hashIterations = 2;
        //4.利用SimpleHash来设置md5(上面三种都可以通过这个来设置，这里举例加盐加散列次数的)
        //第一个参数是算法名称，这里指定md5，第二个是要加密的密码，第三个参数是加盐，第四个是散列次数
        SimpleHash hash = new SimpleHash("md5", password, salt, hashIterations);
        System.out.println(hash);
    }
}
