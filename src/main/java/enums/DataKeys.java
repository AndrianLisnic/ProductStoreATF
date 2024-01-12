package enums;

public enum DataKeys {
    USERNAME("The username used for Log In"),
    PASSWORD("The password used for Log In"),
    SELECTED_PRODUCT("The product name that the user selected from the Landing page"),
    PRODUCT_PRICE("The price of the added product in cart");

    private final String description;

    DataKeys(String description) {
        this.description = description;
    }
}
