package kr.co.woobi.imyeon.moviedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMoive extends AppCompatActivity implements View.OnClickListener {
    private DBHelper mydb;
    EditText name, director, year, nation, rating;
    Button buttonAdd, buttoninsert, buttondelete, buttonedit;
    int id = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        name = (EditText) findViewById(R.id.editTextName);
        director = (EditText) findViewById(R.id.editTextDirector);
        year = (EditText) findViewById(R.id.editTextYear);
        nation = (EditText) findViewById(R.id.editTextNation);
        rating = (EditText) findViewById(R.id.editTextRating);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttondelete = findViewById(R.id.buttondelete);
        buttonedit = findViewById(R.id.buttonedit);
        buttoninsert = findViewById(R.id.buttoninsert);
        buttoninsert.setOnClickListener(this);
        buttonedit.setOnClickListener(this);
        buttondelete.setOnClickListener(this);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                Cursor cursor = mydb.getData(Value);
                id = Value;
                cursor.moveToFirst();
                String n = cursor.getString(cursor.getColumnIndex(DBHelper.MOVIE_COLUMN_NAME));
                String d = cursor.getString(cursor.getColumnIndex(DBHelper.MOVIE_COLUMN_DIRECTOR));
                String y = cursor.getString(cursor.getColumnIndex(DBHelper.MOVIE_COLUMN_YEAR));
                String na = cursor.getString(cursor.getColumnIndex(DBHelper.MOVIE_COLUMN_NATION));
                String r = cursor.getString(cursor.getColumnIndex(DBHelper.MOVIE_COLUMN_RATING));
                if (!cursor.isClosed()) {
                    cursor.close();
                }
                Button b = (Button) findViewById(R.id.buttonAdd);
                b.setVisibility(View.INVISIBLE);

                name.setText((CharSequence) n);
                director.setText((CharSequence) d);
                year.setText((CharSequence) y);
                nation.setText((CharSequence) na);
                rating.setText((CharSequence) r);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttoninsert:
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    int Value = bundle.getInt("id");
                    if (Value > 0) {
                        if (mydb.updateMovie(id, name.getText().toString(), director.getText().toString(), year.getText().toString(),
                                nation.getText().toString(), rating.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        if (mydb.insertMoive(name.getText().toString(), director.getText().toString(),
                                year.getText().toString(), nation.getText().toString(), rating.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "추가되었음", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "추가되지 않았음", Toast.LENGTH_SHORT).show();

                        }
                        finish();
                    }
                }
            case R.id.buttondelete:
                Bundle bundle2 = getIntent().getExtras();
                if (bundle2 != null) {
                    int Value = bundle2.getInt("id");
                    if (Value > 0) {
                        mydb.deleteMoive(id);
                        Toast.makeText(getApplicationContext(), "삭제되었음", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "삭제되지 않았음", Toast.LENGTH_SHORT).show();
                    }

                }
            case R.id.buttonedit:
                Bundle bundle3 = getIntent().getExtras();
                if (bundle3 != null) {
                    int Value = bundle3.getInt("id");
                    if (Value > 0) {
                        if (mydb.updateMovie(id, name.getText().toString(), director.getText().toString(), year.getText().toString(),
                                nation.getText().toString(), rating.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                        }
                    }


                }

        }

    }




    public void insert(View view) {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int Value = bundle.getInt("id");
            if (Value > 0) {
                if (mydb.updateMovie(id, name.getText().toString(), director.getText().toString(), year.getText().toString(),
                        nation.getText().toString(), rating.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mydb.insertMoive(name.getText().toString(), director.getText().toString(),
                        year.getText().toString(), nation.getText().toString(), rating.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "추가되었음", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "추가되지 않았음", Toast.LENGTH_SHORT).show();

                }
                finish();
            }
        }
    }


    public void delete(View view) {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int Value = bundle.getInt("id");
            if (Value > 0) {
                mydb.deleteMoive(id);
                Toast.makeText(getApplicationContext(), "삭제되었음", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "삭제되지 않았음", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void edit(View view) {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int Value = bundle.getInt("id");
            if (Value > 0) {
                if (mydb.updateMovie(id, name.getText().toString(), director.getText().toString(), year.getText().toString(),
                        nation.getText().toString(), rating.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
}
