package com.stackroute.distributor.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @ApiModelProperty(notes = "The Movie specific imdbID")
    int  movieId;
    @NotNull
    @ApiModelProperty(notes = "The Movie specific Title")
    String movieTitle;
    @NotNull
    @Size(min = 4,max = 4)
    @ApiModelProperty(notes = "The Movie specific year of release")
    String yearOfRelease;
    @NotNull
    @ApiModelProperty(notes = "The poster Url of Movie")
    String posterUrl;
    @ApiModelProperty(notes = "List of ratingsfor the Movie")
    int[] ratingArray;
    @ApiModelProperty(notes = "The average rating of Movie")
    int averageRating;
    @ApiModelProperty(notes = "The Language of Movie")
    String language;
    @ApiModelProperty(notes = "The certificate of Movie")
    String certificate;
    @ApiModelProperty(notes = "The genere of Movie")
    String genre;

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