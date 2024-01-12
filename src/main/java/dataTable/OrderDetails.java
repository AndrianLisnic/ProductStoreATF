package dataTable;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    public String name;
    public String country;
    public String city;
    public String creditCard;
    public String month;
    public String year;

}