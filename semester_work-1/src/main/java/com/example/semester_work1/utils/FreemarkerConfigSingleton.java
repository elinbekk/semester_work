package com.example.semester_work1.utils;

import freemarker.template.Configuration;

import javax.servlet.ServletContext;

public class FreemarkerConfigSingleton {
    private static Configuration cfg;
    private static ServletContext sc;

    public static void setServletContext(ServletContext servletContext) {
        sc = servletContext;
    }

    public static Configuration getCfg() {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_30);
            cfg.setServletContextForTemplateLoading(sc, "/WEB-INF/templates/");
        }
        return cfg;
    }
}
