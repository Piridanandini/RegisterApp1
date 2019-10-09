package com.example.welcome.registerapp.admin;

import android.app.Application;

public class global_vars extends Application {

    private String someVariable;

    public String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }
}
