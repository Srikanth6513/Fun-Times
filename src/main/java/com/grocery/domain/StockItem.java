package com.grocery.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StockItem {
    
	private String product;
	private String unit;
    private Double  cost; 
}
