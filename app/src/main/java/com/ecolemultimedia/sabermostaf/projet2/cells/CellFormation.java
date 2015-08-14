package com.ecolemultimedia.sabermostaf.projet2.cells;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.ecolemultimedia.sabermostaf.projet2.R;
import com.ecolemultimedia.sabermostaf.projet2.components.AppController;
import com.ecolemultimedia.sabermostaf.projet2.models.Formation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CellFormation extends RelativeLayout {

    private TextView ui_tx_title_formation = null;
    private TextView ui_tx_sub_title_formation = null;
    private ImageView ui_img_sub_content_formation=null;
    private TextView    ui_tx_formation_time=null;
    private TextView   ui_tx_formation_price=null;

    private TextView ui_tx_formation_date=null;
    private LayoutInflater layoutInflater = null;

    private TextView  ui_tx_formation_note=null;
    private Context m_context = null;

    public CellFormation(Context context) {
        super(context);
        m_context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public View init() {

        View convertView = layoutInflater.inflate(R.layout.cell_formation,
                this);
        ui_tx_formation_date= (TextView) convertView
                .findViewById(R.id.ui_tx_formation_date);

        ui_tx_title_formation = (TextView) convertView
                .findViewById(R.id.ui_tx_title_formation);
        ui_tx_formation_note = (TextView) convertView
                .findViewById(R.id.ui_tx_formation_note);
        ui_tx_sub_title_formation = (TextView) convertView
                .findViewById(R.id.ui_tx_sub_title_formation);
        ui_tx_formation_time= (TextView) convertView
                .findViewById(R.id.ui_tx_formation_time);
        ui_tx_formation_price= (TextView) convertView
                .findViewById(R.id.ui_tx_formation_price);
        ui_tx_sub_title_formation= (TextView) convertView
                .findViewById(R.id.ui_tx_sub_title_formation);
        ui_img_sub_content_formation=(ImageView)convertView.findViewById(R.id.ui_img_sub_content_formation);


        Typeface style = Typeface.createFromAsset(m_context.getAssets(),
                "corbel.otf");
        ui_tx_sub_title_formation.setTypeface(style);
        ui_tx_title_formation.setTypeface(style);

        return this;

    }

    public void reuse(Formation info) {

        cleanView();

        if (info.getTitle() != null) {
            ui_tx_title_formation.setText(info.getTitle());
        }
        if(info.getSubtitle()!=null){
            ui_tx_sub_title_formation.setText(info.getSubtitle());
        }

        if(info.getImage()!=null){

            ImageLoader imageLoader = AppController.getInstance()
                    .getImageLoader();

            imageLoader.get(info.getImage(), new ImageLoader.ImageListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("debeug",
                            "Image Load Error: " + error.getMessage());

                }

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                    if (response.getBitmap() != null) {

                        ui_img_sub_content_formation.setImageBitmap(response.getBitmap());
                    }
                }
            });
        }

        if(info.getDuration()!=null){

            int value= Integer.parseInt(info.getDuration());

            ui_tx_formation_time.setText(getDurationString(value));
        }


        if(info.getPrice()!=null){


            Double val=Double.parseDouble(info.getPrice());

            if(info.getPrice()==null) {
                ui_tx_formation_price.setText("Gratuit");
            }else{

                ui_tx_formation_price.setText("" + Math.round(val)+" €");
            }
        }

        if(info.getRating()!=null){


            Log.v("Debeug","Rating :"+info.getRating());



            ui_tx_formation_note.setText(""+info.getRating()+" / 100");
        }

        if(info.getPublishedDate()!=null){

            Date MyDate;
            try {
                SimpleDateFormat newDateFormat = new SimpleDateFormat(
                        "yyyy-MM-dd");
                MyDate = newDateFormat.parse(info.getPublishedDate().substring(0, 10));

                String myDateTwo = newDateFormat.format(MyDate);
                ui_tx_formation_date.setText(""+myDateTwo);

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }


    }

    public void cleanView() {
        ui_tx_title_formation.setText("");
        ui_tx_sub_title_formation.setText("");
        ui_img_sub_content_formation.setImageBitmap(null);
        ui_tx_formation_price.setText("");
        ui_tx_formation_time.setText("");
        ui_tx_formation_date.setText("");
        ui_tx_formation_note.setText("");
    }

    private String getDurationString(int seconds) {

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return twoDigitString(hours) + " : " + twoDigitString(minutes) + " : " + twoDigitString(seconds);
    }
    private String twoDigitString(int number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }

}