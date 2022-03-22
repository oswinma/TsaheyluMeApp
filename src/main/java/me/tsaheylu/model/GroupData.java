package me.tsaheylu.model;

import lombok.Getter;
import lombok.Setter;
import me.tsaheylu.common.GroupDataStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;


//@Entity
@Getter
@Setter
public class GroupData {
    //  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long toid;
    private Long groupid;
    private int status = GroupDataStatus.INVALID;

    @CreatedDate
    private Date createdTime;

    @LastModifiedDate
//    @Column(name = "last_modified_date")
    private Date lastModifiedTime;
}
