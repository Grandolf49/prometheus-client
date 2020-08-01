
package com.edifice.lora.utilities;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


import org.apache.tomcat.util.codec.binary.Base64;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/***
 * 
 * @author prashantj
 * we will be using jwt for creating and autenticating webtokents 
 * this utitlity class will act as awrapper for docreview serveice for tokens
 */

public class WebToken {

	private final String secret =  "{{secret used for signing}}"; //this is the key which w ill be used to hash/Encrypt the string to contaqin web token parameters 
	private  String issuer;
	private long iat,exp;
	
	//jjwt
	private final String MAGIC_KEY = "ProgramSecretKey12";
	private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	private Claims claims ;
	    
	public WebToken()
	{
		issuer = "https://eddifici.com/";
		
		iat = System.currentTimeMillis() / 1000L; // issued at claim 
		exp = iat + 60L; // expires claim. In this case the token expires in 60 seconds

	}
	
	
	/*usinng jjwt
	public String createToken(UserProfile userDto,boolean neverExpire)
    {
        String jwtToken = "";
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            /**
             * 1000 *60 *60  == 1hour
				1000 *60 *24  == 1day
				
				1 day 	= 24 hrs
				1 hr 	= 60 min
				1 min 	= 60 sec
				1 sec   = 1000 mill

             *
            
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("GMT"));
            
            
            Date expireDate = null ;
            if(neverExpire)
            {
            	//set to 1 week
            	// expireDate = new Date(now.getTime() + 14 *24*3600*1000); // 2 week
            	 
                 //System.out.println("date after 5 months : " + getDate(cal));   
            	 // expireDate = new Date(now.getTime() + 180 *24*3600*1000); // 6 mnths
            	 
            	try {
            		 cal.add(Calendar.MONTH, 3);
					 expireDate = new SimpleDateFormat("dd/MM/yyyy").parse(getDate(cal));
					 System.out.println("3 months = "+expireDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					expireDate = new Date(now.getTime() + 2 * 3600*1000); // 2hours
					System.out.println("error while calculating expiredate, in catch = 2 hours = "+expireDate);
					e.printStackTrace();
				} 
            	 
            	      
        	 
            }
            else
            {
            	 expireDate = new Date(now.getTime() + 2 * 3600*1000); // 2hours
            }
            	
         try {
        	 
        	 //create base64 usr  string
        	 ObjectMapper mapper = new ObjectMapper();
        	 
   		  	mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
   		  	
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY) ;
 
   		  	byte[] byteInst = null ;
			Base64 codec = new Base64();

			byteInst = mapper.writeValueAsBytes(userDto);
			String usrStr = codec.encodeBase64String(byteInst); //convert byte to base encoded string

			String usrJson = mapper.writeValueAsString(userDto);

			Gson gson = new Gson() ;
			String gsn = gson.toJson(userDto,UserProfile.class) ;
            jwtToken = Jwts.builder()
                    .setSubject("users/TzMUocMF4p")
                    .setExpiration(expireDate)
                    .claim("handle", userDto.getUserHandle())
                    .claim("empNum", userDto.getHrid())
                    .claim("usr", userDto)
                    .claim("usrStr", usrStr)
                    .claim("jusr",usrJson)
                    .claim("gsn",gsn)
                    .signWith(signatureAlgorithm, signKey())
                    .compact();
        } catch (Exception ex) {
        	System.out.println("Error creating the key");
            //Logger.getLogger(TokenUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jwtToken ;
    }

*/
	
	public static String getDate(Calendar cal){
        return "" + cal.get(Calendar.DATE) +"/" +
                (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.YEAR);
    }


	public boolean validateRequest(String authToken)
    {
       //This line will throw an exception if it is not a signed JWS (as expected)
        try
        {
                claims = Jwts.parser()         
                		//.setSigningKey(DatatypeConverter.parseBase64Binary(MAGIC_KEY))
                		.setSigningKey(signKey())    
                        .parseClaimsJws(authToken).getBody();
            //validate expiry
            
            Date expires = claims.getExpiration();
            Date currentDate = new Date();
            //String signature = parts[2];

            if (expires.compareTo(currentDate) < 0) //expirex < current date 
            {
                return false;
            }
            /// date is ok check signature
            
        }
        catch(ExpiredJwtException e)
        {
        	System.out.println("Token Has expired");
        	return false ;
        }
        catch(Exception e)
        {
        	return false ;
        }
            //check if user id is currentlylogin user
                
            //System.out.println("ID: " + claims.getId());
            
        return true ;
    }
    
	private Key signKey()
    {
         //The JWT signature algorithm we will be using to sign the token

        //long nowMillis = System.currentTimeMillis();
        //Date now = new Date(nowMillis);
    
        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(MAGIC_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    
        return signingKey ;
    }
    
    public Claims getClaims()
    {
        return claims;
    }
}
