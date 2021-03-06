package com.xst.controller;

import com.xst.bean.DocCategory;
import com.xst.dao.LessonCatgoryDao;
import com.xst.dao.LessonDocDao;
import com.xst.dao.ResourcesDao;
import com.xst.entity.LessonDocument;
import com.xst.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sl on 16-5-3.
 */
@Controller
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LessonDocDao docDao;

    @Autowired
    private LessonCatgoryDao categoryDao;

    @RequestMapping(value = "/list/{id}" , method = RequestMethod.GET)
    public String list(Model model, @PathVariable("id") int id,String page){

        int pageNum = page == null ? 1 : Integer.valueOf(page);
        DocCategory docCategory=null;
        if(id==0){
            docCategory = categoryDao.getDocCategory(1);
        }
        else{
            docCategory = categoryDao.getDocCategory(id);
        }
        Page<LessonDocument> docPage = docDao.queryAllDocPage(pageNum,15,id);
        model.addAttribute("docCategory",docCategory);
        model.addAttribute("page",docPage);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("id",id);
        return "/library/list";
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public String findList(Model model, @PathVariable("id") int id,String page){


        int pageNum = page == null ? 1 : Integer.valueOf(page);
        DocCategory docCategory = categoryDao.getDocCategory(id);
        Page<LessonDocument> docPage = docDao.queryDocPage(pageNum,15,id);
        model.addAttribute("docCategory",docCategory);
        model.addAttribute("page",docPage);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("id",id);
        return "/library/list";
    }

    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public String list(Model model){
        return "redirect:/library/list/0";
    }

}
