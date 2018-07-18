package phongson.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import phongson.com.R;
import phongson.com.model.BinhLuan;

public class BinhLuanAdapter extends ArrayAdapter<BinhLuan> {
    Activity context;
    int resource;
    List<BinhLuan> objects;
    public BinhLuanAdapter(@NonNull Activity context, int resource, @NonNull List<BinhLuan> objects) {
        super(context, resource, objects);
        this.context =context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.item_binhluan,null);

        BinhLuan binhLuan = objects.get(position);

        ImageView imvNguoiBinhLuan = view.findViewById(R.id.imvNguoiBinhLuan);
        TextView txtNguoiBinhLuan = view.findViewById(R.id.txtTenNguoiBinhLuan);
        TextView txtNoiDungBinhLuan = view.findViewById(R.id.txtNoiDungBinhLuan);

        if(binhLuan.getIdUer()!="") {
            String profilePicUrl = "https://graph.facebook.com/" + binhLuan.getIdUer() + "/picture?type=large";
            Picasso.get().load(profilePicUrl).into(imvNguoiBinhLuan);
        }
        txtNguoiBinhLuan.setText("Nguoi Binh Luan");
        txtNoiDungBinhLuan.setText(binhLuan.getNoidung());
        return view;
    }
}
