package me.tsaheylu.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comment")
public class Comment {

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

    @Column(name = "favurlid")
    private Long favurlid;

    @Column(name = "sendtime")
    private String content;

    @Column(name = "content")
    private Date sendtime;


}
