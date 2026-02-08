package com.pearl.warehouse001.entity;

import org.springframework.data.jpa.domain.Specification;

public class WarehouseSpecification {

    public static Specification<Warehouse> hasName(String name){
        //use ".trim()" to remove  all leading (at the start) and trailing (at the end) whitespace from a string
        return (root, query, cb) -> (name == null || name.trim().isEmpty() ? null : cb.like(cb.lower(root.get("name")), "%"+name.toLowerCase()+"%"));

//        return(root, query, cb)-> {
//            if (name == null || name.trim().isEmpty()) return null;
//
//            if (name.length() > 50) return null;
//
//            if (!name.matches("^[a-zA-Z]*$")) return null;
//
//            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
//        };
    }

    public static Specification<Warehouse> globalSearch(String keyword){
        return (root, query, cb)->{
            if(keyword == null || keyword.isBlank()) return null;

            String pattern = "%"+keyword.toLowerCase()+"%";

            return cb.or(
                    cb.like(cb.lower(root.get("name")),pattern),
                    cb.like(cb.lower(root.get("location")),pattern),
                    cb.like(cb.lower(root.get("township")),pattern)
            );
        };
    }
}
