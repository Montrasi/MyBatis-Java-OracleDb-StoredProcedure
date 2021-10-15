package it.mybatis.dto;

import java.io.Serializable;

public class Company implements Serializable {

    private Long companyId;
    private String name;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
