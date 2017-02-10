package com.bink.mybink.view.dashboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bink.mybink.R;
import com.bink.mybink.model.FriendsList;
import com.bink.mybink.utils.ContactAdapter;
import com.gvillani.pinnedlist.GroupListWrapper;
import com.gvillani.pinnedlist.PinnedListLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    @BindView(R.id.fragment_matches_pinned_layout)
    PinnedListLayout mListLayout;

    private RecyclerView.Adapter mListAdapter;
    private RecyclerView mRecyclerView;

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this,rootView);
        initLayout();
        return rootView;
    }

    private void initLayout() {

        mRecyclerView = mListLayout.getRecyclerView();

        FriendsList c1 = new FriendsList("David", "Gower", R.drawable.contact1);
        FriendsList c2 = new FriendsList("Paul", "Betty", R.drawable.contact9);
        FriendsList c11 = new FriendsList("Oliver", "Ernster", R.drawable.contact5);
        FriendsList c3 = new FriendsList("Barbara", "Vas", R.drawable.contact7);
        FriendsList c4 = new FriendsList("Mick", "Latham", R.drawable.contact4);
        FriendsList c5 = new FriendsList("Rachel", "Walsh", R.drawable.contact11);
        FriendsList c6 = new FriendsList("Nick", "Steer", R.drawable.contact6);
        FriendsList c7 = new FriendsList("Rob", "Terry", R.drawable.contact5);
        FriendsList c8 = new FriendsList("Jack", "Rostron", R.drawable.contact11);
        FriendsList c9 = new FriendsList("Christina", "Anderson", R.drawable.contact6);
        FriendsList c10 = new FriendsList("Jack", "McDonnel", R.drawable.contact5);

        List<GroupListWrapper.Selector> contacts = new ArrayList<>();

        contacts.add(c1);
        contacts.add(c2);
        contacts.add(c3);
        contacts.add(c4);
        contacts.add(c5);
        contacts.add(c6);
        contacts.add(c7);
        contacts.add(c8);
        contacts.add(c9);
        contacts.add(c10);
        contacts.add(c11);

        GroupListWrapper listGroup = GroupListWrapper.createAlphabeticList(contacts, GroupListWrapper.ASCENDING);
        mListAdapter =  new ContactAdapter(getActivity().getApplicationContext(), listGroup, mListLayout);
        mRecyclerView.setAdapter(mListAdapter);

    }

}
