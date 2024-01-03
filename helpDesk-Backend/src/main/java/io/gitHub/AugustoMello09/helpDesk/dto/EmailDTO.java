package io.gitHub.AugustoMello09.helpDesk.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import io.gitHub.AugustoMello09.helpDesk.entities.enums.StatusEmail;
import lombok.Data;

@Data
public class EmailDTO {

   
    private UUID emailId;
    private UUID userId;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

}
