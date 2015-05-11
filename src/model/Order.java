package model;

import java.util.Date;
import java.util.List;


public class Order {

    private Date opened;
    private Date closed;
    private Date processed; // trovare un nome migliore

    private List<OrderLine> orderlines;

}
