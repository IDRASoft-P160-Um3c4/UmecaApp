package com.umeca.service.shared;

import org.springframework.web.servlet.ModelAndView;

public interface MainPageService {
    ModelAndView generatePage(String s, ModelAndView model, Long userId);
}
