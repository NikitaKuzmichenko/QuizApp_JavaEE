package by.epam.jwd.testingApp.entity;

import java.io.Serializable;

public class Question implements Serializable {
    private int id;
    private int testId;
    private String title;

    public Question() {}

    public Question(int id, int testId, String title) {
        this.id = id;
        this.testId = testId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != question.id) return false;
        if (testId != question.testId) return false;
        return title != null ? !title.equals(question.title) : question.title != null ;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + testId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "id=" + id +
                ", testId=" + testId +
                ", title='" + title + '\'' +
                '}';
    }
}
