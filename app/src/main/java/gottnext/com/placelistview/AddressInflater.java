package gottnext.com.placelistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AddressInflater extends BaseAdapter {

    private Activity _activity;
    private ArrayList<Address> _addresses;
    private static LayoutInflater _inflater;

    public AddressInflater(Activity _activity, ArrayList<Address> _addresses) {
        this._activity = _activity;
        this._addresses = _addresses;
        _inflater = (LayoutInflater)this._activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { return this._addresses.size(); }

    @Override
    public Object getItem(int position) { return null; }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) view = _inflater.inflate(R.layout.item_list, null);
        TextView placeStreet = view.findViewById(R.id.placeStreet);
        TextView placeColony = view.findViewById(R.id.placeColony);
        TextView placeLat = view.findViewById(R.id.placeLat);
        TextView placeLot = view.findViewById(R.id.placeLot);

        Address a = this._addresses.get(position);
        placeStreet.setText(a.get_street());
        placeColony.setText(a.get_colony());
        placeLat.setText(a.get_latitude());
        placeLot.setText(a.get_logitude());
        return view;
    }


}
