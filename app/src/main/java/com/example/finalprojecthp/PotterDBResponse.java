package com.example.finalprojecthp;

import java.util.List;

public class PotterDBResponse {


    private String message;
    private List<String> apiRoutes;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getApiRoutes() {
        return apiRoutes;
    }

    public void setApiRoutes(List<String> apiRoutes) {
        this.apiRoutes = apiRoutes;
    }

}
