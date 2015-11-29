package projekt.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.siadrenalin.R;

import java.util.ArrayList;
import java.util.List;

import projekt.data.Sport;

public class PriljubljeneArrayAdapter extends ArrayAdapter<Sport>{

    private Context context;
    private final Activity acContext;
	List<Sport> objects;


    static class ViewHolder
	{
		private TextView naziv;
		private TextView tvNaslov;
	}
	
	public PriljubljeneArrayAdapter(Context context, ArrayList<Sport> objects, Activity acCon) {
		super(context, R.layout.activity_seznamlokacij_card, objects);
		this.objects= objects; 
		this.context = context;
		this.acContext = acCon;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {

            LayoutInflater inflater = acContext.getLayoutInflater();
            rowView = inflater.inflate(R.layout.activity_seznamlokacij_card, null);
			ViewHolder viewHolder = new ViewHolder();

			viewHolder.naziv = (TextView) rowView.findViewById(R.id.naziv_sporta);
			viewHolder.tvNaslov = (TextView) rowView.findViewById(R.id.naslov_sporta);

			rowView.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) rowView.getTag();

   	    Sport tmp = objects.get(position);

        holder.naziv.setText(tmp.getNaziv());
		holder.tvNaslov.setText(tmp.getNaslov());

		return rowView;
	}

}
