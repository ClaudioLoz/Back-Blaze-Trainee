package com.blaze.demo.domain.model.generic;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;


public class Address extends CompanyBaseModel {
    public static final String DEFAULT_COUNTRY = "US";
    private String country = DEFAULT_COUNTRY;
    private String state = "";
    private String city = "";
    private String zipCode = "";
    private String address = "";
    private BigDecimal latitude;
    private BigDecimal longitude;

    public void clean() {
        if (address == null) {
            address = "";
        }
        if (city == null) {
            city = "";
        }
        if (state == null) {
            state = "";
        }
        if (zipCode == null) {
            zipCode = "";
        }

    }

    public boolean isValid() {
        return StringUtils.isNotBlank(address)
                || StringUtils.isNotBlank(city)
                || StringUtils.isNotBlank(state)
                || StringUtils.isNotBlank(zipCode);
    }

    public String getNumber() {
        if (StringUtils.isNotBlank(address)) {
            String[] split = address.split(" ");
            if (split.length > 1) {
                return split[0];
            }
        }
        return Strings.EMPTY;
    }


    public String getStreet() {
        if (StringUtils.isNotBlank(address)) {
            String number = getNumber();
            return address.replace(number,"").trim();
        }
        return address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    public String getAddressString() {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(address)) {
            sb.append(address.trim());
        }
        if (StringUtils.isNotBlank(city)) {
            sb.append(StringUtils.isNotBlank(sb) ? "," : "");
            sb.append(city.trim());
        }
        if (StringUtils.isNotBlank(state)) {
            sb.append(StringUtils.isNotBlank(sb) ? "," : "");
            sb.append(state.trim());
        }
        if (StringUtils.isNotBlank(zipCode)) {
            sb.append(StringUtils.isNotBlank(sb) ? "," : "");
            sb.append(zipCode.trim());
        }

        return sb.toString().trim();
    }

    public String generateLabelMakerAddressString(){
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(address)) {
            sb.append(address.trim());
        }
        if (StringUtils.isNotBlank(city)) {
            sb.append(StringUtils.isNotBlank(sb) ? ", " : "");
            sb.append(city.trim());
        }
        if (StringUtils.isNotBlank(state)) {
            sb.append(StringUtils.isNotBlank(sb) ? ", " : "");
            sb.append(state.trim());
        }
        if (StringUtils.isNotBlank(zipCode)) {
            sb.append(StringUtils.isNotBlank(sb) ? " " : "");
            sb.append(zipCode.trim());
        }

        return sb.toString().trim();
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
