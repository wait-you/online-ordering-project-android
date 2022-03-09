// Generated by view binder compiler. Do not edit!
package cn.wenhe9.order.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import cn.wenhe9.order.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ShopItemBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView ivShopPic;

  @NonNull
  public final LinearLayout llInfo;

  @NonNull
  public final TextView tvCost;

  @NonNull
  public final TextView tvSaleNum;

  @NonNull
  public final TextView tvShopName;

  @NonNull
  public final TextView tvTime;

  @NonNull
  public final TextView tvWelfare;

  private ShopItemBinding(@NonNull RelativeLayout rootView, @NonNull ImageView ivShopPic,
      @NonNull LinearLayout llInfo, @NonNull TextView tvCost, @NonNull TextView tvSaleNum,
      @NonNull TextView tvShopName, @NonNull TextView tvTime, @NonNull TextView tvWelfare) {
    this.rootView = rootView;
    this.ivShopPic = ivShopPic;
    this.llInfo = llInfo;
    this.tvCost = tvCost;
    this.tvSaleNum = tvSaleNum;
    this.tvShopName = tvShopName;
    this.tvTime = tvTime;
    this.tvWelfare = tvWelfare;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ShopItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ShopItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.shop_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ShopItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.iv_shop_pic;
      ImageView ivShopPic = ViewBindings.findChildViewById(rootView, id);
      if (ivShopPic == null) {
        break missingId;
      }

      id = R.id.ll_info;
      LinearLayout llInfo = ViewBindings.findChildViewById(rootView, id);
      if (llInfo == null) {
        break missingId;
      }

      id = R.id.tv_cost;
      TextView tvCost = ViewBindings.findChildViewById(rootView, id);
      if (tvCost == null) {
        break missingId;
      }

      id = R.id.tv_sale_num;
      TextView tvSaleNum = ViewBindings.findChildViewById(rootView, id);
      if (tvSaleNum == null) {
        break missingId;
      }

      id = R.id.tv_shop_name;
      TextView tvShopName = ViewBindings.findChildViewById(rootView, id);
      if (tvShopName == null) {
        break missingId;
      }

      id = R.id.tv_time;
      TextView tvTime = ViewBindings.findChildViewById(rootView, id);
      if (tvTime == null) {
        break missingId;
      }

      id = R.id.tv_welfare;
      TextView tvWelfare = ViewBindings.findChildViewById(rootView, id);
      if (tvWelfare == null) {
        break missingId;
      }

      return new ShopItemBinding((RelativeLayout) rootView, ivShopPic, llInfo, tvCost, tvSaleNum,
          tvShopName, tvTime, tvWelfare);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}