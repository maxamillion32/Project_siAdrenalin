package projekt.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import projekt.data.TipSporta;
import com.siadrenalin.R;

public class AktivnostiArrayAdapter extends ArrayAdapter<TipSporta>{
	private final Activity context;
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
    List<TipSporta> objects;

	
	static class ViewHolder {
		public TextView tipSpo_naziv;
        public View vLine;
	}
	
	public AktivnostiArrayAdapter(Activity context, List<TipSporta> objects) {
		super(context, R.layout.activity_seznam, objects);
		this.objects= objects; 
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.activity_seznam, null);
			ViewHolder viewHolder = new ViewHolder();

			viewHolder.tipSpo_naziv = (TextView) rowView.findViewById(R.id.text_NazivTipa);

            viewHolder.vLine = (View) rowView.findViewById(R.id.view_line);
			
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();

		TipSporta tmp = objects.get(position);
		holder.tipSpo_naziv.setText(tmp.getNaziv());
		holder.tipSpo_naziv.setFocusable(false);
		holder.tipSpo_naziv.setFocusableInTouchMode(false);

        Random rand = new Random();
        holder.vLine.setBackgroundColor(barve[rand.nextInt(13-0+1)+0]);

		return rowView;
	}


}
