package com.example.agendamj;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class ModulePeliculas extends AppCompatActivity {

    ImageView img1, img2, img3;
    TextView txt1, txt2, txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_module_peliculas);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);


        Picasso.get().load("https://images-na.ssl-images-amazon.com/images/I/51fD2jhPKKL.jpg")
                .resize(300, 300).centerCrop().into(img1);

        Picasso.get().load("https://pics.filmaffinity.com/toy_story_3-691147043-large.jpg")
                .resize(300, 300).centerCrop().into(img2);

        Picasso.get().load("https://ajrp90.files.wordpress.com/2022/12/soul-poster.png")
                .resize(300, 300).centerCrop().into(img3);


        txt1.setText("Lilo & Stitch: Una niña hawaiana se hace amiga de un extraterrestre.");
        txt2.setText("Toy Story 3: Woody, Buzz y los juguetes enfrentan un nuevo hogar.");
        txt3.setText("Soul: Un músico busca el significado de la vida después de la muerte.");
    }
}
