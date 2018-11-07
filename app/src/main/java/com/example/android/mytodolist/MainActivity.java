package com.example.android.mytodolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener
{
    //variables for widgets created
    private EditText itemField;
    private Button addItem;
    private ListView listOfItems;

    //arrayadapters help fill in list views
    private ArrayAdapter<String> itemAdapter;
    private ArrayList<String> itemsArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign references made to variables
        itemField   = findViewById(R.id.edit_text_field);
        addItem     = findViewById(R.id.enter_button);
        listOfItems = findViewById(R.id.item_list);

        itemsArrayList = FileHelper.readFromFile(this);
        itemAdapter    = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsArrayList);

        //fill list with data read from file
        listOfItems.setAdapter(itemAdapter);

        //create on click listener for addItem
        addItem.setOnClickListener(this);

        //allows deletion of items
        listOfItems.setOnItemClickListener(this);
    }

    /**
     * takes in input from itemField, my text field, and adds it to the list of items
     * @param view
     */
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.enter_button:
                String enteredItem = itemField.getText().toString();
                itemAdapter.add(enteredItem);
                itemField.setText(""); //clears text field after adding item

                //save data to file
                FileHelper.writeToFile(itemsArrayList, this);

                //Toasts are notifications for the user
                Toast.makeText(this, "Item successfully added.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * allows deletion of an item
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        itemsArrayList.remove(position);

        //update adapter
        itemAdapter.notifyDataSetChanged();
        FileHelper.writeToFile(itemsArrayList, this); //updates the file with our new list if an item is deleted

        Toast.makeText(this, "Item successfully deleted", Toast.LENGTH_SHORT).show();

    }
}
