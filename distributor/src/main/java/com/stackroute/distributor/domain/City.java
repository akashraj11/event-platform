package com.stackroute.distributor.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @ApiModelProperty(notes = "The email of distributor")
    String cityName;
    @ApiModelProperty(notes = "The Movie List")
    ArrayList<Movie> movies;
}


/*"email":string,
"City":[{"cityName":string,
          "Movie":[
                  {
                   "movieId":number,
                   "movieTitle":string,
                   "yearOfRelease":number,
                   "posterUrl":string,
                   "ratingArray": number[],
                   "averageRating":number,
                   "language":string,
                   "certificate": string,
                   "genre":string
                  }
                 ]
          }
         ]

}*/