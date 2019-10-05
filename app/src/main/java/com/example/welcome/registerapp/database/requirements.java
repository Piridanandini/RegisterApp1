package com.example.welcome.registerapp.database;

public class requirements {

    public requirements(String installationId, String noofDevice, String typeofDevice, String region, String deviceName, String siteName, String email, String mobileNo, String address, String pincode) {
        this.installationId = installationId;
        NoofDevice = noofDevice;
        TypeofDevice = typeofDevice;
        Region = region;
        DeviceName = deviceName;
        SiteName = siteName;
        Email = email;
        MobileNo = mobileNo;
        Address = address;
        Pincode = pincode;
    }

    private String installationId;
    private String NoofDevice;
    private String TypeofDevice;

    public String getInstallationId() {
        return installationId;
    }

    public String getNoofDevice() {
        return NoofDevice;
    }

    public String getTypeofDevice() {
        return TypeofDevice;
    }

    public String getRegion() {
        return Region;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public String getSiteName() {
        return SiteName;
    }

    public String getEmail() {
        return Email;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public String getAddress() {
        return Address;
    }

    public String getPincode() {
        return Pincode;
    }

    private String Region;
    private String DeviceName;
    private String SiteName;
    private String Email;
    private String MobileNo;
    private String Address;
    private String Pincode;

    public requirements(){
        //empty constructor
    }
}
