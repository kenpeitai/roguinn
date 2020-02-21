package com.example.roguinn;


import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyFragment extends Fragment {



    private MediaPlayer click;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView textView;
    private LinearLayoutManager layoutManager;
    private List<MyItem> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment, container, false);

        textView = view.findViewById(R.id.text_die);
        textView.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"Cn5.ttf"));

        initData();
        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new MyAdapter(list);
        layoutManager = new GridLayoutManager(this.getActivity(), 5);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL));


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        click = new MediaPlayer();
        click = MediaPlayer.create(this.getActivity(),R.raw.btn_click);
        ImageButton imageButton = getActivity().findViewById(R.id.btn_image);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                getActivity().findViewById(R.id.btn_sufan).setVisibility(View.VISIBLE);
                click.start();
                Fragment fragment = getFragmentManager().findFragmentByTag("TAG");
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 112; i++) {

            list.add(new MyItem(R.drawable.enemy1 + i));
        }

//        list.add(new MyItem(R.drawable.enemy1));
//        list.add(new MyItem(R.drawable.enemy2));
//        list.add(new MyItem(R.drawable.enemy3));
//        list.add(new MyItem(R.drawable.enemy4));
//        list.add(new MyItem(R.drawable.enemy5));
//        list.add(new MyItem(R.drawable.enemy6));
//        list.add(new MyItem(R.drawable.enemy7));
//        list.add(new MyItem(R.drawable.enemy8));


    }
}
