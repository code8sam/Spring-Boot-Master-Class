package com.example.todoapispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    private static List<Todo> todoList;
    //custom message if no todo found :
    private static final String to_do_not_found = "Todo not found";
    private TodoService todoService;
    private TodoService todoService2;

    public TodoController(@Qualifier("anotherTodoService") TodoService todoService, @Qualifier("fakeTodoService") TodoService todoService2){
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "todo1", 1));
        todoList.add(new Todo(2, true, "todo2", 2));
        this.todoService = todoService;
        this.todoService2 = todoService2;
    }

    // let's create our first API :

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false, defaultValue = "true") boolean isCompleted){
        System.out.println("------------------------------------"+isCompleted+"-----------------------------"+this.todoService2.doSomething()+"---------------------------------------");
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable("id") long id){
        for (Todo todo : todoList){
            if(todo.getId()==id){
                return ResponseEntity.status(HttpStatus.OK).body(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(to_do_not_found);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id){
        for (Todo todo : todoList){
            if(todo.getId()==id){
                todoList.remove(todo);
                return ResponseEntity.status(HttpStatus.OK).body("todo successfully deleted");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter valid todo to delete");
    }

    @PatchMapping("/todos/{id}")
    ResponseEntity<?> updateTodoById(@PathVariable Long id, @RequestParam(required = false) String title, @RequestParam(required = false) Boolean isCompleted, @RequestParam(required = false) Integer userId) {
        for(Todo todo : todoList) {
            if(todo.getId() == id) {
                if(title != null) {
                    todo.setTitle(title);
                }
                if(isCompleted != null) {
                    todo.setCompleted(isCompleted);
                }
                if(userId != null) {
                    todo.setUserId(userId);
                }

                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(to_do_not_found);
    }

}
