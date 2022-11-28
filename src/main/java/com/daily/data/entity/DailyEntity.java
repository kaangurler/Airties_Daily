package com.daily.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//Entity
@Entity
@Table(name = "daily")
public class DailyEntity extends BaseEntity implements Serializable {
public static final long serialVersionUID=1L;

   //field
    private String dailyHeader;
    private String dailyContent;

    //@Column(name = "email",length = 125,nullable = false,unique = true)
    private String email;
    private String password;


    //javada olsun ancak database bu attribute eklemesin
    //@Transient
    //private String justJava;

    //büyük datalar icin kullanalım.
    //@Lob
    //private Object bigData;
}
