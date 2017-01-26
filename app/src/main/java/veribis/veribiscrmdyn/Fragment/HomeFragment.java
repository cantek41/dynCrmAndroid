package veribis.veribiscrmdyn.Fragment;


import Model.Form.FormProperties;
import veribis.veribiscrmdyn.R;

public class HomeFragment extends MyFragment {
  public HomeFragment() {
    // Required empty public constructor
  }
  @Override
  public HomeFragment setProp(FormProperties prop) {
    this.LayoutId = R.layout.fragment_home;
    return this;
  }

}
