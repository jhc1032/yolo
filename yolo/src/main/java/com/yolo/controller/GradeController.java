package com.yolo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.helpers.CyclicBuffer;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yolo.model.biz.ClassInfoService;
import com.yolo.model.biz.SubjectInfoService;
import com.yolo.model.domain.ClassInfo;
import com.yolo.model.domain.Course;
import com.yolo.model.domain.PageBean;
import com.yolo.model.domain.SubjectInfo;
import com.yolo.model.domain.SubjectMlist;

@Controller
public class GradeController {

	@Autowired
	private SubjectInfoService subservice;

	@ExceptionHandler
	public ModelAndView handler(Exception e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		ModelAndView model = new ModelAndView("index");
		model.addObject("msg", e.getMessage());
		model.addObject("content", "ErrorHandler.jsp");
		return model;
	}

	@RequestMapping(value = "insertGradeForm.do", method = RequestMethod.GET)
	public String register(Model model, HttpSession session, int createcode,
			int cscore) {
		String id = (String) session.getAttribute("id");
		List<SubjectInfo> sub = subservice.searchSubject(id);
		model.addAttribute("slist", sub);
		model.addAttribute("cscore", cscore);
		model.addAttribute("createcode", createcode);

		if (createcode > 0 && createcode <= 100000) {
			List<SubjectMlist> mlist = subservice.searchMlist(createcode);
			model.addAttribute("mlist", mlist);
		}
		model.addAttribute("content", "grade/insertGradeForm.jsp");
		return "index";
	}
	
	@RequestMapping(value="insertScore.do", method=RequestMethod.GET)
	public String score(String id, int createcode, Model model, double score, int cscore){
		
		
		
		Course course = new Course();
		course.setId(id);
		course.setCreatecode(createcode);
		course.setScore(score);
		model.addAttribute("cscore", cscore);
		model.addAttribute("createcode", createcode);
		subservice.updateScore(course);
		//model.addAttribute("content", "grade/insertGradeForm.jsp");
		
		return "redirect: insertGradeForm.do";
		
		
	}

}