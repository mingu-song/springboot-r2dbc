* https://medium.com/pictet-technologies-blog/reactive-programming-with-spring-data-r2dbc-ee9f1c24848b
* https://github.com/pictet-technologies-open-source/reactive-todo-list-r2dbc
* PostgreSQL DB
```yaml
version: "3.5"

services:

  postgres:
    container_name: postgres
    image: postgres:13.2
    restart: always
    environment:
      - POSTGRES_USER=admin
      - POSTGRES_PASSWORD=password
      - POSTGRES_DB=todolist
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/data/db

  pgadmin:
    container_name: pgadmin4
    image: dpage/pgadmin4
    restart: always
    environment:
      PGADMIN_DEFAULT_EMAIL: admin@admin.com
      PGADMIN_DEFAULT_PASSWORD: password
    ports:
      - "5050:80"
    volumes:
      - pgadmin_data:/data/pgadmin

volumes:
  postgres_data:
  pgadmin_data:
```
* PgAdmin : http://localhost:5050/browser/
* Lombok with MapStruct : https://ryanwoo.tistory.com/240
* OpenAPI : http://localhost:8080/swagger-ui/index.html
* 