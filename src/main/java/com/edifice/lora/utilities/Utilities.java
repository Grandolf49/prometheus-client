package com.edifice.lora.utilities;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.security.MessageDigest;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;


/**
 * 
 * @author prashantj
 * This class would act as a utility class containint all the statric functions that are neede to be stand alone functions
 * 
 */


public class Utilities {

	
	public static final Calendar utcTimeZone = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
	public static final String dateformatDefault = "MMM dd, YYYY HH:mm";
	
	

	
	public static String getMonthName(int month){
	    //String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		return monthNames[month];
	}

	
	public static List str2Arr(String strToSplit , String delimiter)
	{
		List<String> items = Arrays.asList(strToSplit.split(delimiter));
		
		return items;
	}
	
	
	/**
	 * 
	 * @param strToEncrypt
	 * @return
	 */
	public static String encrypt(String strToEncrypt )
	{
		/**
		 * have to implement a encruption algo to encryot the string 
		 * for tem p use urlencoded
		 */
		
		return URLEncoder.encode(strToEncrypt);
	}
	
	
	public static String decode(String strToEncrypt )
	{
		/**
		 * have to implement a encruption algo to encryot the string 
		 * for tem p use urlencoded
		 */
		
		return URLDecoder.decode(strToEncrypt);
	}
	
	public static Date getCurrentDate()
	{
		
		java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}
	
	public static String getCustomizeDate(Timestamp ts,String usrTimezone,String dateFrmt)
	{
		 
			String timezone = "" , customizeDatStr = "",dateformat = "" ;
			
			if(usrTimezone == null || usrTimezone.equals(null))
			{
				timezone = utcTimeZone.getTimeZone().getID();
			}
			else
			{
				timezone = usrTimezone ;
			}
			
			if(dateFrmt == null || dateFrmt.equals(null))
			{
				dateformat = dateformatDefault ;
			}
			else
			{
				dateformat  = dateFrmt ;
			}

			//Timestamp ts = rsUser.getTimestamp("revision_date",tzUTC);
			DateTime dt = new DateTime(ts);
			DateTimeZone dtZone = DateTimeZone.forID(timezone);
			DateTime dtus = dt.withZone(dtZone);
			org.joda.time.format.DateTimeFormatter dtfOut = DateTimeFormat.forPattern(dateformat);
			
		
			return dtfOut.print(dtus).toString() ;
	}
	
	public static String removeLastCharacter(String str)
	{
		if (str == null || str.length() == 0) {
	        return str;
	    }
	    return str.substring(0, str.length()-1);
		
	}
	


	public static String decryptAES(String encrypted) 
	{
		/*final String seckey = "12345";
		final String initVector = "12345";
		byte[] key; 
		MessageDigest sha = null;
		try {
			key = seckey.getBytes("UTF-8");
	        sha = MessageDigest.getInstance("SHA-1");
	        key = sha.digest(key);
	        key = Arrays.copyOf(key, 16);
	        
	        Key key1 = new SecretKeySpec(seckey.getBytes("UTF-8"),"AES" );

	        
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			
			Random rand = new SecureRandom();
			byte[] bytes = new byte[16];
			rand.nextBytes(bytes);
			IvParameterSpec ivSpec = new IvParameterSpec(bytes);

			
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec,ivSpec);
			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
		*/
		
		try {
			IvParameterSpec iv = new IvParameterSpec(Constants.VERCTOR_INIT.getBytes("UTF-8"));
			Key key = generateKey();
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
		
	}

	public static String encryptAES(String value) 
	{
		try 
		{
			Key key = generateKey();
			
			IvParameterSpec iv = new IvParameterSpec(Constants.VERCTOR_INIT.getBytes("UTF-8"));

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Base64.encodeBase64String(encrypted);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	
	private static Key generateKey() throws Exception
	{
		byte[] keyAsBytes;
		MessageDigest sha = null;

		keyAsBytes = Constants.CIPHER_SECRET.getBytes(Constants.UNICODE_FORMAT);
		
		sha = MessageDigest.getInstance("SHA-1");
		keyAsBytes = sha.digest(keyAsBytes);
		keyAsBytes = Arrays.copyOf(keyAsBytes, 16);
        
		
		Key key = new SecretKeySpec(keyAsBytes, "AES");
		return key;
	}
	

}
