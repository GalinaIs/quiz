package com.sbt.injection.servlet;

import com.sbt.injection.servlet.context.ApplicationContext;
import com.sbt.injection.Inject;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.Arrays;

import static com.sbt.utils.ClassNameUtil.getCurrentClassName;

public class DependencyInjectionServlet extends HttpServlet {
    private static final String APP_CTX_PATH = "appCtxPath";
    private static final String APP_CTX_CLASS = "appCtxClass";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    public void init() throws ServletException {
        String appCtxPath = this.getInitParameter(APP_CTX_PATH);
        logger.debug("load " + APP_CTX_PATH + " -> " + appCtxPath);
        String appCtxClass = this.getInitParameter(APP_CTX_CLASS);
        logger.debug("load " + APP_CTX_CLASS + " -> " + appCtxClass);

        if (appCtxPath == null) {
            logger.error("I need init param " + APP_CTX_PATH);
            throw new ServletException(APP_CTX_PATH + " init param == null");
        }

        if (appCtxClass == null) {
            logger.error("I need init param " + APP_CTX_CLASS);
            throw new ServletException(APP_CTX_CLASS + " init param == null");
        }

        try {
            ApplicationContext appCtx = (ApplicationContext) Class.forName(appCtxClass).newInstance();
            logger.debug("OK: Create instance of " + appCtxClass + " appCtx = " + appCtx);
            appCtx.init(appCtxPath);
            logger.debug("OK: Init instance of " + appCtxClass);
            Field[] fields = this.getClass().getDeclaredFields();
            logger.debug("I find fields " + Arrays.toString(fields));
            for (Field field : fields) {
                field.setAccessible(true);
                Inject annotation = field.getAnnotation(Inject.class);
                if (annotation != null) {
                    logger.debug("I find field marked by @Inject: " + field);
                    String beanName = annotation.value();
                    logger.debug("I must instantiate and inject '" + beanName + "'");
                    Object bean = appCtx.getBean(beanName);

                    if (bean == null) {
                        throw new Exception("There isn't bean with name '" + beanName + "'");
                    }
                    logger.debug("Instantion - OK '" + beanName + "'");
                    field.set(this, bean);
                }
            }
        } catch (Exception e) {
            throw new ServletException("Can't inject by " + APP_CTX_CLASS, e);
        }
    }
}
