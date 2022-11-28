package com.daily.business.dto;


import com.daily.annotation.UserRegisterUniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

//lombok
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DailyDto implements Serializable {

    private Long id;

    @NotEmpty(message = "{daily.header.validation.constraints.NotNull.message}")
    private String dailyHeader;

    @NotEmpty(message = "{daily.content.validation.constraints.NotNull.message}")
    private String dailyContent;

    @NotEmpty(message = "{daily.email.validation.constraints.NotNull.message}")
    @Email(message = "{daily.email.regex.validation.constraints.NotNull.message}")
    @UserRegisterUniqueEmail
    private String email;

    @NotEmpty(message = "{daily.password.validation.constraints.NotNull.message}")
    //@Size(min=7,max = 12,message = "{blog.password.pattern.validation.constraints.NotNull.message}")
    //@Pattern(regexp = "")
    private String password;

    private Date createdDate;
}
