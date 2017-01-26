package veribis.veribiscrmdyn;

import android.os.Bundle;

import Model.Form.FormProperties;
import veribis.veribiscrmdyn.Fragment.Form.FormFragment;


public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initActivity();
  }
  public void goDetail() {
    fmTr = getSupportFragmentManager().beginTransaction();
    fmTr.add(R.id.content, new FormFragment().setProp(new FormProperties()));
    fmTr.addToBackStack(null);
    fmTr.commit();
  }
  @Override
  protected void initActivity() {
    super.initActivity();
    fmTr = getSupportFragmentManager().beginTransaction();
    fmTr.add(R.id.content, new FormFragment().setProp(new FormProperties()));
    fmTr.commit();
  }
}
