package br.com.caelum.cadastro.task;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.caelum.cadastro.support.WebClient;

/**
 * Created by android5497 on 09/09/15.
 */
public class EnviaAlunosTask extends AsyncTask<Object, Object, String> {

    private String json;
    private Context ctx;
    private Dialog dialog;

    public void onPreExecute(){
        dialog = ProgressDialog.show(ctx, "Carregando", "Aguarde enquanto a lista de alunos eh enviada.", true, true);
    }

    protected String doInBackground(Object... params) {

        String resposta = new WebClient().post(json);
        return resposta;

    }

    @Override
    protected void onPostExecute(String resposta) {
        Toast.makeText(ctx, resposta, Toast.LENGTH_LONG).show();
        dialog.dismiss();
    }
}
