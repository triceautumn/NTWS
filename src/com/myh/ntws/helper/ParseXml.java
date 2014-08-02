package com.myh.ntws.helper;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXml
{
	public static int getPort(String path)
	{
		int port = 0; 
		
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(new File(path));
			Element root = document.getRootElement();
			String portStr = root.attributeValue("port");
			port = Integer.valueOf(portStr);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return port;
	}
	
	public static void main(String[] args)
	{
		ParseXml parse = new ParseXml();
		int port = parse.getPort("src/com/myh/ntws/server/init/init.xml");
		System.out.println(port);
	}
}
