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

    public Cliente() {
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new HashMap<>();
        this.clienteTipo = ClienteTipoEnum.REGULAR;
    }

    public Cliente(String nome, String senha) {
        this.nomeDeUsuario = nome;
        this.senha = senha;
        this.listaParaVer = new ArrayList<>();
        this.listaJaVistas = new HashMap<>();
        this.clienteTipo = ClienteTipoEnum.REGULAR;
    }

    /**
     * Adiciona uma mídia à lista de itens para assistir.
     *
     * @param midia a mídia a ser adicionada
     */
    public void adicionarNaListaParaVer(Midia midia) {
        this.listaParaVer.add(midia);
    }

    /**
     * Adiciona uma mídia à lista de itens já vistos e chama o método
     * clienteEspecialista().
     *
     * @param midia a mídia a ser adicionada
     */
    public void adicionarNaListaJaVista(Midia midia) {
        this.listaJaVistas.put(midia, new Date());
        clienteEspecialista();
    }

    /**
     * Remove uma mídia da lista de itens para assistir com base no nome da série.
     *
     * @param nomeSerie o nome da série da mídia a ser removida
     */
    public void retirarDaListaParaVer(String nomeSerie) {
        var midia = listaParaVer.stream()
                .filter(s -> s.getNome().equals(nomeSerie))
                .findFirst();

        midia.ifPresent(value -> listaParaVer.remove(value));
    }

    /**
     * Verifica se o cliente é um especialista com base na quantidade de itens
     * vistos recentemente.
     * Atualiza a variável clienteTipo se o cliente for considerado um especialista.
     */
    private void clienteEspecialista() {
        Date sysdateMenosMes = removeMes(new Date());
        int count = 0;

        for (Date d : listaJaVistas.values()) {
            if (d.after(sysdateMenosMes)) {
                count += 1;
            }
            if (count >= 5) {
                clienteTipo = ClienteTipoEnum.ESPECIALISTA;
            }
        }
    }

    /**
     * Remove um mês da data fornecida.
     *
     * @param date a data da qual remover um mês
     * @return a data resultante após a remoção de um mês
     */
    private Date removeMes(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

    /**
     * Conta o número total de avaliações de um cliente.
     *
     * @param cliente O objeto Cliente para o qual serão contadas as avaliações.
     * @return O número total de avaliações do cliente.
     */
    public int contarAvaliacoes() {
        return listaJaVistas.keySet().stream()
                .mapToInt(midia -> midia.getAvaliacoes().size())
                .sum();
    }
}
