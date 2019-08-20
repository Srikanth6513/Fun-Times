package com.grocery.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Discount {
	
	private String product;
	private int unitCount;
	private String disProduct;
	private double disProductPer;
	private Date  disStartDate;
	private Date  disEndDate;
	
}
