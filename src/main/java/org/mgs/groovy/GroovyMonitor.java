package org.mgs.groovy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Aspect for monitoring script calls
 */
@Aspect
@Component
public class GroovyMonitor {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ScriptContainer scriptContainer;

    //names of groovy scripts must start with chars '$$' Example '$$FunctionName'
    private static Pattern pattern = Pattern.compile("^\\$\\$.+");

    @Around("@annotation(UseGroovy)")
    public void beforeCucumber(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        for (int i=0;i<args.length;i++){
            //args[i] may be String
            args[i] = transformString(args[i]);
            //args[i] may be List<String>
            if (args[i] instanceof List){
                List listArgs = (List)args[i];
                List resut =new LinkedList();
                for (Object item: listArgs){
                    resut.add(transformString(item));
                }
                args[i] = resut;
                continue;
            }
            //args[i] may be Map<String, String>
            if (args[i] instanceof Map){
                Map mapArgs = (Map) args[i];
                Map resut =new HashMap();
                for (Object entry: mapArgs.entrySet()){
                    Map.Entry<String,String> me = (Map.Entry<String,String>) entry;
                    resut.put(me.getKey(), transformString(me.getValue()));
                }
                args[i] = resut;
                continue;
            }
        }
        joinPoint.proceed(args);
    }

    private Object transformString(Object value){
        if (value instanceof String){
            String str = (String) value;
            if (pattern.matcher(str).matches()) {
//                    log.info("working!");
                return scriptContainer.getResult(str);
            }
        }
        return value;
    }
}
