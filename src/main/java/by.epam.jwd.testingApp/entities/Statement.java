package by.epam.jwd.testingApp.entities;

import java.io.Serializable;

public class Statement implements Serializable {
    private int id;
    private int questionId;
    private String text;
    private boolean isCorrect;

    public Statement() {}

    public Statement(int id, int questionId, String text, boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;

        Statement statement = (Statement) o;

        if (id != statement.id) return false;
        if (questionId != statement.questionId) return false;
        if (isCorrect != statement.isCorrect) return false;
        return text != null ? text.equals(statement.text) : statement.text == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + questionId;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (isCorrect ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", text='" + text + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
