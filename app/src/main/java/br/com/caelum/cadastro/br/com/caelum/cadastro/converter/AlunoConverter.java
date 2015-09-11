package br.com.caelum.cadastro.br.com.caelum.cadastro.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.ArrayList;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5497 on 09/09/15.
 */
public class AlunoConverter {

    JSONStringer jsonStringer = new JSONStringer();



        ArrayList<Aluno> alunos = jsonStringer.object().key("list").array().object().key("aluno").array();


        for(Aluno aluno : alunos){

                jsonStringer.object().key("id").value(aluno.getId()).key("nome").value(aluno.getNome()).endObject();
        }

        String json = jsonStringer.endArray().endObject().endArray().endObject().toString();


}
