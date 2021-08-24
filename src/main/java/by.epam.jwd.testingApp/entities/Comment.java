package by.epam.jwd.testingApp.entities;

import java.util.Date;

public class Comment {
    private int id;
    private int testId;
    private User creator;
    private String comment;
    private Date creationDate;

    public Comment() {}

    public Comment(int id, int testId, User creator, String comment, Date creationDate) {
        this.id = id;
        this.testId = testId;
        this.creator = creator;
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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

        Comment comment1 = (Comment) o;

        if (id != comment1.id) return false;
        if (testId != comment1.testId) return false;
        if (creator != null ? !creator.equals(comment1.creator) : comment1.creator != null) return false;
        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;
        return creationDate != null ? creationDate.equals(comment1.creationDate) : comment1.creationDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + testId;
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }
}
