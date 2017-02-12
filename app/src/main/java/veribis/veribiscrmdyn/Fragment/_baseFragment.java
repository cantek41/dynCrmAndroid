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

import Data.MyPreference;
import Model.Form.baseProperties;
import veribis.veribiscrmdyn.BaseActivity;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.getFromProp;

/**
 * Created by Cantekin on 16.1.2017.
 */
public abstract class _baseFragment extends Fragment {
    public View view;
    protected int LayoutId;
    public baseProperties formProperties;

    public _baseFragment setProp(baseProperties prop) {
        if (prop != null) this.formProperties = prop;
        return this;
    }

    public baseProperties getProp() {
        return formProperties;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the dialog_list for this fragment
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

    public void fabOnClick() {
        baseProperties newProp = MyPreference.getPreference(getContext()).getData(formProperties.getEditLink(), baseProperties.class);
        if (newProp == null) {
            ((MainActivity) getActivity()).showMessage("form is null");
            return;
        }
        newProp.setParentFieldId(formProperties.getParentFieldId());
        ((MainActivity) getActivity())
                .showFragment(FragmentFactory.getFragment(newProp.getFormType())
                        .setProp(newProp));
    }

    protected boolean isConnection() {
        return ((BaseActivity)getActivity()).isConnection();
    }
}
