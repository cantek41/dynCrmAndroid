package veribis.veribiscrmdyn.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Cantekin on 16.1.2017.
 */
public abstract class MyFragment extends Fragment {
  public View view;
  protected int LayoutId;
  public MyFragment setProp()
  {
    return this;
  }
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    setHasOptionsMenu(true);//fragmentlerde options menuyu kullanabilmek için gerekli
    return inflater.inflate(LayoutId, container, false);
  }
  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.view = view;
    getActivity().invalidateOptionsMenu();
    initFragment();
  }
  @Override
  public void onPrepareOptionsMenu(Menu menu) {
    menu.clear();
  }
  public void save(){}
  protected void initFragment(){
  }
}
