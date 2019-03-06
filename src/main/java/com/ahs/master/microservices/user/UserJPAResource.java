package com.ahs.master.microservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {
    
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PostRepository postRepository;
    
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return repository.findAll();
    }
    
    @GetMapping("/jpa/users/{id}")
    public Resource<User> retrieveUser(
        @PathVariable int id){
        
        Optional<User> user = repository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }
        
        // HATEOAS
        Resource<User> resource = new Resource<>(user.get());
        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }
    
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(
        @Valid @RequestBody User user){
        User savedUser = this.repository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        
        return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(
        @PathVariable int id){
        repository.deleteById(id);
    }
    
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUsers(
        @PathVariable int id){
        
        Optional<User> user = repository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }
        return user.get().getPosts();
    }
    
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(
        @PathVariable int id,
        @RequestBody Post post){
        
        Optional<User> userOtional = repository.findById(id);
        if (!userOtional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }
        User user = userOtional.get();
        post.setUser(user);
        postRepository.save(post);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        
        return ResponseEntity.created(location).build();
        
    }
    
}
