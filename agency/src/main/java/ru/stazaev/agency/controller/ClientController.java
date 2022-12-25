package ru.stazaev.agency.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.stazaev.agency.entity.Client;
import ru.stazaev.agency.entity.Wish;
import ru.stazaev.agency.entity.Worker;
import ru.stazaev.agency.service.ClientService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/getAll/{page}")
    public String getAll(Model model,@PathVariable int page){
        List<Client> clients = new ArrayList<>();
        List<Client> all = clientService.getAll();

        int start = (page-1)*30;
        int end = start+30;
        if (end>all.size()){
            end = all.size();
        }
        for (int i = start; i < end; i++) {
            clients.add(all.get(i));
        }
        model.addAttribute("clients",clients);
        model.addAttribute("page",page+1);
        model.addAttribute("prevpage",page-1);
        return "clients/clients";
    }

    @GetMapping("/get/{id}")
    public String getById(Model model, @PathVariable long id){
        model.addAttribute("clients",clientService.getById(id));
        return "clients/clients";
    }

    @GetMapping("/new")
    public String newWorker(Model model){
        model.addAttribute("clients",new Client());
        model.addAttribute("pageTitle", "Create new client");
        return "clients/clients_form";
    }

    @PostMapping("/save")
    public String saveFlat(Client client, RedirectAttributes redirectAttributes){
        try {
            clientService.save(client);
            redirectAttributes.addFlashAttribute("message", "The Client has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/clients/getAll/1";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id){
        clientService.deleteById(id);
        return "redirect:/clients/getAll/1";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Client client = clientService.getById(id);

            model.addAttribute("client", client);
            model.addAttribute("pageTitle", "Edit Client (ID: " + id + ")");

            return "clients/clients_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/clients/getAll/1";
        }
    }
}

