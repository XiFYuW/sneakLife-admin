package com.sneaklife.ut.keyless;

public class HexUtil {

    /**
     * 将普通字符串用16进制描述
     */
    public static String strToHex(String str) {
        byte[] bytes = str.getBytes();
        return bytesToHex(bytes);
    }

    /**
     * 将16进制描述的字符串还原为普通字符串
     */
    public static String hexToStr(String hex) {
        byte[] bytes = hexToBytes(hex);
        return new String(bytes);
    }

    /**
     * 16进制转byte[]
     */
    public static byte[] hexToBytes(String hex) {
        int length = hex.length() / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            // byte:8bit=4bit+4bit=十六进制位+十六进制位
            String tempStr = hex.substring(2 * i, 2 * i + 2);
            bytes[i] = (byte) Integer.parseInt(tempStr, 16);
        }
        return bytes;
    }

    /**
     * byte[]转16进制
     * */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            //byte:8bit,int:32bit;高位相与.
            int tempI = aByte & 0xFF;
            String str = Integer.toHexString(tempI);
            if (str.length() < 2) {
                // 长度不足两位，补齐：如16进制的d,用0d表示。
                sb.append(0).append(str);
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }
}
