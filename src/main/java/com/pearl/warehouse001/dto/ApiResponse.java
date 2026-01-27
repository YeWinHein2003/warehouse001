package com.pearl.warehouse001.dto;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
}
