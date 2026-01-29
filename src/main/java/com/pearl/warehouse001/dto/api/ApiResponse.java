package com.pearl.warehouse001.dto.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


import java.util.List;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T>{
    private boolean success;
    private String status;
    private String message;
    private T content;
    private List<FieldError> errors;
    private Pagination pagination;

    public static <T> ApiResponse<T> success(T content, String message) {
        ApiResponse<T> res = new ApiResponse<>();
        res.success = true;
        res.status = "SUCCESS";
        res.message = message;
        res.content = content;
        return res;
    }

    public static <T> ApiResponse<T> error(String message, List<FieldError> errors) {
        ApiResponse<T> res = new ApiResponse<>();
        res.success = false;
        res.status = "ERROR";
        res.message = message;
        res.errors = errors;
        return res;
    }

    public static <T> ApiResponse<T> error(String message, FieldError error) {
        return error(message, List.of(error));
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
    public Pagination getPagination(){
        return this.pagination;
    }
    public boolean isSuccess() { return success; }
    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public T getContent() { return content; }
    public List<FieldError> getErrors() { return errors; }

}
