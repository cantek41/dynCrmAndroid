package veribis.veribiscrmdyn;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment implements IMyFragment {


  public HomeFragment() {
    // Required empty public constructor
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getActivity().invalidateOptionsMenu();
    initFragment();

  }


  @Override
  public void initFragment() {
    ((BaseMyActivity) getActivity()).changeTitle("Veribis CRM");
    ((BaseMyActivity) getActivity()).fab.setVisibility(View.INVISIBLE);
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    setHasOptionsMenu(true);//fragmentlerde options menuyu kullanabilmek için gerekli
    return inflater.inflate(R.layout.fragment_home, container, false);
  }


}
