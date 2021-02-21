package de.rottstegge.restmenu.model.base;

import java.util.Date;

public interface BaseEntity {

    long getId();

    void setId(long id);

    int getVersion();

    void setVersion(int version);

    Date getCreated();

    void setCreated(Date created);

    Date getLastModified();

    void setLastModified(Date lastModified);

}
