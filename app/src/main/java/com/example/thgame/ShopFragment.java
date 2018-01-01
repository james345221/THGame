package com.example.thgame;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static java.security.AccessController.getContext;

/**
 * Created by asus-pc on 2017/10/12.
 */

class ShopFragment extends Fragment implements View.OnClickListener {

    private TextView bt_cart_all, bt_cart_low, bt_cart_stock;

    private View show_cart_all, show_cart_low, show_cart_stock;

    private AllGoodsFragment allGoodsFragment;
    private FinishGoodsFragment finishGoodsFragment;
    private NotYetFragment notYetFragment;


    private boolean isDel=true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_shop, null);
        initView(view);
        return view;

    }

    private void initView(View view) {
        bt_cart_all = (TextView) view.findViewById(R.id.bt_cart_all);
        bt_cart_low = (TextView) view.findViewById(R.id.bt_cart_low);
        bt_cart_stock = (TextView) view.findViewById(R.id.bt_cart_stock);

        show_cart_all = view.findViewById(R.id.show_cart_all);
        show_cart_low = view.findViewById(R.id.show_cart_low);
        show_cart_stock = view.findViewById(R.id.show_cart_stock);

        bt_cart_all.setOnClickListener(this);
        bt_cart_low.setOnClickListener(this);
        bt_cart_stock.setOnClickListener(this);
        allGoodsFragment = new AllGoodsFragment();
        addFragment(allGoodsFragment);
        showFragment(allGoodsFragment);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Toast.makeText(getContext(), "shop", Toast.LENGTH_SHORT).show();
    }


    /** 添加Fragment **/
    public void addFragment(Fragment fragment) {
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.add(R.id.show_cart_view, fragment);
        ft.commitAllowingStateLoss();
    }
    /** 删除Fragment **/
    public void removeFragment(Fragment fragment) {
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commitAllowingStateLoss();
    }

    /** 显示Fragment **/
    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        if (allGoodsFragment != null) {
            ft.hide(allGoodsFragment);
        }
        if (finishGoodsFragment != null) {
            ft.hide(finishGoodsFragment);
        }
        if (notYetFragment != null) {
            ft.hide(notYetFragment);
        }

        ft.show(fragment);
        ft.commitAllowingStateLoss();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_cart_all:
                if (allGoodsFragment == null) {
                    allGoodsFragment = new AllGoodsFragment();
                    addFragment(allGoodsFragment);
                    showFragment(allGoodsFragment);
                } else {
                    showFragment(allGoodsFragment);
                }
                show_cart_all.setBackgroundColor(getResources().getColor(R.color.black));
                show_cart_low.setBackgroundColor(getResources().getColor(R.color.white));
                show_cart_stock.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.bt_cart_low:
                if (finishGoodsFragment == null) {
                    finishGoodsFragment = new FinishGoodsFragment();
                    addFragment(finishGoodsFragment);
                    showFragment(finishGoodsFragment);
                } else {
                    showFragment(finishGoodsFragment);
                }
                show_cart_low.setBackgroundColor(getResources().getColor(R.color.black));
                show_cart_all.setBackgroundColor(getResources().getColor(R.color.white));
                show_cart_stock.setBackgroundColor(getResources().getColor(R.color.white));

                break;
            case R.id.bt_cart_stock:
                if (notYetFragment == null) {
                    notYetFragment = new NotYetFragment();
                    addFragment(notYetFragment);
                    showFragment(notYetFragment);
                } else {
                    showFragment(notYetFragment);
                }
                show_cart_stock.setBackgroundColor(getResources().getColor(R.color.black));
                show_cart_all.setBackgroundColor(getResources().getColor(R.color.white));
                show_cart_low.setBackgroundColor(getResources().getColor(R.color.white));

                break;

            default:
                break;
        }
    }
}