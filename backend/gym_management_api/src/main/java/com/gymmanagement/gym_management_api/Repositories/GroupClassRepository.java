package com.gymmanagement.gym_management_api.Repositories;

import com.gymmanagement.gym_management_api.Entities.GroupClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface GroupClassRepository extends JpaRepository<GroupClass, Integer> {
    long countByTrainer_UserId(Integer trainerId);
    Iterable<GroupClass>findAllByTrainer_UserId(Integer trainerId);

    @Query("SELECT g FROM GroupClass g JOIN g.client_list c WHERE c.id = :clientId")
    Iterable<GroupClass> findAllByClientId(@Param("clientId") Integer clientId);

    @Query("""
        SELECT g FROM GroupClass g
        JOIN g.client_list c
        WHERE c.id = :clientId
        AND g.date_time > :now
        """)
    Iterable<GroupClass> findAllFutureByClientId(@Param("clientId") Integer clientId, @Param("now") LocalDateTime now);

    @Query("""
        SELECT g FROM GroupClass g
        WHERE g.date_time > :now
        AND NOT EXISTS (
            SELECT 1
            FROM g.client_list c
            WHERE c.id = :clientId
        )
        """)
    Iterable<GroupClass> findAllFutureWhereClientNotPresent(@Param("clientId") Integer clientId, @Param("now") LocalDateTime now);


}
