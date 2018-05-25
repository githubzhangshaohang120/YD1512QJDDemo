package com.example.asus.yd1512qjddemo.ui.mine.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.asus.yd1512qjddemo.R;
import com.example.asus.yd1512qjddemo.bean.BaseBean;
import com.example.asus.yd1512qjddemo.bean.OrdersBean;
import com.example.asus.yd1512qjddemo.component.DaggerHttpComponent;
import com.example.asus.yd1512qjddemo.ui.base.BaseFragment;
import com.example.asus.yd1512qjddemo.ui.mine.adapter.OrderAdapter;
import com.example.asus.yd1512qjddemo.ui.mine.contract.OrdersContract;
import com.example.asus.yd1512qjddemo.ui.mine.presenter.OrdersPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import static java.security.AccessController.getContext;

public class FragmentAllOrder extends BaseFragment<OrdersPresenter> implements OrdersContract.View {
        private RecyclerView recyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    //分页
    private int page = 1;
    //大集合
//    private List<OrderListBean.DataBean> listAll = new ArrayList<>();
//    private OrderListAdapter orderListAdapter;
    private RelativeLayout relative_empty_order;
    private OrderAdapter adapter;
    private boolean isRefresh = true;//刷新状态

    @Override
    public int getContentLayout() {
        return R.layout.fragment_order_all_layout;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_order);
        smartRefreshLayout = view.findViewById(R.id.smart_refresh);
        relative_empty_order = view.findViewById(R.id.relative_empty_order);

        //初始化Recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        //设置适配器
        adapter = new OrderAdapter(getContext(), mPresenter);
        recyclerView.setAdapter(adapter);

        //请求接口获取订单
        mPresenter.getOrders(getUid(), page + "", getToken());

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                isRefresh = true;
                mPresenter.getOrders(getUid(), page + "", getToken());
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                isRefresh = false;
                mPresenter.getOrders(getUid(), page + "", getToken());
            }
        });
    }

    @Override
    public void showOrders(List<OrdersBean.DataBean> list) {
        if (isRefresh) {
            if (adapter != null) {
                adapter.refresh(list);
                smartRefreshLayout.finishRefresh();
            }
        } else {
            if (adapter != null) {
                adapter.loadMore(list);
                smartRefreshLayout.finishLoadmore();
            }
            //判断当前列表的数据，是否大于等于总条目
            //   smartRefreshLayout.setLoadmoreFinished(true);
        }

    }

    @Override
    public void updateOrderSuccess(BaseBean baseBean) {
        if (baseBean.getCode().equals("0")) {
            //修改订单成功
            //再次去获取订单列表
            page = 1;
            isRefresh = true;
            mPresenter.getOrders(getUid(), page + "", getToken());
        }else{
            Toast.makeText(getContext(),"更新订单失败，请稍后再试",Toast.LENGTH_SHORT).show();
        }
    }
}
