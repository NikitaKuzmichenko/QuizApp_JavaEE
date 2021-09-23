package by.epam.jwd.testingApp.entities.category;

public enum Categories {

    CAT1("cat1");

    private String name;
    Categories(String name){
        this.name = name;
    }
    String getName(){return this.name;}
}
