package com.example.mom.afflilate.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailsBean {
    @SerializedName("data")
    @Expose
    private List<ProductInfo> data = null;

    public List<ProductInfo> getData() {
        return data;
    }

    public void setData(List<ProductInfo> data) {
        this.data = data;
    }

    public class ProductInfo {

        @SerializedName("product_title")
        @Expose
        private String productTitle;
        @SerializedName("can_compare")
        @Expose
        private Boolean canCompare;
        @SerializedName("product_lowest_price")
        @Expose
        private Integer productLowestPrice;
        @SerializedName("product_link")
        @Expose
        private String productLink;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("product_category")
        @Expose
        private String productCategory;
        @SerializedName("product_sub_category")
        @Expose
        private String productSubCategory;
        @SerializedName("product_rating")
        @Expose
        private String productRating;
        @SerializedName("product_image")
        @Expose
        private String productImage;

        public String getProductTitle() {
            return productTitle;
        }

        public void setProductTitle(String productTitle) {
            this.productTitle = productTitle;
        }

        public Boolean getCanCompare() {
            return canCompare;
        }

        public void setCanCompare(Boolean canCompare) {
            this.canCompare = canCompare;
        }

        public Integer getProductLowestPrice() {
            return productLowestPrice;
        }

        public void setProductLowestPrice(Integer productLowestPrice) {
            this.productLowestPrice = productLowestPrice;
        }

        public String getProductLink() {
            return productLink;
        }

        public void setProductLink(String productLink) {
            this.productLink = productLink;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductCategory() {
            return productCategory;
        }

        public void setProductCategory(String productCategory) {
            this.productCategory = productCategory;
        }

        public String getProductSubCategory() {
            return productSubCategory;
        }

        public void setProductSubCategory(String productSubCategory) {
            this.productSubCategory = productSubCategory;
        }

        public String getProductRating() {
            return productRating;
        }

        public void setProductRating(String productRating) {
            this.productRating = productRating;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }
    }
}
