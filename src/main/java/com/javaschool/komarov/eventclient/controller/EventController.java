package com.javaschool.komarov.eventclient.controller;

import com.javaschool.komarov.eventclient.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Method to get events from Reha
     *
     * @param model model with events
     * @return model
     */
    @GetMapping(value = "")
    public String sendSchedules(Model model) {
        model.addAttribute("events", eventService.getEvents());
        model.addAttribute("connectionMessage", eventService.getConnectionMessage());
        model.addAttribute("date", LocalDate.now());
        return "event";
    }

}
