package com.example.bootspecialstudy.web;

import com.example.bootspecialstudy.domain.User;
import com.example.bootspecialstudy.domain.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    // final ==> 컴파일이 될 때 초기화가 되어있어야한다
    private final UserRepository userRepository;

    // DI = 의존성 주입
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // http://localhost:8080/user
    @GetMapping("/user")
    public List<User> findAll() {
        System.out.println("findAll()");
        return userRepository.findAll(); // MessageConverter 가 동작함 => (JavaObject -> JSON) 으로 변경함
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable int id) {
        System.out.println("findById()");
        return userRepository.findById(id);
    }

    @PostMapping("/user")
    // x-www-form-urlencoded => request.getParameter()
    public ResponseEntity<?> save(User user) {

        return new ResponseEntity<>("ok", HttpStatus.OK);

    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable int id) {
        System.out.println("delete()" + id);

    }

    @PutMapping("/user/{id}")
    public void update(@PathVariable int id, String password, String phone) {
        System.out.println("update()");
    }


}
