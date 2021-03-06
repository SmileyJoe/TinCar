package za.co.smileyjoedev.tincar.object;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import za.co.smileyjoedev.tincar.helper.JsonHelper;

/**
 * Created by cody on 2016/08/11.
 */
public class Amount implements Serializable {

    private String mSymbol;
    private double mValue;

    private static final long serialVersionUID = 42L;

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    public void setValue(double value) {
        mValue = value;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public double getValue() {
        return mValue;
    }

    public String getFormatted(){
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setCurrencySymbol(mSymbol);
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(mValue);
    }

    public static Amount fromApiResponse(JsonHelper helper){
        Amount amount = new Amount();

        amount.setSymbol(helper.getString("currency_symbol"));
        amount.setValue(helper.getDouble("price"));

        return amount;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "mSymbol='" + mSymbol + '\'' +
                ", mValue=" + mValue +
                '}';
    }
}
