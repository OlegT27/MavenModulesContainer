package com.company.webapp.service;

import org.springframework.stereotype.Service;

@Service
public class Validator {

    public boolean dataValidator(String[] textArgs, Object dateArg) {
        //TODO : in Spring you could use something more interesting
        for (String s : textArgs) {
            if ("".equals(s))
                return false;
        }
        return true;
    }
}
