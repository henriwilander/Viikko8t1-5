package com.example.henriwilander.limsakone;

public class Bottle {


    private String[] trademarks = {"Pepsi Max", "Coca-Cola Zero", "Fanta Zero", "Fanta"};
    private float[] sizes = {0.5f, 1.5f,};
    private float[] prices = {1.8f, 2.2f, 2.0f, 2.5f, 1.95f};
    private String[] products = {"Pepsi Max 0,5l", "Pepsi Max 1,5l", "Coca Cola Zero 0,5l", "Coca Cola Zero 1,5l", "Fanta Zero 0,5l", "Fanta Zero 1,5l", "Fanta 0,5l", "Fanta 1,5l"};

    private String trademark;
    private float size;
    private float price;
    private String product;

    public Bottle(int n) {
        switch (n) {
            case 0:
                trademark = trademarks[0];
                size = sizes[0];
                price = prices[0];
                product = products[0];
                break;
            case 1:
                trademark = trademarks[0];
                size = sizes[1];
                price = prices[1];
                product = products[1];
                break;
            case 2:
                trademark = trademarks[1];
                size = sizes[0];
                price = prices[2];
                product = products[2];
                break;
            case 3:
                trademark = trademarks[1];
                size = sizes[1];
                price = prices[3];
                product = products[3];
                break;
            case 4:
                trademark = trademarks[2];
                size = sizes[0];
                price = prices[4];
                product = products[4];
                break;
            case 5:
                trademark = trademarks[2];
                size = sizes[1];
                price = prices[3];
                product = products[5];
                break;
            case 6:
                trademark = trademarks[3];
                size = sizes[0];
                price = prices[4];
                product = products[6];
                break;

            case 7:
                trademark = trademarks[3];
                size = sizes[1];
                price = prices[3];
                product = products[7];
                break;
            default:
                break;
        }
    }

    public String bottleSelector() {
        return trademark;
    }

    public float sizeSelector() {
        return size;
    }

    public float priceSelector() {
        return price;
    }

    public String getProduct() {
        return product;
    }
}
