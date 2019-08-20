package org.mgs;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FreemarkerController {

    public final String TEMPLATES_DIR = "src/test/resources/templates";

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<String, Object> templateData = new HashMap<>();

    private Configuration config;
    private Template template;

    @PostConstruct
    public void initBean(){
        config = new Configuration(Configuration.getVersion());
        try {
            File resourcesDirectory = new File(TEMPLATES_DIR);
            FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(resourcesDirectory);
            config.setTemplateLoader(fileTemplateLoader);
        } catch (IOException e) {
            log.error("File reading using the Freemarker template engine failed!", e);
        }
    }

    public void startCreateXmlDocFromTemplate(String templateName, Map<String, String> data){

        data.forEach(((k,v)->templateData.put(k,v)));

        try {
            template = config.getTemplate(templateName);
            //template = config.getTemplate("simple.ftl");
            //Writer out = new OutputStreamWriter(System.out);
//            Writer out = new StringWriter();
//            template.process(data, out);
        } catch (IOException e) {
            log.error("File reading using the Freemarker template engine failed!", e);
        }
    }

    public String createXmlDoc() {
        Writer out = new StringWriter();
        try {
            template.process(templateData, out);
        } catch (IOException e) {
            log.error("File reading using the Freemarker template engine failed!", e);
        } catch (TemplateException e) {
            log.error("Error in processing Freemarker template!", e);
        }
        return out.toString();
    }

    public void fillList(String name, List<String> values) {
        templateData.put(name, values);
    }
}
