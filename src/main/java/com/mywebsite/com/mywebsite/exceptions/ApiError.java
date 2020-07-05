package com.mywebsite.com.mywebsite.exceptions;

import lombok.Data;

import java.util.List;

@Data
public class ApiError {
    private final String status = "error";
    private List<String> reason;
}
