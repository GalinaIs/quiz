package com.sbt.entity;

public class Answer {
    private final int id;
    private String answer;
    private boolean correct;

    public Answer(int id) {
        this.id = id;
    }

    public Answer(int id, String answer, boolean correct) {
        this.id = id;
        this.answer = answer;
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "\n\t\tAnswer: " +
                "id=" + id +
                "\n\t\t\tanswer='" + answer + '\'' +
                "\n\t\t\tcorrect=" + correct;
    }
}
