package com.vizient.vizteamsserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private OffsetDateTime createdOn;
    private OffsetDateTime endedOn;
    private List<Member> members;
}
