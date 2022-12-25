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
import ru.stazaev.agency.entity.Worker;
import ru.stazaev.agency.service.ClientService;
import ru.stazaev.agency.service.WishService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/wishes")
public class WishController {
    @Autowired
    private WishService wishService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/getAll/{page}")
    public String getAll(Model model,@Param("keyword") String keyword,@PathVariable int page){
        List<Wish> wishes = new ArrayList<>();
        System.out.println(keyword);
        if (keyword == null) {
            wishService.getAll().forEach(wishes::add);
        } else {
            wishService.getByClientId(Long.valueOf(keyword)).forEach(wishes::add);
            model.addAttribute("keyword", keyword);
        }
        System.out.println(wishes.size());
        List<Wish> list = new ArrayList<>();

        int start = (page-1)*30;
        int end = start+30;
        if (end>wishes.size()){
            end = wishes.size();
        }
        for (int i = start; i < end; i++) {
            list.add(wishes.get(i));
        }
        model.addAttribute("wishes", list);
        model.addAttribute("page",page+1);
        model.addAttribute("prevpage",page-1);
        return "wishes/wishes";
    }

    @GetMapping("/get")
    public String get(Model model){
        for (int i = 0; i < 20; i++) {
            int flats = (int) (Math.random() * 4 + 1);
            int floor = (int) (Math.random() * 15000000 + 2000000);
            int met;
            switch (flats) {
                case 1 -> met = (int) (Math.random() * 20 + 20);
                case 2 -> met = (int) (Math.random() * 20 + 40);
                case 3 -> met = (int) (Math.random() * 40 + 60);
                case 4 -> met = (int) (Math.random() * 40 + 80);
                default -> met = 66;
            }
            long wor = (long) (Math.random() *7 +1);
            Wish wish = new Wish(met,flats,floor,clientService.getById(wor));
            wishService.save(wish);
        }
//        model.addAttribute("flats",flatService.getAll());
        return "wishes/wishes";
    }

    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable long id){
        model.addAttribute("wishes",wishService.getById(id));
        return "wishes/wishes";
    }

    @GetMapping("/new")
    public String newWorker(Model model){
        model.addAttribute("wishes",new Wish());
        model.addAttribute("pageTitle", "Create new wish");

        return "wishes/wishes_form";
    }

    @PostMapping("/save")
    public String saveFlat(Wish wish, RedirectAttributes redirectAttributes){
        try {
            wishService.save(wish);
            redirectAttributes.addFlashAttribute("message", "The Wish has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/wishes/getAll/1";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id){
        wishService.deleteById(id);
        return "redirect:/wishes/getAll/1";
    }


    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Wish wish = wishService.getById(id);

            model.addAttribute("wishes", wish);
            model.addAttribute("pageTitle", "Edit Wish (ID: " + id + ")");

            return "wishes/wishes_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/wishes/getAll/1";
        }
    }
}


