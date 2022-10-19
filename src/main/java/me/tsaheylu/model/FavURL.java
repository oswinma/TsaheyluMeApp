package me.tsaheylu.model;


import lombok.*;
import me.tsaheylu.common.FavURLStatus;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
import javax.persistence.*;
import java.util.Date;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "favurl")
public class FavURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fromid")
    private Long fromid;

    @Column(name = "toid")
    private Long toid;

    @Column(name = "urlid")
    private Long urlid;

    @Column(name = "status")
    private int status = FavURLStatus.PENDING.getId();

    @Column(name = "fstatus")
    private int fstatus = FavURLStatus.ARCHIVE.getId();

    @Column(name = "sendtime")
    private Date sendtime;

    @Column(name = "serial")
    private Long serial;

    @Column(name = "readtime")
    private Date readtime;

    @Column(name = "comment")
    private String comment;

    @Column(name = "channel")
    private String channel;

    @Column(name = "fav")
    private boolean fav = false;


}
