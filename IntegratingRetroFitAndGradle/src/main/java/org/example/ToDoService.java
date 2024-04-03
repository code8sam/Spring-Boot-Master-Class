package org.example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ToDoService {

    @GET("/todos/{id}")
    Call<ToDo> getToDoById(@Path("id") String id);
}
