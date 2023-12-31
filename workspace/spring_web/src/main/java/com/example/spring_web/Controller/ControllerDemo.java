package com.example.spring_web.Controller;

import com.example.spring_web.form.CalcForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring_web.repository.TableRepository;
import java.util.List;

@Controller
public class ControllerDemo {

    @Autowired
    TableRepository tableRepository;


    @GetMapping("/index")
    public String showIndex(){
        return "index";
    }

    @GetMapping ("/home")
    public String showHome(){
        return "home";
    }

//    @GetMapping("/getNameAll")
//    public List<String> getNameAll() {
//        return tableRepository.getAllName();
//    }

    @GetMapping("/function/{no}")
    public String selectFunction(@PathVariable Integer no) {
        String view = null;
        switch (no) {
            case 1: view = "pathVariable/function1";
                break;
            case 2: view = "pathVariable/function2";
                break;
            case 3: view = "pathVariable/function3";
                break;
        }
        return view;
    }

    @PostMapping(value = "send", params = "a")
    public String showAView(){
        return "submit/a";
    }

    @PostMapping(value = "send", params = "b")
    public String showBView(){
        return "submit/b";
    }

    @PostMapping(value = "send", params = "c")
    public String showCView(){
        return "submit/c";
    }
    @ModelAttribute
    public CalcForm setUpForm() {
        return new CalcForm();
    }

    @GetMapping("show")
    public String showShow() {
        return "entry";
    }

    @PostMapping("calc")
    public String confirmView(@Validated CalcForm form, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "entry";
        }
        Integer result = form.getLeftNum() + form.getRightNum();

        model.addAttribute("result", result);

        return "confirm";
    }
}
