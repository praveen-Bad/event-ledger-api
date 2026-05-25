package com.test.eventLedger;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EventLedgerApplicationTests {


	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	void shouldHandleDuplicateEvent() throws Exception {

		String json = """
                {
                  "eventId":"evt-001",
                  "accountId":"acct-123",
                  "type":"CREDIT",
                  "amount":100,
                  "currency":"USD",
                  "eventTimestamp":"2026-05-15T14:02:11Z"
                }
                """;

		mockMvc.perform(post("/events")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isOk());

		mockMvc.perform(post("/events")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isOk());
	}

	@Test
	void shouldReturnBadRequestForInvalidAmount() throws Exception {

		String json = """
                {
                  "eventId":"evt-002",
                  "accountId":"acct-123",
                  "type":"CREDIT",
                  "amount":-10,
                  "currency":"USD",
                  "eventTimestamp":"2026-05-15T14:02:11Z"
                }
                """;

		mockMvc.perform(post("/events")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isBadRequest());
	}
}
