package ru.updateyoureng.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.updateyoureng.dao.UserDetailsRepository;
import ru.updateyoureng.model.Account;
import ru.updateyoureng.model.UserDetails;

@RestController
@RequestMapping(produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @GetMapping(path = "/api/user/{id}")
    @ResponseBody
    public UserDetails getUser(@PathVariable long id) {
        UserDetails userDetails = this.userDetailsRepository.findOne(id);
        return userDetails;
    }

    //TODO Добавить валидацию
    @PutMapping(path = "api/user")
    @ResponseBody
    public UserDetails addUser(@RequestBody UserDetails userDetails){
        return this.userDetailsRepository.save(userDetails);
    }

    @DeleteMapping(path = "api/user/{id}")
    @ResponseBody
    public UserDetails addUser(@PathVariable long id){
        UserDetails result = this.userDetailsRepository.findOne(id);
        if(result!=null){
            this.userDetailsRepository.delete(id);
        }
        return result;
    }



}
