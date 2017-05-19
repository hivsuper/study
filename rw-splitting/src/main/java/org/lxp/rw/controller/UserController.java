package org.lxp.rw.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.annotation.Resource;

import org.lxp.rw.model.User;
import org.lxp.rw.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/add.json", method = POST)
    public User add(@RequestParam String name) {
        return userService.add(name);
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}.json", method = DELETE)
    public boolean delete(@PathVariable int userId) {
        return userService.deleteUserById(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}.json", method = GET)
    public User query(@PathVariable int userId) {
        return userService.queryUserById(userId);
    }
}
