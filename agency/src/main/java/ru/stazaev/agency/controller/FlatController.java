package ru.stazaev.agency.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.stazaev.agency.entity.Flat;
import ru.stazaev.agency.entity.Wish;
import ru.stazaev.agency.service.FlatService;
import ru.stazaev.agency.service.WorkerService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/flats")
public class FlatController {
    @Autowired
    private FlatService flatService;

    @Autowired
    private WorkerService workerService;
/*
    @GetMapping("/getAll")
    public String getAll(Model model,@Param("keyword") String keyword){
        List<Flat> flats = new ArrayList<>();

        if (keyword == null) {
            flatService.getAll().forEach(flats::add);
        } else {
            flatService.getByWorkerId(Long.valueOf(keyword)).forEach(flats::add);
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("flats", flats);
        return "flats/flats";
    }


 */

    @GetMapping("/getAll/{page}")
    public String getAll(Model model,@Param("keyword") String keyword,@PathVariable int page){
        List<Flat> flats = new ArrayList<>();
        if (keyword == null) {
            flats.addAll(flatService.getAll());
        } else {
            flats.addAll(flatService.getByWorkerId(Long.parseLong(keyword)));
            model.addAttribute("keyword", keyword);
        }
        List<Flat> list = new ArrayList<>();
        int start = (page-1)*30;
        int end = start+30;
        if (end>flats.size()){
            end = flats.size();
        }
        for (int i = start; i < end; i++) {
            list.add(flats.get(i));
        }
        model.addAttribute("flats", list);
        model.addAttribute("page",page+1);
        model.addAttribute("prevpage",page-1);
        return "flats/flats";
    }



    @GetMapping("/get")
    public String getAll(Model model){
        for (int i = 0; i < 1000; i++) {
            int flats = (int) (Math.random() * 4 + 1);
            int floor = (int) (Math.random() * 20 + 1);
            int met;
            switch (flats) {
                case 1 -> met = (int) (Math.random() * 20 + 20);
                case 2 -> met = (int) (Math.random() * 20 + 40);
                case 3 -> met = (int) (Math.random() * 40 + 60);
                case 4 -> met = (int) (Math.random() * 40 + 80);
                default -> met = 66;
            }
            long wor = (long) (Math.random() *5 +1);
            Flat flat = new Flat(floor,met,flats,workerService.getById(wor));
            flatService.save(flat);
        }
        model.addAttribute("flats",flatService.getAll());
        return "flats/flats";
    }

    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable long id){
        model.addAttribute("flats",flatService.getById(id));
        return "flats/flats";
    }

    @GetMapping("/new")
    public String newWorker(Model model){
        model.addAttribute("flat",new Flat());
        model.addAttribute("pageTitle", "Create new flat");
        return "flats/flats_form";
    }

    @PostMapping("/save")
    public String saveFlat(Flat flat, RedirectAttributes redirectAttributes){
        try {
            flatService.save(flat);
            redirectAttributes.addFlashAttribute("message", "The Flat has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/flats/getAll/1";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id){
        flatService.deleteById(id);
        return "redirect:/flats/getAll/1";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Flat flat = flatService.getById(id);

            model.addAttribute("flat", flat);
            model.addAttribute("pageTitle", "Edit Flat (ID: " + id + ")");

            return "flats/flats_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/flats/getAll/1";
        }
    }
}

