package com.athanasiou.spring.angular.restfulSpringBoot.Main;

public class MainBean {

    private String message;

    public MainBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("MainBean [message=%s]", message);
    }
}
