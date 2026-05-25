package com.test.eventLedger.service;

import com.test.eventLedger.dto.BalanceResponse;
import com.test.eventLedger.dto.EventRequest;
import com.test.eventLedger.dto.EventResponse;
import com.test.eventLedger.entity.Event;
import com.test.eventLedger.exception.ResourceNotFoundException;
import com.test.eventLedger.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;

    public EventResponse createEvent(EventRequest request) {

        return repository.findByEventId(request.getEventId())
                .map(this::mapToResponse)
                .orElseGet(() -> {

                    Event event = Event.builder()
                            .eventId(request.getEventId())
                            .accountId(request.getAccountId())
                            .type(request.getType())
                            .amount(request.getAmount())
                            .currency(request.getCurrency())
                            .eventTimestamp(request.getEventTimestamp())
                            .metadata(request.getMetadata())
                            .build();

                    Event saved = repository.save(event);

                    return mapToResponse(saved);
                });
    }

    public EventResponse getEvent(String eventId) {

        Event event = repository.findByEventId(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found"));

        return mapToResponse(event);
    }

    public List<Event> getEventsByAccount(String accountId) {
        return repository.findByAccountIdOrderByEventTimestampAsc(accountId);
    }

    public BalanceResponse getBalance(String accountId) {

        return new BalanceResponse(
                accountId,
                repository.calculateBalance(accountId)
        );
    }

    private EventResponse mapToResponse(Event event) {

        return EventResponse.builder()
                .eventId(event.getEventId())
                .accountId(event.getAccountId())
                .type(event.getType())
                .amount(event.getAmount())
                .currency(event.getCurrency())
                .eventTimestamp(event.getEventTimestamp())
                .metadata(event.getMetadata())
                .build();
    }
}
