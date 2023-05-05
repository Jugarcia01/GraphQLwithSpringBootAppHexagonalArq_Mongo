package com.jgo.demo_graphql.infrastructure.outputadapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntityBase implements Serializable {
//public class EntityBase extends SelfValidating implements Serializable {

  @CreatedDate
  @JsonProperty("createdAt")
  private LocalDateTime createdAt;
  @LastModifiedDate
  @JsonProperty("lastModified")
  private LocalDateTime lastModified;
  @JsonProperty("deletedAt")
  private LocalDateTime deletedAt;
  @JsonProperty("messageId")
  private String messageId;

}
