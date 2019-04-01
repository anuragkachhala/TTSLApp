package com.software.ttsl.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("Tenant")
    @Expose
    private String tenant;
    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("JqueryUIi18NPath")
    @Expose
    private String jqueryUIi18NPath;
    @SerializedName("Today")
    @Expose
    private String today;
    @SerializedName("Now")
    @Expose
    private String now;
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("User")
    @Expose
    private String user;
    @SerializedName("Office")
    @Expose
    private String office;
    @SerializedName("MetaView")
    @Expose
    private MetaView metaView;
    @SerializedName("ShortDateFormat")
    @Expose
    private String shortDateFormat;
    @SerializedName("LongDateFormat")
    @Expose
    private String longDateFormat;
    @SerializedName("ThousandSeparator")
    @Expose
    private String thousandSeparator;
    @SerializedName("DecimalSeparator")
    @Expose
    private String decimalSeparator;
    @SerializedName("CurrencyDecimalPlaces")
    @Expose
    private Integer currencyDecimalPlaces;
    @SerializedName("CurrencySymbol")
    @Expose
    private String currencySymbol;
    @SerializedName("DatepickerShowWeekNumber")
    @Expose
    private Boolean datepickerShowWeekNumber;
    @SerializedName("DatepickerWeekStartDay")
    @Expose
    private Integer datepickerWeekStartDay;
    @SerializedName("DatepickerNumberOfMonths")
    @Expose
    private String datepickerNumberOfMonths;
    @SerializedName("OfficeId")
    @Expose
    private Integer officeId;

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getJqueryUIi18NPath() {
        return jqueryUIi18NPath;
    }

    public void setJqueryUIi18NPath(String jqueryUIi18NPath) {
        this.jqueryUIi18NPath = jqueryUIi18NPath;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public MetaView getMetaView() {
        return metaView;
    }

    public void setMetaView(MetaView metaView) {
        this.metaView = metaView;
    }

    public String getShortDateFormat() {
        return shortDateFormat;
    }

    public void setShortDateFormat(String shortDateFormat) {
        this.shortDateFormat = shortDateFormat;
    }

    public String getLongDateFormat() {
        return longDateFormat;
    }

    public void setLongDateFormat(String longDateFormat) {
        this.longDateFormat = longDateFormat;
    }

    public String getThousandSeparator() {
        return thousandSeparator;
    }

    public void setThousandSeparator(String thousandSeparator) {
        this.thousandSeparator = thousandSeparator;
    }

    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    public void setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    public Integer getCurrencyDecimalPlaces() {
        return currencyDecimalPlaces;
    }

    public void setCurrencyDecimalPlaces(Integer currencyDecimalPlaces) {
        this.currencyDecimalPlaces = currencyDecimalPlaces;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public Boolean getDatepickerShowWeekNumber() {
        return datepickerShowWeekNumber;
    }

    public void setDatepickerShowWeekNumber(Boolean datepickerShowWeekNumber) {
        this.datepickerShowWeekNumber = datepickerShowWeekNumber;
    }

    public Integer getDatepickerWeekStartDay() {
        return datepickerWeekStartDay;
    }

    public void setDatepickerWeekStartDay(Integer datepickerWeekStartDay) {
        this.datepickerWeekStartDay = datepickerWeekStartDay;
    }

    public String getDatepickerNumberOfMonths() {
        return datepickerNumberOfMonths;
    }

    public void setDatepickerNumberOfMonths(String datepickerNumberOfMonths) {
        this.datepickerNumberOfMonths = datepickerNumberOfMonths;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }


}
