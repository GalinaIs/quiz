package com.sbt.entity;

import java.util.List;

public class Question {
    private final int id;
    private String caption;
    private String question;
    private List<Answer> answers;
    private String explanation;

    public Question(int id) {
        this.id = id;
    }

    public Question(int id, String caption, String question, String explanation) {
        this.id = id;
        this.caption = caption;
        this.question = question;
        this.explanation = explanation;
    }

    public int getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public String toString() {
        return "\n\tQuestion: " +
                "id=" + id +
                "\n\t\tcaption='" + caption + '\'' +
                "\n\t\tquestion='" + question + '\'' +
                "\n\t\tanswers=" + answers +
                "\n\t\texplanation='" + explanation + '\'';
    }
}
