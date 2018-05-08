package com.movieku.app.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Config;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.movieku.app.Activity.MainActivity;
import com.movieku.app.Configuration.CustomVolleyRequest;
import com.movieku.app.Configuration.URLs;
import com.movieku.app.Model.Movie;
import com.movieku.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {

    View rootView;
    private List<Movie> listMovieAkanTayang;
    private List<Movie> listMovieSedangTayang;
    private List<Movie> listMovieLainnya;

    //Creating Views
    private RecyclerView recyclerViewSedangTayang,recyclerViewAkanTayang,recyclerViewLainnya;
    private RecyclerView.LayoutManager horizontalLayoutManager,horizontalLayoutManager1;
    private RecyclerView.Adapter adapterSedangTayang, adapterAkanTayang,adapterLainnya;
    ProgressBar progressBar1,progressBar2,progressBar3;
    Context context;

    //Volley Request Queue
    private RequestQueue requestQueue;

    //The request counter to send ?page=1, ?page=2  requests
    private int requestCountSedangTayang;
    private int requestCountAkanTayang;
    private int requestCountLainnya;

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_beranda, container, false);
        getActivity().setTitle("Beranda");

        requestCountSedangTayang = 1;
        requestCountAkanTayang = 1;
        requestCountLainnya = 1;
        requestQueue = Volley.newRequestQueue(getActivity());

        progressBar1 = rootView.findViewById(R.id.progressBar1);
        progressBar2 = rootView.findViewById(R.id.progressBar2);
        progressBar3 = rootView.findViewById(R.id.progressBar3);
        recyclerViewSedangTayang = (RecyclerView) rootView.findViewById(R.id.sedangtayang_recycler_view);
        recyclerViewAkanTayang = (RecyclerView) rootView.findViewById(R.id.akantayang_recycler_view);
        recyclerViewLainnya = (RecyclerView) rootView.findViewById(R.id.lainnya_recycler_view);


        horizontalLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalLayoutManager1 = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        //Initializing our superheroes list
        listMovieSedangTayang = new ArrayList<>();
        listMovieSedangTayang.clear();
        getSedangTayang();

        listMovieAkanTayang = new ArrayList<>();
        listMovieAkanTayang.clear();
        getAkanTayang();

        listMovieLainnya = new ArrayList<>();
        listMovieLainnya.clear();
        getLainnya();

        recyclerViewSedangTayang.setHasFixedSize(true);
        recyclerViewSedangTayang.setLayoutManager(horizontalLayoutManager);
        recyclerViewSedangTayang.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLastItemDisplaying(recyclerViewSedangTayang)) {
                    //Calling the method getdata again
                    getSedangTayang();
                }

            }
        });
        adapterSedangTayang = new MovieSedangTayangAdapter(listMovieSedangTayang, getActivity());
        recyclerViewSedangTayang.setAdapter(adapterSedangTayang);

        recyclerViewAkanTayang.setHasFixedSize(true);
        recyclerViewAkanTayang.setLayoutManager(horizontalLayoutManager1);
        recyclerViewAkanTayang.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLastItemDisplaying(recyclerViewAkanTayang)) {
                    //Calling the method getdata again
                    getAkanTayang();
                }
            }
        });
        adapterAkanTayang = new MovieAkanTayangAdapter(listMovieAkanTayang, getActivity());
        recyclerViewAkanTayang.setAdapter(adapterAkanTayang);

        GridLayoutManager gridlayoutManager = new GridLayoutManager(getActivity(),3);
        recyclerViewLainnya.setLayoutManager(gridlayoutManager);
        recyclerViewLainnya.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLastItemDisplaying(recyclerViewLainnya)) {
                    //Calling the method getdata again
                    getLainnya();
                }
            }
        });
        adapterLainnya = new MovieLainnyaAdapter(listMovieLainnya, getActivity());
        recyclerViewLainnya.setAdapter(adapterLainnya);


        return rootView;
    }




    public class MovieSedangTayangAdapter extends RecyclerView.Adapter<MovieSedangTayangAdapter.ViewHolder> {

        //Imageloader to load image
        private ImageLoader imageLoader;
        private Context context;
        private List<Movie> listMovieSedangTayang = new ArrayList<>();

        //List to store all superheroes

        //Constructor of this class
        public MovieSedangTayangAdapter(List<Movie> movie, Context context){
            super();
            //Getting all superheroes

            this.listMovieSedangTayang = movie;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            //Getting the particular item from the list
            final Movie movie =  this.listMovieSedangTayang.get(position);

            //Loading image from url
            //imageLoader = CustomVolleyRequest.getInstance(getActivity()).getImageLoader();
            //imageLoader.get(movie.getImage_movie(), ImageLoader.getImageListener(holder.imageView, R.drawable.ic_movie_filter_black_24dp, android.R.drawable.ic_dialog_alert));

            //Showing data on the views
            //holder.imageView.setImageUrl(movie.getImage_movie(), imageLoader);
            Glide.with(this.context)
                    .load(movie.getImage_movie())
                    .into(holder.imageView);
            String nama_tahun = movie.getNama_movie()+" ("+movie.getTahun_movie()+")";
            holder.textViewNamaMovie.setText(nama_tahun);

            holder.layoutitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    //Lowongan lowonganklik = lowongan;
                    bundle.putSerializable("id_movie", movie.getId_movie());
                    DetailmovieFragment dmf = new DetailmovieFragment();
                    dmf.setArguments(bundle);
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.content, dmf);
                    ft.addToBackStack("list");
                    ft.commit();
                }
            });

        }

        @Override
        public int getItemCount() {
            return this.listMovieSedangTayang.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            //Views
            public ImageView imageView;
            public TextView textViewNamaMovie;
            public LinearLayout layoutitem;

            //Initializing Views
            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.imageviewMovie);
                textViewNamaMovie = (TextView) itemView.findViewById(R.id.textviewNamaMovie);
                layoutitem = (LinearLayout) itemView.findViewById(R.id.layout_item);
            }
        }
    }

    public class MovieAkanTayangAdapter extends RecyclerView.Adapter<MovieAkanTayangAdapter.ViewHolder> {

        //Imageloader to load image
        private ImageLoader imageLoader;
        private Context context;
        private List<Movie> listMovieAkanTayang = new ArrayList<>();

        //List to store all superheroes

        //Constructor of this class
        public MovieAkanTayangAdapter(List<Movie> movie, Context context){
            super();
            //Getting all superheroes

            this.listMovieAkanTayang = movie;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            //Getting the particular item from the list
            final Movie movie =  this.listMovieAkanTayang.get(position);

            //Loading image from url
            //imageLoader = CustomVolleyRequest.getInstance(getActivity()).getImageLoader();
            //imageLoader.get(movie.getImage_movie(), ImageLoader.getImageListener(holder.imageView, R.drawable.ic_movie_filter_black_24dp, android.R.drawable.ic_dialog_alert));

            //Showing data on the views
            //holder.imageView.setImageUrl(movie.getImage_movie(), imageLoader);
            Glide.with(this.context)
                    .load(movie.getImage_movie())
                    .into(holder.imageView);
            String nama_tahun = movie.getNama_movie()+" ("+movie.getTahun_movie()+")";
            holder.textViewNamaMovie.setText(nama_tahun);

            holder.layoutitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "you "+movie.getNama_movie(), Toast.LENGTH_LONG).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return this.listMovieAkanTayang.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            //Views
            public ImageView imageView;
            public TextView textViewNamaMovie;
            public LinearLayout layoutitem;

            //Initializing Views
            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.imageviewMovie);
                textViewNamaMovie = (TextView) itemView.findViewById(R.id.textviewNamaMovie);
                layoutitem = (LinearLayout) itemView.findViewById(R.id.layout_item);
            }
        }
    }

    public class MovieLainnyaAdapter extends RecyclerView.Adapter<MovieLainnyaAdapter.ViewHolder> {

        //Imageloader to load image
        private ImageLoader imageLoader;
        private Context context;
        private List<Movie> listMovieLainnya = new ArrayList<>();

        //List to store all superheroes

        //Constructor of this class
        public MovieLainnyaAdapter(List<Movie> movie, Context context){
            super();
            //Getting all superheroes

            this.listMovieLainnya = movie;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            //Getting the particular item from the list
            final Movie movie =  this.listMovieLainnya.get(position);

            //Loading image from url
            //imageLoader = CustomVolleyRequest.getInstance(getActivity()).getImageLoader();
            //imageLoader.get(movie.getImage_movie(), ImageLoader.getImageListener(holder.imageView, R.drawable.ic_movie_filter_black_24dp, android.R.drawable.ic_dialog_alert));

            //Showing data on the views
            //holder.imageView.setImageUrl(movie.getImage_movie(), imageLoader);
            Glide.with(this.context)
                    .load(movie.getImage_movie())
                    .into(holder.imageView);
            String nama_tahun = movie.getNama_movie()+" ("+movie.getTahun_movie()+")";
            holder.textViewNamaMovie.setText(nama_tahun);

            holder.layoutitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "you "+movie.getNama_movie(), Toast.LENGTH_LONG).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return this.listMovieLainnya.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            //Views
            public ImageView imageView;
            public TextView textViewNamaMovie;
            public LinearLayout layoutitem;

            //Initializing Views
            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.imageviewMovie);
                textViewNamaMovie = (TextView) itemView.findViewById(R.id.textviewNamaMovie);
                layoutitem = (LinearLayout) itemView.findViewById(R.id.layout_item);
            }
        }
    }

    private JsonArrayRequest getSedangTayang(int requestCount) {
        //Initializing ProgressBar


        //Displaying Progressbar
        progressBar1.setVisibility(View.VISIBLE);
        progressBar1.setIndeterminate(true);

        //JsonArrayRequest of volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLs.URL_MOVIE_SEDANGTAYANG + String.valueOf(requestCount),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Calling method parseData to parse the json response
                        parseSedangTayang(response);
                        //Hiding the progressbar
                        progressBar1.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar1.setVisibility(View.GONE);
                        //If an error occurs that means end of the list has reached
                        Toast.makeText(getActivity(), "Movie Sedang Tayang tidak ada lagi", Toast.LENGTH_SHORT).show();
                    }
                });

        //Returning the request
        return jsonArrayRequest;
    }

    private JsonArrayRequest getAkanTayang(int requestCount) {
        //Initializing ProgressBar


        //Displaying Progressbar
        progressBar2.setVisibility(View.VISIBLE);
        progressBar2.setIndeterminate(true);

        //JsonArrayRequest of volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLs.URL_MOVIE_AKANTAYANG + String.valueOf(requestCount),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Calling method parseData to parse the json response
                        parseAkanTayang(response);
                        //Hiding the progressbar
                        progressBar2.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar2.setVisibility(View.GONE);
                        //If an error occurs that means end of the list has reached
                        Toast.makeText(getActivity(), "Movie Akan Tayang tidak ada lagi", Toast.LENGTH_SHORT).show();
                    }
                });

        //Returning the request
        return jsonArrayRequest;
    }

    private JsonArrayRequest getLainnya(int requestCount) {
        //Initializing ProgressBar


        //Displaying Progressbar
        progressBar3.setVisibility(View.VISIBLE);
        progressBar3.setIndeterminate(true);

        //JsonArrayRequest of volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLs.URL_MOVIE_LAINNYA + String.valueOf(requestCount),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Calling method parseData to parse the json response
                        parseLainnya(response);
                        //Hiding the progressbar
                        progressBar3.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar3.setVisibility(View.GONE);
                        //If an error occurs that means end of the list has reached
                        Toast.makeText(getActivity(), "Movie tidak ada lagi", Toast.LENGTH_SHORT).show();
                    }
                });

        //Returning the request
        return jsonArrayRequest;
    }

    //This method will get data from the web api
    private void getSedangTayang() {
        //Adding the method to the queue by calling the method getDataFromServer
        requestQueue.add(getSedangTayang(requestCountSedangTayang));
        //Incrementing the request counter
        requestCountSedangTayang++;
    }

    private void getAkanTayang() {
        //Adding the method to the queue by calling the method getDataFromServer
        requestQueue.add(getAkanTayang(requestCountAkanTayang));
        //Incrementing the request counter
        requestCountAkanTayang++;
    }

    private void getLainnya() {
        //Adding the method to the queue by calling the method getDataFromServer
        requestQueue.add(getLainnya(requestCountLainnya));
        //Incrementing the request counter
        requestCountLainnya++;
    }



    //This method will parse json data
    private void parseSedangTayang(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            //Creating the superhero object
            Movie movie = new Movie();
            JSONObject json = null;
            try {
                //Getting json
                json = array.getJSONObject(i);

                //Adding data to the superhero object
                movie.setImage_movie(json.getString("image_movie"));
                movie.setNama_movie(json.getString("nama_movie"));
                movie.setId_movie(json.getInt("id_movie"));
                movie.setTahun_movie(json.getString("tahun_movie"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Adding the superhero object to the list
            listMovieSedangTayang.add(movie);
        }

        //Notifying the adapter that data has been added or changed
        adapterSedangTayang.notifyDataSetChanged();
    }

    private void parseAkanTayang(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            //Creating the superhero object
            Movie movie = new Movie();
            JSONObject json = null;
            try {
                //Getting json
                json = array.getJSONObject(i);

                //Adding data to the superhero object
                movie.setImage_movie(json.getString("image_movie"));
                movie.setNama_movie(json.getString("nama_movie"));
                movie.setId_movie(json.getInt("id_movie"));
                movie.setTahun_movie(json.getString("tahun_movie"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Adding the superhero object to the list
            listMovieAkanTayang.add(movie);
        }

        //Notifying the adapter that data has been added or changed
        adapterAkanTayang.notifyDataSetChanged();
    }

    private void parseLainnya(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            //Creating the superhero object
            Movie movie = new Movie();
            JSONObject json = null;
            try {
                //Getting json
                json = array.getJSONObject(i);

                //Adding data to the superhero object
                movie.setImage_movie(json.getString("image_movie"));
                movie.setNama_movie(json.getString("nama_movie"));
                movie.setId_movie(json.getInt("id_movie"));
                movie.setTahun_movie(json.getString("tahun_movie"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Adding the superhero object to the list
            listMovieLainnya.add(movie);
        }

        //Notifying the adapter that data has been added or changed
        adapterLainnya.notifyDataSetChanged();
    }

    //This method would check that the recyclerview scroll has reached the bottom or not
    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }






}



