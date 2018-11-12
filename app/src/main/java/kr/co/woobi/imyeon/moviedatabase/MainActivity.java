package kr.co.woobi.imyeon.moviedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    DBHelper mydb;
    ArrayAdapter mAdapter;
    private Button buttonAdd, buttoninsert, buttonedit, buttondelete;


    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.clear();
        mAdapter.addAll(mydb.getAllMovies());
        mAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd=(Button)findViewById(R.id.buttonAdd);
        buttoninsert=findViewById(R.id.buttoninsert);
        buttonedit=findViewById(R.id.buttonedit);
        buttondelete=findViewById(R.id.buttondelete);

        mydb=new DBHelper(this);
        ArrayList arrayList=mydb.getAllMovies();

        mAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList) ;
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg4) {
                String item=(String)((ListView)parent).getItemAtPosition(position);
                String[] strArray=item.split(" ");
                int id=Integer.parseInt(strArray[0]);
                Bundle bundle=new Bundle();
                bundle.putInt("id",id);
                Intent intent = new Intent(getApplicationContext(),DisplayMoive.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
