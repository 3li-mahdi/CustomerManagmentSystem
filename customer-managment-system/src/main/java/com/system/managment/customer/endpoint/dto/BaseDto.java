package com.system.managment.customer.endpoint.dto;

import java.time.Instant;
import java.util.Objects;

abstract class BaseDto {
	
	private Long id;
    private Instant createdAt;
    private Instant updatedAt;

    protected BaseDto() {
    }

    public BaseDto(Long id, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDto)) return false;
        BaseDto baseDto = (BaseDto) o;
        return Objects.equals(id, baseDto.id) &&
            Objects.equals(createdAt, baseDto.createdAt) &&
            Objects.equals(updatedAt, baseDto.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt);
    }

    protected String fieldsString() {
        return "id=" + id +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt;
    }

    @Override
    public String toString() {
        return "BaseDto{ " + fieldsString() + " }";
    }
}
