//TESTANDO E APRENDENDO O BÁSICO DE JAVA
class Funcionario {
    private String nome;
    public Funcionario (String umNome) {
        this.nome = umNome;
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
        int a = 4;
        int b = 3;
        Funcionario f = new Funcionario("Nome1");
        if (a > b) {
            int s = f.getSum(a, b);
            System.out.println(s);
            System.out.println(f.nome);
        }
    }
}