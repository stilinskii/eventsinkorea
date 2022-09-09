package com.jenn.eventsinkorea.domain.email;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MailInfo {
    String to;
    String by;
    String email;
    MailOption mailOption;
}
