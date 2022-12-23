package ru.stazaev.agency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.stazaev.agency.entity.Worker;
import ru.stazaev.agency.service.WorkerService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/workers")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping("/getAll")
    public String getAll(Model model){
        model.addAttribute("workers",workerService.getAll());
        return "workers/workers";
    }

    @GetMapping("/getAll/{page}")
    public String getAll(Model model,@PathVariable int page){
        System.out.println(page);
        List<Worker> workers = new ArrayList<>();
        List<Worker> all = workerService.getAll();
        for (int i = page; i < page+1; i++) {
            workers.add(all.get(i));
        }
        model.addAttribute("workers",workers);
        model.addAttribute("page",page+1);
        return "workers/workers";
    }



    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable long id){
        Worker worker = workerService.getById(id);
        model.addAttribute("workers",workerService.getById(id));
        return "workers/workers";
    }

    @GetMapping("/new")
    public String newWorker(Model model){
        Worker worker = new Worker();
        model.addAttribute("workers",worker);
        model.addAttribute("pageTitle", "Create new worker");

        return "workers/workers_form";
    }

    @PostMapping("/save")
    public String saveWorker(Worker worker, RedirectAttributes redirectAttributes){
        try {
            workerService.save(worker);
            redirectAttributes.addFlashAttribute("message", "The Worker has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/workers/getAll";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id){
        workerService.deleteById(id);
        return "redirect:/workers/getAll";
    }

    @GetMapping("/update/{id}")
    public String editTutorial(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Worker worker = workerService.getById(id);
            model.addAttribute("workers", worker);
            model.addAttribute("pageTitle", "Edit Worker (ID: " + id + ")");

            return "workers/workers_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/workers/getAll";
        }
    }

    /*
    @PostMapping("/saveUpd")
    public String saveUpdateWorker(Worker worker, RedirectAttributes redirectAttributes){
        try {
            System.out.println(worker.toString());
            workerService.update(worker);
            redirectAttributes.addFlashAttribute("message", "The Worker has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/workers/getAll";
    }

     */

}
