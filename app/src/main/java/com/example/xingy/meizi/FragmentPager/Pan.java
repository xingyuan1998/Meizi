package com.example.xingy.meizi.FragmentPager;

/**
 * Created by xingy on 2017/6/23.
 */

public class Pan {
    private String img;
    private String movieId;
    private Float score;
    private String name;
    private String status;

    public Pan(String img,String movieId,Float score,String name,String status){
        this.img=img;
        this.movieId=movieId;
        this.score=score;
        this.name=name;
        this.status=status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String url) {
        this.img = img;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }





}
