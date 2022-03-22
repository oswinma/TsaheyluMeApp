package me.tsaheylu.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import java.util.Date;


//@Entity
@Getter
@Setter
public class Group {
    //  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long fromid;
    private int status = 0;
    private String des;
    private String name;
    private String type;
    @CreatedDate
    private Date createdTime;

    @LastModifiedDate
//    @Column(name = "last_modified_date")
    private Date lastModifiedTime;


}
