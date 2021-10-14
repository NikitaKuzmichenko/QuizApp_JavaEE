package by.epam.jwd.testingApp.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private int id;
    private int testId;
    private int creatorId;
    private String comment;
    private Date creationDate;

    public Comment() {}

    public Comment(int id, int testId, int creatorId, String comment, Date creationDate) {
        this.id = id;
        this.testId = testId;
        this.creatorId = creatorId;
        this.comment = comment;
        this.creationDate = creationDate;
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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (testId != comment.testId) return false;
        if (creatorId != comment.creatorId) return false;
        if (this.comment != null ? !this.comment.equals(comment.comment) : comment.comment != null) return false;
        return creationDate != null ? creationDate.equals(comment.creationDate) : comment.creationDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + testId;
        result = 31 * result + creatorId;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "id=" + id +
                ", testId=" + testId +
                ", creatorId=" + creatorId +
                ", comment='" + comment + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
