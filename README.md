# Backend Practice:

## _Cycling Tour_

### _By: Sofía Idárraga_

---

One of the most important events in cycling worldwide is the Tour de France. As part of the technology team that supports the competition,
you have been entrusted with the task of developing an application or service that allows the registration of the teams and their respective cyclists.

As system requirements, the following rules were established:
- Each team must have as main data: team name, a code unique abbreviation (alphanumeric, maximum 3 characters), and an associated country
- Each cyclist must have as main data: full name, a number of unique competitor (maximum 3 digits), be associated to a team and a nationality.
- A cycling team will have a maximum of 8 riders.

- This Backend was created with Spring Boot and MySQL

---
## Starting

- Download the complete project
- Open the project in IntelliJ Idea (Ultimate by suggestion).
- Set the user and password of your DataSource in _application.properties_
  Like this:
```sh
spring.datasource.username=root
spring.datasource.password= ${mypassword}
```
- Create a schema in MySQL Workbench called _**tour**_
- Run the project

You must see in consol:
```sh
2022-07-29 12:12:09.756  INFO 12524 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
```

Now you can test the project with an API platform like Postman.

---
## Routes
```
${BaseURL} = http://localhost:8080/api
```
| Action                       | Method   | Rout                                   |
|------------------------------|----------|----------------------------------------|
| Get all teams                | `GET`    | `${BaseURL}/teams`                     |
| Find team by code            | `GET`    | `${BaseURL}/team/code/{code}`          |
| Find teams by country        | `GET`    | `${BaseURL}/team/country/{country}`    |
| Find cyclist by country      | `GET`    | `${BaseURL}/cyclist/country/{country}` |
| Find cyclist by code         | `GET`    | `${BaseURL}/cyclist/code/{code}`       |
| Create team                  | `POST`   | `${BaseURL}/new/team`                  |
| Add Cyclist to a team        | `POST`   | `${BaseURL}/new/cyclist`               |
| Update Cyclist info          | `PUT`    | `${BaseURL}/update/cyclist`            |
| Update Team info             | `PUT`    | `${BaseURL}/update/team`               |
| Delete Cyclist               | `DELETE` | `${BaseURL}/delete/cyclist/{code}`     |
| Delete complete team         | `DELETE` | `${BaseURL}/delete/team/{code}`        |

### Take in count
- To add a cyclist it must be a team already created.
- You cannot update the codes of the cyclists and teams
- If you delete a team, al cyclist will be deleted too
- You cannot add more than 8 cyclist to a team
- The unique codes aren't created in the backend
