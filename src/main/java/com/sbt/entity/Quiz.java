package com.sbt.entity;

import java.util.List;

public class Quiz {
    private final int id;
    private String caption;
    private String question;
    private List<Answer> answers;
    private String explanation;

    public Quiz(int id, String caption, String question, List<Answer> answers, String explanation) {
        this.id = id;
        this.caption = caption;
        this.question = question;
        this.answers = answers;
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
        return "Quiz{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
