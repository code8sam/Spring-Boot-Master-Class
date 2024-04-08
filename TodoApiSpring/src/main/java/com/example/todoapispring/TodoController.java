package com.example.todoapispring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    private static List<Todo> todoList;

    public TodoController(){
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "todo1", 1));
        todoList.add(new Todo(2, true, "todo2", 2));
    }

    // let's create our first API :

    @GetMapping("/todos")
    public List<Todo> getTodos(){
        return todoList;
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return newTodo;
    }

}
