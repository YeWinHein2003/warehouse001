package com.pearl.warehouse001.repository.Specification;

import com.pearl.warehouse001.entity.Zone;
import org.springframework.data.jpa.domain.Specification;

public class ZoneSpecification {
    public static Specification<Zone> hasName(String name){
        return (root, query, cb) -> (name == null || name.trim().isEmpty()) ? null : cb.like(cb.lower(root.get("name")),"%"+name.toLowerCase()+"%");
    }

    public static Specification<Zone> globalSearch(String keyword){
        return (root, query, cb) -> {
            if(keyword == null || keyword.isBlank()) return null;

            String pattern = "%"+keyword.toLowerCase()+"%";
            return cb.or(
                    cb.like(cb.lower(root.get("name")),pattern),
                    cb.like(cb.lower(root.get("zoneType")),pattern)
            );
        };
    }
}
