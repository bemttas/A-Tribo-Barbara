import java.util.LinkedList;

public class Guerreiro{

    private String nome;
    private int terrasTotal;
    private int terraConquistada;
    private int terraHerdada;
    private int geracao;
    private boolean maiorDonoTerrasUltimaGeracao;
    private Guerreiro pai;
    private LinkedList<Guerreiro> Filhos;

    public Guerreiro(String nome, int terraConquistada){ 
        this.nome = nome;
        this.terrasTotal = 0;
        this.terraHerdada = 0;
        this.terraConquistada = terraConquistada;
        this.geracao = 0;
        this.maiorDonoTerrasUltimaGeracao = false;
        this.pai = null;
        this.Filhos = new LinkedList<>();
    }

    public Guerreiro(Guerreiro pai, String nome, int terraConquistada){ 
        this.pai = pai;
        this.nome = nome;
        this.terrasTotal = 0;
        this.terraHerdada = 0;
        this.terraConquistada = terraConquistada;
        this.geracao = pai.geracao + 1;
        this.maiorDonoTerrasUltimaGeracao = false;
        this.Filhos = new LinkedList<>();
    }
    
    public boolean addGuerreiro(String nomePai, String nome, int terraConquistada){
        Guerreiro pai = this.findGuerreiro(nomePai);
        if (pai == null){
            return false;
        }
        
        Guerreiro novoGuerreiro = new Guerreiro(pai, nome, terraConquistada);
        pai.addFilho(novoGuerreiro);
        return true;
    }

    public String getNome(){
        return this.nome;
    }

    public int getTerras(){
        return this.terrasTotal;
    }

    public void addFilho (Guerreiro filho) {
        this.Filhos.add(filho);
    }

    public boolean temFilhos(){
        return !this.Filhos.isEmpty();
    }

    public Guerreiro findGuerreiro (String nomeProcurado) {
        if (this.nome.equals(nomeProcurado))
            return this;
        Guerreiro res = null;
        if (this.temFilhos()){
            for (Guerreiro guerreiro : this.Filhos) {
                res = guerreiro.findGuerreiro(nomeProcurado);
                if (res != null)
                return res;
            }
        }
        return res;
    }

    public void tranfereTerras() {
        int terrasTotal = this.terraConquistada + this.terraHerdada;
        this.terrasTotal = terrasTotal;

        if (this.temFilhos()) {
            int heranca = this.terrasTotal / this.Filhos.size();
            for (Guerreiro guerreiro : this.Filhos) {
                guerreiro.transfereTerras(heranca);
            } 
        }
    }

    public void transfereTerras(int heranca) {
        this.terraHerdada = heranca;
        this.tranfereTerras();
    }

    public LinkedList<Guerreiro> aLinkedList() {
        LinkedList<Guerreiro> list = new LinkedList<>();
        this.appendOnLinkedList(list);
        return list;
    }

    private LinkedList<Guerreiro> appendOnLinkedList(LinkedList<Guerreiro> list) {
        list.add(this);
        if (this.temFilhos()) {
            for (Guerreiro guerreiro : this.Filhos) {
                guerreiro.appendOnLinkedList(list);
            }
        }
        return list;
    }

    public String toString() {
        String res = this.nome;
        if (pai != null) {
            res = res + " Filho de " + pai.nome;
        }
        res = res + ", conquistou" + this.terraConquistada;
        return res;
    }

    public String toString(boolean incluirFilhos) {
        String res = this.toString();
        if (incluirFilhos && this.temFilhos()) {
            for (Guerreiro guerreiro : this.Filhos) {
                res = res + "\n" + guerreiro.toString(incluirFilhos);
            }
        }
        return res;
    }


    public String dotNodo() {
        String atributos = "[label=\"" + this.nome + " - " + this.terrasTotal + "\"";
        if (this.maiorDonoTerrasUltimaGeracao) {
            atributos = atributos + "color=blue style=bold";
        }
        atributos = atributos + "]";
        return "   " + this.nome + "  " + atributos + "\n";
}
    
    public String geraDotNodos() {
        String res = this.dotNodo();
        if (this.temFilhos()) {
            for (Guerreiro guerreiro : this.Filhos) {
                res = res + guerreiro.geraDotNodos();
            }
        }
        return res;
    }

    public String geraDotConexoes() {
        String res = "";
        if (this.temFilhos()) {
            for (Guerreiro guerreiro : this.Filhos) {
                res = res + "  " + this.nome + " -> " + guerreiro.nome + ";\n";
            }
            for (Guerreiro guerreiro : this.Filhos) {
                res = res + guerreiro.geraDotConexoes();
            }
        }
        return res;
    }

    public void IdentifiqueMaiorDonoTerrasUltimaGeracao() {
        Guerreiro guerreiro = this.qualMaiorDonoTerrasUltimaGeracao(null);
        guerreiro.maiorDonoTerrasUltimaGeracao = true;
    }

    private Guerreiro qualMaiorDonoTerrasUltimaGeracao(Guerreiro maiorDono) {
        if (this.temFilhos()) {
            for (Guerreiro guerreiro : this.Filhos) {
                maiorDono = guerreiro.qualMaiorDonoTerrasUltimaGeracao(maiorDono);
            }
            return maiorDono;
        }
        if (maiorDono == null) {
            return this;
        }
        if (maiorDono.geracao > this.geracao) {
            return maiorDono;
        }
        if (maiorDono.geracao < this.geracao) {
            return this;
        }
        if (maiorDono.terrasTotal > this.terrasTotal) {
            return maiorDono;
        }
        else {
            return this;
        }
    }

    public int nroGuerreiros() {
        return this.nroGuerreiros(0);
    }

    private int nroGuerreiros (int acc) {
        acc = acc + 1;
        if (this.temFilhos()) {
            for (Guerreiro guerreiro : this.Filhos) {
                acc = guerreiro.nroGuerreiros(acc);
            }
        }
        return acc;
    }

    public Guerreiro maiorDonoTerras() {
        if (this.maiorDonoTerrasUltimaGeracao) {
            return this;
        }
        if (this.temFilhos()) {
            Guerreiro aux = null;
            for (Guerreiro guerreiro : this.Filhos) {
                aux = guerreiro.maiorDonoTerras();
                if (aux != null) {
                    return aux;
                }
            }
        }

        return null;
    }

}