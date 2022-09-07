package com.jenn.eventsinkorea.web.email;

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
