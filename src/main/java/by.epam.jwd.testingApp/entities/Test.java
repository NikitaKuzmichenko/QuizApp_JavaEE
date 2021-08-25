package by.epam.jwd.testingApp.entities;

import java.io.Serializable;
import java.util.*;

public class Test implements Serializable {

    private int id;
    private int creatorId;
    private int categoryId;
    private String name;
    private Date creationDate;
    private int passedNumber;
    private boolean removed = false;

    public Test(){}

    public Test(int id, int creatorId, int categoryId, String name, Date creationDate, int passedNumber) {
        this.id = id;
        this.creatorId = creatorId;
        this.categoryId = categoryId;
        this.name = name;
        this.creationDate = creationDate;
        this.passedNumber = passedNumber;
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

    public int getPassedNumber() {
        return passedNumber;
    }

    public void setPassedNumber(int passedNumber) {
        this.passedNumber = passedNumber;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return this.getClass() + "{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", passedNumber=" + passedNumber +
                ", removed=" + removed +
                '}';
    }
}
