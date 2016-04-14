package com.demo.swiperefresh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.R;
import com.demo.recyclerview.VHItem;

import java.util.ArrayList;


/**
 * Created by user on 2016/3/22.
 */
public class AppointsTodayListActivity extends ViewPagerListActivity {
    //    private ShopPresenternter shopPresenter;
    private int shopid;
    private int d50;

//    @Override
//    protected void findViews() {
//        d50 = DisplayUtil.dip2px(getContext(), 50);
//        super.findViews();
//        setTopBarTitle(R.string.today_appoint);
//    }

    @Override
    public void onRefresh(int index) {
        if (index == 0) {
//            shopPresenter.getBeauticianAppListByShop(shopid, true, Constants.ITEM_SIZE, pageArray[index]);
        } else {
        }
    }

    @Override
    public void onLoadMore(int index) {
//        if (index == 0) {
//            shopPresenter.getBeauticianAppListByShop(shopid, true, Constants.ITEM_SIZE, pageArray[index]);
//        } else {
//        }
    }

//    @Override
//    protected void initDatas() {
//        shopPresenter = new ShopPresenter(this);
//        shopid = getIntent().getIntExtra(Extra.ID, 0);
//        super.initDatas();
//    }

    @Override
    protected ArrayList<String> getPageTitle() {
        ArrayList<String> data = new ArrayList<String>();
        data.add("beautician_appoiment_today");
        data.add("shop_appoiment_today");
        return data;
    }

    @Override
    protected MnjBaseRecycleAdapter[] getRecyclerAdapters() {

        MnjBaseRecycleAdapter[] adapters = new MnjBaseRecycleAdapter[2];
        MnjBaseRecycleAdapter adapter1 = new MnjBaseRecycleAdapter() {
            @Override
            public VHItem onCreateItemViewHolder(ViewGroup parent, int viewType) {
                System.err.println("----1---");
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beautician_item_appoiment, null);
                return new VHItem(view);
            }

            @Override
            public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
                TextView tv = (TextView) holder.itemView.findViewById(R.id.beautician_item_appointment_roundBtn);
                tv.setText(position + "");
            }
        };
        MnjBaseRecycleAdapter adapter2 = new MnjBaseRecycleAdapter() {
            @Override
            public VHItem onCreateItemViewHolder(ViewGroup parent, int viewType) {
                System.err.println("----2---");
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beautician_item_appoiment, null);
                return new VHItem(view);
            }

            @Override
            public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        };
        adapters[0] = adapter1;
        adapters[1] = adapter2;
        for (int i = 0; i < 40; i++) {
            adapter1.add(i + "");
            adapter2.add(i + "");
        }
        return adapters;
    }

//    @Override
//    protected boolean autoFresh() {
//        return true;
//    }
}
