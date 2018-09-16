//package com.milos.wir.managment.post;
//
//import com.milos.wir.managment.user.entity.UserEntity;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Table(name = "post")
//public class PostEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", unique = true, nullable = false, updatable = false)
//    private Long id;
//
//    @Column(name = "post", nullable = false)
//    private String post;
//
//    @OneToMany(mappedBy = "postEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//    private List<PostCategoryEntity> postCategories;
//
//    @OneToMany(mappedBy = "postEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
//    private List<CommentEntity> commnents;
//
//    @OneToOne
//    @JoinColumn(name = "source_user_id", nullable = false)
//    private UserEntity sourceUser;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity targetUser;
//
//
//
//    @Column(name = "created_date", nullable = false)
//    LocalDateTime createdDate;
//
//}
