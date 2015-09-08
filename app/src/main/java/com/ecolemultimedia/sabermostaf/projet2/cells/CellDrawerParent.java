package com.ecolemultimedia.sabermostaf.projet2.cells;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecolemultimedia.sabermostaf.projet2.R;


public class CellDrawerParent extends RelativeLayout {

	private TextView ui_tx_title_cat = null;

	private ImageView ui_img_logo_group = null;
	private LayoutInflater layoutInflater = null;
	private Context m_context = null;

	public CellDrawerParent(Context context) {
		super(context);
		m_context = context;
		layoutInflater = LayoutInflater.from(context);
	}

	public View init() {

		View convertView = layoutInflater.inflate(R.layout.cell_drawer_parent,
				this);

		ui_tx_title_cat = (TextView) convertView
				.findViewById(R.id.ui_tx_title_cat);

		Typeface style = Typeface.createFromAsset(m_context.getAssets(),
				"Gotham/Gotham-Bold.otf");

		ui_tx_title_cat.setTypeface(style);

		ui_img_logo_group = (ImageView) convertView
				.findViewById(R.id.ui_img_logo_group);

		return this;

	}

	public void reuse(String info) {

		cleanView();
		if (info != null) {

			ui_tx_title_cat.setText(info);

			if (info.equals("Vidéo-Compositing")) {
				ui_img_logo_group.setImageResource(R.drawable.video);
			} else if (info.equals("Code")) {

				ui_img_logo_group.setImageResource(R.drawable.code);
			} else if (info.equals("Audio-MAO")) {

				ui_img_logo_group.setImageResource(R.drawable.audio);
			} else if (info.equals("Infographie")) {

				ui_img_logo_group.setImageResource(R.drawable.infographie);
			} else if (info.equals("Web-Multimédia")) {

				ui_img_logo_group.setImageResource(R.drawable.web_multimedia);
			} else if (info.equals("Informatique")) {

				ui_img_logo_group.setImageResource(R.drawable.informatique);
			} else if (info.equals("Photographie")) {

				ui_img_logo_group.setImageResource(R.drawable.photographie);
			} else if (info.equals("Business")) {

				ui_img_logo_group.setImageResource(R.drawable.business);
			} else if (info.equals("3D")) {

				ui_img_logo_group.setImageResource(R.drawable.troisd);
			} else if (info.equals("Bureautique")) {

				ui_img_logo_group.setImageResource(R.drawable.bureautique);
			}




		}
	}

	public void cleanView() {

		ui_tx_title_cat.setText("");
		ui_img_logo_group.setImageResource(0);
	}

}