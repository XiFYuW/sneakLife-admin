package com.sneaklife.pkv;

import com.sneaklife.SneakLifeAdminApplication;
import com.sneaklife.ut.keyless.Base64Util;
import com.sneaklife.ut.keyless.RSAUtil;
import com.sneaklife.ut.spring.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/17 18:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SneakLifeAdminApplication.class)
@ContextConfiguration
public class PkvTest {

    @Autowired
    private RsaPKV rsaPKV;

    @Autowired
    private AesPKV aesPKV;

    @Autowired
    private AilSmsPKV ailSmsPKV;

    @Test
    public void getRsaPkv() {
        System.out.println(rsaPKV.getKeyAlgorithm());
    }

    @Test
    public void getAesPkv() {
        System.out.println(aesPKV.getKeyAlgorithm());
    }

    @Test
    public void getAilSmsPkv() {
        System.out.println(ailSmsPKV.getDefaultConnectTimeout());
    }

    @Test
    public void getSpringContext() {
        RsaPKV rsaPKV = SpringContextUtil.getBean(RsaPKV.class);
        System.out.println(rsaPKV.getKeyAlgorithm());
    }

    @Test
    public void tes() {
        String s1 = "dOsoGscULAjaop8nr5rFhyUFzFhFF7v8qBff04REGsCklAS2bau8Bb9T49Ap2LDcxpRa4vJlQA1L+8J5P10+bZafCpb3w6mv9K5O/4LCHoB2dBU19JCp2t5VW7j1tJOgNs5bSkbPSasEMXU32vN0eopT8kfG+T1r3rtfXF4yNBxHPJKBF1iCeaNquVv1RvUr9MVoWDXMKON545vPRRdHyI3cJfuE2bpQ9M/3NEMtO9ilkpz3+gMVodoQdHz6Zv26eNCboFC1OjkFaiPvZhhqDSQB2/aPpxJhWuDoIKS/SHcWkUocYTmQ+0Wfxemfm83MReD3WDOUwXfAP7OF3ecOtGyM+WwGfxZKVNiUH2UXh5LF61gFlMQWSE1U4+jWA52n4v8Js751xVwbB3odsfk44dLAnxofnBc82pxmqbSIquGhN9jMkA9JqeKQFgI0lWShhYNjvj2B++WWGeJdmEEKHa4Kdoq9n1akslfxN5cMLL9KyNspB4Vhfowz4LHf7O43TpwYWf3/Wr0H98jyqM1VVJ2u8TALtwzaEoJHY8EWHxvvgiEREhcd0AY8UrLG6FVs9ya7tO/M0uSKmpMPb9zj7q40PouXU4x+QgR21Q34+18F9dKxePR1e7RYrr942keqMZv9T/GvEb4P4EWqsHWzA+PVfaTBZLXtne8O4WKDKA=";
        String s2 = "4f866fa4a373b1e2cc35110608d6c132d1cfb6901c5e3445d17346abb5036f47e66ae27c18194f831cfcc11e7a0847068e05d0ab62bb74d81a398b740702bc32df2e0d3c3aea2961cb198deb0900c5262d1fa3450d5046dbf576741a031ccca57ab971b064d5e92c265542f66f136bc327c3b8f77ca2c2ba28b3eb67c06e46c8";
        byte[] ba = new byte[0];
        try {
            ba = Base64Util.base64Decode(s1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ba.length);
    }

    @Test
    public void fg() throws Exception {
//        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKhnFOxdVHN2X8zFni2YfKwHlhrx+luV2JErdF0AM+z5vkeK4GQqMpl46wqcYalOHTfCZKGf6jKbRBEYbMq1iI/kZ+xAtznzmPY3INE4/GpV/iFRADn/t7B3ZsJIO0WTxxbIEJQ2P2Ty6BPvj79uLkLSG3G3YuqMO9C3Vdq6hAthAgMBAAECgYBuXRADT6729I8YHjLp4tNh0p73DbprpyqFCRt7j5R4K7weGs+wFU+P8QTosU/3qu/PCSD2I11SyRq0sXMq7gmOYxQMI1XT4E/INwyXbZW1ly04rPViw8IQTi1lTxuRPjWbqDc1PHJ9qL1A7he3KzggdpaxSUbLTENfrqPxSbuYeQJBANyx6Cj7yYk4J7+ZMUUIIksECOhOf+tTcefB5x1dS0ZfHloW6ggOLRQ3DLOib+cO9HMmMPioTi1X1bla4PLM6wMCQQDDV6fB8ChjldbUlDv1vE5bC1uLyfCTLTq1kOE+e8uuabAVRGNKDYndsldt8WdmUgHh7/LqAEjh+uF1JK/onJDLAkAsrSs1WLtUtvR8+fMaaWofy4JOHM4SgoL1irBRpng9l7XaQPdBiDUh+X7twn1qxgFAakPRoqiozzYp3rbZEBinAkA3f+QN2enfwWa0JxmSC9pHS8bKxKkxZ5xSkcMtOYb5GtYIdNGw7H/LGSx19Cxoicw3ITFnAoWyjtEXs0C3WiRZAkAejTaEfNokVqN2TpbIhXGgYOI3YdGXC+wuhwQ4mppoksJjmQ0ARKrYPjhZ2a1Hj9bMpJ0D2B84byqBh+JpkHnL";
//        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoZxTsXVRzdl/MxZ4tmHysB5Ya8fpbldiRK3RdADPs+b5HiuBkKjKZeOsKnGGpTh03wmShn+oym0QRGGzKtYiP5GfsQLc585j2NyDROPxqVf4hUQA5/7ewd2bCSDtFk8cWyBCUNj9k8ugT74+/bi5C0htxt2LqjDvQt1XauoQLYQIDAQAB";
//        System.out.println("privateKey: " + privateKey);
//        System.out.println("publicKey:" + publicKey);
//        System.err.println("公钥加密——私钥解密");
//        String source = "{\"data\":\"vgDT0K+6yb4lkkFhfwTjeYYvR3YvhPkmAo4krm81+Qakql7zAOJJwUBFqJ4hHOgImpdaUeMQGkhLg0zpWo7y8UyfqWwwTdRujwNAH0wFqCFuYxQbeNhJisCXjso+m7P2Upl2KXzOjjJXJ2BYsevQPpGz/wkcjjZSn3em6TcykXguKXCylwuUsUqMPd06BBnEzOQPip0cYUN7VgAewURYlA==\",\"token\":\"VDBNlLK1syp8WIQRnZuwy/6n2Huzsiss4CSjYkq/XlkhXYshddmiIYyzYkODBL8av9wGR3JlkowjN3/u2vn0MzQqypcorPQpFyMsOa3h3NWNGEt0UfLJ2DnF8XAwIRpvRPrfUv26BdkrNhKiquRMAHxHZ0QFSY7zHfte6V9CsfQ=\"}";
//        System.out.println("加密前文字：" + source);
//        byte[] data = source.getBytes();
////        byte[] encodedData = RSAUtil.encryptByPublicKey(data, publicKey);
//        byte[] encodedData = Base64Util.base64Decode("xcnTyEY6o/peNWjwlXtD3BwQhJAaqGttqd5mg66LyxF6QZ/Fxt3JbqU8ao/7fojjS/j+g5byhiZ/Yndd0YVOKvBAz49oC5UrqG4M85pc5qh06wxP3dFtZLArZKgYClU/0dkSR0VYzGVSZf8hFzbEjIm2oz5ViJBHcCvvRtQCbDUf/kw6NTXC7kSbqJoOyYyslzeYTw7qbhE743+olR4wuizkqahSiYOGN8jIS5C1yq2ykxOG4l8jSD0VIUL4SGnG4K/cgLqbsZ1pnWLIezkPUcLzjUB7r5AeJJBSQe/JBcEJPRcEL9XLRN6Qtu8rnAVer93KD9JxWvJEn5m6p4Z/LNsB7Ref75jnijDOawOElRSdE3hGqDFxHHpG8Zsi/c6RxxcKpKk1ja4OVuvVPTZC6ZBZZsE/eov+4XHX9oBy5lMB1lrciu/rTcilWB/BKrU4wx4utF0uv6plN784K7cFwApJnyP/hsThMyNrDsI39MxR+eni50qedYKwpWJLI/QkN1eI8uIx3iGgvXELznDd/wlSPTJFzQgcYLyyzQgVPFGL4DuKjdI2kaVZmMWk0VtKXXsIZnp/rcP5B4xuYzH+gvvL5rjZhAXTa8aKB+XsQMXubuwDSgjoWMCUizD/xuXZfeyR3NI9Rrrg5kT0J89rQx4aVsL/BeIXAHu61Ba31g==");
////        System.out.println("加密后文字：" + Base64Util.base64Encode(encodedData));
//        byte[] decodedData = RSAUtil.decryptByPrivateKey(encodedData, privateKey);
//        String target = new String(decodedData);
//        System.out.println("解密后文字: " + target);
        System.out.println(510 % 128);
    }

}
