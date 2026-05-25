package com.test.eventLedger.repository;


import com.test.eventLedger.entity.Event;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByEventId(String eventId);

    List<Event> findByAccountIdOrderByEventTimestampAsc(String accountId);

    @Query("""
            SELECT COALESCE(SUM(
                CASE
                    WHEN e.type = 'CREDIT'
                    THEN e.amount
                    ELSE -e.amount
                END
            ), 0)
            FROM Event e
            WHERE e.accountId = :accountId
            """)
    BigDecimal calculateBalance(@Param("accountId") String accountId);
}
