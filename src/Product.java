public class Product {
    //声明实例变量
    private String code;
    private String description;
    private double price;

    //Constructor Method 构造函数
    Product(String initCode, String initDescription, double initPrice) {
        code = initCode;
        description = initDescription;
        price = initPrice;
    }

    //Accessor Method  访问方法
    public String getCode() {
        return this.code;
    }
    public String getDescription() {
        return this.description;
    }
    public Double getPrice() {
        return this.price;
    }

    @Override
    //此处重写的equals()、toString()方法需要再考虑什么因素？
    //尝试使用code作为区分
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

