package dev.rdkln.tracker.weighthistory.domain;

import java.time.LocalDateTime;

import dev.rdkln.tracker.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeightHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double weight;

    private LocalDateTime weighInDate;

    @ManyToOne(optional = false)
    private User user;

    public WeightHistory(User user, LocalDateTime date, Double weight) {
        this.user = user;
        this.weighInDate = date;
        this.weight = weight;
    }
}
