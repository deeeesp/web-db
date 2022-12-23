package ru.stazaev.agency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.stazaev.agency.entity.Flat;
import ru.stazaev.agency.entity.Test;
import ru.stazaev.agency.entity.Worker;
import ru.stazaev.agency.service.FlatService;
import ru.stazaev.agency.service.TestServ;
import ru.stazaev.agency.service.WorkerService;

@Controller
public class Cont {
    @Autowired
    private TestServ serv;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private FlatService flatService;

    @GetMapping("/tests")
    public String v(Model model){
        model.addAttribute("tests",serv.getAll());
        return "test";
    }

    @GetMapping("/tests/new")
    public String nn(Model model){
        model.addAttribute("tests",new Test());
        return "test_form";
    }

    @PostMapping("/tests/save")
    public String saveTutorial(Test test, RedirectAttributes redirectAttributes) {
        try {
//            System.out.println(test.toString());
            serv.save(test);
            redirectAttributes.addFlashAttribute("message", "The Tutorial has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/tests";
    }


    @GetMapping("/test")
    public String t(){
        Worker worker = workerService.getById(1);
        System.out.println(worker.getFlats().get(0).toString());
//        Flat flat = new Flat(4,44,1,worker);
//        workerService.save(worker);
//        flatService.save(flat);
        return "test";
    }
}
