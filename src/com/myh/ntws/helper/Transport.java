package com.myh.ntws.helper;

import java.io.Serializable;

public class Transport implements Serializable
{
	  private static final long serialVersionUID = 1675991188209117209L;  
	    private String clazz;  
	    private String method;  
	    private Object message;  
	    private String key;
		public String getClazz()
		{
			return clazz;
		}
		public void setClazz(String clazz)
		{
			this.clazz = clazz;
		}
		public String getMethod()
		{
			return method;
		}
		public void setMethod(String method)
		{
			this.method = method;
		}
		public Object getMessage()
		{
			return message;
		}
		public void setMessage(Object message)
		{
			this.message = message;
		}
		public String getKey()
		{
			return key;
		}
		public void setKey(String key)
		{
			this.key = key;
		}
		public static long getSerialversionuid()
		{
			return serialVersionUID;
		} 
}
