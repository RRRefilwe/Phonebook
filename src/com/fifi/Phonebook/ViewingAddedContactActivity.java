package com.fifi.Phonebook;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Fifi on 2014-08-23.
 */
public class ViewingAddedContactActivity extends Activity {

    @Override
    protected void  onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.viewingadded);
        TextView tv = (TextView)findViewById(R.id.tvSqlInfo);
        final DatasourceDAO dao = new DatabasesourceDAOImpl(this);


        String data = dao.getData();
         tv.setText(data);

    }
}
