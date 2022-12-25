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
import ru.stazaev.agency.entity.Deal;
import ru.stazaev.agency.entity.Flat;
import ru.stazaev.agency.entity.Wish;
import ru.stazaev.agency.service.DealService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/deals")
public class DealController {
    @Autowired
    private DealService dealService;

    @GetMapping("/getAll/{page}")
    public String getAll(Model model,@Param("keyword") String keyword,@PathVariable int page){
        List<Deal> deals = new ArrayList<>();
        System.out.println(keyword);
        if (keyword == null) {
            dealService.getAll().forEach(deals::add);
        } else {
            String[] s = keyword.split(",");
            dealService.getByWorkerIdOrClientIdOrFlatId(Long.valueOf(s[0]),Long.valueOf(s[1]),Long.valueOf(s[2])).forEach(deals::add);
            model.addAttribute("keyword", keyword);
        }
        List<Deal> list = new ArrayList<>();
        int start = (page-1)*30;
        int end = start+30;
        if (end>deals.size()){
            end = deals.size();
        }
        System.out.println(start);
        System.out.println(end);
        for (int i = start; i < end; i++) {
            list.add(deals.get(i));
        }

        model.addAttribute("deals", list);

        model.addAttribute("page",page+1);
        model.addAttribute("prevpage",page-1);
        return "deals/deals";
    }

    @GetMapping("/get")
    public String getAll(Model model){
        for (int i = 0; i < 30; i++) {
            long wI = (long) (Math.random()*5 +1);
            long cI = (long) (Math.random()*7 +1);
            long fI = (long) (Math.random()*900 +1);
            int cost = (int) (Math.random()* 15000000 + 2000000);
            String s = "buy";
            if (i%5==0){
                s = "rent";
            }
//            Deal deal = new Deal(wI,fI,cI,s,cost);
//            dealService.save(deal);
        }
//        model.addAttribute("flats",dealService.getAll());
        return "flats/flats";
    }

    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable long id){
        model.addAttribute("deals",dealService.getById(id));
        System.out.println(dealService.getById(id));
        return "deals/deals";
    }

    @GetMapping("/new")
    public String newWorker(Model model){
        model.addAttribute("deal",new Deal());
        model.addAttribute("pageTitle", "Create new deal");

        return "deals/deals_form";
    }

    @PostMapping("/save")
    public String saveFlat(Deal deal, RedirectAttributes redirectAttributes){
        try {
            dealService.save(deal);
            redirectAttributes.addFlashAttribute("message", "The Deal has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/deals/getAll/1";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id){
        dealService.deleteById(id);
        return "redirect:/deals/getAll/1";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Deal deal = dealService.getById(id);

            model.addAttribute("deal", deal);
            model.addAttribute("pageTitle", "Edit Deal (ID: " + id + ")");

            return "deals/deals_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/deals/getAll/1";
        }
    }

}

