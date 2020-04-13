package com.example.obidi.miwok2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        // get references of the textViews and set up an onClick event listener
        TextView numbers = (TextView) findViewById(R.id.category_numbers);

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, CategoryListActivity.class);
                intent.putExtra("category", "number");
                intent.putExtra("title", "Numbers");
                startActivity(intent);
            }
        });

        TextView family = (TextView) findViewById(R.id.category_family);

        family.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, CategoryListActivity.class);
                intent.putExtra("category", "family");
                intent.putExtra("title", "Family");
                startActivity(intent);
            }
        });

        TextView colors = (TextView) findViewById(R.id.category_colors);

        colors.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, CategoryListActivity.class);
                intent.putExtra("category", "color");
                intent.putExtra("title", "Colors");
                startActivity(intent);
            }
        });

        TextView phrases = (TextView) findViewById(R.id.category_phrases);

        phrases.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, CategoryListActivity.class);
                intent.putExtra("category", "phrase");
                intent.putExtra("title", "Phrases");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
