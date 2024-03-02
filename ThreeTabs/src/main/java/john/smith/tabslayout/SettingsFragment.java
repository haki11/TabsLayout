package john.smith.tabslayout;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private TextView dataTextView;
    private CheckBox hockeyCheckBox, basketballCheckBox, baseballCheckBox;
    private Button sportButton;
    String result = "";

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported.
                result = bundle.getString("bundleKey");

                if (dataTextView != null) {
                    dataTextView.setTypeface(null, Typeface.BOLD);
                    dataTextView.setTextColor(getResources().getColor(R.color.black, null));
                    if (result.toUpperCase().contains("YELLOW")) {
                        dataTextView.setText(result);
                        dataTextView.setTextColor(getResources().getColor(R.color.yellow, null));
                    }
                    if (result.toUpperCase().contains("GREEN")) {
                        dataTextView.setText(result);
                        dataTextView.setTextColor(getResources().getColor(R.color.green, null));
                    }
                    if (result.toUpperCase().contains("RED")) {
                        dataTextView.setText(result);
                        dataTextView.setTextColor(getResources().getColor(R.color.red, null));
                    }

                }
                // Do something with the result.
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        dataTextView = view.findViewById(R.id.dataTextView);
        hockeyCheckBox = view.findViewById(R.id.hockeyCheckBox);
        basketballCheckBox = view.findViewById(R.id.basketballCheckBox);
        baseballCheckBox = view.findViewById(R.id.baseballCheckBox);
        sportButton = view.findViewById(R.id.sportButton);


        // Set up the button click listener
        sportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the button click, for example, check which checkboxes are selected
                List<String> selectedSports = new ArrayList<>();

                if (hockeyCheckBox.isChecked()) {
                    selectedSports.add("Hockey");
                }
                if (basketballCheckBox.isChecked()) {
                    selectedSports.add("Basketball");
                }
                if (baseballCheckBox.isChecked()) {
                    selectedSports.add("Baseball");
                }

                if (selectedSports.size() == 0) selectedSports.add("NO SPORT");
                // Display an AlertDialog with the selected sports
                showAlertDialog(selectedSports);
            }
        });

        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);

// Get the array from strings.xml
        String[] emailsArray = getResources().getStringArray(R.array.emails);

// Create an ArrayAdapter with the array and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, emailsArray);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(2);

        return view;
    }

    private void showAlertDialog(List<String> selectedSports) {
        // Create a CharSequence array from the list of selected sports
        CharSequence[] items = selectedSports.toArray(new CharSequence[selectedSports.size()]);

        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Selected Sports")
                .setIcon(R.drawable.sport)
                .setItems(items, null)
                .setPositiveButton("OK", null);

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}