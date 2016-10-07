package com.technocurl.www.parsejson.utility;

/**
 * Created by dinesh on 9/11/16.
 */
public class Constants {
    public static String TAG="dinesh";
public static String   BASE_URL="http://ilrcapi.asishthapa.com.np/api/";
    public static String GET_SUCHI=BASE_URL+"law/GetLawSubGroupByLawGroupAndLawMainGroup";
    public static String GET_KANUNI_KHOJ= BASE_URL + "law/GetLawByLawSubGroup";
    public static String GET_NEARBY_NAJIR=BASE_URL +  "law/GetRelatedNajirByLaw";
    public static String GET_TATHYA=BASE_URL +  "law/GetTathyaByLawSubGroup";
    public static String TATHYA_CLICK_DAFA= BASE_URL + "law/GetDaphaAndTippaniByTathya";
    public static String TATHYA_CLICK_NAJIR= BASE_URL + "law/GetNajirByTathya";
    public static String TIPANI_BY_THATHYA="http://api.asishthapa.com.np/api/Law/GetTippaniByTathya";
    public static String CHECK_PHONE= BASE_URL + "User/CheckRegistration";
    public static String REGISTRR_USER=BASE_URL +  "User/InsertUserInfo";
    public static String GET_ALL_PACKAGE=BASE_URL + "User/GetAllPackages";
    public static String GET_NAJIR_MAIN=BASE_URL + "Najir/GetNajirDetails";
    public static String GET_GAZET_MAIN=BASE_URL + "Gadget/GetGadgetDetails";
    public static String INSERT_USER_PACKAGE=BASE_URL+"User/InsertUserPackage";
    //english
    public static String NAJIR_ENGLISH=BASE_URL+"NajirEnglish/GetNajirDetails";

}
