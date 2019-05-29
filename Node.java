import java.time.LocalDateTime;

class Node implements Comparable<Node>{
    protected char tipo;
    protected LocalDateTime data;

    Node(char tipo, LocalDateTime data){
        this.tipo = tipo;
        this.data = data;
    }
    
    public int compareTo(Node n){
        
        if(n.data.isBefore(this.data)){
            return -1;
        }else if(n.data.isAfter(this.data)){
            return 1;
        }else{
            if(n.tipo == 'I') return 1;
            else if(n.tipo=='F') return -1;
            else return 1;
        }          
    }

    char getTipo(){
        return this.tipo;
    }

    LocalDateTime getData(){
        return this.data;
    }

}