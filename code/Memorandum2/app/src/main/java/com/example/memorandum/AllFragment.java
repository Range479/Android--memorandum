package com.example.memorandum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AllFragment extends Fragment {


    public AllFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView notesRecycle = (RecyclerView) inflater.inflate(R.layout.fragment_all, container, false);
        String[] notesName = new String[Note.notes.size()];
        for (int i = 0; i < notesName.length; i++)
            notesName[i] = Note.notes.get(i).getTitle();
        cardJ adapter = new cardJ(notesName);
        notesRecycle.setAdapter(adapter);
        //GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        notesRecycle.setLayoutManager(layoutManager);
        adapter.setListener(new cardJ.Listener() {
            @Override
            public void onClick(int postion) {
                Intent intent = new Intent(getActivity(), read.class);
                intent.putExtra(read.EXTRA_NOTE_ID, postion);
                intent.putExtra("type", 0);
                getActivity().startActivity(intent);
            }
        });
        return notesRecycle;
    }


}
