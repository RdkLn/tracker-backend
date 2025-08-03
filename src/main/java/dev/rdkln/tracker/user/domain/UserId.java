package dev.rdkln.tracker.user.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(Long id) {
}
