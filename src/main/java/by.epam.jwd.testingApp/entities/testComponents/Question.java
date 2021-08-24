package by.epam.jwd.testingApp.entities.testComponents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Question {
    private int id;
    private int testId;
    private String title;

    List<Statement> statements = new ArrayList<>();

    public Question() {}

    public Question(int id, int testId, String title) {
        this.id = id;
        this.testId = testId;
        this.title = title;
    }

    public Question(int id, int testId, String title, List<Statement> statements) {
        this.id = id;
        this.testId = testId;
        this.title = title;
        this.statements = statements;
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

    public Iterator<Statement> getStatementsIterator() {
        return statements.iterator();
    }

    public void addStatement(Statement statement){
        statements.add(statement);
    }

    public void addAllStatements(Collection<Statement> statements){
        this.statements.addAll(statements);
    }

    public void removeStatement(Statement statement){
        statements.remove(statement);
    }

    public void removeAllStatements(Collection<Statement> statements){
        this.statements.removeAll(statements);
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != question.id) return false;
        if (testId != question.testId) return false;
        if (title != null ? !title.equals(question.title) : question.title != null) return false;
        return statements != null ? statements.equals(question.statements) : question.statements == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + testId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (statements != null ? statements.hashCode() : 0);
        return result;
    }
}
