* IN This Project we call different 3 api and that use 
* API Caller is a powerful and flexible tool designed for making HTTP requests to various APIs. This project simplifies the process of integrating and interacting with external services by providing an easy-to-use interface for sending API requests and handling responses.

 * Features
- Flexible Request Options: Support for GET, POST, PUT, DELETE, and other HTTP methods.
- Customizable Headers: Easily set and manage request headers for authentication and other purposes.
- Dynamic Query Parameters: Build and manage query parameters dynamically for complex API requests.
- Response Handling: Handle and parse API responses with built-in utilities for JSON and XML formats.
- Error Handling: Robust error handling to manage and debug issues with API requests.
 * How It Works
- Configure API Endpoints: Define the API endpoints and request methods you wish to use.
- Send Requests: Use the provided methods to make API calls with custom headers, body, and query parameters.
- Handle Responses: Process and utilize the response data according to your needs, with built-in support for various response formats.
- Error Management: Handle errors and exceptions to ensure smooth API interaction.

STEP FOR DATA FATCH FROM API USING RETROFIT

STEP.1
implement latest dependancy of retrofit.

  implementation 'com.squareup.retrofit2:retrofit:2.9.0'
  implementation 'com.google.code.gson:gson:2.9.0'
  implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

STEP.2
Give Internate Permission.

  <uses-permission android:name="android.permission.INTERNET"/>

STEP.3
Make Interface And Modalclass.

  @GET("posts")
    Call<List<Post>>getdata();

STEP.4
Make inctence class like this(single reposetry pattern).

public class Retrofiteinctentce {

    public static Retrofiteinctentce incentce;
    Jsonplaceholderapi jsonplaceholderapi;

    Retrofiteinctentce(){
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonplaceholderapi = retrofit.create(Jsonplaceholderapi.class);
    }

    public static Retrofiteinctentce getIncentce(){
        if (incentce ==null){
            incentce = new Retrofiteinctentce();
        }
        return incentce;
    }
}

STEP.5
And fatch like this in main activity.

public class MainActivity extends AppCompatActivity {

    public static String api = "https://jsonplaceholder.typicode.com";

    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = findViewById(R.id.textview);

        Retrofiteinctentce.getIncentce().jsonplaceholderapi.getdata().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                textview.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

THIS IS MY FULL CODE  

MAINACTIVTY:::

public class MainActivity extends AppCompatActivity {

    public static String api = "https://jsonplaceholder.typicode.com";

    List<Post> alluserlist;

    RecyclerView rcvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvMain = findViewById(R.id.rcvmain);
        rcvMain.setLayoutManager(new LinearLayoutManager(this));


        Retrofiteinctentce.getIncentce().jsonplaceholderapi.getdata().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                alluserlist = response.body();
                rcvMain.setAdapter(new Useradpter(MainActivity.this, alluserlist));
                
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


ADPTER::::

package com.example.apicalling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Useradpter extends RecyclerView.Adapter<Useradpter.userholder> {

    MainActivity mainActivity;
    List<Post> alluserlist;

    public Useradpter( MainActivity mainActivity,List<Post> alluserlist) {
        this.mainActivity = mainActivity;
        this.alluserlist = alluserlist;
    }


    @NonNull
    @Override
    public Useradpter.userholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new userholder(LayoutInflater.from(mainActivity).inflate(R.layout.item_user, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Useradpter.userholder holder, int position) {
        holder.itemtext.setText(alluserlist.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return alluserlist.size();
    }

    class userholder extends RecyclerView.ViewHolder {
        TextView itemtext;

        public userholder(@NonNull View itemView) {
            super(itemView);

            itemtext = itemView.findViewById(R.id.itemtext);
        }
    }
}

   removedItems.add(alluserlist.get(position)); // Add to removed list
                alluserlist.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, alluserlist.size());
