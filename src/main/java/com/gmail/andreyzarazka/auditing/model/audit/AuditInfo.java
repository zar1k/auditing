package com.gmail.andreyzarazka.auditing.model.audit;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditInfo implements Serializable {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createDate;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant lastModifiedDate;
}
