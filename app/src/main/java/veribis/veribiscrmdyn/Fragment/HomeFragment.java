package veribis.veribiscrmdyn.Fragment;


import Model.Form._baseProperties;
import veribis.veribiscrmdyn.R;

public class HomeFragment extends _baseFragment {
  public HomeFragment() {
    // Required empty public constructor
  }
  @Override
  public HomeFragment setProp(_baseProperties prop) {
    this.LayoutId = R.layout.fragment_home;
    return this;
  }

}
