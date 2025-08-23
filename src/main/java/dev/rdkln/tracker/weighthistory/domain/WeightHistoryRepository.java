package dev.rdkln.tracker.weighthistory.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.rdkln.tracker.user.domain.UserId;


@Repository
public interface WeightHistoryRepository extends JpaRepository<WeightHistory,Long> {

    List<WeightHistory> findByUserId(UserId userId);

    @Query("""
            delete from WeightHistory wh where wh.userId = :userId and wh.weighInDate between :startDate and :endDate
            """)
    @Modifying
    void deleteWeighEntryByDate(UserId userId, LocalDateTime startDate,LocalDateTime endDate);
}
