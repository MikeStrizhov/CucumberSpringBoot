package org.mgs.groovy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import groovy.util.Eval;

@Component
public class ScriptContainer {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<String, ScriptInfo> scripts = new HashMap<>();

    public void addScript(String name, boolean cashResult, String scriptText){
        scripts.put(name, new ScriptInfo(cashResult, scriptText));
    }

    public String getResult(String name) {
        if (!scripts.containsKey(name)){
            log.error("Not found groovy script name:" + name);
            throw new IllegalArgumentException("Not found groovy script name:" + name);
        }
        ScriptInfo scriptInfo = scripts.get(name);
        if (scriptInfo.isCache() && (scriptInfo.getResult() != null)){
            return  scriptInfo.getResult();
        }

        String result = Eval.me(scriptInfo.getScriptText()).toString();
        scriptInfo.setResult(result);
        return result;
    }
}
