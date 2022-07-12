package com.example.demo.controller;

import com.example.demo.entities.Address;
import com.example.demo.entities.User;
import com.example.demo.repository.UserReponsitory;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Value("${spring.datasource.url}")
    String url;

    String status = System.getenv("status");

    @Autowired
    private UserReponsitory userRepository;


    @PostConstruct
    public void initData(){
        System.out.println("__________Reset and init data________________");
    }

    @RequestMapping("/registUser")
    public void registUser(){
        Address address = new Address("Hoa Binh");
        User user = new User("Pham Van A");
        user.setAddress(address);

        this.userRepository.save(user);

    }

    @RequestMapping("/user")
    public ResponseEntity<?> ListUser(@RequestParam (name = "page", required = false,defaultValue = "0")Integer page,
                                      @RequestParam(name = "size",required = false,defaultValue  ="5")Integer size,
                                      @RequestParam(name = "sort",required = false,defaultValue = "id,ASC")String sort){

        System.out.println("spring.datasource.url" + url);
        System.out.println("environtment" + status);

        Sort sortabble = null;
        if (sort.equals("ASC")){
            sortabble = Sort.by("id").ascending();
        }
        if (sort.equals("DESC")){
            sortabble = Sort.by("id").descending();
        }
        Pageable pageable = PageRequest.of(page,size,sortabble);

        return ResponseEntity.ok(userRepository.findUser(pageable));
    }

}
