package com.esparza.nacricardoesparzarm71238.dao;

import com.activeandroid.query.Select;
import com.esparza.nacricardoesparzarm71238.model.Produto;

import java.util.List;

/**
 * Created by Esparza on 10/27/2017.
 */

public class ProdutoDAO {

    public List<Produto> getAll(){
        return new Select().from(Produto.class).orderBy("name ASC").execute();
    }
}
