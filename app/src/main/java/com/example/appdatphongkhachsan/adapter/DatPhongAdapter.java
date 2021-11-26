package com.example.appdatphongkhachsan.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdatphongkhachsan.R;
import com.example.appdatphongkhachsan.activity.chitietDatPhong;
import com.example.appdatphongkhachsan.model.khachsan;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DatPhongAdapter extends BaseAdapter {
    ArrayList<khachsan> arrayKhachSan;
    Context context;

    public DatPhongAdapter( Context context,ArrayList<khachsan> arrayKhachSan) {
        this.arrayKhachSan = arrayKhachSan;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayKhachSan.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayKhachSan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public TextView txtKhachSan,txtMota, txtGia,txtSdt,txtEmail,txtDiaChi;
        public ImageView imageViewDatPhong;
        public ImageButton imageButton;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.datphong_adapter, null);
            viewHolder.imageViewDatPhong = (ImageView) convertView.findViewById(R.id.imageViewDatPhong);
            viewHolder.txtGia = (TextView) convertView.findViewById(R.id.textViewGiaKhachSan);
            viewHolder.txtSdt = (TextView) convertView.findViewById(R.id.textViewSdt);
            viewHolder.txtEmail = (TextView) convertView.findViewById(R.id.textViewEmail);
            viewHolder.txtKhachSan = (TextView) convertView.findViewById(R.id.textViewKhachSan);
            viewHolder.txtDiaChi = (TextView) convertView.findViewById(R.id.textViewDiaChi);
            viewHolder.txtMota = (TextView) convertView.findViewById(R.id.textViewMoTa);
            viewHolder.imageButton = (ImageButton) convertView.findViewById(R.id.tym) ;
            convertView.setPadding(0,10,0,10);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        khachsan khachsan =(khachsan) getItem(position);
        viewHolder.txtGia.setText( khachsan.getGia()+".");
        viewHolder.txtMota.setMaxLines(3);
        viewHolder.txtMota.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMota.setText( khachsan.getMota());
        viewHolder.txtKhachSan.setText(khachsan.getTenks());
        viewHolder.txtSdt.setText(khachsan.getSdt());
        viewHolder.txtEmail.setText(khachsan.getEmail());
        viewHolder.txtDiaChi.setText(khachsan.getDiachi());

        Picasso.with(context).load(khachsan.getHinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imageViewDatPhong);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, chitietDatPhong.class);
                it.putExtra("thongtinkhachsan",arrayKhachSan.get(position));
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(it);
            }
        });
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.imageButton.setBackgroundColor(R.drawable.custom_tym);
            }
        });
        return convertView;
    }

}
