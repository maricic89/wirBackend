//package com.milos.wir.managment.post;
//
//import com.milos.wir.managment.user.entity.UserEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "comment")
//public class CommentEntity {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", unique = true, nullable = false, updatable = false)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false, insertable=false)
//    private PostEntity postEntity;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;
//
//    @Column(name = "comment", nullable = false)
//    private String comment;
//
//    @Column(name = "created_date", nullable = false)
//    private LocalDateTime createdDate;
//}
