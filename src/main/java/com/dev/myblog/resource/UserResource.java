package com.dev.myblog.resource;

import com.dev.myblog.model.User;
import com.dev.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;

    public UserResource(@Qualifier("jpaUserService") UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id){

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        user.setId(userService.getAllUsers().size() + 1);
        return ResponseEntity.created(getLocation(user.getId())).body(userService.addUser(user));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.deleteById(id));
    }

    private URI getLocation(Integer id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(id).toUri();
    }
}
