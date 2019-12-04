package org.hongxi.whatsmars.spring.boot.controller;

import org.hongxi.whatsmars.spring.boot.config.UserConfig;
import org.hongxi.whatsmars.spring.boot.common.LocaleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

/**
 * Created by shenhongxi on 2017/3/27.
 */
@Api(tags = "Sample", description = "样例")
@Controller
public class SampleController {

    @Autowired
    private UserConfig userConfig;

    @Autowired
    private LocaleService localeService;

    /**
     * 设置区域解析器 (default is AcceptHeaderLocaleResolver)
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("language");
        localeResolver.setCookieMaxAge(Integer.MAX_VALUE);
        localeResolver.setDefaultLocale(LocaleService.DEFAULT_LOCALE); // Locale.getDefault()
        return localeResolver;
    }

    @RequestMapping(value = "/changeLang", method = RequestMethod.POST)
    @ResponseBody
    public String changeLang(HttpServletRequest request, HttpServletResponse response, @RequestParam String lang){
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if ("zh".equals(lang)) {
            localeResolver.setLocale(request, response, Locale.SIMPLIFIED_CHINESE);
        } else if("en".equals(lang)){
            localeResolver.setLocale(request, response, Locale.US);
        }
        return "lang:" + LocaleContextHolder.getLocale().getLanguage();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map<String,Object> map) {
        System.out.println(LocaleService.isUSLocale());
        map.put("hello", "Hi, boy!");
        map.put("country", localeService.getMessage("country"));
        return "index";
    }

    @Profile("test,dev")
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    @ResponseBody
    public String welcome() {
        return userConfig.getWelcome();
    }

}
