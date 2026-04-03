package com.example.calculatorapp;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;

public class DbResourceBundle extends ResourceBundle {
    private final Map<String, String> data;

    public DbResourceBundle(String language){
        this.data = LocalizationService.getLabelsByLanguage(language);
    }

    @Override
    protected Object handleGetObject(String key) {
        return data.get(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return Collections.enumeration(data.keySet());
    }
}
