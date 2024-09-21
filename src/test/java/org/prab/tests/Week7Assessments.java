package org.prab.tests;

import org.prab.fw.TestBase;
import org.testng.annotations.Test;
import org.prab.pages.Week7ContextMenuPage;
import org.prab.pages.Week7EcommercePage;

public class Week7Assessments extends TestBase {
    @Test(priority = 1)
    public void contextMenuTest() {
        driver.navigate().to("https://qaplayground.dev/apps/context-menu/");
        Week7ContextMenuPage contextMenu = new Week7ContextMenuPage(driver);
        contextMenu.doShareAndTest();
    }

    @Test(priority = 2)
    public void sortingTest() throws InterruptedException {
        driver.navigate().to("https://ecommerce-playground.lambdatest.io/");
        Week7EcommercePage ecommercePage = new Week7EcommercePage(driver);

        String option = "Apple";
        ecommercePage.clickMegaMenuOption(option);
        // Retrieve displayed products count
        int count = ecommercePage.getProductsCount();
        System.out.println("Total products displayed in the first page: " + count);

        // Assume maximum products < 100
        System.out.println("Change the maximum products to show to 100");
        ecommercePage.setProductsToShow("100");
        System.out.println("Total products displayed in the current page: " + ecommercePage.getProductsCount());

        int limit = (count > 5) ? 5 : count; // If the total count is less than 5 then it will take the count
        // Print first 5 products Name and Price (Before Sort)
        System.out.println("First " + limit + " product details before sorting");
        ecommercePage.printProductDetailsByLimit(limit);

        // Print first 5 products Name and Price (After Sort)
        ecommercePage.sortProducts("Price (Low > High)");
        System.out.println("First " + limit + " product details after sorting");
        ecommercePage.printProductDetailsByLimit(limit);
    }
}