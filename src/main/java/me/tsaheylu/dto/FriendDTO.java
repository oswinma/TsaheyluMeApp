package me.tsaheylu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.tsaheylu.common.FriendStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendDTO {
    private Long id;
    private Long fromid;
    private Long toid;
    private int status = FriendStatus.INVALID.getId();
    private boolean popup = true;
    private Date bondtime;
}
