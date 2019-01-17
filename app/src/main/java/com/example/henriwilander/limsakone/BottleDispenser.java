package com.example.henriwilander.limsakone;

import android.content.Context;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BottleDispenser {

    private static BottleDispenser dispenser = new BottleDispenser();

    private int bottles;
    // The array for the Bottle-objects
    ArrayList<Bottle> bottle_array;
    private float money;


    private BottleDispenser() {
        bottles = 8;
        money = 0;
        // Initialize the array
        bottle_array = new ArrayList();
        // Add Bottle-objects to the array
        for(int i = 0;i<bottles;i++) {
            // Use the default constructor to create new Bottles
            bottle_array.add(new Bottle(i));
        }
    }

    public static BottleDispenser getInstance() {
        return dispenser;
    }

    public void addMoney(TextView screen, SeekBar seekbar) {
        int coins;
        coins = seekbar.getProgress();
        money += coins;
        seekbar.setProgress(0);
        screen.setText("Klink! Lisää rahaa laitteeseen!");
    }

    public Bottle isBottlesLeft(String code) {
        for (Bottle a : bottle_array) {
            if (a.getProduct().equals(code)) {
                return a;
            }
        }
        return null;
    }

    public void bottleChecker (String userChoice, TextView screen, Context context) {
        Bottle bottle = isBottlesLeft(userChoice);
        if (bottle == null) {
            screen.setText("Juomat loppuivat!\n");
        } else if (moneyCheck() < bottle.priceSelector()) {
            screen.setText("Syötä rahaa ensin!\n");
        } else {
            String receipt = "Ostokuitti\n"+bottle.getProduct()+" "+bottle.priceSelector()+"€\n";
            writeFile(receipt, context);
            buyBottle(bottle, screen);
        }


    }

    public void buyBottle(Bottle userChoice, TextView screen) {
        bottles -= 1;
        screen.setText("KACHUNK! "+userChoice.bottleSelector()+" tipahti masiinasta!\n");
        money -= userChoice.priceSelector();
        removeBottle(userChoice);
    }

    public void returnMoney(TextView screen) {
        float money1 = (float) (Math.round(money*100)/100.0);
        Float.toString(money1);
        String print = ("Klink klink. Sinne menivät rahat!\n Rahaa tuli ulos "+money1+"€.\n");
        screen.setText(print);
        money = 0;
    }

    public float moneyCheck() {
        return money;
    }

    public void printBottles(TextView screen) {
        int i = 0;
        String print = "";
        for(Bottle bottle : bottle_array) {
            i++;
            print = print + i+". Nimi: "+bottle.bottleSelector()+" Koko: "+bottle.sizeSelector()+"	Hinta: "+bottle.priceSelector()+"\n";
        }
        screen.setText(print);
    }

    public void removeBottle(Bottle n) {
        bottle_array.remove(n);
    }

    public void writeFile(String purchaseEvent, Context context) {
        try {
            String fileName = "kuitti.txt";
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            ows.write(purchaseEvent);
            ows.close();
            readFile(context);
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", "Tiedostoa ei löydy.");
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä.");
        }
    }

    public void readFile(Context context) {
        try {
            InputStream ins = context.openFileInput("kuitti.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";
            String printText = "";

            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
            ins.close();
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", "Tiedostoa ei löydy.");
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä.");
        }
    }


}
