package com.technocurl.www.parsejson.kanun;

import java.io.Serializable;

/**
 * Created by dinesh on 9/13/16.
 */
public class Tathya implements Serializable {

    String LawSubGroupId;
    String Name;
    String sn;
    String Dapha;

    public String getDapha() {
        return Dapha;
    }

    public void setDapha(String dapha) {
        Dapha = dapha;
    }

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
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
