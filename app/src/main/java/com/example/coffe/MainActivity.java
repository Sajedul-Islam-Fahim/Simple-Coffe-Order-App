package com.example.coffe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void decrease(View view) {
        if (quantity==0)
        {
            return;
        }
        else
        {
            quantity=quantity-1;
            TextView textView =findViewById(R.id.quantityId);
            textView.setText(""+quantity);
        }
    }

    public void increase(View view) {
        quantity=quantity+1;
        TextView textView =findViewById(R.id.quantityId);
        textView.setText(""+quantity);
    }

    public void result(View view) {
        EditText editText=findViewById(R.id.nameid);
        String name = editText.getText().toString();

        CheckBox creambox =findViewById(R.id.frstItemId);
        boolean hascream = creambox.isChecked();

        CheckBox choclatebox = findViewById(R.id.scndItemId);
        boolean haschoclate = choclatebox.isChecked();

        int price = totalPrice(hascream,haschoclate);
        String pricemessage=createSummary(name,price,hascream,haschoclate,quantity);




        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,pricemessage );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
        displaymessage(pricemessage);

    }


    private int totalPrice(boolean hascream, boolean haschoclate) {
        int baseprice=5;
        if (hascream)
        {
            baseprice=baseprice+1;
        }
        if (haschoclate)
        {
            baseprice=baseprice+2;
        }
        return  baseprice*quantity;
    }


    private void displaymessage(String pricemessage) {
        TextView textView=findViewById(R.id.resultId);
        textView.setText(pricemessage);
    }

    private String createSummary(String name, int price, boolean hascream, boolean haschoclate, int quantity) {
        String message = "Name: "+name;
        message+="\nAdd Whipped Cream?\n"+hascream;
        message+="\nAdd Chocolate?\n"+haschoclate;
        message+="\nQuantity: "+quantity;
        message+="\nTotal Price:$ "+price;
        message+="\n"+getString(R.string.thank_you);
        return message;
    }
    }
