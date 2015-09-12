package br.com.caelum.cadastro;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.cadastro.fragment.MapaFragment;

/**
 * Created by android5497 on 11/09/15.
 */
public class MostraAlunosActivity extends ActionBarActivity {

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_mostra_alunos);

        MapaFragment mapaFragment = new MapaFragment();

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();

        tx.replace(R.id.mostra_alunos_mapa, mapaFragment);

        tx.commit();


    }

}
