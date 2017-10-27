package com.esparza.nacricardoesparzarm71238;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esparza.nacricardoesparzarm71238.adapter.ProdutoAdapter;
import com.esparza.nacricardoesparzarm71238.dao.ProdutoDAO;
import com.esparza.nacricardoesparzarm71238.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutosActivity extends AppCompatActivity {

    private TextView tvNomeUsuario;
    private EditText etNomeProduto;
    private RecyclerView rvProdutos;
    private String nomeUsuarioPref = "UserName";
    private ProdutoAdapter produtoAdapter;
    private ProdutoDAO produtoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        tvNomeUsuario = findViewById(R.id.tvNomeUsuario);
        etNomeProduto = findViewById(R.id.etNomeProduto);
        tvNomeUsuario.setText(getPrefs().getString(nomeUsuarioPref, ""));
        produtoDAO =  new ProdutoDAO();
        inicializaLista(new ArrayList<Produto>());
        carregaListaProdutos();
    }

    private void inicializaLista(List<Produto> produtos) {
        rvProdutos = (RecyclerView) findViewById(R.id.rvProdutos);
        produtoAdapter = new ProdutoAdapter(this, produtos);
        rvProdutos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvProdutos.setItemAnimator(new DefaultItemAnimator());
        rvProdutos.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvProdutos.setAdapter(produtoAdapter);
    }

    private void carregaListaProdutos(){
            produtoAdapter.addAll(produtoDAO.getAll());
            produtoAdapter.notifyDataSetChanged();
    }

    public void btInserirProdutoOnClick(View view){
        Produto produto = new Produto();

        String nomeProduto = etNomeProduto.getText().toString();
        if(nomeProduto.trim().equals("")){
            toaster("Insira o nome do produto");
        } else {
            produto.setName(nomeProduto);
            produto.save();

            produtoAdapter.add(produto);

            produtoAdapter.notifyDataSetChanged();

            toaster("Cadastro efetuado");
        }
    }

    private void toaster(String message){
        Toast.makeText(ProdutosActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private SharedPreferences getPrefs(){
        return PreferenceManager.getDefaultSharedPreferences(this);
    }
}
