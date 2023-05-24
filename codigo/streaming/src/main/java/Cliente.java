import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private List<Midia> listaParaVer;
    private Map<Midia, Date> listaJaVistas;
    private boolean clienteEspecialista;

    public Cliente(){
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new HashMap<>();
    }

    public Cliente(String nome, String senha){
        this.nomeDeUsuario = nome;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new HashMap<>();
    }

    public void adicionarNaListaParaVer(Midia midia){
        this.listaParaVer.add(midia);
    }

    public void adicionarNaListaJaVista(Midia midia){
        this.listaJaVistas.put(midia, new Date());
    }

    public void retirarDaListaParaVer(String nomeSerie){
        var midia = listaParaVer.stream()
                .filter(s -> s.getNome().equals(nomeSerie))
                .findFirst();

        midia.ifPresent(value -> listaParaVer.remove(value));
    }

    public void clienteEspecialista(){
        Date sysdateMenos2Meses = remove2Meses(new Date());
        
        listaJaVistas.forEach((k,v) -> {
        	int count = 0;
        	if(v.after(sysdateMenos2Meses)) {
        		count += 1;
        	}if(count >= 5) {
        		clienteEspecialista = true;
        	}
        });
    }
    
    private Date remove2Meses(Date date) {
    	Calendar c = Calendar.getInstance(); 
    	c.setTime(date); 
    	c.add(Calendar.MONTH, -2);
    	return c.getTime();
    }
}
