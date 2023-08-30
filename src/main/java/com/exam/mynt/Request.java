package com.exam.mynt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    private Double weight;
    private Double height;
    private Double width;
    private Double length;
    private String voucher;

}
