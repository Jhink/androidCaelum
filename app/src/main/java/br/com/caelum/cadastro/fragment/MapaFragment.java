package br.com.caelum.cadastro.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.caelum.cadastro.MapsTeste;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.util.Localizador;

/**
 * Created by android5497 on 11/09/15.
 */
public class MapaFragment extends SupportMapFragment {

    public void onResume() {

        super.onResume();

        Localizador localizador = new Localizador(getActivity());
        LatLng local = localizador.getCoordenada("Rua Desembargador Eliseu Guilherme 69 Paraiso");
        this.centralizaNo(local);

        private void centralizaNo(LatLng local){




        }


    /*
    AlunoDAO dao = new AlunoDAO(getActivity());
    List<Aluno> alunos = dao.getListaAlunos();

    dao.close();

    Geocoder gc = new Geocoder(getActivity());

    for(Aluno aluno : alunos ){
        String endereco = aluno.getEndereco();

        List<Address> ads = null;
        try {
            ads = gc.getFromLocationName(endereco, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    LatLng coord = new LatLng(ads.get(0).getLatitude(), ads.get(0).getLongitude());

    MarkerOptions marcador = new MarkerOptions();

    marcador.position(coord).title(aluno.getNome()).snippet(aluno.getNota().toString()).getCoordenada().addMarker("");*/


    }

}
