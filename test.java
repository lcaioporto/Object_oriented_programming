//TESTANDO E APRENDENDO O BÁSICO DE JAVA
class Funcionario {
    private String nome;
    public Funcionario (String umNome) {
        this.nome = umNome;
    }

    public String getNome() {
        return nome;
    }
    public int getSum(int a, int b) {
        return a + b;
    }
    public void runGetSum() {
        int a = 0;
        int b = 0;
        int s = getSum(a, b);
        System.out.println(s);
    }
    public static void main(String[] args){
        Funcionario f = new Funcionario("Nome1");
        System.out.println(f.getNome());
        System.out.println(f.getNome());
    }
}