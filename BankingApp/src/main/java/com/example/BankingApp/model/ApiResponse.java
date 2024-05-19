package com.example.BankingApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    public Object data;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
    private String[] pages;
    private int currentPage;
    private boolean isSuccess;

    public ApiResponse SetResponse(Object data,long totalElements,int totalPages,
                                   boolean hasNext,boolean hasPrevious,int currentPage
    ){

        this.data=data;
        this.totalElements=totalElements;
        this.totalPages=totalPages;
        this.hasNext=hasNext;
        this.hasPrevious=hasPrevious;
        this.currentPage=currentPage;
        this.pages=General.getPages(totalPages,currentPage);
        return this;
    }
}