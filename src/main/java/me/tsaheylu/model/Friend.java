package me.tsaheylu.model;

import lombok.*;
import me.tsaheylu.common.FriendStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "friend")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fromid")
    private Long fromid;

    @Column(name = "toid")
    private Long toid;

    @Column(name = "status")
    private int status = FriendStatus.INVALID.getId();

    @Column(name = "popup")
    private boolean popup = true;

    @Column(name = "bondtime")
    private Date bondtime;

}
//@Entity
