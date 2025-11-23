package Com.PageLocators;

public class PaymentPageLocators {
	public static String country = "//input[contains(@placeholder,'Country')]"; // xpath
	public static String countryList = "//section[contains(@class,'ta-results')]"; // xpath

	public static String shipping_country = "//button/span[contains(text(), 'dynamicxpath')]";

	public static String placeOrder = ".actions a"; // css

	public static String popupMsg = "#toast-container .toast-success[style*='opacity: 1']";

}
