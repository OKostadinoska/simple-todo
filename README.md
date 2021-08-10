# springboot-crud-web-app

#requirements:
1. maven
2. Java 11 (in case you use Java 8 do this changes in your Development Tool before you run the application: 
      - File->Project Structure->Modules ->> Language level to 8;
      - File -> Settings -> Build, Execution, Deployment -> Compiler -> Java Compiler -> 8  )
3. Intellij, Spring Boot
4. postgresql and pgAdmin


Step by step on how to run the application:
1. clone the project from github
2. open folder in your development tool
3. create the appropriate database (you can use these details to create your database or create your won
      - spring.datasource.url = jdbc:postgresql://localhost:5432/ToDoDB
      - spring.datasource.username = postgres
      - spring.datasource.password = 0livera0
4. run the application


#database
1. once you start the application the tables will be created automatically 
2. we seed the database with Unit tests (run the unit tests one by one to create new User in order to Log in)
3. after Loging in you can start using the ToDo app and create your ToDo List
