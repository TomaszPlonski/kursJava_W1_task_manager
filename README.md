# Task Manager
This is my first app here on GitHub.

This app is simple task manager working in console.

Apart from pure java, I also used class ConsoleColors to make the messages displayed in the console more transparent.

Program will write and read tasks from tasks.csv files.

# What is my purpose?
After starting the program, the console will display options that can be selected. To select a specific function, enter its name in the console and confirm.


# Options to choose from:
First you need some Container for app -> I use Tomcat.

Next you need database with users table. Here is quick dump for this table:

```sql
CREATE TABLE users (
    id INT(11) AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE,
    username VARCHAR(255),
    password VARCHAR(60),
    PRIMARY KEY(id)
);
```

If you have problems connecting to the database, you may need to modify the DBUtil.java file.

For me main page is:
```
http://localhost:8080/list
```
On this page you will see list of all users. To add, show details, remove or update user just click on corresponding button.

# Why this simple?
For now, the functionality and my skills are limited. 
<b>Therefore any comments and advice will be appreciated</b>

I plan to add data and admin validation. And then who knows what more ...
