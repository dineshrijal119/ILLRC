package com.technocurl.www.parsejson.kanun;

import java.io.Serializable;

/**
 * Created by dinesh on 9/11/16.
 */
public class Kanunimodel implements Serializable {
    String Sn;
    String id;
    String Dapha;
    String Detailnow;
    String Detailold;
    String Daphasn;
    String Daphaord;
    String LawSubGroupId;
    String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLawSubGroupId() {
        return LawSubGroupId;
    }

    public void setLawSubGroupId(String lawSubGroupId) {
        LawSubGroupId = lawSubGroupId;
    }

    public String getSn() {
        return Sn;
    }

    public String getId() {
        return id;
    }

    public String getDapha() {
        return Dapha;
    }

    public String getDetailnow() {
        return Detailnow;
    }

    public String getDetailold() {
        return Detailold;
    }

    public String getDaphaord() {
        return Daphaord;
    }

    public String getDaphasn() {
        return Daphasn;
    }

    public void setSn(String sn) {
        Sn = sn;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDapha(String dapha) {
        Dapha = dapha;
    }

    public void setDetailnow(String detailnow) {
        Detailnow = detailnow;
    }

    public void setDaphaord(String daphaord) {
        Daphaord = daphaord;
    }

    public void setDaphasn(String daphasn) {
        Daphasn = daphasn;
    }

    public void setDetailold(String detailold) {
        Detailold = detailold;
    }
}
