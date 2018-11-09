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
public class Distributor {
    @Id
    @ApiModelProperty(notes = "The email of distributor")
    String email;
    @ApiModelProperty(notes = "The Movie List")
    ArrayList<City> cities;
}





/*{
"email":string,
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

}
*/