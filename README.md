# Testing System
### Application description
 The website Testing System is designed to create, view, pass tests.<br>
 
 * **Key elements**
    - MySQL database
    - Log4j2
    - Java Servlets
    - JSP
    - JSTL
    - Custom connection pool
    - Pagination
    - Localization: EN, RU

The user's roles and actions available to them are described in the table below.
      
Command | GUEST | USER | TUTOR 
---------|-------|--------|------
Change language| * | * | * |
Sort tests| * | * | * |
View popular tests| * | * | * |
View new tests| * | * | * |
Take test|   | * | * |
View test result|   | * | * |
View passed tests|   | * | * |
Sign in|   | * | * |
Sign out|   | * | * |
Register| * |   |   |
Create test|   |   | * |
Edit test|   |   | * |
View created tests|   |   | * |
Change test status|   |   | * |
Delete test|   |   | * |
	
# Database tables
![](https://github.com/NikitaKuzmichenko/QuizApp_JavaEE/blob/master/dataBaseInfo/schema.png)
