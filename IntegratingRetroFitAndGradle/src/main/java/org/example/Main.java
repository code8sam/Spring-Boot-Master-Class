package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Hello");



//        OkHttpClient client = new OkHttpClient();
//
//        String url = "https://jsonplaceholder.typicode.com/todos/1/";
//
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        try (Response response = client.newCall(request).execute()) {
//                if (!response.isSuccessful()) {
//                    System.out.println("Something went wrong");
//                }
//                System.out.println(response.body().string());
//        } catch (IOException e){
//            e.printStackTrace();
//        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create service object
        ToDoService toDoService = retrofit.create(ToDoService.class);
        // .execute will make synchronous API call
        ToDo todo = toDoService.getToDoById("1").execute().body();
        System.out.println("todo object is : "+ todo.toString());

    }
}