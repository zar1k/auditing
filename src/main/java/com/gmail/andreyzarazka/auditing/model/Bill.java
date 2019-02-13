package com.gmail.andreyzarazka.auditing.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gmail.andreyzarazka.auditing.model.audit.AuditInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Audited
public class Bill extends AuditInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private long uuid;

    @NotNull
    private BigDecimal balance;

    @NotNull
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "addresses_id")
    private Address address;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Provider> providers = new HashSet<>();
}
