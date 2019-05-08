package com.sbt.injection.servlet.context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class DomApplicationContext implements ApplicationContext {
    private Document document;

    @Override
    public void init(String xmlFile) {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(xmlFile);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            this.document = docBuilder.parse(in);
            this.document.getDocumentElement().normalize();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getBean(String name) {
        return getBean(name, Object.class);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> clazz) {
        try {
            NodeList beanNodes = this.document.getElementsByTagName("bean");
            for (int beanIndex = 0; beanIndex < beanNodes.getLength(); beanIndex++) {
                Node node = beanNodes.item(beanIndex);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String currentBeanName = element.getAttribute("name");
                    String beanClass = element.getAttribute("class");
                    if (beanName.equals(currentBeanName)) {
                        return (T) newInstatnce(beanClass);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("No such bean declaration: beanName = " + beanName);
    }

    private Object newInstatnce(String beanClassName) throws ReflectiveOperationException {
        Class<?> beanClass = Class.forName(beanClassName);
        return beanClass.newInstance();
    }
}
