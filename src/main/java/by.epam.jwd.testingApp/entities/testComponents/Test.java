package by.epam.jwd.testingApp.entities.testComponents;

import by.epam.jwd.testingApp.entities.Comment;
import by.epam.jwd.testingApp.entities.User;

import java.util.*;

public class Test  {

    private int id;
    private String name;
    private String category;
    private int passedNumber;
    private Date creationDate;
    private boolean removed = false;

    private User creator;
    List<Question> questions  = new ArrayList<>();
    List<Comment> comments  = new ArrayList<>();

    public Test(){}

    public Test(int id, String name, String category, int passedNumber, Date creationDate, boolean removed) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.passedNumber = passedNumber;
        this.creationDate = creationDate;
        this.removed = removed;
    }

    public Test(int id, String name, String category, int passedNumber, Date creationDate, boolean removed,
                User creator, List<Question> questions, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.passedNumber = passedNumber;
        this.creationDate = creationDate;
        this.removed = removed;
        
        this.creator = creator;
        this.questions = questions;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPassedNumber() {
        return passedNumber;
    }

    public void setPassedNumber(int passedNumber) {
        this.passedNumber = passedNumber;
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Iterator<Question> getQuestionsIterator() {
        return questions.iterator();
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

    public void addAllQuestions(Collection<Question> questions){
        this.questions.addAll(questions);
    }

    public void removeQuestion(Question question){
        questions.remove(question);
    }

    public void removeAllQuestions(Collection<Question> questions){
        this.questions.removeAll(questions);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


    public Iterator<Comment> getCommentsIterator() {
        return comments.iterator();
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public void addAllComments(Collection<Comment> comments){
        this.comments.addAll(comments);
    }

    public void removeComment(Comment comment){
        comments.remove(comment);
    }

    public void removeAllComments(Collection<Comment> comments){
        this.comments.removeAll(comments);
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (id != test.id) return false;
        if (passedNumber != test.passedNumber) return false;
        if (removed != test.removed) return false;
        if (name != null ? !name.equals(test.name) : test.name != null) return false;
        if (category != null ? !category.equals(test.category) : test.category != null) return false;
        if (creationDate != null ? !creationDate.equals(test.creationDate) : test.creationDate != null) return false;
        if (creator != null ? !creator.equals(test.creator) : test.creator != null) return false;
        if (questions != null ? !questions.equals(test.questions) : test.questions != null) return false;
        return comments != null ? comments.equals(test.comments) : test.comments == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + passedNumber;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (removed ? 1 : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}
