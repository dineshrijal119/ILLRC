package com.technocurl.www.parsejson.model;


import java.io.Serializable;

/**
 * Created by dinesh on 8/28/16.
 */
public class NajirNepalimodel implements Serializable {
    String SN;
    String Publication;
    String PageNumber;
    String NirnayeNumber;
    String Adalat;
    String RegisterId;
    String DartaMiti;
    String SubjectCaseType;
    String Ijlash;
    String CaseType;
    String CaseTypeII;
    String CaseTypeIII;
    String CaseTypeIV;
    String CaseTypeV;
    String Miti;
    String Judge;
    String Purnawedan;
    String Partyashi;
    String PurnawedanTarf;
    String partyashiTarf;
    String ShortSubject;
    String DecisionInShort;
    String AttravtiveSubject;
    String Month;
    String Pubyear;
    String File;
    boolean selected;

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    /*    public NajirNepalimodel(String SN,String publication,String PageNumber,String NirnayeNumber,String Adalat,String RegisterId,String DartaMiti,String SubjectCaseType,String Ijlash,String Judge,String Miti,String Month,String Pubyear){
        this.SN=SN;
        this.Publication=publication;
        this.PageNumber=PageNumber;
        this.NirnayeNumber=NirnayeNumber;
        this.Adalat=Adalat;
        this.RegisterId=RegisterId;
        this.DartaMiti=DartaMiti;
        this.SubjectCaseType=SubjectCaseType;
        this.Ijlash=Ijlash;
        this.Judge=Judge;
        this.Miti=Miti;
        this.Month=Month;
        this.Pubyear=Pubyear;
    }*/

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
        File = file;
    }

    public String getAttravtiveSubject() {
        return AttravtiveSubject;
    }

    public void setAttravtiveSubject(String attravtiveSubject) {
        AttravtiveSubject = attravtiveSubject;
    }

    public String getShortSubject() {
        return ShortSubject;
    }

    public void setShortSubject(String shortSubject) {
        ShortSubject = shortSubject;
    }

    public String getPartyashiTarf() {
        return partyashiTarf;
    }

    public void setPartyashiTarf(String partyashiTarf) {
        this.partyashiTarf = partyashiTarf;
    }

    public String getPurnawedanTarf() {
        return PurnawedanTarf;
    }

    public void setPurnawedanTarf(String purnawedanTarf) {
        PurnawedanTarf = purnawedanTarf;
    }

    public String getPartyashi() {
        return Partyashi;
    }

    public void setPartyashi(String partyashi) {
        Partyashi = partyashi;
    }

    public String getCaseType() {
        return CaseType;
    }

    public void setCaseType(String caseType) {
        CaseType = caseType;
    }

    public String getPurnawedan() {
        return Purnawedan;
    }

    public void setPurnawedan(String purnawedan) {
        Purnawedan = purnawedan;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getPublication() {
        return Publication;
    }

    public void setPublication(String publication) {
        Publication = publication;
    }

    public String getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(String pageNumber) {
        PageNumber = pageNumber;
    }

    public String getNirnayeNumber() {
        return NirnayeNumber;
    }

    public void setNirnayeNumber(String nirnayeNumber) {
        NirnayeNumber = nirnayeNumber;
    }

    public String getAdalat() {
        return Adalat;
    }

    public void setAdalat(String adalat) {
        Adalat = adalat;
    }

    public String getRegisterId() {
        return RegisterId;
    }

    public void setRegisterId(String registerId) {
        RegisterId = registerId;
    }

    public String getDartaMiti() {
        return DartaMiti;
    }

    public void setDartaMiti(String dartaMiti) {
        DartaMiti = dartaMiti;
    }

    public String getSubjectCaseType() {
        return SubjectCaseType;
    }

    public void setSubjectCaseType(String subjectCaseType) {
        SubjectCaseType = subjectCaseType;
    }

    public String getIjlash() {
        return Ijlash;
    }

    public void setIjlash(String ijlash) {
        Ijlash = ijlash;
    }

    public String getJudge() {
        return Judge;
    }

    public void setJudge(String judge) {
        Judge = judge;
    }

    public String getMiti() {
        return Miti;
    }

    public void setMiti(String miti) {
        Miti = miti;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getPubyear() {
        return Pubyear;
    }

    public void setPubyear(String pubyear) {
        Pubyear = pubyear;
    }
}
