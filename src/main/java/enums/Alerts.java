package enums;

import lombok.Getter;

@Getter
public enum Alerts {
    PRODUCT_ADDED("Product added.");

    public String content;

    Alerts(String content) {
        this.content = content;
    }
}
