package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5497 on 03/09/15.
 */
public class AlunoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 2;
    private static final String TABELA = "Alunos";
    private static final String DATABASE = "CadastroCaelum";

    public AlunoDAO(Context context){
        super(context, DATABASE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String sql = " CREATE TABLE Alunos" +
                " (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, " +
                " telefone TEXT, endereco TEXT, site TEXT, nota REAL, caminhoFoto TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int versaoNova){

        String sql = "ALTER TABLE " + TABELA + " ADD COLUMN caminhoFoto TEXT;";

        database.execSQL(sql);
        onCreate(database);

    }

    public void insere(Aluno aluno){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("telefone", aluno.getTelefone());
        dados.put("endereco", aluno.getEndereco());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());
        dados.put("caminhoFoto", aluno.getCaminhoFoto());

        db.insert(TABELA, null, dados);

    }

    public List<Aluno> getListaAlunos(){
        String sql = "SELECT * FROM Alunos";
        SQLiteDatabase db = getReadableDatabase();
        db.rawQuery(sql, null);

        Cursor c = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<>();

        while(c.moveToNext()){
            Aluno aluno = new Aluno();

            Long id = c.getLong( c.getColumnIndex("id"));
            aluno.setId(id);

            String nome = c.getString( c.getColumnIndex("nome") );
            aluno.setNome(nome);

            String telefone = c.getString(c.getColumnIndex("telefone"));
            aluno.setTelefone(telefone);

            String endereco = c.getString(c.getColumnIndex("endereco"));
            aluno.setEndereco(endereco);

            String site = c.getString(c.getColumnIndex("site"));
            aluno.setSite(site);

            Double nota = c.getDouble(c.getColumnIndex("nota"));
            aluno.setNota(nota);

            aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));

            alunos.add(aluno);

        }

        c.close();
        return alunos;
    }

    public void deletar (Aluno aluno){

        SQLiteDatabase db = getWritableDatabase();
        db.delete("Alunos", "id=?", new String[]{ aluno.getId().toString() });
    }

    public void alterar (Aluno aluno){
        ContentValues values = new ContentValues();

        SQLiteDatabase db = getWritableDatabase();
        values.put("nome", aluno.getNome());
        values.put("endereco", aluno.getEndereco());
        values.put("telefone", aluno.getTelefone());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());
        values.put("caminhoFoto", aluno.getCaminhoFoto());

        db.update(TABELA, values, "id=?", new String[]{aluno.getId().toString()});

    }
}
