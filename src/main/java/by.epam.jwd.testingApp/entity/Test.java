package by.epam.jwd.testingApp.entity;

import java.io.Serializable;
import java.util.*;

public class Test implements Serializable {

    private int id;
    private int creatorId;
    private int categoryId;
    private String name;
    private Date creationDate;
    private boolean removed = false;

    public Test(){}

    public Test(int id, int creatorId, int categoryId, String name, Date creationDate) {
        this.id = id;
        this.creatorId = creatorId;
        this.categoryId = categoryId;
        this.name = name;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (id != test.id) return false;
        if (creatorId != test.creatorId) return false;
        if (categoryId != test.categoryId) return false;
        if (removed != test.removed) return false;
        if (name != null ? !name.equals(test.name) : test.name != null) return false;
        return creationDate != null ? creationDate.equals(test.creationDate) : test.creationDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + creatorId;
        result = 31 * result + categoryId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (removed ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", removed=" + removed +
                '}';
    }
}
