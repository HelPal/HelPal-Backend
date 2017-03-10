package algo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncodeToken {
	//hash加密	
		public static String Hash_addkey(String str, String type){
			String s=Encrypt(str,type);
	        return s;
		}
		private static String Encrypt(String strSrc, String encName) {
	        MessageDigest md = null;
	        String strDes = null;
	        byte[] bt = strSrc.getBytes();
	        try {
	            md = MessageDigest.getInstance(encName);
	            md.update(bt);
	            strDes = bytes2Hex(md.digest()); // to HexString
	        } catch (NoSuchAlgorithmException e) {
	            System.out.println("  加密失败！");
	            return null;
	        }
	        return strDes;
	    }
	    private static String bytes2Hex(byte[] bts) {
	        String des = "";
	        String tmp = null;
	        for (int i = 0; i < bts.length; i++) {
	            tmp = (Integer.toHexString(bts[i] & 0xFF));
	            if (tmp.length() == 1) {
	                des += "0";
	            }
	            des += tmp;
	        }
	        return des;
	    }
		
	//BASE64编码	
	    private static String encryptBASE64(String key){
	    	byte[] bt = key.getBytes();
	    	return (new BASE64Encoder()).encodeBuffer(bt);
	    	}
	//将 BASE64 编码的字符串 key 进行解码 
	    private static String getFromBASE64(String key) { 
	        if (key == null) return null; 
	        BASE64Decoder decoder = new BASE64Decoder(); 
	        try {
	        	byte[] b = decoder.decodeBuffer(key); 
	            return new String(b); 
	        } catch (Exception e) { 
	            return null; } 
	        }

	//URLEncode编码
	    private static String encryptGBK(String key) throws UnsupportedEncodingException{
	    	String encode = URLEncoder.encode(key, "GBK");
	    	return encode;
	    }
	//URLEncode解码 
	    private static String getFromGBK(String key) throws UnsupportedEncodingException {
	    	String encode = URLDecoder.decode(key, "GBK");
	    	return encode;
	    }
	    
   //加密
	    public static String Encode(String ini_token) throws UnsupportedEncodingException{
	    	String end_token = encryptGBK(encryptBASE64(ini_token));
	    	return end_token;
	    } 
   //解密
	    public static String Getcode(String end_token) throws UnsupportedEncodingException{
	    	String new_ini_token = getFromBASE64(getFromGBK(end_token));
	    	return new_ini_token;
	    } 
}
