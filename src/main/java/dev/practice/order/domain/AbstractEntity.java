package dev.practice.order.domain;

import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
	// @CreatedDate // ZonedDateTime 지원하지 않음.
	@CreationTimestamp
	private ZonedDateTime createdAt;

	// @LastModifiedDate // ZonedDateTime 지원하지 않음.
	@UpdateTimestamp
	private ZonedDateTime updatedAt;
}
