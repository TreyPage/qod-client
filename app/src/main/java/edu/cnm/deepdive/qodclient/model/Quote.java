package edu.cnm.deepdive.qodclient.model;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

public class Quote {

  private UUID id;

  private String text;

  private Date created;

  private URI href;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

}
