# recipes-app

Very simple api following a turorial and adding some endpoints in order to practice clojure and duct-framework

[![CircleCI](https://circleci.com/gh/amaralunao/recipes-app.svg?style=svg)](https://circleci.com/gh/amaralunao/recipes-app)

## Developing

### Setup

When you first clone this repository, run:

```sh
lein duct setup
```

This will create files for local configuration, and prep your system
for the project.

### Environment

To begin developing, start with a REPL.

```sh
lein repl
```

Then load the development environment.

```clojure
user=> (dev)
:loaded
```

Run `go` to prep and initiate the system.

```clojure
dev=> (go)
:duct.server.http.jetty/starting-server {:port 3000}
:initiated
```

By default this creates a web server at <http://localhost:3000>.

When you make changes to your source files, use `reset` to reload any
modified files and reset the server.

```clojure
dev=> (reset)
:reloading (...)
:resumed
```
### Calling Endpoints

#### Add a recipe
- URL
http://localhost:3000/add-recipe

- Method:
  GET 

- URL Params

  None

- Data Params

  ```
  name: not required, type string, unique constraint,
  author: not required, type string
  url: not required, type string
  text: not required, type string
  rating: not required, type int 
  ```
- Success:
  Response: 200 OK
  ```
  "Recipe
  Name: Example
  Author: Example
  Url: www.example.com
  Text: Recipe text
  Rating: 9
  Recipe added"
  ```
- Failure:
  Response 200 OK

  ```
  "Recipe not added due to [SQLITE_CONSTRAINT_UNIQUE] A UNIQUE constraint failed (UNIQUE constraint failed: recipe.name)"
  ```

#### List all recipes
- URL
http://localhost:3000/recipes

- Method:
  GET 

- URL Params

  None

- Data Params

  None

- Success:
  Response: 200 OK
  Returns a list of all recipes in database 

- Empty response:
  Response: 200 OK
  ```
  "No recipes found"
  ```
#### Get one recipe instance

- URL
http://localhost:3000/recipes/1

- Method:
  GET 

- URL Params
  ``` 
  recipe id: type int
  ```

- Data Params

  None

- Success:
  Response: 200 OK
  Returns a recipe instance

- Not found:
  Response: 200 OK
  ```
  "Recipe not found"
  ```

#### Delete one recipe instance

- URL
http://localhost:3000/recipes/1

- Method:
  DELETE 

- URL Params
  ```
  recipe id: type int
  ```

- Data Params

  None

- Success:
  Response: 200 OK
  ```
  "Recipe deleted"
  ```
- Not found:
  Response: 200 OK
  ```
  "Recipe not found"
  ```

### Testing

Testing is fastest through the REPL, as you avoid environment startup
time.

```clojure
dev=> (test)
...
```

But you can also run tests through Leiningen.

```sh
lein test
```

## Legal

Copyright © 2021 Maria Vatasoiu 
