package com.company.webapp.viewmaker;

import com.company.webapp.service.datamanager.OrderDataManager;
import com.company.webapp.user.daoimpl.newUserDAOImpl;
import com.company.webapp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ViewMaker {

    // @Autowired
    // private UserDataManager userDataManager;
    @Qualifier("newUserDAOImpl")
    @Autowired
    private newUserDAOImpl userDAO;
    @Autowired
    private OrderDataManager orderDataManager;

    public ModelAndView getIndexPage(String viewName) {
        ModelAndView index = new ModelAndView(viewName);
        index.addObject("userToSubmit", new User());
        index.addObject("currentUser", new User());
        return index;
    }

    public ModelAndView getUsersList(ModelAndView model) {
        model.addObject("usersList", userDAO.getAllExistUsers());
        return model;
    }

   /* public ModelAndView getUpdateView(int userId, String viewName) {
        ModelAndView model = new ModelAndView();
        model.addObject("userToUpdate", userDataManager.getUserById(userId));
        model.setViewName(viewName);
        return model;
    }

    public ModelAndView getOrdersPage(int userId, String viewName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ordersList", orderDataManager.getOrderByUser(userId));
        modelAndView.addObject("orderToAdd", new Order());
        modelAndView.setViewName(viewName);
        return modelAndView;
    }*/
}
