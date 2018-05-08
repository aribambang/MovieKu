package com.movieku.app.Configuration;

import com.movieku.app.R;

public class URLs {
    private static final String ROOT_URL = "http://movieku.jktserver.com/api.php?apicall=";

    public static final String URL_REGISTER = ROOT_URL + "register";
    public static final String URL_LOGIN = ROOT_URL + "login";
    public static final String URL_MOVIE_SEDANGTAYANG = ROOT_URL + "sedangtayang_list&page=";
    public static final String URL_MOVIE_AKANTAYANG = ROOT_URL + "akantayang_list&page=";
    public static final String URL_MOVIE_LAINNYA = ROOT_URL + "lainnya_list&page=";
}
