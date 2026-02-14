package com.pearl.warehouse001.repository.Specification;

import com.pearl.warehouse001.entity.Bin;
import com.pearl.warehouse001.entity.Zone;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class BinSpecification {

    public static Specification<Bin> filterBins(String code, String zoneName) {
        return (root, query, cb) -> {
            var predicates = new java.util.ArrayList<jakarta.persistence.criteria.Predicate>();

            // Search by Bin Code (Partial Match)
            if (StringUtils.hasText(code)) {
                predicates.add(cb.like(cb.lower(root.get("code")), "%" + code.toLowerCase() + "%"));
            }

            // Search by Zone Name (Joining the Zone table)
            if (StringUtils.hasText(zoneName)) {
                Join<Bin, Zone> zoneJoin = root.join("zone");
                predicates.add(cb.like(cb.lower(zoneJoin.get("name")), "%" + zoneName.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        };
    }


//    public static Specification<Bin> searchByKeyword(String keyword) {
//        return (root, query, cb) -> {
//            if (!StringUtils.hasText(keyword)) return null;
//
//            String pattern = "%" + keyword.toLowerCase() + "%";
//            Join<Bin, Zone> zoneJoin = root.join("zone");
//
//            return cb.or(
//                    cb.like(cb.lower(root.get("code")), pattern),
//                    cb.like(cb.lower(zoneJoin.get("name")), pattern)
//            );
//        };
//    }
}

