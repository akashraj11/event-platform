package com.stackroute.producerregistrationservice.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Theatre")
public class Theatre {
    @ApiModelProperty(notes = "Theatre Id")
    @Id
    String theatreId;
    @ApiModelProperty(notes = "Theatre Name")
    String theatreName;
    @ApiModelProperty(notes = "Theatre City")
    String theatreCity;
    @ApiModelProperty(notes = "Theatre Address")
    String theatreAddress;
    @ApiModelProperty(notes = "Theatre ScreenLayout")
    ScreenLayout screenLayout;

}
