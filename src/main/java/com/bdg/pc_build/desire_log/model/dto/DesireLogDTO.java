package com.bdg.pc_build.desire_log.model.dto;

import com.bdg.pc_build.desire_log.model.entity.DesireLog;
import com.bdg.pc_build.desire_log.model.request.DesireLogRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class DesireLogDTO {

    String componentType;

    String name;

    String description;

    Integer count;

    public DesireLogDTO(final DesireLogRequest request) {
        if (request.getComponentType() != null) {
            this.componentType = request.getComponentType().trim();
        } else {
            this.componentType = null;
        }
        this.name = request.getName().trim();
        this.description = request.getDescription().trim();
        this.count = 1;
    }

    public DesireLogDTO(final DesireLog entity) {
        this.componentType = entity.getComponentType();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.count = entity.getCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DesireLogDTO that = (DesireLogDTO) o;
        return Objects.equals(componentType, that.componentType) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentType, name, description);
    }
}