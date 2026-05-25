package com.test.eventLedger.controller;

import com.test.eventLedger.dto.EventRequest;
import com.test.eventLedger.dto.EventResponse;
import com.test.eventLedger.entity.Event;
import com.test.eventLedger.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService service;

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(
            @Valid @RequestBody EventRequest request) {

        EventResponse response = service.createEvent(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEvent(
            @PathVariable String id) {

        return ResponseEntity.ok(service.getEvent(id));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEventsByAccount(
            @RequestParam String account) {

        return ResponseEntity.ok(
                service.getEventsByAccount(account)
        );
    }
}