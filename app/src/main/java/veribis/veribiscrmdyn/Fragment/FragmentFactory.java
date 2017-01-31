package veribis.veribiscrmdyn.Fragment;

import veribis.veribiscrmdyn.Fragment.Form.FormFragment;
import veribis.veribiscrmdyn.Fragment.List.ListFragment;

/**
 * Created by Cantekin on 30.1.2017.
 */
public class FragmentFactory {

  private FragmentFactory(){}

  public static MyFragment getFragment(EnumFragmentType fragmentType){
    MyFragment fragment=null;
    switch (fragmentType) {
      case LIST:
        fragment=new ListFragment();
        break;
      case FORM:
        fragment=new FormFragment();
        break;
    }
    return fragment;
  }
}
