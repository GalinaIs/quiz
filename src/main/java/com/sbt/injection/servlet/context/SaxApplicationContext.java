package com.sbt.injection.servlet.context;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

public class SaxApplicationContext implements ApplicationContext {
    private String xmlFile;

    @Override
    public void init(String xmlFile) {
        this.xmlFile = xmlFile;
    }

    @Override
    public Object getBean(String name) {
        return getBean(name, Object.class);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> clazz) {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(this.xmlFile);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new BeanContentHandler(beanName);
            saxParser.parse(in, handler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("No such bean declaration: beanName = " + beanName);
    }

    private static class BeanContentHandler extends DefaultHandler {
        private boolean tagOn = false;
        private String beanName;

        BeanContentHandler(String beanName) {
            this.beanName = beanName;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            tagOn = (qName.equalsIgnoreCase(beanName));
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            if (tagOn) {

                tagOn = false;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
        }

        @Override
        public void startDocument() throws SAXException {
            //System.out.println("Начало разбора документа!");
        }

        @Override
        public void endDocument() throws SAXException {
            //System.out.println("Разбор документа завершен!");
        }
    }
}
