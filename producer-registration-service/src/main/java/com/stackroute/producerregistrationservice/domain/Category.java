package com.stackroute.producerregistrationservice.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Category")
public class Category {
    @ApiModelProperty(notes = "Type")
    @NotNull
    String type;
    @ApiModelProperty(notes = "noOfColumn")
    @NotNull
    Integer noOfColumns;
    @ApiModelProperty(notes = "noOfRows")
    @NotNull
    Integer noOfRows;
    @ApiModelProperty(notes = "SeatLayout")
    List<SeatLayout> seatLayout;
}
