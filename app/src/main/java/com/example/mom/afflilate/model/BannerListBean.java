package com.example.mom.afflilate.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class BannerListBean {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private Integer code;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public class Data implements Serializable {

        @SerializedName("videos")
        @Expose
        private ArrayList<Video> videos = null;
        @SerializedName("banners")
        @Expose
        private ArrayList<Banner> banners = null;

        public ArrayList<Video> getVideos() {
            return videos;
        }

        public void setVideos(ArrayList<Video> videos) {
            this.videos = videos;
        }

        public ArrayList<Banner> getBanners() {
            return banners;
        }

        public void setBanners(ArrayList<Banner> banners) {
            this.banners = banners;
        }
    }

    public class Video implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("video_id")
        @Expose
        private String videoId;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("post_date")
        @Expose
        private String postDate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPostDate() {
            return postDate;
        }

        public void setPostDate(String postDate) {
            this.postDate = postDate;
        }
    }

    public class Banner implements Serializable{

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("redirection_link")
        @Expose
        private String redirectionLink;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRedirectionLink() {
            return redirectionLink;
        }

        public void setRedirectionLink(String redirectionLink) {
            this.redirectionLink = redirectionLink;
        }
    }
}
