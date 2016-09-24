package com.technocurl.www.parsejson.gazat;

import java.io.Serializable;

/**
 * Created by dinesh on 9/14/16.
 */
public class Gazetmodel implements Serializable{
    String id,barsha,bargikaran,sankhya,mantralaya,bishaya,barema,miti;

    public String getMiti() {
        return miti;
    }

    public void setMiti(String miti) {
        this.miti = miti;
    }

    public String getBarema() {
        return barema;
    }

    public void setBarema(String barema) {
        this.barema = barema;
    }

    public String getBishaya() {
        return bishaya;
    }

    public void setBishaya(String bishaya) {
        this.bishaya = bishaya;
    }

    public String getMantralaya() {
        return mantralaya;
    }

    public void setMantralaya(String mantralaya) {
        this.mantralaya = mantralaya;
    }

    public String getSankhya() {
        return sankhya;
    }

    public void setSankhya(String sankhya) {
        this.sankhya = sankhya;
    }

    public String getBargikaran() {
        return bargikaran;
    }

    public void setBargikaran(String bargikaran) {
        this.bargikaran = bargikaran;
    }

    public String getBarsha() {
        return barsha;
    }

    public void setBarsha(String barsha) {
        this.barsha = barsha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
