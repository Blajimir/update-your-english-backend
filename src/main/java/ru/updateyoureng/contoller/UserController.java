package ru.updateyoureng.contoller;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.updateyoureng.dao.AccountRepository;
import ru.updateyoureng.model.User;
import ru.updateyoureng.model.Account;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    private Account getCurrentAccount() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return accountRepository.findByUser_Username(login);
    }

    @GetMapping(path = "/api/user/{id}")
    @ResponseBody
    public Account getUser(@PathVariable long id) {
        Account account = this.accountRepository.findOne(id);
        return account;
    }

    //TODO Добавить валидацию
    @PutMapping(path = "/api/user")
    @ResponseBody
    public Account addUser(@RequestBody Account account) {
        return this.accountRepository.save(account);
    }

    @DeleteMapping(path = "/api/user/{id}")
    @ResponseBody
    public Account deleteUser(@PathVariable long id) {
        Account result = this.accountRepository.findOne(id);
        if (result != null) {
            this.accountRepository.delete(id);
        }
        return result;
    }
    //Пользовательские методы
    @PostMapping(path = "/api/login")
    @ResponseBody
    public Map<String,Object> userLogin(@RequestBody User user) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            AuthenticationManager authenticationManager = authenticationManagerBuilder.getOrBuild();
            Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            resultMap.put("Auth", true);
            resultMap.put("Account", accountRepository.findByUser_Username(user.getUsername()));
        }catch (AuthenticationException e){
            resultMap.put("Auth", false);
            resultMap.put("Error",e.getMessage());
        }finally {
            return resultMap;
        }
    }
    //TODO допилить создание нового аккаунта
    @PostMapping(path ="/api/login/new")
    @ResponseBody
    public Map<String,Object> newUser(@RequestBody User user){
        if(StringUtils.isEmpty(user.getPassword())){
           return Collections.singletonMap("Error","Password is not empty");
        }
        return null;
    }


}
