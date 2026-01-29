//package com.pearl.warehouse001.dto.api;



//public class ApiResponseBuilder {
//
//    public static <T> ApiResponse<T> success(T content, String message) {
//        return ApiResponse.<T>builder()
//                .success(true)
//                .status("SUCCESS")
//                .message(message)
//                .content(content)
//                .build();
//    }
//
//    public static <T> ApiResponse<T> success(T content, String message, Pagination pagination) {
//        return ApiResponse.<T>builder()
//                .success(true)
//                .status("SUCCESS")
//                .message(message)
//                .content(content)
//                .pagination(pagination)
//                .build();
//    }
//
//    public static <T> ApiResponse<T> error(String errorCode, String message) {
//        return ApiResponse.<T>builder()
//                .success(false)
//                .status("ERROR")
//                .errorCode(errorCode)
//                .message(message)
//                .build();
//    }
//}

