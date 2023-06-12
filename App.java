import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class App {
    public Guerreiro root;
    public String p;

    public App (String filePath) {
        Path path1 = Paths.get(filePath);
        String fileName = path1.getFileName().toString();
        this.p = fileName;

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            String aux[];
            String s = reader.readLine();

            int terraOriginal = Integer.parseInt(s);

            s = reader.readLine();
            aux = s.split(" ");

            root = new Guerreiro (aux[0], terraOriginal);

            while (s != null) {
                aux = s.split(" ");
                root.addGuerreiro(aux[0], aux[1], Integer.parseInt(aux[2]));
                s = reader.readLine();
            }

            root.tranfereTerras();
            root.IdentifiqueMaiorDonoTerrasUltimaGeracao();

        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }  
    }

    public String toString() {
        return root.toString(true);
    }

    public String geraDot() {
        String dot = "digraph g { \n" +
            "graph [rankdir=LR]\n" +
            "node [shape=record fillcolor=lightgrey style=filled]\n" +
            root.geraDotNodos() +
            root.geraDotConexoes() +
            "}"
            ;
        return dot;
    }

    public boolean writeDot() {
        String filePath = "dot/" + this.p + ".Dot";
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(this.geraDot());
            myWriter.close();
            return true;

        } catch (IOException e) {
            System.out.println("Não foi possível escrever o arquivo:\n \"" + filePath + "\"");
            e.printStackTrace();
            return false;
        }
    }

    public int nroGuerreiros() {
        return this.root.nroGuerreiros();
    }

    public String resultado() {
        String res = "";
        res = res + this.nroGuerreiros();
        Guerreiro maiorDonoTerrasUltimaGeracao = this.root.maiorDonoTerras();
        res = res + ", " + maiorDonoTerrasUltimaGeracao.getNome();
        res = res + ", " + maiorDonoTerrasUltimaGeracao.getTerras();

        return res;
    }
}
    
