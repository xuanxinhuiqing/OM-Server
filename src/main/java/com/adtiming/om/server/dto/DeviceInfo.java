// Copyright 2020 ADTIMING TECHNOLOGY COMPANY LIMITED
// Licensed under the GNU Lesser General Public License Version 3

package com.adtiming.om.server.dto;

public interface DeviceInfo {

    /**
     * Client first install App time, in seconds
     */
    int getFit();

    /**
     * The first time the client opens the App, in seconds
     */
    int getFlt();

    int getPlat();

    String getDid();
    
    // ADDCODE aaron.song
    String getImei();
    
    String getMdid();
    // 

    int getDtype();

    String getUid();

    String getSession();

    String getLang();

    String getLangname();

    int getJb();

    String getBundle();

    String getMake();

    String getBrand();

    String getModel();

    String getOsv();

    String getAppv();

    int getContype();

    String getCarrier();

    String getMccmnc();

    int getAbt();

    default String getIp() {
        return null;
    }

    default String getCountry() {
        return null;
    }

    default String getRegion() {
        return null;
    }

    default String getCity() {
        return null;
    }

}
