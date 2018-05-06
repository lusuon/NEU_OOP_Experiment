public class CoffeeBrewer extends Product {
    private String model;
    private String waterSupply;
    private int numberOfCups;

    public CoffeeBrewer(String initCode, String initDescription,double initiPrice,String initiModel,String initWaterSupply, int initNumberOfCups){
        super(initCode,initDescription,initiPrice);
        model = initiModel;
        waterSupply = initWaterSupply;
        numberOfCups = initNumberOfCups;
    }

    public String getModel() {
        return this.model;
    }
    public String getWaterSupply() {
        return this.waterSupply;
    }
    public int getNumberOfCups(){
        return this.numberOfCups;
    }

    public String toString() {
        return this.getCode()+"_"+this.getDescription()+"_"+this.getPrice()+"_"+this.getModel()+"_"+this.getWaterSupply()+"_"+this.numberOfCups;
    }
}
