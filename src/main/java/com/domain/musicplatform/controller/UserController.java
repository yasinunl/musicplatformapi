package com.domain.musicplatform.controller;

import com.domain.musicplatform.dto.AccountDTO;
import com.domain.musicplatform.entity.User;
import com.domain.musicplatform.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getById(@PathVariable int id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PostMapping("/add-artist")
    ResponseEntity<User> addArtist(@RequestBody AccountDTO accountDTO){
        return ResponseEntity.ok(userService.addArtist(accountDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable int id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    @DeleteMapping("/delete-artist")
    ResponseEntity<User> deleteArtist(@RequestBody AccountDTO accountDTO){
        return ResponseEntity.ok(userService.deleteArtist(accountDTO));
    }
    @PutMapping
    ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(user));
    }
    @PostMapping("/add-song")
    ResponseEntity<User> addFavoriteSong(@RequestBody User user){
        return ResponseEntity.ok(userService.addFavoriteSong(user));
    }
    @DeleteMapping("/delete-song")
    ResponseEntity<User> deleteFavoriteSong(@RequestBody User user){
        return ResponseEntity.ok(userService.deleteFavoriteSong(user));
    }
}
