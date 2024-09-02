public class Professor {
    private String nome;
    private String id;

    public Professor(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Professor{nome='" + nome + "', id='" + id + "'}";
    }
}
