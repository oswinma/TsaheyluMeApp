package me.tsaheylu.model;

import lombok.*;
import me.tsaheylu.common.URLInfoStatus;

import javax.persistence.*;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;


//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "urlinfo")
public class URLInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "icon")
    private String icon;

    @Column(name = "host")
    private String host;

    @Column(name = "status")
    private int status = URLInfoStatus.VALID.getId();

    @Column(name = "share")
    private Long share = 0l;

    @Column(name = "favs")
    private Long favs = 0l;
}
