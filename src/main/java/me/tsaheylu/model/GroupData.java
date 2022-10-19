package me.tsaheylu.model;

import lombok.*;
import me.tsaheylu.common.GroupDataStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;


//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "groupdata")
public class GroupData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "toid")
    private Long toid;

    @Column(name = "groupid")
    private Long groupid;

    @Column(name = "status")
    private int status = GroupDataStatus.INVALID.getId();

    @Column(name = "createdTime")
    @CreatedDate
    private Date createdTime;

    @Column(name = "lastModifiedTime")
    @LastModifiedDate
//    @Column(name = "last_modified_date")
    private Date lastModifiedTime;
}
