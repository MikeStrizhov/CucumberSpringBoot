package org.mgs.groovy;

public class ScriptInfo {
    private boolean cache;
    private String scriptText;
    private String result = null;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ScriptInfo(boolean cache, String scriptText) {
        this.cache = cache;
        this.scriptText = scriptText;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public String getScriptText() {
        return scriptText;
    }

    public void setScriptText(String scriptText) {
        this.scriptText = scriptText;
    }
}
