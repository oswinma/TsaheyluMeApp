package me.tsaheylu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDataDTO {

    private Long id;
    private Long toid;
    private Long groupid;
    private int status;
    private Date createdTime;
    private Date lastModifiedTime;

//  public GroupDTO(Group g, int num) {
//    this.createtime = g.getCreatetime();
//    this.des = g.getDes();
//    this.fromid = g.getFromid();
//    this.id = g.getId();
//    this.name = g.getName();
//    this.num = num;
//    this.status = g.getStatus();
//    this.type = g.getType();
//  }

}
