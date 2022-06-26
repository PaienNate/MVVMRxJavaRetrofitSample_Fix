package com.jc.mvvmrxjavaretrofitsample.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//使用GSON生成替代原本的数据类，主要是接口更新但是我懒得自己写数据类了。
public class Movie {

    /**
     * data
     */
    @SerializedName("data")
    private List<DataBean> data;
    /**
     * createdAt
     */
    @SerializedName("createdAt")
    private Long createdAt;
    /**
     * updatedAt
     */
    @SerializedName("updatedAt")
    private Long updatedAt;
    /**
     * id
     */
    @SerializedName("id")
    private String id;
    /**
     * originalName
     */
    @SerializedName("originalName")
    private String originalName;
    /**
     * imdbVotes
     */
    @SerializedName("imdbVotes")
    private Integer imdbVotes;
    /**
     * imdbRating
     */
    @SerializedName("imdbRating")
    private String imdbRating;
    /**
     * rottenRating
     */
    @SerializedName("rottenRating")
    private String rottenRating;
    /**
     * rottenVotes
     */
    @SerializedName("rottenVotes")
    private Integer rottenVotes;
    /**
     * year
     */
    @SerializedName("year")
    private String year;
    /**
     * imdbId
     */
    @SerializedName("imdbId")
    private String imdbId;
    /**
     * alias
     */
    @SerializedName("alias")
    private String alias;
    /**
     * doubanId
     */
    @SerializedName("doubanId")
    private String doubanId;
    /**
     * type
     */
    @SerializedName("type")
    private String type;
    /**
     * doubanRating
     */
    @SerializedName("doubanRating")
    private String doubanRating;
    /**
     * doubanVotes
     */
    @SerializedName("doubanVotes")
    private Integer doubanVotes;
    /**
     * duration
     */
    @SerializedName("duration")
    private Integer duration;
    /**
     * dateReleased
     */
    @SerializedName("dateReleased")
    private String dateReleased;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Integer getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(Integer imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getRottenRating() {
        return rottenRating;
    }

    public void setRottenRating(String rottenRating) {
        this.rottenRating = rottenRating;
    }

    public Integer getRottenVotes() {
        return rottenVotes;
    }

    public void setRottenVotes(Integer rottenVotes) {
        this.rottenVotes = rottenVotes;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(String doubanId) {
        this.doubanId = doubanId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoubanRating() {
        return doubanRating;
    }

    public void setDoubanRating(String doubanRating) {
        this.doubanRating = doubanRating;
    }

    public Integer getDoubanVotes() {
        return doubanVotes;
    }

    public void setDoubanVotes(Integer doubanVotes) {
        this.doubanVotes = doubanVotes;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDateReleased() {
        return dateReleased;
    }

    public void setDateReleased(String dateReleased) {
        this.dateReleased = dateReleased;
    }

    public static class DataBean {
        /**
         * createdAt
         */
        @SerializedName("createdAt")
        private Long createdAt;
        /**
         * updatedAt
         */
        @SerializedName("updatedAt")
        private Long updatedAt;
        /**
         * id
         */
        @SerializedName("id")
        private String id;
        /**
         * poster
         */
        @SerializedName("poster")
        private String poster;
        /**
         * name
         */
        @SerializedName("name")
        private String name;
        /**
         * genre
         */
        @SerializedName("genre")
        private String genre;
        /**
         * description
         */
        @SerializedName("description")
        private String description;
        /**
         * language
         */
        @SerializedName("language")
        private String language;
        /**
         * country
         */
        @SerializedName("country")
        private String country;
        /**
         * lang
         */
        @SerializedName("lang")
        private String lang;
        /**
         * shareImage
         */
        @SerializedName("shareImage")
        private String shareImage;
        /**
         * movie
         */
        @SerializedName("movie")
        private String movie;

        public Long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Long createdAt) {
            this.createdAt = createdAt;
        }

        public Long getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Long updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getShareImage() {
            return shareImage;
        }

        public void setShareImage(String shareImage) {
            this.shareImage = shareImage;
        }

        public String getMovie() {
            return movie;
        }

        public void setMovie(String movie) {
            this.movie = movie;
        }
    }
}
