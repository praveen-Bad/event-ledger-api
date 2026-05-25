package com.test.eventLedger.controller;


import com.test.eventLedger.dto.BalanceResponse;
import com.test.eventLedger.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final EventService service;

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BalanceResponse> getBalance(
            @PathVariable String accountId) {

        return ResponseEntity.ok(
                service.getBalance(accountId)
        );
    }
}