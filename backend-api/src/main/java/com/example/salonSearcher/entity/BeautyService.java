package com.example.salonSearcher.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * A service a Provider offers (haircut, manicure, facial, ...).
 * Many BeautyServices belong to one Provider (0..* -> 1).
 *
 * Named BeautyService (not Service) so it does not clash with
 * Spring's @Service annotation.
 */
@Entity
@Table(name = "beauty_services")
@Getter
@Setter
@NoArgsConstructor
public class BeautyService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    private String category;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    @Column(nullable = false)
    private Integer durationMinutes;

    // Owning side of the relationship (this table holds the provider_id column).
    // Serialized in JSON responses; the Provider side is @JsonIgnore, so no loop.
    // Loaded eagerly (the @ManyToOne default) so it can be serialized to JSON
    // after the service method ends when open-in-view=false.
    // The client links a provider by sending {"provider": {"id": 1}}
    // (or switch to a providerId in a request DTO).
    @NotNull(message = "Provider is required")
    @ManyToOne(optional = false)
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;
}
