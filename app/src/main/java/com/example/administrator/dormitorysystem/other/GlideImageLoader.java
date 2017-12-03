package com.example.administrator.dormitorysystem.other;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.dormitorysystem.MyApplication;
import com.example.administrator.dormitorysystem.R;
import com.imnjh.imagepicker.ImageLoader;

/**
 * Created by Martin on 2017/1/18.
 */

public class GlideImageLoader implements ImageLoader {
  @Override
  public void bindImage(ImageView imageView, Uri uri, int width, int height) {
    Glide.with(MyApplication.getContext()).load(uri).placeholder(R.color.gray)
        .error(R.color.gray).override(width, height).dontAnimate().into(imageView);
  }

  @Override
  public void bindImage(ImageView imageView, Uri uri) {
    Glide.with(MyApplication.getContext()).load(uri).placeholder(R.color.gray)
        .error(R.color.gray).dontAnimate().into(imageView);
  }

  @Override
  public ImageView createImageView(Context context) {
    ImageView imageView = new ImageView(context);
    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    return imageView;
  }

  @Override
  public ImageView createFakeImageView(Context context) {
    return new ImageView(context);
  }
}
