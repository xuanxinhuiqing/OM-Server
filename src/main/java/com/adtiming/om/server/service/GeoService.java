// Copyright 2020 ADTIMING TECHNOLOGY COMPANY LIMITED
// Licensed under the GNU Lesser General Public License Version 3

package com.adtiming.om.server.service;

import com.adtiming.om.server.dto.CommonRequest;
import com.adtiming.om.server.dto.GeoData;
import com.adtiming.om.server.dto.GeoDataCommon;
import com.adtiming.om.server.util.CountryCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * Obtain geo info
 * The default implementation is to get country from CommonRequest
 * Modify this implementation to use your own IP Service
 */
@Service
public class GeoService {

    private static final Logger LOG = LogManager.getLogger();

    public GeoData getGeoData(HttpServletRequest req, CommonRequest o) {
        String ip = getClientIP(req);
        GeoDataCommon geo = new GeoDataCommon(ip);
        if (o != null) {
            String country = CountryCode.convertToA2(o.getLcountry());
            geo.setCountry(country);
        }
        return geo;
    }

    private String getClientIP(HttpServletRequest req) {
    	// LOG.debug("aaron.song ip Addr -> " + getIpAddr(req));
		// String clientIP = req.getHeader("X-Real-IP");
		//    if (clientIP == null) {
		//        clientIP = req.getRemoteAddr();
		//    }
		//    return clientIP;
		    	
		//    String xff = req.getHeader("X-Forwarded-For");
		//    if (StringUtils.isNotBlank(xff)) {
		//        return StringUtils.trim(xff.split(",")[0]);
		//    } else
		//  return remote_ip;
    	return getIpAddr(req);
    }
    
    // ADDCODE aaron.song
    /** 
     * 获取当前网络ip 
     * @param request 
     * @return 
     */  
    public String getIpAddr(HttpServletRequest request){  
        String ipAddress = request.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                        e.printStackTrace();  
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }  
            }  
            return ipAddress;   
    }
    // 

}
