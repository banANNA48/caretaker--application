package com.thesis2020.monitoringapp;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Exclude;

public class Records {

    private String Day;
    private String documentId;
    private String Month;
    private String VIP;

    public Records() {} // Need for firebase

    @Exclude
    public String getDocumentId() {
        return documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Records(String VIP, String Month, String Day){
        this.Day = Day;
        this.Month = Month;
        this.VIP = VIP;
    }


    public String getDay(){
        return Day;
    }
    public void setDay(String Day){
        this.Day = Day;
    }

    public String getMonth (){
        return Month;
    }
    public void setMonth(String Month){
        this.Month = Month;
    }

    public String getVIP (){
        return VIP;
    }
    public void setVIP(String VIP){
        this.VIP = VIP;
    }


    @Override
    public String toString() {
        return "Product{" +
                "Day ='" + Day + '\'' +
                ", Month =" + Month +
                ", VIP =" + VIP +
                '}';
    }
}
