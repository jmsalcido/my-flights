package com.nearsoft.myflights.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: jsalcido
 * Date: 8/23/13
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "index")
    public String index(){
        return "redirect:/pages/index.html";
    }

}
