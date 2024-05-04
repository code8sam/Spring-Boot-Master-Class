package com.example.todoapispring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("fakeTodoService")
public class FakeTodoService implements TodoService{

    @TimeMonitor
    public String doSomething(){
        return "something";
    }
}
