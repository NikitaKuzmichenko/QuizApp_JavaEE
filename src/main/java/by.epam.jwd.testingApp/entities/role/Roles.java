package by.epam.jwd.testingApp.entities.role;

public enum Roles {
    ROLE1("role1");

    private String name;
    Roles(String name){
        this.name = name;
    }
    String getName(){return this.name;}
}
