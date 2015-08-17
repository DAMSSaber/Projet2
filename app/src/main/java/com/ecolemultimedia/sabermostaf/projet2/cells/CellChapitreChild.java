package com.ecolemultimedia.sabermostaf.projet2.cells;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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
import com.ecolemultimedia.sabermostaf.projet2.models.Item;


public class CellChapitreChild extends RelativeLayout {

    private TextView ui_tx_sub_title_video = null;
    private ImageView ui_img_sub_content_videon = null;
    private RelativeLayout ui_rl_sub_content_video = null;
    private LayoutInflater layoutInflater = null;
    private Context m_context = null;

    public CellChapitreChild(Context context) {
        super(context);
        m_context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public View init() {

        View convertView = layoutInflater.inflate(R.layout.cell_chapitre_parent,
                this);
        ui_rl_sub_content_video = (RelativeLayout) convertView.findViewById(R.id.ui_rl_sub_content_video);
        ui_tx_sub_title_video = (TextView) convertView
                .findViewById(R.id.ui_tx_title_chapitre);
        ui_img_sub_content_videon = (ImageView) convertView.findViewById(R.id.ui_img_sub_content_videon);
        Typeface style = Typeface.createFromAsset(m_context.getAssets(),
                "Gotham/Gotham-Bold.otf");

        ui_tx_sub_title_video.setTypeface(style);


        return this;

    }

    public void reuse(final Item info) {

        cleanView();
        if (info != null) {

            ui_tx_sub_title_video.setText(info.getTitle());

        }

        if (info.getField_poster() != null) {

            ImageLoader imageLoader = AppController.getInstance()
                    .getImageLoader();

            imageLoader.get(info.getField_poster(), new ImageLoader.ImageListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("debeug",
                            "Image Load Error: " + error.getMessage());
                }

                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                    if (response.getBitmap() != null) {

                        ui_img_sub_content_videon.setImageBitmap(response.getBitmap());
                    }
                }
            });
        }

        ui_rl_sub_content_video.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                Uri data = Uri.parse(info.getField_video());
                intent.setDataAndType(data, "video/mp4");
                m_context.startActivity(intent);
            }
        });
    }

    public void cleanView() {

        ui_tx_sub_title_video.setText("");

    }
}