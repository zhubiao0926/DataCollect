package com.briup.util.impl;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.DocumentException;

import com.briup.util.Log;
import com.briup.util.Xml;

public class LogImpl implements Log{
	
	private Logger log;
    
    {
    	
			String string;
			try {
				string = Xml.xmlpath("Log", "log4j-properties");
			 	//读其配置文件
		    	PropertyConfigurator.configure(string);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		
    	//获得总的输出对象
    	log= Logger.getRootLogger();
    }

    public void debug(String message) {
		// TODO Auto-generated method stub
		log.debug(message);
	}

	public void info(String message) {
		// TODO Auto-generated method stub
		log.info(message);
	}

	public void warn(String message) {
		// TODO Auto-generated method stub
		log.warn(message);
	}

	public void error(String message) {
		// TODO Auto-generated method stub
		log.error(message);
	}

	public void fatal(String message) {
		// TODO Auto-generated method stub
		log.fatal(message);
	}
}
