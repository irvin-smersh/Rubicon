package com.example.irvin.rubicon;

import java.util.List;

public class TvShow {

    private String original_name;
    private List<Integer> genre_ids;
    private String name;
    private float popularity;
    private List<String> origin_country;
    private int vote_count;
    private String first_air_date;
    private String backdrop_path;
    private String original_language;
    private int id;
    private float vote_average;
    private String overview;
    private String poster_path;

    public TvShow(){

    }

    public TvShow(String original_name, List<Integer> genre_ids, String name, float popularity, List<String> origin_country, int vote_count, String first_air_date, String backdrop_path, String original_language, int id, float vote_average, String overview, String poster_path) {
        this.original_name = original_name;
        this.genre_ids = genre_ids;
        this.name = name;
        this.popularity = popularity;
        this.origin_country = origin_country;
        this.vote_count = vote_count;
        this.first_air_date = first_air_date;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.poster_path = poster_path;
    }

    public TvShow(TvShow tvShow) {
        this.original_name = tvShow.original_name;
        this.genre_ids = tvShow.genre_ids;
        this.name = tvShow.name;
        this.popularity = tvShow.popularity;
        this.origin_country = tvShow.origin_country;
        this.vote_count = tvShow.vote_count;
        this.first_air_date = tvShow.first_air_date;
        this.backdrop_path = tvShow.backdrop_path;
        this.original_language = tvShow.original_language;
        this.id = tvShow.id;
        this.vote_average = tvShow.vote_average;
        this.overview = tvShow.overview;
        this.poster_path = tvShow.poster_path;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
