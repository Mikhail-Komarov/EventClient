package com.javaschool.komarov.eventclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaschool.komarov.eventclient.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Slf4j
@Service
public class EventService {
    private List<Event> events;
    private final SimpMessagingTemplate messageTemplate;
    private final ObjectMapper objectMapper;
    private final String EVENT_RESPONSE_QUEUE = "QUEUE";
    private static final String target = "http://localhost:8080/event/today";

    @Autowired
    public EventService(SimpMessagingTemplate messageTemplate, ObjectMapper objectMapper) {
        this.messageTemplate = messageTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Method to get updated events from message broker
     *
     * @param message message
     * @throws JsonProcessingException if converting error is exists
     */
    @JmsListener(destination = EVENT_RESPONSE_QUEUE)
    public void updateEvents(String message) throws JsonProcessingException {
        events = objectMapper.readValue(message, new TypeReference<List<Event>>() {
        });
        messageTemplate.convertAndSend("/update", "Events");
        log.info("Event update was successful");
    }

    /**
     * Method to get events after start
     *
     * @throws JsonProcessingException if converting error is exists
     */
    @PostConstruct
    public void initEvents() throws JsonProcessingException {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target(target);
            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            String response = invocationBuilder.get(String.class);
            events = objectMapper.readValue(response, new TypeReference<List<Event>>() {
            });
            log.info("Initialization of events was successful");
        } catch (ProcessingException e) {
            log.error("Reha connection error " + e);
        }
    }

    /**
     * Method to get events
     *
     * @return events
     */
    public List<Event> getEvents() {
        return events;
    }

}
