package br.com.caelum.cadastro;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import br.com.caelum.cadastro.fragment.DetalhesProvaFragment;

/**
 * Created by android5497 on 10/09/15.
 */
public class ProvasActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);

        setContentView(R.layout.activity_provas);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();

        if (isTablet()) {
            tx
              .replace(R.id.provas_lista, new ListaProvasFragment())
                    .replace(R.id.provas_detalhes, new DetalhesProvaFragment());
        } else {
            tx.replace(R.id.provas_view, new ListaProvasFragment());
        }

        tx.commit();
    }

    private boolean isTablet(){
        return getResources().getBoolean(R.bool.isTablet);
    }

}
