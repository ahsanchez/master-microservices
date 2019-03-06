package com.ahs.master.microservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Esta es la entidad de usuario.")
@Entity
public class User {
    
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, message = "Name should have at least 2 characters")
    @ApiModelProperty(notes = "El nombre debe tener al menos 2 caracteres.")
    private String name;
    @Past
    @ApiModelProperty(notes = "La fecha tiene que ser anterior al día de hoy.")
    private Date birthDate;
    
    // name of the field in post.java
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;
    
    public Integer getId(){
        return id;
    }
    
    public void setId(
        Integer id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(
        String name){
        this.name = name;
    }
    
    public Date getBirthDate(){
        return birthDate;
    }
    
    public void setBirthDate(
        Date birthDate){
        this.birthDate = birthDate;
    }
    
    public List<Post> getPosts(){
        return posts;
    }
    
    public void setPosts(
        List<Post> posts){
        this.posts = posts;
    }
    
    public User(
            Integer id,
            @Size(min = 2, message = "Name should have at least 2 characters") String name,
            @Past Date birthDate,
            List<Post> posts) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.posts = posts;
    }
    
    @Override
    public String toString(){
        return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", posts=" + posts + "]";
    }
    
    public User() {}
    
}
