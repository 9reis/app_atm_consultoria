package br.com.lucas9reis.atmconsultoria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import br.com.lucas9reis.atmconsultoria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //FloatingActionButton
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarEmail();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_principal, R.id.nav_servico, R.id.nav_clientes,
                R.id.nav_contato,R.id.nav_sobre
                )
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void enviarEmail( ){
        /*
        String celular = "989928284";
        String img = "https://www.rbsdirect.com.br/imagesrc/24093638.jpg?w=700";
        Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse(celular)); // Intenção
        Intent intentImg = new Intent( Intent.ACTION_VIEW, Uri.parse(img)); // Utilizado para abrir o google Maps
        startActivity(intentImg); // Executa a intenção
         */

        Intent intentShare = new Intent( Intent.ACTION_SEND ); // Compartilhar algo
        intentShare.putExtra( Intent.EXTRA_EMAIL, new String[]{"teste@teste.com"});  //Param direto na intent // Destinatario
        intentShare.putExtra( Intent.EXTRA_SUBJECT, "Assunto do email");
        intentShare.putExtra( Intent.EXTRA_TEXT, "Msg do email");
        intentShare.setType("message/rfc822");// )Tipo de dado compartilhado (email)
        //Mime Types
        //text.plain - Texto padrão
        //image/* - Qualquer tipo de imagem

        startActivity(Intent.createChooser(intentShare, "Compartilhar")); // seta os apps que podem corresponder ao tipo de dado
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}