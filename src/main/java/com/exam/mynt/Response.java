package com.exam.mynt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private String classification;
    private String description;
    private Double volume;
    private Double price;
    private Double discount;
    private Double totalPrice;

}
