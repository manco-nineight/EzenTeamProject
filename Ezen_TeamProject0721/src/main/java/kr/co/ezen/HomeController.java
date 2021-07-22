package kr.co.ezen;


import java.util.Locale;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
   
  
   @RequestMapping(value = "/home2", method = RequestMethod.GET)
   public void home2() {
      
   }
   
   
   
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String home(Locale locale, Model model) {
      
      System.out.println(":::::::::::::::::::::");
      
      
      
      
      
      return "/home";
   }
   
}