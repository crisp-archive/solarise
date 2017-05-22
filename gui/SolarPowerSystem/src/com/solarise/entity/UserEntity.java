package com.solarise.entity;

public class UserEntity {
	public String email;
	public String firstname;
	public String lastname;
	public String gender;
	public String password;
	public String role;
	public String address;
	public String postCode;
	
	public String byte2str(byte[] b)
    { 
     String hs=""; 
     String stmp=""; 
     for (int n=0;n<b.length;n++) 
      { 
       stmp=(java.lang.Integer.toHexString(b[n] & 0XFF)); 
       if (stmp.length()==1) hs=hs+"0"+stmp; 
       else hs=hs+stmp; 
       if (n<b.length-1)  hs=hs+":"; 
      } 
     return hs.toUpperCase(); 
    } 
}
