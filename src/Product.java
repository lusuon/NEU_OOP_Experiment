public class Product {
    //先声明实例变量
    private String code;
    private String description;
    private Double price;

    //再写构造函数
    Product(String initCode, String initDescription, double initPrice) {
        code = initCode;
        description = initDescription;
        price = initPrice;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}


/*
    public Boolean equals(Object object) //格式可能不规范
    @Override
    public String toString() {
        return super.toString();
    }
}
*/
