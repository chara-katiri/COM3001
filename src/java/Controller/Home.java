package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/*
 *Controller for home 
 */
@Controller
public class Home {

    /*
     *Request mapping to index page – is the home page
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String homePage() {
        /*
         *return statement returns the name of the home page file     
         */
        return "index";
    }

    /*
     *if request for mapping is unsuccessful return error 403. Point user to error page 403.
     */
    @RequestMapping(value = "/errorPage", method = RequestMethod.GET)
    public String accessDeniedPage() {
        return "errorPage";
    }
 
}
