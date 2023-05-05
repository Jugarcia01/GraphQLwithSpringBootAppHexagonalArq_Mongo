package com.jgo.demo_graphql.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

public abstract class SelfValidating<T> {

  private final Validator validator;

  @CreatedDate
  private LocalDateTime createdAt;
  @LastModifiedDate
  private LocalDateTime lastModified;
  @JsonProperty("deletedAt")
  private LocalDateTime deletedAt;
  @JsonProperty("messageId")
  private String messageId;


  public SelfValidating() {
    var factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  public void validateSelf() {
    Set<ConstraintViolation<T>> violations = validator.validate((T) this);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }
  public void setLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
  }

  public LocalDateTime getDeletedAt() {
    return deletedAt;
  }
  public void setDeletedAt(LocalDateTime deletedAt) {
    this.deletedAt = deletedAt;
  }

  public String getMessageId() {
    return messageId;
  }
  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

}
