# SerVC Test Assignment Backend Java

Just use this repository as a [**template**](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template) to generate repo in your account and make an assignment in it.

Create a **new** branch, make the task in it, then make a **Pull Request** to the **main** branch, so we are able to review it and leave some comments if needed.

## Task

Create a server on Spring Boot. Starter code can be obtained [**here**](https://start.spring.io/).

Book data is stored in a SQLite database.

Make a CRUD for a bookstore with 5 endpoints:
* **GET** /books - Get array of all books
* **GET** /books/:id - Get one book by ID
* **POST** /books - Create new book
* **PUT** /books/:id - Update book by ID
* **DELETE** /books/:id - Delete book by ID

The backend data exchange format is **JSON**.

Book fields:
- **id**
- **name**
- **isbn**
- **author**
- **releaseDate**

### Extras (optional)
* Add pagination (pagination) - getting a portion of books (for example, 10 books per page) through the query parameter take and skip. For example **GET** `/books?take=10&skip=20` to get ten books starting from 21.
* Add sorting when receiving books. In the GET request for /books, add the query parameter sort to be able to sort by all books. Example **GET** `/books?sort=name:asc,author:desc`.
