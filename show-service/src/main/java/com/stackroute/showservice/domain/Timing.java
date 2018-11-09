package com.stackroute.showservice.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Timing {
     private String showTime;
     private String showId;
     private List<Category> categories;

     public Timing(){
     }

     public Timing(String showTime, String showId, List<Category> categories) {
          this.showTime = showTime;
          this.showId = showId;
          this.categories = categories;
     }

     public String getShowTime() {
          return showTime;
     }

     public void setShowTime(String showTime) {
          this.showTime = showTime;
     }

     public String getShowId() {
          return showId;
     }

     public void setShowId(String showId) {
          this.showId = showId;
     }

     public List<Category> getCategories() {
          return categories;
     }

     public void setCategories(List<Category> categories) {
          this.categories = categories;
     }

     @Override
     public String toString() {
          return "Timing{" +
                  "showTime='" + showTime + '\'' +
                  ", showId='" + showId + '\'' +
                  ", categories=" + categories +
                  '}';
     }
}
