package br.com.caelum.cadastro;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import br.com.caelum.cadastro.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastro.modelo.Prova;

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

    public void selecionaProva(Prova prova){
        FragmentManager manager = getSupportFragmentManager();

        if(isTablet()){
            DetalhesProvaFragment detalhesProva = (DetalhesProvaFragment) manager.findFragmentById(R.id.provas_detalhes);
            detalhesProva.populaCamposComDados(prova);
        }else{

            Bundle argumentos = new Bundle();

            argumentos.putSerializable("prova", prova);

            DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
            detalhesProva.setArguments(argumentos);

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.provas_view, detalhesProva);
            transaction.commit();

        }

    }

}
