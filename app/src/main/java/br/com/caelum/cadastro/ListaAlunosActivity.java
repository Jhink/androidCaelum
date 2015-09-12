package br.com.caelum.cadastro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;

import br.com.caelum.cadastro.br.com.caelum.cadastro.adapter.ListaAlunosAdapter;
import br.com.caelum.cadastro.br.com.caelum.cadastro.converter.AlunoConverter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.support.WebClient;


public class ListaAlunosActivity extends ActionBarActivity {
    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        AlunoDAO dao = new AlunoDAO(this);

        List<Aluno> alunos =  dao.getListaAlunos();

        dao.close();

        ListaAlunosAdapter adapter = new ListaAlunosAdapter(this, alunos);

        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        listaAlunos.setAdapter(adapter);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent edit = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                Aluno alunoSelecionado = (Aluno) listaAlunos.getItemAtPosition(position);
                edit.putExtra(FormularioActivity.ALUNO_SELECIONADO, alunoSelecionado);
                startActivity(edit);
            }
        });

        Button botaoNovo = (Button) findViewById(R.id.lista_botao);
        botaoNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.carregaLista();
    }

    private void carregaLista() {
        AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
        List<Aluno> alunos = dao.getListaAlunos();
        dao.close();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {

            case R.id.menu_enviar_notas:

                AlunoDAO dao = new AlunoDAO(this);
                List<Aluno> alunos = dao.getListaAlunos();
                dao.close();

                String json = new AlunoConverter().toJSON(alunos);
                WebClient client = new WebClient();
                String resposta = client.post(json);
                Toast.makeText(this, resposta, Toast.LENGTH_LONG).show();

            case R.id.menu_receber_provas:
                Intent provas = new Intent(this, ProvasActivity.class);
                startActivity(provas);
                return true;

            case R.id.menu_mapa:
                Intent mapa = new Intent(this, MostraAlunosActivity.class );
                startActivity(mapa);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        final Aluno alunoSelecionado = (Aluno) listaAlunos.getAdapter().getItem(info.position);

        menu.add("Ligar");
        menu.add("Enviar SMS");
        menu.add("Achar no Mapa");
        menu.add("Navegar no Site");

        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar")
                        .setMessage("Deseja mesmo deletar?")
                        .setPositiveButton("Quero",
                                new DialogInterface.OnClickListener(){
                                  public void onClick(DialogInterface dialog, int which) {
                                      AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                                      dao.deletar(alunoSelecionado);
                                      dao.close();
                                      carregaLista();
                                  }
                                }).setNegativeButton("Nao", null).show();
                return false;
            }

        });


    }
}
