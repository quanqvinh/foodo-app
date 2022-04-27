package hcmute.edu.vn.foodoapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.foodoapp.R;
import hcmute.edu.vn.foodoapp.activity.LoginActivity;
import hcmute.edu.vn.foodoapp.activity.MainActivity;
import hcmute.edu.vn.foodoapp.model.User;
import hcmute.edu.vn.foodoapp.service.UserService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private EditText etPassword, etAddress, etPhone;
    private TextView tvUsername, tvUsernameProfile;
    private Button btnChangeProfile, btnLogout;

    private Boolean isChange = true;
    final User user = MainActivity.userService.getOne(MainActivity.userId);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvUsername = view.findViewById(R.id.tvUsername);
        tvUsername.setText(user.getUsername());
        tvUsernameProfile = view.findViewById(R.id.tvUsernameProfile);
        tvUsernameProfile.setText(user.getUsername());
        etPassword = view.findViewById(R.id.etPasswordProfile);
        etPassword.setText(user.getPassword());
        etAddress = view.findViewById(R.id.etAddressProfile);
        etAddress.setText(user.getAddress());
        etPhone = view.findViewById(R.id.etPhoneProfile);
        etPhone.setText(user.getPhone());

        btnChangeProfile = view.findViewById(R.id.btnChangeProfile);
        btnChangeProfile.setOnClickListener(this::changeProfileHandler);


        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this::logoutHandler);

        return view;
    }

    private void logoutHandler(View view) {
        Intent intent = new Intent(this.getActivity().getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void changeProfileHandler(View view) {
        if (isChange) {
            btnChangeProfile.setText("Lưu");
            etPassword.setFocusableInTouchMode(true);
            etAddress.setFocusableInTouchMode(true);
            etPhone.setFocusableInTouchMode(true);
            isChange = false;
        }
        else {
            String password = etPassword.getText().toString();
            String address = etAddress.getText().toString();
            String phone = etPhone.getText().toString();
            if (password.equals("") || address.equals("") || phone.equals("")){
                Toast.makeText(this.getActivity().getApplicationContext(),
                        "Vui lòng không để trống thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }
            user.setPassword(password);
            user.setAddress(address);
            user.setPhone(phone);
            UserService userService = new UserService();
            userService.update(user);

            etPassword.setFocusableInTouchMode(false);
            etAddress.setFocusableInTouchMode(false);
            etPhone.setFocusableInTouchMode(false);
            etPassword.setFocusable(false);
            etAddress.setFocusable(false);
            etPhone.setFocusable(false);



            isChange = true;
            btnChangeProfile.setText("Thay đổi");


            Toast.makeText(this.getActivity().getApplicationContext(),
                    "Cập nhật thành công!", Toast.LENGTH_LONG).show();
        }

    }
}