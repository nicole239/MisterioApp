package com.nicole.misterioapp;

import static com.nicole.misterioapp.utils.Animations.collapse;
import static com.nicole.misterioapp.utils.Animations.expand;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicole.misterioapp.data.Constants;
import com.nicole.misterioapp.data.DataNotebookGUI;
import com.nicole.misterioapp.model.Notebook;
import com.nicole.misterioapp.utils.NotebookAdapter;
import com.nicole.misterioapp.data.DefaultData;
import com.nicole.misterioapp.data.IData;
import com.nicole.misterioapp.model.CardType;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotebookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotebookFragment extends Fragment {

    private RecyclerView[] notebookView;

    private int shownNotebookPageNumber = -1;

    private Notebook notebook;

    private final int notebookItemSize = 70;

    public NotebookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NotebookFragment.
     */
    public static NotebookFragment newInstance(Notebook notebook) {
        NotebookFragment notebookFragment = new NotebookFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.NOTEBOOK_DATA, notebook);
        notebookFragment.setArguments(args);
        return notebookFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.notebook = args.getParcelable(Constants.NOTEBOOK_DATA);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notebook, container, false);
        setupNotebook(rootView);
        // Inflate the layout for this fragment
        return rootView;
    }

    private void setupNotebook(View rootView){
        notebookView = new RecyclerView[3];
        setupNotebookPage(rootView,0, CardType.SUSPECT, R.id.lstNotebookSuspects, R.id.btnShowNotebookSuspects);
        setupNotebookPage(rootView,1, CardType.ROOM, R.id.lstNotebookRooms, R.id.btnShowNotebookRooms);
        setupNotebookPage(rootView,2, CardType.WEAPON, R.id.lstNotebookWeapons, R.id.btnShowNotebookWeapons);
    }

    private void setupNotebookPage(View rootView, int position, CardType cardType, int viewId, int btnShowId){
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(viewId);
        IData data = DefaultData.getInstance();
        NotebookAdapter adapter = new NotebookAdapter(new DataNotebookGUI(cardType, data, notebook, rootView.getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        notebookView[position] = recyclerView;

        TextView btnShowNotebook = rootView.findViewById(btnShowId);
        btnShowNotebook.setOnClickListener(view -> changeNotebookPage(position));
    }

    private void changeNotebookPage(int goToPage){
        if(shownNotebookPageNumber >= 0) {
            //Hide current notebook page
            collapse(notebookView[shownNotebookPageNumber]);
        }
        if(shownNotebookPageNumber == goToPage){
            shownNotebookPageNumber = -1;
        }else{
            //Show clicked notebook page
            shownNotebookPageNumber = goToPage;
            int pageHeightDps = notebookItemSize * notebook.numberOfItemsInPage(CardType.values()[shownNotebookPageNumber]);
            expand(notebookView[shownNotebookPageNumber], dpsToPixels(pageHeightDps));
            notebookView[shownNotebookPageNumber].setVisibility(View.VISIBLE);
        }
    }

    private int dpsToPixels(int dps){
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }
}