package com.bdg.pc_build.desire_log.model.dto;

import com.bdg.pc_build.desire_log.model.entity.DesireLog;
import com.bdg.pc_build.desire_log.model.request.DesireLogCreationRequest;
import lombok.Getter;
import java.util.Objects;

@Getter
public class DesireLogDTO {

    private final String componentType;

    private final String name;

    private final String description;

    private final Integer count;

    public DesireLogDTO(final DesireLogCreationRequest request) {
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