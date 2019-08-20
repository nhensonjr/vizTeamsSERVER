package com.vizient.vizteamsserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(exclude = {"members"})
@ToString(exclude = {"members"})
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
    private List<Member> members;
}
