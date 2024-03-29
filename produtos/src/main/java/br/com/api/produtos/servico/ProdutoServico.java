package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;


@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio pr;

    @Autowired
    private RespostaModelo rm;

    //Metodo para listar todos produtos
    public Iterable<ProdutoModelo> listar() {
        return pr.findAll();
    }

    //Metodo para cadastrar produtos
    public ResponseEntity<?> cadastrar(ProdutoModelo pm){
        
        if(pm.getNome().equals("")){
            rm.setMensagem("Nome do produto obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else if(pm.getMarca().equals("")){
            rm.setMensagem("Nome da marca obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.CREATED);
        }
    }

}
