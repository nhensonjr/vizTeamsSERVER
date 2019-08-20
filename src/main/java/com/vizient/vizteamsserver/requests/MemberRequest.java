package com.vizient.vizteamsserver.requests;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    private String firstName;
    private String lastName;
    private String title;
    private String pathToPhoto;
    private Long team;
}
