package com.vizient.vizteamsserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vizient.vizteamsserver.helpers.OffsetDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MemberHistory {

    @Id
    @GeneratedValue
    private Long id;

    private Long memberId;

    private Long teamId;

    @Convert(converter = OffsetDateTimeConverter.class)
    private OffsetDateTime startedOnTeam;

    @Convert(converter = OffsetDateTimeConverter.class)
    private OffsetDateTime leftTeam;

}
