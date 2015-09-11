package br.com.caelum.cadastro.br.com.caelum.cadastro.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5497 on 09/09/15.
 */
public class AlunoConverter {

    JSONStringer jsonStringer = new JSONStringer();


        public String toJSON(List<Aluno> alunos){
            try{
                JSONStringer jsonStringer = new JSONStringer();
                jsonStringer.object().key("list").array().object().key("aluno").array();

                for(Aluno aluno : alunos){
                   JSON json = jsonStringer.object().key("id").value(aluno.getId()).key("nome").value(aluno.getNome()).endObject();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return
        }
}
