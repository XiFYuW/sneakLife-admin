package com.sneaklife.ut.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author https://github.com/XiFYuW
 * @date 2019/11/18 15:44
 */
public class SneakLifeServletFactory {

    public static SneakLifeServlet getSneakLifeServletObject(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        return new SneakLifeServlet(httpServletRequest,httpServletResponse);
    }
}
