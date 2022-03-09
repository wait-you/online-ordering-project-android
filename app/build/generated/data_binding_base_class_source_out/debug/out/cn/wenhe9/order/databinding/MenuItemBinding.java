// Generated by view binder compiler. Do not edit!
package cn.wenhe9.order.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

public final class MenuItemBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnAddCar;

  @NonNull
  public final ImageView ivFoodPic;

  @NonNull
  public final TextView tvFoodName;

  @NonNull
  public final TextView tvPrice;

  @NonNull
  public final TextView tvSaleNum;

  @NonNull
  public final TextView tvTaste;

  private MenuItemBinding(@NonNull RelativeLayout rootView, @NonNull Button btnAddCar,
      @NonNull ImageView ivFoodPic, @NonNull TextView tvFoodName, @NonNull TextView tvPrice,
      @NonNull TextView tvSaleNum, @NonNull TextView tvTaste) {
    this.rootView = rootView;
    this.btnAddCar = btnAddCar;
    this.ivFoodPic = ivFoodPic;
    this.tvFoodName = tvFoodName;
    this.tvPrice = tvPrice;
    this.tvSaleNum = tvSaleNum;
    this.tvTaste = tvTaste;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MenuItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MenuItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.menu_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MenuItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_add_car;
      Button btnAddCar = ViewBindings.findChildViewById(rootView, id);
      if (btnAddCar == null) {
        break missingId;
      }

      id = R.id.iv_food_pic;
      ImageView ivFoodPic = ViewBindings.findChildViewById(rootView, id);
      if (ivFoodPic == null) {
        break missingId;
      }

      id = R.id.tv_food_name;
      TextView tvFoodName = ViewBindings.findChildViewById(rootView, id);
      if (tvFoodName == null) {
        break missingId;
      }

      id = R.id.tv_price;
      TextView tvPrice = ViewBindings.findChildViewById(rootView, id);
      if (tvPrice == null) {
        break missingId;
      }

      id = R.id.tv_sale_num;
      TextView tvSaleNum = ViewBindings.findChildViewById(rootView, id);
      if (tvSaleNum == null) {
        break missingId;
      }

      id = R.id.tv_taste;
      TextView tvTaste = ViewBindings.findChildViewById(rootView, id);
      if (tvTaste == null) {
        break missingId;
      }

      return new MenuItemBinding((RelativeLayout) rootView, btnAddCar, ivFoodPic, tvFoodName,
          tvPrice, tvSaleNum, tvTaste);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
