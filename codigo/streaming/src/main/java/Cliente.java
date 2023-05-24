import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void retirarDaLista(String nomeSerie){
        var serie = listaParaVer.stream()
                .filter(s -> s.getNome().equals(nomeSerie))
                .findFirst()
                .orElseThrow();

        listaParaVer.remove(serie);
    }

    public List<Midia> filtrarPorGenero(GeneroEnum genero){
        return listaParaVer.stream()
                .filter(g -> g.getGenero().equals(genero))
                .collect(Collectors.toList());
    }

    public List<Midia> filtrarPorIdioma(String idioma) {
        return listaParaVer.stream()
                .filter(s -> s.getIdioma().equals(idioma))
                .collect(Collectors.toList());
    }

    /* public List<Serie> filtrarPorQtdEpisodios(int quantEpisodios){
        return listaParaVer.stream()
                .filter(q -> q.getQuantidadeEpisodios() == quantEpisodios)
                .collect(Collectors.toList());
    }*/

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
    
    public void removerDaListaParaVer (Midia midia) {
    	listaParaVer.remove(midia);
    }
}
