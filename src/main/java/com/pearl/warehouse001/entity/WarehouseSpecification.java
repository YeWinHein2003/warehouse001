package com.pearl.warehouse001.entity;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.batch.core.job.JobInstance;
import org.springframework.data.jpa.domain.Specification;

public class WarehouseSpecification {


    public static Specification<Warehouse> hasName(String name){
        //use ".trim()" to remove  all leading (at the start) and trailing (at the end) whitespace from a string
        return (root, query, cb) -> (name == null || name.trim().isEmpty() ? null : cb.like(cb.lower(root.get("name")), "%"+name.trim().toLowerCase()+"%"));

    }

    public static Specification<Warehouse> globalSearch(String keyword){
        return (root, query, cb)->{
            if(keyword == null || keyword.trim().isBlank()) return null;

            String pattern = "%"+keyword.trim().toLowerCase()+"%";

            // join with township table and region table

            Join<Warehouse , Township> joinTownship = root.join("township", JoinType.LEFT);

            // This line allows searching in the Region table even if Warehouse isn't directly linked to it
            Join<Township, Region> joinRegion = joinTownship.join("region", JoinType.LEFT);

            return cb.or(
                    cb.like(cb.lower(root.get("name")),pattern),
                    //as warehouseType is ENUM ,change enum to string before cb.lower
                    cb.like(cb.lower(root.get("warehouseType").as(String.class)),pattern),
                    cb.like(cb.lower(joinTownship.get("township_name")),pattern),
                    cb.like(cb.lower(joinRegion.get("region_name")),pattern)
            );
        };
    }
}
