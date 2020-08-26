package com.xuecheng.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexlou on 2020/7/9.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtTest {
    //创建Jwt令牌
    @Test
    public void testCreateJwt(){
        //秘钥库文件
        String keystore = "xc.keystore";
        //秘钥库密码
        String keystore_password = "xuechengkeystore";
        //秘钥库文件路径
        ClassPathResource classPathResource = new ClassPathResource(keystore);
        //秘钥的别名
        String alias = "xckey";
        //秘钥的访问密码
        String key_password = "xuecheng";
        //秘钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource,keystore_password.toCharArray());
        //密钥对（公钥和私钥）
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, key_password.toCharArray());
        //获取私钥
        RSAPrivateKey pairPrivate = (RSAPrivateKey) keyPair.getPrivate();
        //令牌的内容
        Map<String,String> body = new HashMap<>();
        body.put("name","louyun");
        String s = JSON.toJSONString(body);
        //生成jwt令牌
        Jwt jwt = JwtHelper.encode(s, new RsaSigner(pairPrivate));
        //生成jwt编码
        String jwtEncoded = jwt.getEncoded();
        System.out.println(jwtEncoded);
    }
    //校验jwt令牌
    @Test
    public void testVerify(){
        //公钥
        String publicKey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiVixPM5q+rB8/nuP/FXhPSzrKTfzH98rPDA5N7hz35oJb0dQW76MKM1TAMhdLGzhmjLVKaG1hWuYPFfFcOB4FflTHy9JOCo1Eu0hT5spxjPXcsLtLZPEMpftTW7iIhIXzR5rHsvMN8gltOCrJNW9PkjkQpt3rG0R+zYRyu8Qt28+BKGxcyNerz4JODev9SlN0LcgUVVVfjz1FPsOB+Sjrwl/ytHr0qlClGouhJLyYjoDzQuFTLvEOsWoRtVmbjNFqMgSqSHPPhmogCsmpDBE/iXyFRY1pffxSaYGWrHio9uwF3gm56Z3LRH9eTkerBx9tOXrAkJfRnMP01GiOSKhWwIDAQAB-----END PUBLIC KEY-----";
        //jwt令牌
        String token ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoibG91eXVuIn0.KJY80bPZEk66xEuxZJU3msqMVaa8NADjBxfmHItyX1RrThKXI-xen5kEXxW3RITVq86EPcB1rkTA17vMzLP1hTBB9jT44LS4y_GS9pQrTuRgsoFGVFBkzkML-3wC12tp2MUUcA2FZtmkOo4mGS8JasLJZwlgn5YABl2Q-msX7pG_dX4ZPmtE7gVGKla2Id5PfvzSabaRTqDxEPKBo4nfhvrHufpjePt8_YAOkDjsg6KhwNjs987eIHgCmnJ01YRid19iM0GUrKe8hjm4QzQt8xnwL1I-l6i24khrAeMtUifOk-i0W2NWXMlP2yDOmTPI8SqC3X4bSJBmbR5O2asb7g";
        //校验令牌
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publicKey));
        //拿到jwt令牌中的自定义内容
        String claims = jwt.getClaims();
        System.out.println(claims);

    }
}
