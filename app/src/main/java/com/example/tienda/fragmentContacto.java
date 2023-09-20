package com.example.tienda;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentContacto extends Fragment {
    public fragmentContacto() {
        // Constructor vacío requerido por defecto
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacto, container, false);

        // Obtén referencias a los ImageView de las redes sociales
        ImageView imageViewFacebook = view.findViewById(R.id.imageViewFacebook);
        ImageView imageViewInstagram = view.findViewById(R.id.imageViewInstagram);

        // Configura clics en los iconos para abrir las páginas de redes sociales
        imageViewFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebook();
            }
        });
        imageViewInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstagram();
            }
        });

        return view;
    }

    private void openFacebook() {
        String facebookUrl = getString(R.string.facebook_url);
        openUrl(facebookUrl);
    }

    private void openInstagram() {
        String instagramUrl = getString(R.string.instagram_url);
        openUrl(instagramUrl);
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
