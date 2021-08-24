package by.epam.jwd.testingApp.entities;

import java.util.Date;

public class Result {
    private int userId;
    private int testId;
    private int result;
    private Date passingDate;

    public Result() {}

    public Result(int userId, int testId, int result, Date passingDate) {
        this.userId = userId;
        this.testId = testId;
        this.result = result;
        this.passingDate = passingDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Date getPassingDate() {
        return passingDate;
    }

    public void setPassingDate(Date passingDate) {
        this.passingDate = passingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;

        Result result1 = (Result) o;

        if (userId != result1.userId) return false;
        if (testId != result1.testId) return false;
        if (result != result1.result) return false;
        return passingDate != null ? passingDate.equals(result1.passingDate) : result1.passingDate == null;
    }

    @Override
    public int hashCode() {
        int result1 = userId;
        result1 = 31 * result1 + testId;
        result1 = 31 * result1 + result;
        result1 = 31 * result1 + (passingDate != null ? passingDate.hashCode() : 0);
        return result1;
    }
}
