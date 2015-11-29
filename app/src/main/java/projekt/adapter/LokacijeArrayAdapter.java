package projekt.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.siadrenalin.R;

import java.io.File;
import java.util.List;
import java.util.Random;

import projekt.data.Sport;

public class LokacijeArrayAdapter extends ArrayAdapter<Sport>  {

	private final Activity context;
	List<Sport> objects;
	private int[] barve = { Color.rgb(255, 0, 0),
			Color.rgb(255,128,0),
			Color.rgb(128,255,0),
			Color.rgb(0,255,0),
			Color.rgb(0,255,128),
			Color.rgb(0,255,255),
			Color.rgb(0,128,255),
			Color.rgb(0,0,255),
			Color.rgb(127,0,255),
			Color.rgb(255,0,255),
			Color.rgb(255,0,127),
			Color.rgb(128,128,128),
			Color.rgb(192,192,192),
			Color.rgb(255,102,102),};


	static class ViewHolder {
		public TextView naslov_sporta;
		public android.support.v7.widget.CardView layout_over;
        public TextView naziv_sporta;
		public ImageView logotip_sporta;
		public View vLine;
	}

	public LokacijeArrayAdapter(Activity context, List<Sport> objects) {
		//super(context, R.layout.activity_seznamlokacij, objects);
        super(context, R.layout.activity_seznamlokacij_card, objects);
		this.objects = objects;
		this.context = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
        if (rowView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.activity_seznamlokacij_card, null);
			ViewHolder viewHolder = new ViewHolder();

			viewHolder.layout_over = (android.support.v7.widget.CardView) rowView
					.findViewById(R.id.layout_overlay);

			viewHolder.logotip_sporta = (ImageView)rowView.findViewById(R.id.iv_logotip);

            viewHolder.naslov_sporta = (TextView)rowView.findViewById(R.id.naslov_sporta);

            viewHolder.naziv_sporta = (TextView)rowView.findViewById(R.id.naziv_sporta);

			viewHolder.vLine = rowView.findViewById(R.id.view_line);

			rowView.setTag(viewHolder);
		}

		final ViewHolder holder = (ViewHolder) rowView.getTag();
		final Sport sport = objects.get(position);

		File file = new File(sport.getLogotip());
		Uri uri = Uri.fromFile(file);
		holder.logotip_sporta.setImageURI(uri);

        holder.naziv_sporta.setText(sport.getNaziv());

        holder.naslov_sporta.setText(sport.getNaslov());

		Random rand = new Random();
		holder.vLine.setBackgroundColor(barve[rand.nextInt(13 - 0 + 1) + 0]);

		return rowView;
	}
}