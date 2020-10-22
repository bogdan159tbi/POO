package NumarRational.java;

import javax.print.attribute.standard.NumberUp;
import java.util.Collection;
import java.util.Scanner;


public class NumarRational {
    private int m,n;
    NumarRational(){
    this.n = 1;
    }
    NumarRational(int m,int n){
        this.m = m;
        this.n = n;
    }
    public void setN(int value){
        this.n = value;
    }
    public void setM(int value){
        this.m = value;
    }
    public int getN(){
        return this.n;
    }
    public int getM(){
        return this.m;
    }
    public static int cmmdc(int x,int y){
        while(x != y){
            if(x > y)
                x -= y;
            else
                y -= x;
        }
        return x;
    }
    public static int cmmc(int x, int y){
        int cx = x,cy = y;
        while(x != y){
            if(x > y)
                x -= y;
            else
                y -= x;
        }
        return (cx * cy) / x;
    }

    @Override
    public String toString(){
        return m +"/" + n;
    }
    public static NumarRational add(NumarRational a,NumarRational b){
        NumarRational s = new NumarRational();
        NumarRational ca = new NumarRational(a.m,a.n);
        NumarRational cb = new NumarRational(b.m,b.n);

        s.m = cmmc(ca.n,cb.n) / ca.n * ca.m + cmmc(ca.n,cb.n) / cb.n * cb.m;
        s.n = cmmc(ca.n,cb.n);
        s.m = s.m / cmmdc(s.m,s.n);
        s.n = s.n / cmmdc(s.m,s.n);
        return s;
    }
    public static NumarRational multiply(NumarRational a,NumarRational b){
        NumarRational p = new NumarRational();
        NumarRational ca = new NumarRational(a.m,a.n);
        NumarRational cb = new NumarRational(b.m,b.n);
        p.m = ca.m * cb.m;
        p.n = ca.n * cb.n;
        p.m = p.m / cmmdc(p.m,p.n);
        p.n = p.n / cmmdc(p.m,p.n);
        return p;
    }
    public boolean maiMare (NumarRational r)
    {
        NumarRational copie = new NumarRational(this.m,this.n);
        NumarRational copie2 = new NumarRational(r.m,r.n);

        int multiplu = cmmc(copie.n,copie2.n);
        copie.m *= copie2.n ;
        copie.n *= copie2.n;

        copie2.m *= copie.n;
        copie2.n *= copie.n;

        if(copie.m > copie2.m)
            return true;
        else
            return  false;
    }
    public void swap( NumarRational b){
        int t1,t2;
        t1 = this.m;
        t2 = this.n;
        this.m = b.m;
        this.n = b.n;

        b.m = t1;
        b.n = t2;
    }
    public static void sortare(NumarRational[] v)
    {
        for(int i = 0 ;i <  1; i++){
            if(v[i].maiMare(v[i+1]) == true)
                v[i].swap(v[i+1]);
        }
    }
    public static void main(String[] args){
        NumarRational n1 = new NumarRational(5,33);
        NumarRational n2 = new NumarRational(3,33);

        NumarRational sum = add(n1,n2);
        System.out.println(sum);

        NumarRational p = multiply(n1,n2);
        System.out.println(p);

        // problema 3
        Scanner s = new Scanner(System.in);
        int k = s.nextInt();
        NumarRational[] elem = new NumarRational[5];
        NumarRational suma = new NumarRational();
        for(int i = 0; i < k ; i++){
            elem[i] = new NumarRational();
            int num,numitor;
            num = s.nextInt();
            numitor = s.nextInt();
            elem[i].setM(num);
            elem[i].setN(numitor);
        }
        for(int i = 0; i < k;i++){
            suma = add(suma,elem[i]);
        }
        NumarRational.sortare(elem);
        
        System.out.println(suma);
        s.close();
    }
}
