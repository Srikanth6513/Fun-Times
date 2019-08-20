package com.grocery.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.grocery.domain.Discount;
import com.grocery.domain.PurchaseProduct;
import com.grocery.domain.StockItem;
import com.grocery.util.ProductNames;

public class GroceryService {
	NumberFormat nf = NumberFormat.getInstance();
	public List<StockItem> getProductList() {
		List<StockItem> itemList = new ArrayList<>();
		StockItem stockItem = new StockItem();
		stockItem.setProduct(ProductNames.SOUP.getName());
		stockItem.setUnit("tin");
		stockItem.setCost(0.65);
		itemList.add(stockItem);
		stockItem = new StockItem();
		stockItem.setProduct(ProductNames.BREAD.getName());
		stockItem.setUnit("loaf");
		stockItem.setCost(0.80);
		itemList.add(stockItem);
		stockItem = new StockItem();
		stockItem.setProduct(ProductNames.MILK.getName());
		stockItem.setUnit("bottle");
		stockItem.setCost(1.30);
		itemList.add(stockItem);
		stockItem = new StockItem();
		stockItem.setProduct(ProductNames.APPLES.getName());
		stockItem.setUnit("single");
		stockItem.setCost(0.10);
		itemList.add(stockItem);
		return itemList;
	}
	public List<Discount> getDiscountDetails() {
		List<Discount> discountDetails = new ArrayList<>();
		Discount discount = new Discount();
		discount.setProduct(ProductNames.SOUP.getName());
		discount.setUnitCount(2);
		discount.setDisProduct(ProductNames.BREAD.getName());
		discount.setDisProductPer(0.5);
		discount.setDisStartDate(getDiscountDate(-1));
		discount.setDisEndDate(getDiscountDate(7));
		discountDetails.add(discount);
		discount = new Discount();
		discount.setProduct(ProductNames.APPLES.getName());
		discount.setUnitCount(1);
		discount.setDisProduct(ProductNames.APPLES.getName());
		discount.setDisProductPer(0.1);
		discount.setDisStartDate(getDiscountDate(3));
		discount.setDisEndDate(getMonthEndDate());
		discountDetails.add(discount);
		return discountDetails;
	}
	
	private Date getDiscountDate(int days) {
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, days);
	    return cal.getTime();
	}
	
	private Date getMonthEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}
	
	public double calculateTotalPrice(List<PurchaseProduct> purchaseProducts,int days) {
		double discountAmout = 0;
		double actualPrice = 0;
		try{
			
			List<Discount> discountDetails = getDiscountDetails();
			List<StockItem> itemList = getProductList();
			Date purchaseDate = getDiscountDate(days);
			for(PurchaseProduct purchaseProduct:purchaseProducts) {
				StockItem stockInd = itemList.stream().filter(stock -> stock.getProduct().equals(purchaseProduct.getProduct())).findAny().get();
				actualPrice = actualPrice + stockInd.getCost() * purchaseProduct.getUnitCount() ;	
			    Optional<Discount> discountDetOpt = discountDetails.stream().filter(discount -> discount.getProduct().equals(purchaseProduct.getProduct())).findAny();
			    if(discountDetOpt.isPresent() && purchaseDate.after(discountDetOpt.get().getDisStartDate()) && purchaseDate.before(discountDetOpt.get().getDisEndDate()) ) {
					Discount discountDet = discountDetOpt.get();
					Optional<PurchaseProduct> disPurchasedProd = purchaseProducts.stream().filter(disPurProd -> disPurProd.getProduct().equals(discountDet.getDisProduct())).findAny();
					if(disPurchasedProd.isPresent() &&  purchaseProduct.getUnitCount() >= discountDet.getUnitCount() ){
						StockItem disStock = itemList.stream().filter(stock -> stock.getProduct().equals(disPurchasedProd.get().getProduct())).findAny().get();
						int disProdCount = purchaseProduct.getUnitCount()/discountDet.getUnitCount();
						double actDisProd =  disPurchasedProd.get().getUnitCount()*disStock.getCost();
						double disPrice = disProdCount * disStock.getCost() * discountDet.getDisProductPer();
					    if(disPrice < actDisProd) {
					    	discountAmout = discountAmout+disPrice;
					    }else {
					    	discountAmout = discountAmout+actDisProd;
					    }
					}
				}
			}
			System.out.println("Actual Price:"+actualPrice);
			System.out.println("Discount Price:"+discountAmout);
			nf.setMaximumFractionDigits(2);
				
		}catch(NoSuchElementException nse){
			System.err.println("Please Enter Products From [soup,bread,milk and apple]");
		}
		return Double.parseDouble(nf.format(actualPrice - discountAmout));
	}
	
}
