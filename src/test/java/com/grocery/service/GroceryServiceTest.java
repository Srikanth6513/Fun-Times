package com.grocery.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.grocery.domain.PurchaseProduct;

public class GroceryServiceTest {

	@Test
	public void testScenario1(){
		
		List<PurchaseProduct> purchaseProducts=new ArrayList<PurchaseProduct>();
		
		PurchaseProduct soup=new PurchaseProduct();
		soup.setProduct("soup");
		soup.setUnitCount(3);
		
		PurchaseProduct bread=new PurchaseProduct();
		bread.setProduct("bread");
		bread.setUnitCount(2);
		
		purchaseProducts.add(soup);
		purchaseProducts.add(bread);
		
		GroceryService groceryService=new GroceryService();
		
		double result=groceryService.calculateTotalPrice(purchaseProducts, 1);
		assertEquals(3.15,result,5);
		
	}
	
	@Test
	public void testScenario2() {
		List<PurchaseProduct> purchaseProducts = new ArrayList<PurchaseProduct>();

		PurchaseProduct apples = new PurchaseProduct();
		apples.setProduct("apples");
		apples.setUnitCount(6);

		PurchaseProduct milk = new PurchaseProduct();
		milk.setProduct("milk");
		milk.setUnitCount(1);

		purchaseProducts.add(apples);
		purchaseProducts.add(milk);

		GroceryService groceryService = new GroceryService();

		double result = groceryService.calculateTotalPrice(purchaseProducts, 1);
		assertEquals(1.90, result, 0);

	}
	
	@Test
	public void testScenario3() {
		List<PurchaseProduct> purchaseProducts = new ArrayList<PurchaseProduct>();

		PurchaseProduct apples = new PurchaseProduct();
		apples.setProduct("apples");
		apples.setUnitCount(6);

		PurchaseProduct milk = new PurchaseProduct();
		milk.setProduct("milk");
		milk.setUnitCount(1);

		purchaseProducts.add(apples);
		purchaseProducts.add(milk);

		GroceryService groceryService = new GroceryService();

		double result = groceryService.calculateTotalPrice(purchaseProducts, 5);
		assertEquals(1.84, result, 4);

	}
	
	@Test
	public void testScenario4() {
		List<PurchaseProduct> purchaseProducts = new ArrayList<PurchaseProduct>();

		PurchaseProduct apples = new PurchaseProduct();
		apples.setProduct("apples");
		apples.setUnitCount(3);

		PurchaseProduct soup = new PurchaseProduct();
		soup.setProduct("soup");
		soup.setUnitCount(2);

		purchaseProducts.add(apples);
		purchaseProducts.add(soup);

		GroceryService groceryService = new GroceryService();

		double result = groceryService.calculateTotalPrice(purchaseProducts, 5);
		assertEquals(1.97, result, 7);

	}
	
}
