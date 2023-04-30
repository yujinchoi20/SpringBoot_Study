package org.example.Domain;

import jakarta.persistence.MappedSuperclass;

import java.sql.Date;

@MappedSuperclass
public class BasicEntity {

    private Date createDate; //등록일
    private Date lastModifiedDate; //수정일

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
