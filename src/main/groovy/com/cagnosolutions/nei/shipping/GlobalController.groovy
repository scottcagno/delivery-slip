package com.cagnosolutions.nei.shipping

import com.cagnosolutions.nei.shipping.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

import javax.servlet.http.HttpSession
import java.security.Principal

/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */

@Controller
class IndexController {

    @RequestMapping(value="/", method=RequestMethod.GET)
    String index() {
        "index"
    }
}

@Controller
class Authentication {

    @Autowired
    UserService userData

    @RequestMapping(value=["/login"])
    String login() {
        "login"
    }

    @RequestMapping(value=["/secure/login"], method=[RequestMethod.GET])
    String secureLogin(@RequestParam String forward, HttpSession session, Principal principal) {
        if(principal.name != "admin"){
            def user = userData.findOne(principal.name)
            user.lastSeen = System.currentTimeMillis()
            userData.save user
        }
        session.setAttribute "authenticated", principal.name
        "redirect:/secure/" + forward
    }
}

@Controller
class ErrorHandler {

    @ExceptionHandler(value=[Exception.class, RuntimeException.class])
    String errors(Exception e, Model model) {
        def stack = []
        for (frame in e.getStackTrace()) {
            stack << frame.toString()
        }
        model.addAllAttributes([message: e.getLocalizedMessage(), exception: stack.join('\n')])
        return "error";
    }
}
