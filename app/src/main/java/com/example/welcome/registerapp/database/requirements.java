package com.example.welcome.registerapp.database;

public class requirements {

    private String installationId;
    private String noofdevice;

    public String getInstallationId() {
        return installationId;
    }

    public String getNoofdevice() {
        return noofdevice;
    }

    public String getTypeofdevice() {
        return typeofdevice;
    }

    public String getRegion() {
        return region;
    }

    public String getDevicename() {
        return devicename;
    }

    public String getSitename() {
        return sitename;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getAddress() {
        return address;
    }

    public String getPincode() {
        return pincode;
    }

    private String typeofdevice;
    private String region;



    private String devicename;
    private String sitename;
    private String email;
    private String mobileno;
    private String address;
    private String pincode;

    public requirements(String installationId, String noofdevice, String typeofdevice, String region, String devicename, String sitename, String email, String mobileno, String address, String pincode) {
        this.installationId = installationId;
        this.noofdevice = noofdevice;
        this.typeofdevice = typeofdevice;
        this.region = region;
        this.devicename = devicename;
        this.sitename = sitename;
        this.email = email;
        this.mobileno = mobileno;
        this.address = address;
        this.pincode = pincode;
    }


    public requirements() {
        //empty constructor
    }





}
