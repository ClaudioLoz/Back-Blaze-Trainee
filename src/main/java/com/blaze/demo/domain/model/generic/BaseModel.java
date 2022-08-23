package com.blaze.demo.domain.model.generic;


import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import java.io.Serializable;

public abstract class BaseModel implements Serializable {
    @MongoId
    @MongoObjectId
    protected String id;

    protected Long  created;
    protected Long  modified;
    protected boolean deleted;

    public BaseModel() {
        this.created = org.joda.time.DateTime.now().getMillis();
        this.modified = org.joda.time.DateTime.now().getMillis();
        this.deleted = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getModified() {
        return modified;
    }

    public void setModified(Long modified) {
        this.modified = modified;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
