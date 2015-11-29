package projekt.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.siadrenalin.R;

import java.util.List;

import projekt.model.NavItem;

/**
 * Created by Mitja on 12.5.2015.
 */
public class NavAdapter extends ArrayAdapter<NavItem> {

    Context context;

    public NavAdapter(Context context, int resourceId,
                                 List<NavItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        NavItem rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.nav_item, null);
            holder = new ViewHolder();

            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();


        holder.imageView.setImageResource(rowItem.getImageId());

        return convertView;
    }
}
