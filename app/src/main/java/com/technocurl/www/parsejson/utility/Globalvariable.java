package com.technocurl.www.parsejson.utility;

import android.app.Application;

/**
 * Created by dinesh on 9/24/16.
 */
public class Globalvariable extends Application {
    String cell_phone,uniqueid;

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }
}
