package com.example.appdatphongkhachsan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.appdatphongkhachsan.R;
import com.example.appdatphongkhachsan.model.slidesave;

import java.util.List;


public class slideSaveAdapter extends PagerAdapter {

    private Context mcontext;
    private List<slidesave> mslidesave;

    public slideSaveAdapter(Context mcontext, List<slidesave> mslidesave) {
        this.mcontext = mcontext;
        this.mslidesave = mslidesave;
    }

    public slideSaveAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slide_save, container, false);
        ImageView imageSlide = view.findViewById(R.id.imgslide);

        slidesave slide = mslidesave.get(position);
        if(slide != null){
            Glide.with(mcontext).load(slide.getResourceId()).into(imageSlide);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(mslidesave != null){
            return mslidesave.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        super.destroyItem(container, position, object);
    }
}
