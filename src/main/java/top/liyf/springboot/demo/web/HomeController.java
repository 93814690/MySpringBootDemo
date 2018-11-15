package top.liyf.springboot.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import top.liyf.springboot.demo.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @author liyf
 * @description
 * @date Created in 2018\10\22
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/")
    public String hello(Model model) {
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage("welcome", null, locale);
        model.addAttribute("ht", msg);
        model.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping("/changeLanguage")
    public String changeLanguage(HttpServletRequest request, HttpServletResponse response, String lang) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if ("zh".equals(lang)) {
            localeResolver.setLocale(request, response, new Locale("zh", "CN"));
        } else if ("en".equals(lang)) {
            localeResolver.setLocale(request, response, new Locale("en", "US"));
        }
        return "redirect:/";
    }
}
