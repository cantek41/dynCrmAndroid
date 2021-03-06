package veribis.veribiscrmdyn.Menu.Buttons;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.MenuItem;

import veribis.veribiscrmdyn.Fragment._baseFragment;
import veribis.veribiscrmdyn.MainActivity;
import veribis.veribiscrmdyn.Menu.IMenuButtonCommand;
import veribis.veribiscrmdyn.R;


/**
 * Created by Cantekin on 16.1.2017.
 */
public class AttachButtonCommand implements IMenuButtonCommand {
    Context context;
    _baseFragment fragment;

    enum choiseItem {
        Kamera, Galeri, Dosya, Iptal
    }

    final CharSequence[] items = {choiseItem.Kamera.toString(),
            choiseItem.Galeri.toString(), choiseItem.Dosya.toString(), choiseItem.Iptal.toString()};

    public AttachButtonCommand(Context context) {
        this.context = context;
    }

    public AttachButtonCommand(_baseFragment fragment) {
        this.context = fragment.getContext();
        this.fragment = fragment;
    }

    @Override
    public void execute() {
        if (fragment.getProp().getRecordId() == null) {
            ((MainActivity) context).showMessage("Önce çalışmayı kaydetmelisiniz");
            return;
        }
        userChoise();
    }

    private void userChoise() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Dosya Ekle");  //// TODO: 27.1.2017 dinamik string
        AlertDialog.Builder builder1 = builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                choiseItem choise = choiseItem.valueOf(items[item].toString());
                switch (choise) {
                    case Kamera:
                        cameraIntent();
                        break;
                    case Galeri:
                        galleryIntent();
                        break;
                    case Dosya:
                        fileIntent();
                        break;
                    case Iptal:
                        dialog.dismiss();
                        break;
                    default:
                        dialog.dismiss();
                        break;
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            return;
        }
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        ((MainActivity) context).startActivityForResult(Intent.createChooser(intent, "Select File"), MainActivity.SELECT_FILE);
    }

    private void fileIntent() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            return;
        }
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        ((MainActivity) context).startActivityForResult(Intent.createChooser(intent, "Select File"), MainActivity.SELECT_FILE);

    }

    private void cameraIntent() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ((MainActivity) context).startActivityForResult(intent, MainActivity.REQUEST_CAMERA);
    }

    @Override
    public String name() {
        return "Ekle";
    }

    @Override
    public int icon() {
        return R.drawable.dokumanekle;
    }

    @Override
    public int ShowAsAction() {
        return MenuItem.SHOW_AS_ACTION_IF_ROOM;
    }
}
