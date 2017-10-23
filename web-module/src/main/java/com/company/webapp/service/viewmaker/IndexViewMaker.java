package com.company.webapp.service.viewmaker;


import com.company.webapp.entity.User;
import com.company.webapp.service.datamanager.UserDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class IndexViewMaker {

    @Autowired
    private UserDataManager userDataManager;

    public ModelAndView initIndexPage(String viewName) {
        ModelAndView index = new ModelAndView(viewName);
        index.addObject("userToSubmit", new User());
        index.addObject("currentUser", new User());
        return index;
    }

    public ModelAndView getUsersList(ModelAndView model) {
        model.addObject("usersList", userDataManager.getAllExistUsers());
        return model;
    }
}
