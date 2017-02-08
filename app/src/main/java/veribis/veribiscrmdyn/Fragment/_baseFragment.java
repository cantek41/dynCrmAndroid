package veribis.veribiscrmdyn.Fragment;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import Model.Form._baseProperties;
import veribis.veribiscrmdyn.MainActivity;

/**
 * Created by Cantekin on 16.1.2017.
 */
public abstract class _baseFragment extends Fragment {
  public View view;
  protected int LayoutId;
  public _baseProperties formProperties;

  public _baseFragment setProp(_baseProperties prop) {
    if (prop != null) this.formProperties = prop;
    return this;
  }
  public _baseProperties getProp() {
    return formProperties;
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

  public void save() {
  }

  protected void initFragment() {
    ((MainActivity) getActivity()).changeTitle(formProperties.getFormTitle());
    if (formProperties.isActionButtonIsVisible())
      ((MainActivity) getActivity()).fab.setVisibility(View.VISIBLE);
    else
      ((MainActivity) getActivity()).fab.setVisibility(View.INVISIBLE);
  }

  protected boolean isConnection() {
    ConnectivityManager connMgr = (ConnectivityManager) getContext()
      .getSystemService(getContext().CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    if (networkInfo != null && networkInfo.isConnected())
      return true;
    else
      Toast.makeText(getContext(), "Bağlantınızı Kontrol edin", Toast.LENGTH_SHORT).show();
    // TODO: 25.1.2017 textler dinamik gelmeli
    return false;
  }
}
