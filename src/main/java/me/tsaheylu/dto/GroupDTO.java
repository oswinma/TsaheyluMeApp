package me.tsaheylu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.tsaheylu.model.Group;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {

    private Long id;
    private Long fromid;
    private int status = 0;
    private String des;
    private String name;
    private String type;
    //    private int num = 0;
    private Date createdTime;
    private Date lastModifiedTime;

    public GroupDTO(Group g) {
        this.createdTime = g.getCreatedTime();
        this.des = g.getDes();
        this.fromid = g.getFromid();
        this.id = g.getId();
        this.name = g.getName();
        this.status = g.getStatus();
        this.type = g.getType();
    }

}
