package me.tsaheylu.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import javax.persistence.*;
import java.util.Date;


//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fromid")
    private Long fromid;

    @Column(name = "status")
    private int status = 0;

    @Column(name = "des")
    private String des;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "createdTime")
    @CreatedDate
    private Date createdTime;

    @Column(name = "lastModifiedTime")
    @LastModifiedDate
    private Date lastModifiedTime;


}
