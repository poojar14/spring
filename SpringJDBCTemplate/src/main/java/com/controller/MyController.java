package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.EmpDao;
import com.model.Employee;

@Controller
public class MyController 
{
	@Autowired
	EmpDao dao;
	@RequestMapping(value="/save",method = RequestMethod.POST)  
    public void save(Employee e)
	{  
        dao.save(e);  
       //return new ModelAndView("redirect:/viewemp"); 
    }  
	@RequestMapping("/viewemp")  
    public ModelAndView viewemp(){  
        List<Employee> list=dao.getEmployees();  
        return new ModelAndView("viewemp","list",list);  
    } 
	
	@RequestMapping(value="/delete/{id}")  
    public ModelAndView delete(@PathVariable int id){  
        dao.delete(id);  
        return new ModelAndView("redirect:/viewemp");  
    }  
	
	@RequestMapping(value="/editemp/{id}")  
    public ModelAndView edit(@PathVariable int id){  
        Employee emp=dao.getEmpById(id);  
        return new ModelAndView("editform","command",emp);  
    }  
    /* It updates model object. */  
    @RequestMapping(value="/editsave",method = RequestMethod.POST)  
    public ModelAndView editsave(@ModelAttribute("emp") Employee emp){  
        dao.update(emp);  
        return new ModelAndView("redirect:/viewemp");  
    }  
}
