public class Lista {
    private Node varf;
    private Node coada;
    private int contor;
    public Lista(){
        this.contor = 0;
        this.varf = this.coada = null;
    }
    public void adauga(int x){
        if(this.contor == 0) {
            this.contor++;
            this.varf = new Node(x);
            this.coada = this.varf;
        }
        else{
            Node n = this.varf;
            for(int i = 0 ;i < this.contor - 1; i++)
                n = n.urm;
            n.urm = new Node(x);
            this.contor++;

        }
    }
    public int dimensiune(){
        return this.contor;
    }
    public void adaugaPozitie(int index, int x){
        if(index < 0 || index >= this.contor)
            return;
        Node n = this.varf;
        for(int i = 0 ;i < index -1 ; i++)
            n = n.urm;
        Node atIndex = new Node(x);
        atIndex.urm = n.urm;
        n.urm = atIndex;
    }
    public int elementPozitie(int index){
        if(index < 0 || index >= this.contor  || this.dimensiune() == 0)
            return -1;
        Node n = this.varf;
        for(int i = 0 ;i < index; i++)
            n = n.urm;
        return n.val;
    }
    public int elimina(int index){
        Node deleted;
        if(index < 0 || index >= this.dimensiune())
            return -1;
        Node n = this.varf;
        for(int i = 0 ;i < index -1 ; i++)
            n = n.urm;
        deleted = n.urm;
        n.urm = n.urm.urm;
        return deleted.val;
    }
    public void seteaza(int index, int x){
        if(index < 0 || index >= this.contor  || this.dimensiune() == 0)
            return ;
        Node n = this.varf;
        for(int i = 0 ;i < index ; i++)
            n = n.urm;
        n.val = x;
    }
    public void afisareLista(){
        Node n = this.varf;
        for(;n != null; n = n.urm){
            System.out.print(n.val +" ");
        }
    }
    class Node{
        private int val;
        private Node urm;
        public Node(){

        }
        public Node(int x) {
            this.val = x;
            this.urm= null;
        }
    }
    public static void main(String args[]){
        Lista list = new Lista();
        list.adauga(2);
        list.adauga(3);
        list.adauga(4);
        list.adauga(5);
        list.adauga(6);

        list.elimina(2);
        list.seteaza(3,28);
        list.afisareLista();
    }
}
