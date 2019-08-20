package com.grocery.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.grocery.domain.PurchaseProduct;
import com.grocery.service.GroceryService;
import com.grocery.util.ProductNames;

public class Grocery {

	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private Scanner input = null;

	public void groceryMenuDetails() {
		input = new Scanner(System.in);
		GroceryService grocesyService = new GroceryService();
		int choice = showMenu();
		do {
			switch (choice) {
			case 1:
				System.out.println("product\tunit\tcost");
				grocesyService.getProductList().forEach(
						item -> System.out.println(item.getProduct() + "\t" + item.getUnit() + "\t" + item.getCost()));
				break;
			case 2:
				System.out.println("product\tunit\tcost\tdisProduct\tdisProdPer\tdisStartDate\tdisEndDate");
				grocesyService.getDiscountDetails()
						.forEach(discount -> System.out.println(discount.getProduct() + "\t" + discount.getUnitCount()
								+ "\t" + discount.getDisProduct() + "\t" + discount.getDisProductPer() + "\t"
								+ DateFormatUtils.format(discount.getDisStartDate(), Grocery.DATE_FORMAT) + "\t"
								+ DateFormatUtils.format(discount.getDisEndDate(), Grocery.DATE_FORMAT)));
				break;
			case 3:
				String oneMoreProduct = "N";

				List<PurchaseProduct> purchaseProducts = new ArrayList<>();
				do {
					PurchaseProduct purchaseProduct = new PurchaseProduct();
					System.out.println(String.format("Please enter the product name (%s,%s,%s,%s):",
							ProductNames.SOUP.getName(), ProductNames.APPLES.getName(), ProductNames.MILK.getName(),
							ProductNames.BREAD.getName()));
					purchaseProduct.setProduct(input.nextLine());
					System.out.println("Please enter the product count:");
					purchaseProduct.setUnitCount(Integer.parseInt(input.nextLine()));
					purchaseProducts.add(purchaseProduct);
					System.out.println("are you going to add one more product to basket (Y or N):");
					oneMoreProduct = input.nextLine();
				} while ("Y".equalsIgnoreCase(oneMoreProduct));
				System.out.println("Please enter the int value for date(today=1,tomorrow=2....):");
				double totalCost = grocesyService.calculateTotalPrice(purchaseProducts,Integer.parseInt(input.nextLine()));
				System.out.println("total cost:" + totalCost);
				break;
			case 4:
				System.exit(0);
			default:
				System.out.println("Sorry, please enter valid Option");
			}
			choice = showMenu();
		} while (choice != 4);
		System.out.println("Thank you. Good Bye.");
		input.close();
	}

	public static void main(String[] args) {
		Grocery grocery = new Grocery();
		grocery.groceryMenuDetails();
	}

	private int showMenu() {
		int option = 0;
		System.out.println("Please choose the below options");
		System.out.println("--------------------------------");
		System.out.println("1. Available stock");
		System.out.println("2. Available discounts");
		System.out.println("3. Add product to basket and Calculate total price");
		System.out.println("4. Quit");
		System.out.println("--------------");
		System.out.println("Enter your choice:");
		option = Integer.parseInt(input.nextLine());
		return option;

	}

}
