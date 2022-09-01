package com.jenn.eventsinkorea.web.admin;

public enum SearchOption {

    ALL("All"), ID("Id"), USERNAME("Username"), EMAIL("Email");

    private String name;


    SearchOption(String name){
        this.name = name;
    }
}
