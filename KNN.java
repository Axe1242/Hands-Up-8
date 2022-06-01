package HandsUp8;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class KNN extends Agent {
    public void setup() {
        System.out.println("El Agente "+getAID().getName()+" Esta listo");
        System.out.println("Nombre del agente: "+getLocalName());
        addBehaviour(new MyCyclicBehaviour());
     }
    
    private class MyCyclicBehaviour extends CyclicBehaviour{
        int i=0;
        int k=0;
        String LL[]={};
        double A = 5.3;
        double B = 3.7;
        String C = "?";
        double X[] = {5.3, 5.1, 7.2, 5.4, 5.1, 5.4, 7.4, 6.1, 7.3, 6.0, 5.8, 6.3, 5.1, 6.3, 5.5};
        double Y[] = {3.7, 3.8, 3.0, 3.4, 3.3, 3.9, 2.8, 2.8, 2.9, 2.7, 2.8, 2.3, 2.5, 2.5, 2.4};
        String L[] = {"Setosa", "Setosa", "Virginica", "Setosa", "Setosa", "Setosa", "Virginica", "Verscicolor", "Virginica", "Verscicolor", "Virginica", "Verscicolor", "Verscicolor", "Verscicolor", "Verscicolor"};
        public double D[] = {};
        public int R[] = {};
        int n = X.length;
        public void action(){
            if(i!=n){
                if(LabelL(LL, L, i, k)){
                    k++;
                    LL[k-1]=L[i];
                }
                D[i] = range(X[i], Y[i], A, B);
                R[i]=i;
                if(i!=0)
                    rank(i, D, R);
                i++;
            }else{
                C = vote(R, k, L);
                onEnd();
            }
        }
        
        public boolean LabelL(String LL[], String L[], int i, int k){
        for(int x=0; x<k; x++){
            if(LL[x] == L[i]){
                return false;
            }
        }
        return true;
    }
    
    public double range(double x, double y, double a, double b){
        double v1 = x-a;
        double v2 = y-b;
        //Math.sqrt( ( ((x-a)*(x-a))+((y-b)*(y-b)) ) );
        return Math.sqrt((Math.pow(v1, 2)+Math.pow(v2, 2)));
    }
    
    public void rank(int i, double D[], int R[]){
        for(int l=0; l<i; l++){
            for(int m=i; m>l; m--){
                if(R[m]<R[m-1]){
                int temp = R[m];
                R[m] = R[m-1];
                R[m-1] = temp;
                }
            }
        }
    }
    
    public String vote(int R[], int k, String L[]){
        int V[]={0,0,0};
        int temp=0;
        for(int j=0; j<k; j++){
            if(L[R[k]] == "Setosa"){
                V[0]++;
            }
            if(L[R[k]] == "Virginica"){
                V[1]++;
            }else{
                V[2]++;
            }
        }
        for(int n=0; n<k; n++){
            if(V[n]>V[n+1]){
                temp=n;
            }
        }
        return L[temp];
    }

        
        public int onEnd() {
            myAgent.doDelete();
            return super.onEnd();
        } 
    }
}