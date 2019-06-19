package org.mgs;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DocumentContainer {

    private Map<String, String> documents = new HashMap<>();

    public void put(String name, String document){
        documents.put(name,document);
    }

    public String get(String name){
        return documents.get(name);
    }
}
