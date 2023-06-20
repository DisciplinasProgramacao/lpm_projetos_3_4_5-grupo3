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
    private ClienteTipoEnum clienteTipo;
    private String profissao;

    public Cliente(){
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new HashMap<>();
        this.clienteTipo = ClienteTipoEnum.REGULAR;
    }

    public Cliente(String nome, String senha){
        this.nomeDeUsuario = nome;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new HashMap<>();
        this.clienteTipo = ClienteTipoEnum.REGULAR;
    }

    public void adicionarNaListaParaVer(Midia midia){
        this.listaParaVer.add(midia);
    }

    public void adicionarNaListaJaVista(Midia midia){
        this.listaJaVistas.put(midia, new Date());
        clienteEspecialista();
    }

    public void retirarDaListaParaVer(String nomeSerie){
        var midia = listaParaVer.stream()
                .filter(s -> s.getNome().equals(nomeSerie))
                .findFirst();

        midia.ifPresent(value -> listaParaVer.remove(value));
    }

    private void clienteEspecialista(){
        Date sysdateMenosMes = removeMes(new Date());
        int count = 0;

        for(Date d : listaJaVistas.values()){
            if(d.after(sysdateMenosMes)) {
                count += 1;
            }if(count >= 5) {
                clienteTipo = ClienteTipoEnum.ESPECIALISTA;
            }
        }
    }
    
    private Date removeMes(Date date) {
    	Calendar c = Calendar.getInstance(); 
    	c.setTime(date); 
    	c.add(Calendar.MONTH, -1);
    	return c.getTime();
    }
}
