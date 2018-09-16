//package com.milos.wir.managment.post;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "post_category")
//public class PostCategoryEntity {
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
//    @Column(name = "code")
//    private String code;
//
//    @Column(name = "name")
//    private String name;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public PostEntity getPostEntity() {
//        return postEntity;
//    }
//
//    public void setPostEntity(PostEntity postEntity) {
//        this.postEntity = postEntity;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return "PostCategoryEntity{" +
//                "id=" + id +
//                ", postEntity=" + postEntity +
//                ", code='" + code + '\'' +
//                ", name='" + name + '\'' +
//                '}';
//    }
//}
