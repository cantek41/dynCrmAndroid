package veribis.veribiscrmdyn;


import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import Data.MyPreference;
/**
 * Created by Cantekin on 10.2.2017.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyPreferenceTest {
    private MainActivity mainActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setActivity() {
        mainActivity = mActivityRule.getActivity();
    }

    @Test(expected = NullPointerException.class)
    public void preferanceNullDataTest() {
        MyPreference.getPreference(mainActivity.getApplicationContext()).setData(null, null);
    }

    @Test
    public void preferanceNewMenuDataTest() {

//        MenuModel menuModel2 = MyPreference.getPreference(context).getMenu();
//        Assert.assertNotNull(menuModel2);
    }

    @Test
    public void demoListmDataTest() {
//        baseProperties formProperties = getDemoView.getMesaiList();
//        Context context = mainActivity.getApplicationContext();
//        MyPreference.getPreference(context).setData(formProperties.getFormName(), jsonHelper.objectToJson(formProperties));
//        baseProperties form = MyPreference.getPreference(context).getData(formProperties.getFormName(), baseProperties.class);
//        Assert.assertNotNull(form);
    }

    @Test
    public void demoFormDataTest() {
//        baseProperties formProperties = getDemoView.getMesaiForm();
//        Context context = mainActivity.getApplicationContext();
//        MyPreference.getPreference(context).setData(formProperties.getFormName(), jsonHelper.objectToJson(formProperties));
//        baseProperties form = MyPreference.getPreference(context).getData(formProperties.getFormName(), baseProperties.class);
//        Assert.assertNotNull(form);
    }

}

