package phongson.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import phongson.com.R;
import phongson.com.model.LichSuDoc;
import phongson.com.model.TinTuc;

public class LichSuDocAdapter extends ArrayAdapter<LichSuDoc> {
    Activity context;
    int resource;
    List<LichSuDoc> objects;

    public LichSuDocAdapter(@NonNull Activity context, int resource, @NonNull List<LichSuDoc> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.item_lichsudoc,null);
        TextView textView = view.findViewById(R.id.txtTieuDe);

        LichSuDoc lichSuDoc = objects.get(position);
        textView.setText(lichSuDoc.getTieude());

        return view;
    }
}
