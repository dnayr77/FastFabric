package edu.umich.fastfabricui1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class ImageActivity extends AppCompatActivity {

    public static final String KEY_User_Document1 = "doc1";
    ProgressBar loading;
    private String Document_img1="";
    private static final int REQUEST_CAPTURE_IMAGE = 100;
    private static final int REQUEST_GALLERY_IMAGE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        setTitle("FastFabric.io");
        // sets the toolbar and back button
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        Button imageButton = findViewById(R.id.photoCamera);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent pictureIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE
                );
                if(pictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(pictureIntent, REQUEST_CAPTURE_IMAGE);
                }
            }
        });

        Button categoryButton = findViewById(R.id.cameraRoll);
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK);
                pickPhoto.setType("image/*");
                startActivityForResult(pickPhoto , REQUEST_GALLERY_IMAGE);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home:
                login(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void login(MenuItem item) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        Log.d("picture", "a result was returned");
        if (resultCode == Activity.RESULT_OK) {
            String session_id = "photo_search";
            switch (requestCode) {
                case REQUEST_CAPTURE_IMAGE:
                    Log.d("image", "Image taken");
                    Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                    Bitmap scaleBitmap = getResizedBitmap(imageBitmap, 605);
                    String fabricFile = encodeImage(scaleBitmap);
                    Intent intent = new Intent(ImageActivity.this, ResultsActivity.class);
                    intent.putExtra("Image_String", fabricFile);
                    intent.putExtra("Session_ID", session_id);
                    startActivity(intent);
                    break;
                case REQUEST_GALLERY_IMAGE:
                    Log.d("gallery", "Image selected, retrieving data");
                    Bitmap galleryBitmap;
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    if (Build.VERSION.SDK_INT >= 29) {
                        // You can replace '0' by 'cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID)'
                        // Note that now, you read the column '_ID' and not the column 'DATA'
                        Log.d("uri", "getting the URI");
                        Uri imageUri= ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cursor.getInt(0));

                        // now that you have the media URI, you can decode it to a bitmap
                        try (ParcelFileDescriptor pfd = this.getContentResolver().openFileDescriptor(imageUri, "r")) {
                            if (pfd != null) {
                                galleryBitmap = BitmapFactory.decodeFileDescriptor(pfd.getFileDescriptor());
                                Bitmap scaleGalleryBitmap = getResizedBitmap(galleryBitmap, 605);
                                String fabricGalleryFile = encodeImage(scaleGalleryBitmap);
                                Log.d("gallery", "Starting results activity 29");
                                Intent galleryIntent = new Intent(ImageActivity.this, ResultsActivity.class);
                                galleryIntent.putExtra("Image_String", fabricGalleryFile);
                                galleryIntent.putExtra("Session_ID", session_id);
                                startActivity(galleryIntent);
                            }
                        } catch (IOException ex) {

                        }
                    } else {
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String imgDecodableString = cursor.getString(columnIndex);
                        galleryBitmap = BitmapFactory.decodeFile(imgDecodableString);
                        Bitmap scaleGalleryBitmap = getResizedBitmap(galleryBitmap, 605);
                        String fabricGalleryFile = encodeImage(scaleGalleryBitmap);
                        Log.d("gallery", "Starting results activity");
                        Intent galleryIntent = new Intent(ImageActivity.this, ResultsActivity.class);
                        galleryIntent.putExtra("Image_String", fabricGalleryFile);
                        galleryIntent.putExtra("Session_ID", session_id);
                        startActivity(galleryIntent);
                    }

                    break;
            }
        }
    }


    private String encodeImage(Bitmap bm)
    {
        Log.d("Processing", "Encoding image");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();

        return Base64.encodeToString(b, Base64.DEFAULT);
    }


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}