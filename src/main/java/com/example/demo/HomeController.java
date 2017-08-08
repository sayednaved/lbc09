package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/list")
    public String listPeople(Model model){
        model.addAttribute("people", personRepository.findAll());
        return "listpeople";
    }

    @RequestMapping("/add")
    public String addPerson(Model model){
        model.addAttribute("person", new Person());
        return "peopleform";
    }

    @RequestMapping("/process")
    public String processForm(@ModelAttribute Person person){
        personRepository.save(person);
        return "redirect:/list";
    }

    @RequestMapping("/detail/{id}")
    public String showPerson(@PathVariable("id") long id, Model model){
        model.addAttribute("person", personRepository.findOne(id));
        return "detail";
    }

    @RequestMapping("/delete/{id}")
    public String delPerson(@PathVariable("id") long id, Model model){
        personRepository.delete(id);
        return "redirect:/list";
    }
}
